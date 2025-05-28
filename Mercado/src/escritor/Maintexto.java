package escritor;

import java.sql.SQLException;

public class Maintexto {
    public static void main(String[] args) throws SQLException {
        var relatorioVenda = "Relatorio_venda.txt";

        var escritor = new Escritor();
        escritor.ManipulacaoBD();
        escritor.Relatoriovenda(relatorioVenda);

    }
}
