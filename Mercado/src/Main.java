
import conexao.ConexaoComPropriedades;
import conexao.Tabelas;
import mercado.cliente.ClienteJuridico;
import mercado.cliente.ClienteService;
import mercado.produto.Produto;
import mercado.produto.ProdutoService;

import java.sql.Connection;

import static mercado.cliente.Categoria.DESCONTO1;

public class Main {

    public static void main(String[] args) {
        Connection conn = ConexaoComPropriedades.getConnection();
        if (conn != null) {
            System.err.println("mercado.Conexao estabelecida com sucesso!");
            ConexaoComPropriedades.close(conn);
        } else {
            System.err.println("Erro na tentativa de conexao!");
            System.exit(1);
        }

        ProdutoService prd = new ProdutoService();
        prd.ManipulacaoBD();
        prd.criarTabelaProduto();
        Produto feijao = new Produto("Feijão", 7.49, 80);

        prd.cadastrarProduto(feijao);
        prd.consultarProduto(1);
        prd.excluirProduto(2);
        prd.atualizar(1, "arroz", 5.33, 10);

        Tabelas tbl = new Tabelas();
        tbl.criarTabelaCliente();
        tbl.criarTabelaCliente_pf();
        tbl.criarTabelaCliente_pj();
        tbl.criarTabelaVenda();
        tbl.criarTabelaItemVenda();

        ClienteService clt = new ClienteService();
        clt.ManipulacaoBD();
        ClienteJuridico clientePJ = new ClienteJuridico(DESCONTO1, -1, "aaa", -1, "12345678900019");
        clt.cadastrarClientePJ(clientePJ, clientePJ.getCnpj());

    }
}
