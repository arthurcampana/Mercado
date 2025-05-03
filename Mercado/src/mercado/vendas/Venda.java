package mercado.vendas;

import mercado.cliente.Cliente;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Venda {
    private int id;
    private LocalDateTime datahora;
    private Cliente cliente;
    private double desconto;
    private ItemVenda[] itens;
    private double valorTotal;

    public Venda( ItemVenda[] itens, Cliente cliente, LocalDateTime datahora, Double desconto, double valorTotal) {

        this.itens = itens;
        this.cliente = cliente;
        this.datahora = datahora;
        this.desconto = desconto;
        this.valorTotal = valorTotal;

    }

    public int getId() {
        return id;
    }

    public ItemVenda[] getItens() {
        return itens;
    }

    public void setItens(ItemVenda[] itens) {
        this.itens = itens;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.datahora = datahora;
    }

    @Override
    public String toString() {
        return "Venda {\n" +
                "  id = " + id + ",\n" +
                "  datahora = " + datahora + ",\n" +
                "  cliente = " + cliente + ",\n" +
                "  desconto = " + desconto + ",\n" +
                "  itens = " + Arrays.toString(itens) + ",\n" +
                "  valor total = " + valorTotal + "\n" +
                '}';
    }
}
