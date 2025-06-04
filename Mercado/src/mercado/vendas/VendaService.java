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

    public void criarVenda(String documento, Map<Produto, Integer> produtos) throws SQLException {

        ClienteService clienteService = new ClienteService();
        DescontoFidelidade df = new DescontoFidelidade();
        Cliente cli = clienteService.consultarCliente(documento);
        double desconto = df.getDesconto(cli);
        LocalDateTime datahora = LocalDateTime.now();

        String inserirVenda = "INSERT INTO venda (fk_id_cliente,data_hora,desconto,valor_total) VALUES (?, ?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(inserirVenda, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1,cli.getId());
            stmt.setTimestamp(2, Timestamp.valueOf(datahora));
            stmt.setDouble(3, desconto);
            stmt.setDouble(4,0.0);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int idVenda = 0;
            if (rs.next()) {
                idVenda = rs.getInt(1);

            }

            double valortotal = criarItemVenda(idVenda,produtos);
            valortotal = aplicarDesconto(valortotal, cli);
            String sqlvalortotal = "UPDATE venda SET valor_total = ? WHERE id_venda = ?";
            PreparedStatement stmtvalortotal = conn.prepareStatement(sqlvalortotal);
            stmtvalortotal.setDouble(1,valortotal);
            stmtvalortotal.setInt(2,idVenda);
            stmtvalortotal.execute();
        } catch (SQLException e) {
            System.err.println("Nao foi possivel realizar a insercao: " + e.getMessage());
        }





    }


    public double criarItemVenda(int idVenda, Map<Produto, Integer> produtos) throws SQLException {

        double valorTotal = 0.0;

        String sqlItem = "INSERT INTO item_venda (fk_id_venda, nome, quantidade, valor) VALUES (?, ?, ?, ?)";
        PreparedStatement stmtItem = conn.prepareStatement(sqlItem);

        String sqlEstoque = "UPDATE produto SET estoque = estoque - ? WHERE id_produto = ?";
        PreparedStatement stmtEstoque = conn.prepareStatement(sqlEstoque);

        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {

            Produto produto = entry.getKey();
            int quantidade = entry.getValue();

            stmtItem.setInt(1, idVenda);
            stmtItem.setString(2, produto.getNome());
            stmtItem.setInt(3, quantidade);
            stmtItem.setDouble(4, produto.getValor());
            stmtItem.addBatch();

            stmtEstoque.setInt(1, quantidade);
            stmtEstoque.setInt(2, produto.getId());
            stmtEstoque.addBatch();

            valorTotal += produto.getValor() * quantidade;

        }
            stmtItem.executeBatch();
            stmtEstoque.executeBatch();

        return valorTotal;

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




    // for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
    //            Produto produto = entry.getKey();
    //            int quantidade = entry.getValue();
    //
    //
    //        //VERIFICADOR SE O ITEM TEM ESTOQUE PARA SER COMPRADO
    //        if (produto.getEstoque() < quantidade) {
    //                System.out.println("Estoque insuficiente para o produto: " + produto.getNome());
    //                return;
    //           }
    //
    //
    //
    //
    //
    //        }
    }


