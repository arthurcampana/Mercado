package mercado;

import mercado.cliente.ClienteFisico;
import mercado.produto.Produto;
import mercado.vendas.VendaService;

import java.util.HashMap;
import java.util.Map;

import static mercado.cliente.Categoria.DESCONTO1;

public class Main2 {
    public static void main(String[] args) {

        Produto arroz = new Produto(1, "Arroz", 5.99, 100);
        Produto feijao = new Produto(2, "Feijão", 7.49, 80);
        Produto macarrao = new Produto(3, "Macarrão", 4.29, 60);

        Map<Produto, Integer> produtosComprados = new HashMap<>();
        produtosComprados.put(arroz, 2);
        produtosComprados.put(feijao, 1);
        produtosComprados.put(macarrao, 3);


        ClienteFisico clientePf = new ClienteFisico(DESCONTO1, 141, "ASD", 2);
        clientePf.setCpf("12345678910");

        VendaService vendaService = new VendaService();

        vendaService.criarVenda(null, produtosComprados, 8);
    }

}
