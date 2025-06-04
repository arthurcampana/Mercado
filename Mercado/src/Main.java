
import conexao.ConexaoComPropriedades;
import conexao.Tabelas;
import mercado.cliente.*;
import mercado.produto.Produto;
import mercado.produto.ProdutoService;

import java.sql.Connection;

import static mercado.cliente.Categoria.*;

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
        Produto feijao = new Produto(1,"Feijão", 7.49, 80);

        ProdutoService prd2 = new ProdutoService();
        prd.ManipulacaoBD();
        prd.criarTabelaProduto();
        Produto arroz = new Produto(2,"Arroz", 6.50, 100);

        ProdutoService prd3 = new ProdutoService();
        prd.ManipulacaoBD();
        prd.criarTabelaProduto();
        Produto skol = new Produto(2,"Skol", 5.00, 101);

        prd.cadastrarProduto(feijao);
        prd.cadastrarProduto(arroz);
        prd.cadastrarProduto(arroz);
        prd.cadastrarProduto(skol);
        prd.consultarProduto(1);
        prd.excluirProduto(2);
        prd.atualizar(3, "arroz", 5.30, 90);

        Tabelas tbl = new Tabelas();
        tbl.criarTabelaCliente();
        tbl.criarTabelaCliente_pf();
        tbl.criarTabelaCliente_pj();
        tbl.criarTabelaVenda();
        tbl.criarTabelaItemVenda();

        ClienteService clt = new ClienteService();
        clt.ManipulacaoBD();

        ClienteFisico clienteGenerico = new ClienteFisico(DESCONTO0,000,"Consumidor_Final",0,"00000000000");
        clt.cadastrarCliente(clienteGenerico);

        ClienteJuridico clientePJ = new ClienteJuridico(DESCONTO2, -1, "CLienteCNPJ1", 1, "12345678300019");
        ClienteFisico clientePf = new ClienteFisico(DESCONTO1, 141, "ClienteCPF1", 2, "12345678910");
        ClienteFisico clientePf2 = new ClienteFisico(DESCONTO2, 01234, "ClienteCPF2", 3, "99999999999");
        clt.cadastrarCliente(clientePJ);
        clt.cadastrarCliente(clientePf);
        clt.cadastrarCliente(clientePf2);

        System.out.println(clt.consultarCliente("12345678910"));
        System.out.println(clt.consultarCliente("00000000000"));
    }
}
