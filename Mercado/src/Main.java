
import conexao.ConexaoComPropriedades;
import conexao.Tabelas;
import mercado.cliente.ClienteFisico;
import mercado.cliente.ClienteJuridico;
import mercado.cliente.ClienteService;
import mercado.produto.Produto;
import mercado.produto.ProdutoService;

import java.sql.Connection;

import static mercado.cliente.Categoria.DESCONTO1;
import static mercado.cliente.Categoria.DESCONTO2;

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
        Produto feijao = new Produto(0,"Feijão", 7.49, 80);

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
        ClienteJuridico clientePJ = new ClienteJuridico(DESCONTO2, -1, "aaa", 0, "12345678300019");
        ClienteFisico clientePf = new ClienteFisico(DESCONTO1, 141, "ASD", 2, "12345678910");
        clt.cadastrarCliente(clientePJ);
        clt.cadastrarCliente(clientePf);
        System.out.println(clt.consultarCliente("12345678910"));
    }
}
