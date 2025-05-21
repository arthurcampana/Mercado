package mercado.cliente;
import conexao.Conexao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteService {


    private Connection conn;

    public void ManipulacaoBD() {
        conn = Conexao.getConnection();
    }

    public void cadastrarCliente(Cliente cliente, String documento) {


        if (isCpf(documento)) {
            cadastrarClientePF(cliente, documento);
        } else if (isCnpj(documento)) {
            cadastrarClientePJ(cliente, documento);
        } else {
            System.out.println("Documento inválido: deve ter 11 (CPF) ou 14 (CNPJ) dígitos.");
        }
    }

   // @Override
    public void cadastrarClientePF(Cliente cliente, String documento) {
        String sqlCliente = "INSERT INTO cliente (nome, telefone) VALUES (?, ?)";
        String sqlPF = "INSERT INTO cliente_pf (id_cliente, cpf) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sqlCliente)) {
            stmt.setString(1, cliente.getNome());
            stmt.setDouble(2, cliente.getTelefone());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
        } catch (SQLException e) {
            System.err.println("Nao foi possivel realizar a insercao: " + e.getMessage());
        }

        try (PreparedStatement stmt = conn.prepareStatement(sqlPF)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, documento);
            stmt.setString(3, cliente.getCategoria().name());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
        } catch (SQLException e) {
            System.err.println("Nao foi possivel realizar a insercao: " + e.getMessage());
        }
    }

   // @Override
   public void cadastrarClientePJ(Cliente cliente, String documento) {
       String sqlCliente = "INSERT INTO cliente (nome, telefone, categoria) VALUES (?, ?, ?)";
       String sqlPJ = "INSERT INTO cliente_pj (id_cliente, cnpj) VALUES (?, ?)";

       try (
               PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS)
       ) {
           // Inserir na tabela cliente
           stmtCliente.setString(1, cliente.getNome());
           stmtCliente.setString(2, String.valueOf(cliente.getTelefone()));
           stmtCliente.setString(3, cliente.getCategoria().name());
           stmtCliente.executeUpdate();

           // Recuperar o ID gerado automaticamente
           ResultSet rs = stmtCliente.getGeneratedKeys();
           if (rs.next()) {
               int idCliente = rs.getInt(1);

               // Inserir na tabela cliente_pj com o ID gerado
               try (PreparedStatement stmtPJ = conn.prepareStatement(sqlPJ)) {
                   stmtPJ.setInt(1, idCliente);
                   stmtPJ.setString(2, documento);
                   stmtPJ.executeUpdate();
                   System.out.println("Cliente PJ cadastrado com sucesso!");
               }
           }
       } catch (SQLException e) {
           System.err.println("Erro ao cadastrar cliente PJ: " + e.getMessage());
       }
   }

    

//    @Override
//    public Cliente consultarCliente(int id) {
//        if (clientes.containsKey(id)) {
//            return clientes.get(id);
//        }
//        return null;
//    }
//
//    @Override
//    public Cliente[] listarCliente() {
//        Cliente[] clientes = new Cliente[this.clientes.size()];
//        return this.clientes.values().toArray(clientes);
//    }

//    @Override
//    public void editarCliente(int id, String nome, int telefone, Categoria categoria) {
//        if (clientes.containsKey(id)) {
//            Cliente clientea = clientes.get(id);
//            clientea.setNome(nome);
//            clientea.setTelefone(telefone);
//            clientea.setCategoria(categoria);
//            System.out.println("Nome do cliente atualizado para: " + nome
//                                + "Telefone do cliente atualizado para" + telefone
//                                + "Categoria do cliente atualizada para" + categoria);
//        }else{
//            System.out.println("Cliente nao encontrado");
//        }
//
//
//
//    }
//@Override
//public void editarCliente(Cliente cliente) {
//    int id = cliente.getId();
//
//    if (clientes.containsKey(id)) {
//        Cliente clienteEditado = clientes.get(id);
//        clienteEditado.setNome(cliente.getNome());
//        clienteEditado.setTelefone(cliente.getTelefone());
//        clienteEditado.setCategoria(cliente.getCategoria());
//
//        System.out.println("Cliente atualizado:");
//        System.out.println("Nome: " + cliente.getNome());
//        System.out.println("Telefone: " + cliente.getTelefone());
//        System.out.println("Categoria: " + cliente.getCategoria());
//    } else {
//        System.out.println("Cliente não encontrado.");
//    }
//}
//@Override
//public void editarCliente(Cliente cliente) {
//
//        System.out.println("atualizo po");
//}


//    @Override
//    public Cliente[] listarClientePJ() {
//        List<Cliente> listaPJ = new ArrayList<>();
//
//        for (Cliente cliente : clientes.values()) {
//            if (cliente instanceof ClienteJuridico) {
//                listaPJ.add(cliente);
//            }
//        }
//
//
//        return listaPJ.toArray(new Cliente[0]);
//    }
//    @Override
//    public Cliente[] listarClientePF() {
//        List<Cliente> listaPF = new ArrayList<>();
//
//        for (Cliente cliente : clientes.values()) {
//            if (cliente instanceof ClienteFisico) {
//                listaPF.add(cliente);
//            }
//        }
//
//        return listaPF.toArray(new Cliente[0]);
//    }

    private static boolean isCpf(String documento) {
        return documento != null && documento.length() == 11;
    }

    private static boolean isCnpj(String documento){
        return documento != null && documento.length() == 14;
    }
}
