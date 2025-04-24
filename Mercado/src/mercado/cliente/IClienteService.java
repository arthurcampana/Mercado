package mercado.cliente;

public interface IClienteService {
    void cadastrarCliente(Cliente cliente,String documento);
    Cliente consultarCliente(int id);
    Cliente[] listarCliente();
    Cliente[] listarClientePJ();
    void editarCliente(int id);
}
