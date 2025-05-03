package mercado.produto;


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
    public void editarProduto(int id, String nome,double valor) {
        if (produtos.containsKey(id)) {
            Produto produto = produtos.get(id);
            produto.setNome(nome);
            produto.setValor(valor);
        }
    }


    @Override
    public Produto[] listarProdutos() {
        Produto[] produtos = new Produto[this.produtos.size()];
        return this.produtos.values().toArray(produtos);
    }
}
