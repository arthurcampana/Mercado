package mercado.cliente;

public class ClienteJuridico extends Cliente {
    private String cnpj;

    public ClienteJuridico(Categoria categoria, int telefone, String nome, int id, String cnpj) {
        super(categoria, telefone, nome, id);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getDocumento(){
        return this.cnpj;
    }

    @Override
    public String toString() {
        return super.toString() + ", CNPJ: '" + cnpj + "'";
    }

}
