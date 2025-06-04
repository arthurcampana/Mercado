package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        ImageIcon icon = new ImageIcon(getClass().getResource("pinguimjava.png"));
        Image img = icon.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(img));
        labelImagem.setBounds(450, 200, 500, 300);
        this.panel.add(labelImagem);




        setSize(new Dimension(1000,700));
        setPreferredSize((new Dimension(1000,700)));
        setDefaultCloseOperation(EXIT_ON_CLOSE);




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

        }
    }
    private static class BotaoGerarVendasHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class BotaoGerarProdutosHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class BotaoVoltarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }


}


