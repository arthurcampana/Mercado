package conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tabelas {
    public void criarTabelaCliente() {
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cliente (" +
                    "id_cliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "telefone INT, " +
                    "categoria VARCHAR(20));");
            System.out.println("Tabela CLIENTE criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela CLIENTE: " + e.getMessage());
        }
    }
    public void criarTabelaCliente_pf() {
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cliente_pf (" +
                    "id_cliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "cpf VARCHAR(11));");
            System.out.println("Tabela CLIENTE_PF criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela CLIENTE: " + e.getMessage());
        }
    }
    public void criarTabelaCliente_pj() {
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cliente_pj (" +
                    "id_cliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "cnpj VARCHAR(14));");
            System.out.println("Tabela CLIENTE_PJ criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela CLIENTE: " + e.getMessage());
        }
    }


    public void criarTabelaVenda() {
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS venda (" +
                    "id_venda INT NOT NULL AUTO_INCREMENT PRIMARY KEY , " +
                    "fk_id_cliente INT, " +
                    "data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                    "desconto decimal(10,2) NOT NULL, " +
                    "FOREIGN KEY (fk_id_cliente) REFERENCES cliente(id_cliente));");
            System.out.println("Tabela criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void criarTabelaItemVenda() {
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS item_venda (" +
                    "id_item_venda INT NOT NULL AUTO_INCREMENT PRIMARY KEY , " +
                    "fk_id_venda INT NOT NULL, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "quantidade decimal(10,2) NOT NULL, " +
                    "valor decimal(10,2) NOT NULL, " +
                    "FOREIGN KEY (fk_id_venda) REFERENCES venda(id_venda));");
            System.out.println("Tabela criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void criarTabelaProduto() {
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS produto (" +
                    "id_produto INT NOT NULL AUTO_INCREMENT PRIMARY KEY , " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "valor DOUBLE NOT NULL, " +
                    "estoque INT NOT NULL);");
            System.out.println("Tabela criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}
