package mercado.cliente;

public class ClienteFisico extends Cliente{
    private String cpf;

    public ClienteFisico(Categoria categoria, int telefone, String nome, int id, String cpf) {
        super(categoria, telefone, nome, id);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getDocumento(){
        return this.cpf;
    }

    @Override
    public String toString() {
        return super.toString() + ", CPF: '" + cpf + "'";
    }

}
