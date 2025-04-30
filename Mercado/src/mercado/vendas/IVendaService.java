package mercado.vendas;

import mercado.cliente.Cliente;
import mercado.produto.Produto;

import java.util.Map;

public interface IVendaService {
    void criarVenda(Cliente cliente, Map<Produto, Integer> produtos, int id);
    double aplicarDesconto(double valorTotal, Cliente cliente);
}
