import mercado.produto.Produto;
import mercado.produto.ProdutoService;

import java.util.HashMap;
import java.util.Map;

public class MainVenda {
    public static void main(String[] args) {
        ProdutoService prd = new ProdutoService();
        prd.ManipulacaoBD();

        Map<Produto, Integer> produtosComprados = new HashMap<>();
        produtosComprados.put(prd.consultarProduto(1), 1);
        produtosComprados.put(prd.consultarProduto(4), 1);
        produtosComprados.put(prd.consultarProduto(3), 3);

        System.out.println(produtosComprados);

    }
}
