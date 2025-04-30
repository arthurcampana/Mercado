package mercado.produto;

public class Produto {
    private int id;
    private String nome;
    private double valor;
    private int estoque;

    public Produto(int id, String nome, double valor,int estoque) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {

        return nome;
    }

    public double getValor() {

        return valor;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", estoque=" + estoque +
                '}';
    }
}
