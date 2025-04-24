package mercado.cliente;
import java.util.HashMap;
import java.util.Map;

public class ClienteService implements IClienteService {
    @Override
    public void cadastrarCliente(Cliente cliente,String documento) {
        if(isCpf(documento)) {
            ClienteFisico clienteFisico = new ClienteFisico();
            clienteFisico.setNome(cliente.getNome());
        }

    }

    @Override
    public Cliente consultarCliente(int id) {
        return null;
    }

    @Override
    public Cliente[] listarCliente() {
        return new Cliente[0];
    }

    @Override
    public Cliente[] listarClientePJ() {
        return new Cliente[0];
    }

    @Override
    public void editarCliente(int id) {

    }

    private static boolean isCpf(String documento) {
        return documento != null && documento.length() == 11;
    }

    private static boolean isCnpj(String documento){
        return documento != null && documento.length() == 14;
    }
}
