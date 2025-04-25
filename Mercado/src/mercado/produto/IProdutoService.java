package mercado.produto;

public interface IProdutoService {
    void cadastrarProduto(Produto produto);
    Produto consultarProduto(int id);
    void editarProduto(int id,String nome,double valor);
    Produto[] listarProdutos();
}
