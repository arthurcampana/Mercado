package conexao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoComPropriedades {
    private static String url = null;
    private static String usuario = null;
    private static String senha = null;
    private static Connection conn;

    public static void carregarPropriedades() {
        if (url != null) {
            // já foi carregado, não precisa carregar novamente
            return;
        }

        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("config/bd.properties")) {
            properties.load(inputStream);
            url = properties.getProperty("url");
            usuario = properties.getProperty("usuario");
            senha = properties.getProperty("senha");
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo de propriedades: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        }

        carregarPropriedades();
        try {
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("mercado.Conexao estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro de SQL na conexao: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro na conexao: " + e.getMessage());
        }

        return conn;
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erro de SQL no encerramento da conexao: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro no encerramento da conexao: " + e.getMessage());
        }
        conn = null;
    }
}

