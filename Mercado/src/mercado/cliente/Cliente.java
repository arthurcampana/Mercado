package mercado.cliente;

public class Cliente {
    private int id;
    private String nome;
    private int telefone;
    private Categoria categoria;


//    //public Cliente(Categoria categoria, int telefone, String nome, int id) {
//        this.categoria = categoria;
//        this.telefone = telefone;
//        this.nome = nome;
//        this.id = id;
//    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getDocumento(){
      return null;
    };

    public Categoria getCategoria() {

        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone=" + telefone +
                ", categoria=" + categoria +
                '}';
    }
}
