package Interface;

import escritor.Escritor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RelatorioInterface extends JFrame {
    private JPanel panel;
    public RelatorioInterface(){

        setTitle("Relatorios");
        setLayout(new FlowLayout());

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setPreferredSize(new Dimension(1000,700));
        add(this.panel);

        criarBotao("Vendas Realizadas", new BotaoGerarVendasHandler(),100,200);
        criarBotao("Clientes Mais Rentáveis", new BotaoGerarClientesHandler(),100,300);
        criarBotao("Produtos Mais Rentáveis", new BotaoGerarProdutosHandler(),100,400);
        criarBotao("Voltar",new BotaoVoltarHandler(),100,500);


        ImageIcon icon = new ImageIcon(getClass().getResource("pinguimvingaca.png"));
        Image img = icon.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(img));
        labelImagem.setBounds(450, 50, 500, 600);
        this.panel.add(labelImagem);




        setSize(new Dimension(1000,700));
        setPreferredSize((new Dimension(1000,700)));
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        var relatorioClientes = "Relatorio_clientes.txt";
        var relatorioProdutos = "Relatorio_Produtos.txt";




        setVisible(true);

    }

    private void criarBotao(String label, ActionListener listener, int x, int y){
        JButton botao = new JButton(label);
        botao.addActionListener(listener);
        botao.setPreferredSize(new Dimension(300,80));
        botao.setBounds(x,y,300,80);
        this.panel.add(botao);
    }
    private static class BotaoGerarClientesHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            var relatorioClientes = "Relatorio_clientes.txt";
            var escritor = new Escritor();
            escritor.ManipulacaoBD();
            try {
                escritor.RelatorioClientes(relatorioClientes);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private static class BotaoGerarVendasHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            var relatorioVenda = "Relatorio_venda.txt";
            var escritor = new Escritor();
            escritor.ManipulacaoBD();
            try {
                escritor.Relatoriovenda(relatorioVenda);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private static class BotaoGerarProdutosHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            var relatorioProdutos = "Relatorio_produtos.txt";
            var escritor = new Escritor();
            escritor.ManipulacaoBD();
            try {
                escritor.RelatorioProdutos(relatorioProdutos);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }


    }

    private class BotaoVoltarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }


}


