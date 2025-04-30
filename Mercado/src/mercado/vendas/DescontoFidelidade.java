package mercado.vendas;

import mercado.cliente.Categoria;
import mercado.cliente.Cliente;

public class DescontoFidelidade {


    public double getDesconto(Cliente cliente){
        if(cliente == null){
            return 0.0;
        }
        if(cliente.getCategoria() == Categoria.DESCONTO1){
            return 0.05;
        }else if(cliente.getCategoria() == Categoria.DESCONTO2){
            return 0.10;
        }else {
            return 0.0;
        }
    }

}
