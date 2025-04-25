package mercado;

import mercado.cliente.*;

import java.util.Arrays;

import static mercado.cliente.Categoria.DESCONTO1;
import static mercado.cliente.Categoria.DESCONTO2;

public class Main {
    public static void main(String[] args) {
        System.out.println("a");


        IClienteService clienteService = new ClienteService();

        ClienteJuridico clientePJ = new ClienteJuridico(DESCONTO1, 141, "ASD", 1);
        clientePJ.setCnpj("12345678000199");

        ClienteFisico clientePf = new ClienteFisico(DESCONTO1, 141, "ASD", 2);
        clientePf.setCpf("12345678910");

        clienteService.cadastrarClienteCNPJ(clientePJ);
        System.out.println(clienteService.consultarCliente(1));
        clienteService.cadastrarClienteCPF(clientePf);
        System.out.println(clienteService.consultarCliente(2));

        System.out.println(Arrays.toString(clienteService.listarCliente()));

        clienteService.editarCliente(1, "asdfs", 51234, DESCONTO2);
        System.out.println(clienteService.consultarCliente(1));

        System.out.println(Arrays.toString(clienteService.listarClientePF()));

    }
}
