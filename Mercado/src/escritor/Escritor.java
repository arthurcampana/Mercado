package escritor;

import conexao.Conexao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class Escritor{
    private Connection conn;

    public void ManipulacaoBD() {
        conn = Conexao.getConnection();
    }

    public Escritor(){}


    public void Relatoriovenda(String nomeArquivo) throws SQLException {
        String sqlString = "SELECT * FROM venda WHERE DATE(data_hora) = CURDATE();";
        PreparedStatement stmtRelatorio = conn.prepareStatement(sqlString);
        ResultSet rs = stmtRelatorio.executeQuery();

        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))){

            while (rs.next()) {
                int id = rs.getInt("id_venda");
                double valor = rs.getDouble("valor_total");
                LocalDateTime dataHora = rs.getTimestamp("data_hora").toLocalDateTime();
                writer.printf("ID: %d, Valor: %.2f, Data: %s%n", id, valor, dataHora);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
