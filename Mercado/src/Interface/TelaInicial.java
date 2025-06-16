package Interface;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    private JPanel panel;
    public TelaInicial(){

        setTitle("Mercado");
        setLayout(new FlowLayout());

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setPreferredSize(new Dimension(1000,700));
        add(this.panel);

        criarBotao("Produtos", new BotaoCadastrarHandler(),100,200);
        criarBotao("Relatórios", new BotaoRelatoriosHandler(),100,400);
        criarBotao("Venda", new BotaoVendaHandler(),100,300);
        criarBotao("Sair", new BotaoSairHandler(),100,500);

        ImageIcon icon = new ImageIcon(getClass().getResource("java-removebg-preview.png"));
        Image img = icon.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(img));
        labelImagem.setBounds(450, 200, 500, 300);
        this.panel.add(labelImagem);



        setSize(new Dimension(1000,700));
        setPreferredSize((new Dimension(1000,700)));
        setDefaultCloseOperation(EXIT_ON_CLOSE);




        setVisible(true);

    }

    private void criarBotao(String label, ActionListener listener,int x,int y){
        JButton botao = new JButton(label);
        botao.addActionListener(listener);
        botao.setPreferredSize(new Dimension(300,80));
        botao.setBounds(x,y,300,80);
        this.panel.add(botao);
    }
    private static class BotaoCadastrarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ProdutoInterface();
        }
    }
    private static class BotaoRelatoriosHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new RelatorioInterface();
        }
    }

    private static class BotaoVendaHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new VendaInterface();
        }
    }

    private static class BotaoSairHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}
