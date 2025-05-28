package mercado.cliente;
import conexao.Conexao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteService {


    private Connection conn;
    public ClienteService() {
        this.conn = Conexao.getConnection();
    }
    public void ManipulacaoBD() {
        conn = Conexao.getConnection();
    }

    public void cadastrarCliente(Cliente cliente) {
        if (isCpf(cliente.getDocumento())) {
            cadastrarClientePF(cliente);
        } else if (isCnpj(cliente.getDocumento())) {
            cadastrarClientePJ(cliente);
        } else {
            System.out.println("Documento inválido: deve ter 11 (CPF) ou 14 (CNPJ) dígitos.");
        }
    }

    public Cliente consultarCliente(String documento) {
        if (isCpf(documento)) {
            return consultarClientePF(documento);
        } else if (isCnpj(documento)) {
            return consultarClientePJ(documento);
        } else {
            System.out.println("Documento inválido: deve ter 11 (CPF) ou 14 (CNPJ) dígitos.");
        }
        return null;

    }



    public Cliente consultarClientePF(String documento) {
        String sqlCliente = "SELECT c.id_cliente, c.nome, c.telefone, c.categoria " +
                "FROM cliente c " +
                "JOIN cliente_pf pf ON c.id_cliente = pf.id_cliente " +
                "WHERE pf.cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlCliente)) {
            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
                Cliente cliente = new Cliente(categoria, rs.getInt("telefone"), rs.getString("nome"),rs.getInt("id_cliente"));
                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cliente consultarClientePJ(String documento) {
        String sqlCliente = "SELECT c.id_cliente, c.nome, c.telefone, c.categoria " +
                "FROM cliente c " +
                "JOIN cliente_pj pj ON c.id_cliente = pj.id_cliente " +
                "WHERE pj.cnpj = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlCliente)) {
            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
                Cliente cliente = new Cliente(categoria, rs.getInt("telefone"), rs.getString("nome"),rs.getInt("id_cliente"));
                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   // @Override
   public void cadastrarClientePF(Cliente cliente) {
       String sqlCliente = "INSERT INTO cliente (nome, telefone, categoria) VALUES (?, ?, ?)";
       String sqlPF = "INSERT INTO cliente_pf (id_cliente, cpf) VALUES (?, ?)";

       try (PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS))
       {
           stmtCliente.setString(1, cliente.getNome());
           stmtCliente.setInt(2, cliente.getTelefone());
           stmtCliente.setString(3, cliente.getCategoria().name());
           stmtCliente.executeUpdate();

           ResultSet rs = stmtCliente.getGeneratedKeys();
           if (rs.next()) {
               int idCliente = rs.getInt(1);

               try (PreparedStatement stmtPF = conn.prepareStatement(sqlPF)) {
                   stmtPF.setInt(1, idCliente);
                   stmtPF.setString(2, cliente.getDocumento());
                   stmtPF.executeUpdate();
                   System.out.println("Cliente PF cadastrado com sucesso!");
               }
           }
       } catch (SQLException e) {
           System.err.println("Erro ao cadastrar cliente PF: " + e.getMessage());
       }
   }

   // @Override
   public void cadastrarClientePJ(Cliente cliente) {
       String sqlCliente = "INSERT INTO cliente (nome, telefone, categoria) VALUES (?, ?, ?)";
       String sqlPJ = "INSERT INTO cliente_pj (id_cliente, cnpj) VALUES (?, ?)";

       try (PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS))
       {
           stmtCliente.setString(1, cliente.getNome());
           stmtCliente.setInt(2, cliente.getTelefone());
           stmtCliente.setString(3, cliente.getCategoria().name());
           stmtCliente.executeUpdate();

           ResultSet rs = stmtCliente.getGeneratedKeys();
           if (rs.next()) {
               int idCliente = rs.getInt(1);

               try (PreparedStatement stmtPJ = conn.prepareStatement(sqlPJ)) {
                   stmtPJ.setInt(1, idCliente);
                   stmtPJ.setString(2, cliente.getDocumento());
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
