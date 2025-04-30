package mercado;

import mercado.produto.Produto;
import mercado.produto.ProdutoService;

import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) {
        Produto arroz = new Produto(1, "Arroz", 5.99, 100);
        Produto feijao = new Produto(2, "Feijão", 7.49, 80);
        Produto macarrao = new Produto(3, "Macarrão", 4.29, 60);

        ProdutoService produtoService = new ProdutoService();

        produtoService.cadastrarProduto(feijao);
        produtoService.cadastrarProduto(arroz);
        produtoService.cadastrarProduto(macarrao);

        System.out.println(Arrays.toString(produtoService.listarProdutos()));;
    }

}
