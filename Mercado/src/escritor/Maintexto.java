package escritor;

import java.sql.SQLException;

public class Maintexto {
    public static void main(String[] args) throws SQLException {
        var relatorioVenda = "Relatorio_venda.txt";
        var relatorioClientes = "Relatorio_clientes.txt";
        var relatorioProdutos = "Relatorio_Produtos.txt";

        var escritor = new Escritor();
        escritor.ManipulacaoBD();
        escritor.Relatoriovenda(relatorioVenda);
        escritor.RelatorioClientes(relatorioClientes);
        escritor.RelatorioProdutos(relatorioProdutos);

    }
}
