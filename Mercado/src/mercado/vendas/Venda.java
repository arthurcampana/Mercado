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

    public Venda( ItemVenda[] itens, Cliente cliente) {

        this.itens = itens;

        this.cliente = cliente;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Venda{" +
                "id=" + id +
                ", datahora=" + datahora +
                ", cliente=" + cliente +
                ", desconto=" + desconto +
                ", itens=" + Arrays.toString(itens) +
                '}';
    }
}
