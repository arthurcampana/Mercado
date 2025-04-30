package mercado.vendas;

import mercado.cliente.Cliente;
import mercado.produto.Produto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VendaService {


    private double aplicarDesconto(double valorTotal, Cliente cliente) {
        if (cliente != null) {
            descontoFidelidade df = new descontoFidelidade();
            return valorTotal * (1 - df.GetDesconto(cliente));
        }
        return valorTotal;
    }


    public void criarVenda(Cliente cliente, Map<Produto, Integer> produtos, int id) {
        ItemVenda[] itensVenda = new ItemVenda[produtos.size()];
        double valorTotal = 0.0;
        int i = 0;
            // for(Produto produto : produtos)
        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();

            ItemVenda item = new ItemVenda(produto.getValor(), quantidade, produto.getNome());

            valorTotal += produto.getValor() * quantidade;
            itensVenda[i++] = item;
        }
        valorTotal = aplicarDesconto(valorTotal, cliente);

        descontoFidelidade df = new descontoFidelidade();

        LocalDateTime datahora = LocalDateTime.now();

        double desconto = df.GetDesconto(cliente);;

        Venda venda = new Venda(itensVenda, cliente, datahora, desconto);
        System.out.println(venda + " - Valor total: R$" + String.format("%.2f", valorTotal));

    }

}
