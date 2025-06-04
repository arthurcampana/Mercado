import mercado.produto.Produto;
import mercado.produto.ProdutoService;
import mercado.vendas.VendaService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MainVenda {
    public static void main(String[] args) throws SQLException {
        ProdutoService prd = new ProdutoService();
        prd.ManipulacaoBD();

        VendaService vendaService = new VendaService();
        vendaService.ManipulacaoBD();

        Map<Produto, Integer> produtosComprados = new HashMap<>();
        produtosComprados.put(prd.consultarProduto(1), 1);


        System.out.println(produtosComprados);
        vendaService.criarVenda("1234567e8910", produtosComprados);


    }
}
