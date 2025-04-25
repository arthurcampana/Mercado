package mercado.produto;



import mercado.cliente.Cliente;

import java.util.HashMap;
import java.util.Map;

public class ProdutoService implements IProdutoService {

    private Map<Integer, Produto> produtos;
    public ProdutoService() {
        this.produtos = new HashMap<>();
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        if(produto != null){
            produtos.put(produto.getId(), produto);
        }else{
            System.out.println("Produto invalido");
        }

    }

    @Override
    public Produto consultarProduto(int id) {
        if(produtos.containsKey(id)){
            return produtos.get(id);
        }else{
            return null;
        }
    }

    @Override
    public void editarProduto(int id) {

    }


    @Override
    public Produto[] listarProdutos() {
        Produto[] lista = new Produto[produtos.size()];
        for (int i = 0; i < produtos.size(); i++) {
            lista[i] = produtos.get(i);
        }
        return lista;
    }
}
