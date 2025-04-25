package mercado.cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteService implements IClienteService {

    private Map<Integer,Cliente> clientes;
    public ClienteService() {
        this.clientes = new HashMap<>();
    }

    @Override
    public void cadastrarClienteCPF(ClienteFisico cliente) {
        if (cliente != null && isCpf(cliente.getCpf())) {
            clientes.put(cliente.getId(), cliente);
        } else {
            System.out.println("CPF invalido ou cliente nulo.");
        }
    }

    @Override
    public void cadastrarClienteCNPJ(ClienteJuridico cliente){
        if (cliente != null && isCnpj(cliente.getCnpj())) {
            clientes.put(cliente.getId(), cliente);
        } else {
            System.out.println("CNPJ invalido ou cliente nulo.");
        }
    }

    @Override
    public Cliente consultarCliente(int id) {
        if (clientes.containsKey(id)) {
            return clientes.get(id);
        }
        return null;
    }

    @Override
    public Cliente[] listarCliente() {
        Cliente[] clientes = new Cliente[this.clientes.size()];
        return this.clientes.values().toArray(clientes);
    }

//    @Override
//    public void editarCliente(int id, String nome, int telefone, Categoria categoria) {
//        if (clientes.containsKey(id)) {
//            Cliente clientea = clientes.get(id);
//            clientea.setNome(nome);
//            clientea.setTelefone(telefone);
//            clientea.setCategoria(categoria);
//            System.out.println("Nome do cliente atualizado para: " + nome
//                                + "Telefone do cliente atualizado para" + telefone
//                                + "Categoria do cliente atualizada para" + categoria);
//        }else{
//            System.out.println("Cliente nao encontrado");
//        }
//
//
//
//    }
//@Override
//public void editarCliente(Cliente cliente) {
//    int id = cliente.getId();
//
//    if (clientes.containsKey(id)) {
//        Cliente clientea = clientes.get(id);
//        clientea.setNome(cliente.getNome());
//        clientea.setTelefone(cliente.getTelefone());
//        clientea.setCategoria(cliente.getCategoria());
//
//        System.out.println("Cliente atualizado:");
//        System.out.println("Nome: " + cliente.getNome());
//        System.out.println("Telefone: " + cliente.getTelefone());
//        System.out.println("Categoria: " + cliente.getCategoria());
//    } else {
//        System.out.println("Cliente não encontrado.");
//    }
//}
@Override
public void editarCliente(Cliente cliente) {

        System.out.println("atualizo po");
}


    @Override
    public Cliente[] listarClientePJ() {
        List<Cliente> listaPJ = new ArrayList<>();

        for (Cliente cliente : clientes.values()) {
            if (cliente instanceof ClienteJuridico) {
                listaPJ.add(cliente);
            }
        }


        return listaPJ.toArray(new Cliente[0]);
    }
    @Override
    public Cliente[] listarClientePF() {
        List<Cliente> listaPF = new ArrayList<>();

        for (Cliente cliente : clientes.values()) {
            if (cliente instanceof ClienteFisico) {
                listaPF.add(cliente);
            }
        }

        return listaPF.toArray(new Cliente[0]);
    }

    private static boolean isCpf(String documento) {
        return documento != null && documento.length() == 11;
    }

    private static boolean isCnpj(String documento){
        return documento != null && documento.length() == 14;
    }
}
