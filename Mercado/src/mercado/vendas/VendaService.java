package mercado.vendas;

import conexao.Conexao;
import mercado.cliente.Cliente;
import mercado.cliente.ClienteService;
import mercado.produto.Produto;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Map;

public class VendaService {


    private Connection conn;

    public VendaService() throws SQLException {
    }

    public void ManipulacaoBD() {
        conn = Conexao.getConnection();
    }

    private double aplicarDesconto(double valorTotal, Cliente cliente) {
        if (cliente != null) {
            DescontoFidelidade df = new DescontoFidelidade();
            return valorTotal * (1 - df.getDesconto(cliente));
        }
        return valorTotal;
       }

    public void criarVenda(String documento, Map<Produto, Integer> produtos) {

        ClienteService clienteService = new ClienteService();
        DescontoFidelidade df = new DescontoFidelidade();
        Cliente cli = clienteService.consultarCliente(documento);
        double desconto = df.getDesconto(cli);
        LocalDateTime datahora = LocalDateTime.now();

        String inserirVenda = "INSERT INTO venda (fk_id_cliente,data_hora,desconto,valor_total) VALUES (?, ?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(inserirVenda, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cli.getId());
            stmt.setTimestamp(2, Timestamp.valueOf(datahora));
            stmt.setDouble(3, desconto);
            stmt.setDouble(4,0.0);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idVenda = rs.getInt(1);

            }
        } catch (SQLException e) {
            System.err.println("Nao foi possivel realizar a insercao: " + e.getMessage());
        }



        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();


        //VERIFICADOR SE O ITEM TEM ESTOQUE PARA SER COMPRADO
        if (produto.getEstoque() < quantidade) {
                System.out.println("Estoque insuficiente para o produto: " + produto.getNome());
                return;
           }





        }
    }




    //public void criarVenda(Cliente cliente, Map<Produto, Integer> produtos, int id) {
        
//        if (produtos == null || produtos.isEmpty()) {
//            System.out.println("Não há produtos na venda.");
//            return;
//        }
//
//        ItemVenda[] itensVenda = new ItemVenda[produtos.size()];
//        double valorTotal = 0.0;
//        int i = 0;
//            // for(Produto produto : produtos)
//        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
//            Produto produto = entry.getKey();
//            int quantidade = entry.getValue();
//
//            if (quantidade <= 0) {
//                System.out.println("Quantidade invalida para o produto " + produto.getNome());
//                return;
//            }
//            if (produto.getEstoque() < quantidade) {
//                System.out.println("Estoque insuficiente para o produto: " + produto.getNome());
//                return;
//            }
//
//            produto.saidaEstoque(quantidade);
//
//            ItemVenda item = new ItemVenda(produto.getValor(), quantidade, produto.getNome());
//
//            valorTotal += produto.getValor() * quantidade;
//            itensVenda[i++] = item;
//        }
//        valorTotal = aplicarDesconto(valorTotal, cliente);
//
//        DescontoFidelidade df = new DescontoFidelidade();
//
//        LocalDateTime datahora = LocalDateTime.now();
//
//        double desconto = df.getDesconto(cliente);
//
//        Venda venda = new Venda(itensVenda, cliente, datahora, desconto, valorTotal);
//        System.out.println(venda + " - Valor total: R$" + String.format("%.2f", valorTotal));

    }


