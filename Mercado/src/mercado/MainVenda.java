package mercado;

import mercado.cliente.ClienteFisico;
import mercado.produto.Produto;
import mercado.produto.ProdutoService;
import mercado.vendas.VendaService;

import java.util.HashMap;
import java.util.Map;

import static mercado.cliente.Categoria.DESCONTO1;

public class MainVenda {
    public static void main(String[] args) {

        Produto arroz = new Produto(1, "Arroz", 5.99, 100);
        Produto feijao = new Produto(2, "Feijão", 7.49, 80);
        Produto macarrao = new Produto(3, "Macarrão", 4.29, 60);

        ProdutoService produtoService = new ProdutoService();

        produtoService.cadastrarProduto(feijao);
        produtoService.cadastrarProduto(arroz);
        produtoService.cadastrarProduto(macarrao);



        Map<Produto, Integer> produtosComprados = new HashMap<>();
        produtosComprados.put(produtoService.consultarProduto(1), 2);
        produtosComprados.put(produtoService.consultarProduto(2), 1);
        produtosComprados.put(produtoService.consultarProduto(3), 3);



        ClienteFisico clientePf = new ClienteFisico(DESCONTO1, 141, "ASD", 2);
        clientePf.setCpf("12345678910");

        VendaService vendaService = new VendaService();

        vendaService.criarVenda(clientePf, produtosComprados, 8);

        System.out.println(produtoService.consultarProduto(1));
        System.out.println(produtoService.consultarProduto(2));
        System.out.println(produtoService.consultarProduto(3));


    }

}
