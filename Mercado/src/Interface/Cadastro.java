package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cadastro extends JFrame {

    private JPanel panel;
    private JTextField tfAno;
    private JTextField tfNome;
    private JTextField tfValor;

    public Cadastro() {
        setTitle("Cadastro de Antiguidades");
        setLayout(new FlowLayout());

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.panel.setPreferredSize(new Dimension(500,500));
        add(this.panel);

        criarTextFieldNome("Nome da Antiguidade");
        criarTextFieldAno("Ano da Antiguidade");
        criarTextFieldValor("Valor da antiguidade");


        criarBotao("Salvar", new BotaoSalvarHandler());
        criarBotao("Voltar",new BotaoVoltarHandler());

        setSize(new Dimension(500,500));
        setPreferredSize(new Dimension(500,500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void criarAntiguidade(){
        JOptionPane.showMessageDialog(null,"Cadastro " + "realizado com sucesso");

    }
    private void criarBotao(String label, ActionListener listener){
        JButton botao = new JButton(label);
        botao.addActionListener(listener);
        botao.setPreferredSize(new Dimension(300,80));
        this.panel.add(botao);
    }

    public void criarLabel(String texto){
        JLabel label = new JLabel(texto);
        label.setPreferredSize(new Dimension(400,40));
        this.panel.add(label);
    }

    private void criarTextFieldNome(String texto){
        criarLabel(texto);
        this.tfNome = new JTextField();
        this.tfNome.setPreferredSize(new Dimension(400,40));
        this.panel.add(this.tfNome);
    }

    private void criarTextFieldValor(String texto){
        criarLabel(texto);
        this.tfValor = new JTextField();
        this.tfValor.setPreferredSize(new Dimension(400,40));
        this.panel.add(this.tfValor);
    }

    private void criarTextFieldAno(String texto){
        criarLabel(texto);
        this.tfAno = new JTextField();
        this.tfAno.setPreferredSize(new Dimension(400,40));
        this.panel.add(this.tfAno);
    }

    private class BotaoSalvarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            criarAntiguidade();
        }
    }

    private class BotaoVoltarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }

}
