package mercado.cliente;

public interface IClienteService {
    void cadastrarClienteCPF(ClienteFisico cliente);
    void cadastrarClienteCNPJ(ClienteJuridico cliente);
    Cliente consultarCliente(int id);
    Cliente[] listarCliente();
    Cliente[] listarClientePJ();
    void editarCliente(int id);
}
