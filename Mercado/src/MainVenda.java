import mercado.cliente.ClienteService;
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
        ClienteService clienteService = new ClienteService();
        clienteService.ManipulacaoBD();

        VendaService vendaService = new VendaService();
        vendaService.ManipulacaoBD();

        Map<Produto, Integer> produtosComprados = new HashMap<>();
        produtosComprados.put(prd.consultarProduto(1), 1);  //ID 1 == FEIJAO, ID 3 == ARROZ
        produtosComprados.put(prd.consultarProduto(3), 2);

        Map<Produto, Integer> produtosComprados2 = new HashMap<>();
        produtosComprados2.put(prd.consultarProduto(1), 2);
        produtosComprados2.put(prd.consultarProduto(3), 3);


//        System.out.println(produtosComprados);
//        vendaService.criarVenda("12345678910", produtosComprados);
//        vendaService.criarVenda("12345678910", produtosComprados2);
//        vendaService.criarVenda("99999999999", produtosComprados2);
//        vendaService.criarVenda("12345678300019", produtosComprados2);
//        vendaService.criarVenda(null, produtosComprados2);
        System.out.println(clienteService.consultarClientePF("12345678911"));





    }
}
