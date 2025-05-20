
import conexao.ConexaoComPropriedades;
import conexao.Tabelas;
import mercado.produto.Produto;
import mercado.produto.ProdutoService;

import java.sql.Connection;

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
    }
}
