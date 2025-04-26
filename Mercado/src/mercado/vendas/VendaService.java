package mercado.vendas;

import mercado.cliente.Cliente;
import mercado.produto.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VendaService {





    public void criarVenda(Cliente cliente, Map<Produto, Integer> produtos, int id){
        ItemVenda[] itensVenda = new ItemVenda[produtos.size()];
        int i = 0;
        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();

            ItemVenda item = new ItemVenda(produto.getValor(), quantidade, produto.getNome());

            itensVenda[i++] = item;
        }
        Venda venda = new Venda(itensVenda, cliente);
        System.out.println(venda);


    }
}
