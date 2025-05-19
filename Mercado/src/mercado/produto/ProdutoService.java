package mercado.produto;



import conexao.Conexao;

import java.sql.*;

public class ProdutoService {

    private Connection conn;

    public void ManipulacaoBD() {
        conn = Conexao.getConnection();
    }




    public void cadastrarProduto(Produto produto) {
//        if(produto != null){
//            produtos.put(produto.getId(), produto);
//        }else{
//            System.out.println("Produto invalido");
//        }
        String inserirSQL = "INSERT INTO produto (nome, valor, estoque) VALUES (?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(inserirSQL)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());
            stmt.setInt(3, produto.getEstoque());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Nao foi possivel realizar a insercao: " + e.getMessage());
        }

    }

//    @Override
    public Produto consultarProduto(int id) {
//        if(produtos.containsKey(id)){
//            return produtos.get(id);
//        }else{
//            return null;
//        }
            String sql = "SELECT nome, valor, estoque FROM produto WHERE id_produto = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        double valor = rs.getDouble("valor");
                        int estoque = rs.getInt("estoque");

                        System.out.println("ID: " + id + " Nome: " + nome + " Valor: " + valor + " Estoque: " + estoque );
                    }
                }
            } catch (SQLException e) {
                System.err.println("Erro ao consultar produto: " + e.getMessage());
            }

            return null;
    }

    public void excluirProduto(int id) {
        String deletarSQL = "DELETE FROM produto WHERE id_produto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(deletarSQL)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Nao foi possivel realizar a atualizacao: " + e.getMessage());
        }

    }

    public void atualizar(int id, String nome, Double valor, int estoque) {
        String atualizarSQL = "UPDATE produto SET nome = ?, valor = ?, estoque = ? WHERE id_produto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(atualizarSQL)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, valor);
            stmt.setInt(3, estoque);
            stmt.setInt(4, id);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Nao foi possivel realizar a atualizacao: " + e.getMessage());
        }

    }

//    @Override
//    public void editarProduto(int id, String nome,double valor) {
//        if (produtos.containsKey(id)) {
//            Produto produto = produtos.get(id);
//            produto.setNome(nome);
//            produto.setValor(valor);
//        }
//    }


//    @Override
//    public Produto[] listarProdutos() {
//        Produto[] produtos = new Produto[this.produtos.size()];
//        return this.produtos.values().toArray(produtos);
//    }


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
