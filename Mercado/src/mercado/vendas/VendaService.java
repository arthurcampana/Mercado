package mercado.vendas;

import conexao.Conexao;
import mercado.cliente.Cliente;
import mercado.produto.Produto;

import java.sql.*;
import java.util.Map;

public class VendaService {


    private Connection conn;

    public VendaService() throws SQLException {
    }

    public void ManipulacaoBD() {
        conn = Conexao.getConnection();
    }

    //private double aplicarDesconto(double valorTotal, Cliente cliente) {
      //  if (cliente != null) {
      //      DescontoFidelidade df = new DescontoFidelidade();
      //      return valorTotal * (1 - df.getDesconto(cliente));
       // }
      //  return valorTotal;
      // }

    String pegarIDcliente = "SELECT id from cliente WHERE id_cliente = ?";


    //VERIFICAÇÃO SE TEM O ESTOQUE DO ITEM
    public void criarVenda(String documento, Map<Produto, Integer> produtos) {
        // (BUSCADOR)



        
        String estoquesql = "SELECT estoque from produto WHERE id_produto = ?";
        try (PreparedStatement ps = conn.prepareStatement(estoquesql)) {
            int idproduto = 1;
            ps.setInt(1, idproduto);
            ResultSet rs = ps.executeQuery();



        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente PF: " + e.getMessage());
        }
    }




    public void criarVenda(Cliente cliente, Map<Produto, Integer> produtos, int id) {
        
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

}
