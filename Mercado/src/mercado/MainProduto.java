package mercado;

import mercado.produto.Produto;
import mercado.produto.ProdutoService;

import java.util.Arrays;

public class MainProduto {
    public static void main(String[] args) {
        //3 produtos
        Produto arroz = new Produto(1, null, -10, -1);
        Produto feijao = new Produto(2, "Feijão", 7.49, 80);
        Produto macarrao = new Produto(3, "Macarrão", 4.29, 60);

        ProdutoService produtoService = new ProdutoService();

        //Cadastro dos produtos
        produtoService.cadastrarProduto(feijao);
        produtoService.cadastrarProduto(arroz);
        produtoService.cadastrarProduto(macarrao);

        //Lista todos os produtos
        System.out.println(Arrays.toString(produtoService.listarProdutos()));


        //Consulta de produto
        System.out.println(produtoService.consultarProduto(2));
    }
}
