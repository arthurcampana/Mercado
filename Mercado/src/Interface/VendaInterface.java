package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendaInterface extends JFrame {
    private JPanel panel;
    private JTextField tfIDProduto;
    private JTextField tfQuantidade;
    private JTextArea areaCarrinho;  // Adicionando a área do carrinho
    private JTextField tfDocumento;

    public VendaInterface() {
        setTitle("Venda");
        setLayout(new FlowLayout());

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.panel.setPreferredSize(new Dimension(500, 700));
        add(this.panel);

        criarTextFieldIDProduto("Documento");
        criarTextFieldIDProduto("Código do Produto");
        criarTextFieldQuantidade("Quantidade do Produto");

        criarBotao("Adicionar", new BotaoAdicionarHandler());
        criarBotao("Finalizar", new BotaoFinalizarHandler());
        criarBotao("Voltar", new BotaoVoltarHandler());

        // Área para mostrar os itens adicionados
        criarAreaCarrinho();

        setSize(new Dimension(500, 700));
        setPreferredSize(new Dimension(500, 700));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void criarAreaCarrinho() {
        criarLabel("Itens no Carrinho:");

        areaCarrinho = new JTextArea();
        areaCarrinho.setPreferredSize(new Dimension(400, 200));
        areaCarrinho.setEditable(false); // usuário não digita diretamente
        areaCarrinho.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.panel.add(areaCarrinho);
    }

    private void adicionarItemCarrinho() {
        String produto = tfIDProduto.getText();
        String quantidade = tfQuantidade.getText();

        if (produto.isEmpty() || quantidade.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
            return;
        }

        String linha = "Produto: " + produto + " | Quantidade: " + quantidade;
        areaCarrinho.append(linha + "\n");

        tfIDProduto.setText("");
        tfQuantidade.setText("");
    }

    private void finalizarVenda() {
        JOptionPane.showMessageDialog(null, "Venda finalizada!\nItens:\n" + areaCarrinho.getText());
        areaCarrinho.setText(""); // limpa o carrinho
    }

    private void criarBotao(String label, ActionListener listener) {
        JButton botao = new JButton(label);
        botao.addActionListener(listener);
        botao.setPreferredSize(new Dimension(300, 50));
        this.panel.add(botao);
    }

    public void criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setPreferredSize(new Dimension(400, 30));
        this.panel.add(label);
    }

    private void criarTextFieldIDProduto(String texto) {
        criarLabel(texto);
        this.tfIDProduto = new JTextField();
        this.tfIDProduto.setPreferredSize(new Dimension(400, 40));
        this.panel.add(this.tfIDProduto);
    }

    private void criarTextFieldQuantidade(String texto) {
        criarLabel(texto);
        this.tfQuantidade = new JTextField();
        this.tfQuantidade.setPreferredSize(new Dimension(400, 40));
        this.panel.add(this.tfQuantidade);
    }

    private void criarTextFieldDocumento(String texto) {
        criarLabel(texto);
        this.tfDocumento = new JTextField();
        this.tfDocumento.setPreferredSize(new Dimension(400, 40));
        this.panel.add(this.tfDocumento);
    }

    // Handlers dos botões
    private class BotaoAdicionarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            adicionarItemCarrinho();
        }
    }

    private class BotaoFinalizarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            finalizarVenda();
        }
    }

    private class BotaoVoltarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
