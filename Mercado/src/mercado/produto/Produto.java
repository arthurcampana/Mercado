package mercado.produto;

public class Produto {
    private int id;
    private String nome;
    private double valor;
    private int estoque;

    public Produto( String nome, double valor,int estoque) {
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
    }

    public int getEstoque() {
        return estoque;
    }

    public void saidaEstoque(int estoque) {
        this.estoque -= estoque;
    }

    public void entradaEstoque(int estoque) {
        this.estoque += estoque;
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
