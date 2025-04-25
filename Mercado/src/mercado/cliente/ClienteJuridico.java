package mercado.cliente;

public class ClienteJuridico extends Cliente {
    private String cnpj;

    public ClienteJuridico(Categoria categoria, int telefone, String nome, int id) {
        super(categoria, telefone, nome, id);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return super.toString() + ", CNPJ: '" + cnpj + "'";
    }

}
