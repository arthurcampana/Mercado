package Interface;

import mercado.produto.Produto;
import mercado.produto.ProdutoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdutoInterface extends JFrame {
    private JPanel panel;
    private JTextField tfNomeProduto;
    private JTextField tfValorProduto;
    private JTextField tfEstoqueProduto;
    private JTextField tfCodigoProduto;

    private ProdutoService produtoService;

    public ProdutoInterface() {
        setTitle("Cadastro de Produto");
        setLayout(new FlowLayout());

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.panel.setPreferredSize(new Dimension(500, 700));
        add(this.panel);

        produtoService = new ProdutoService();
        produtoService.ManipulacaoBD();

        criarTextFieldCodigoProduto("Código do Produto:");
        criarTextFieldNomeProduto("Nome do Produto:");
        criarTextFieldValorProduto("Valor do Produto (R$):");
        criarTextFieldEstoqueProduto("Quantidade em Estoque:");

        criarBotao("Salvar Produto", new BotaoSalvarHandler());
        criarBotao("Atualizar Produto", new BotaoAtualizarHandler());  // Novo botão
        criarBotao("Consultar Produto", new BotaoConsultarHandler());
        criarBotao("Voltar", new BotaoVoltarHandler());

        setSize(new Dimension(500, 700));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void criarBotao(String texto, ActionListener listener) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(400, 40));
        botao.addActionListener(listener);
        this.panel.add(botao);
    }

    private void criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setPreferredSize(new Dimension(400, 30));
        this.panel.add(label);
    }

    private void criarTextFieldNomeProduto(String label) {
        criarLabel(label);
        tfNomeProduto = new JTextField();
        tfNomeProduto.setPreferredSize(new Dimension(400, 40));
        this.panel.add(tfNomeProduto);
    }

    private void criarTextFieldValorProduto(String label) {
        criarLabel(label);
        tfValorProduto = new JTextField();
        tfValorProduto.setPreferredSize(new Dimension(400, 40));
        this.panel.add(tfValorProduto);
    }

    private void criarTextFieldEstoqueProduto(String label) {
        criarLabel(label);
        tfEstoqueProduto = new JTextField();
        tfEstoqueProduto.setPreferredSize(new Dimension(400, 40));
        this.panel.add(tfEstoqueProduto);
    }

    private void criarTextFieldCodigoProduto(String label) {
        criarLabel(label);
        tfCodigoProduto = new JTextField();
        tfCodigoProduto.setPreferredSize(new Dimension(400, 40));
        this.panel.add(tfCodigoProduto);
    }

    private class BotaoSalvarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String nome = tfNomeProduto.getText().trim();
            String valorStr = tfValorProduto.getText().trim();
            String estoqueStr = tfEstoqueProduto.getText().trim();

            if (nome.isEmpty() || valorStr.isEmpty() || estoqueStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                return;
            }
            try {
                double valor = Double.parseDouble(valorStr);
                int estoque = Integer.parseInt(estoqueStr);

                Produto produto = new Produto(0, nome, valor, estoque);
                produtoService.cadastrarProduto(produto);

                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");

                limparCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor e Estoque devem ser números válidos.");
            }
        }
    }

    private class BotaoAtualizarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String codigo = tfCodigoProduto.getText().trim();
            String nome = tfNomeProduto.getText().trim();
            String valor = tfValorProduto.getText().trim();
            String estoque = tfEstoqueProduto.getText().trim();

            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite o código do produto para atualizar!");
                return;
            }

            // Aqui você atualizaria no banco, se quiser
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!\n" +
                    "Código: " + codigo + "\n" +
                    "Nome: " + nome + "\n" +
                    "Valor: R$ " + valor + "\n" +
                    "Estoque: " + estoque + " unidades");
        }
    }

    private class BotaoConsultarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String codigo = tfCodigoProduto.getText().trim();

            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite o código do produto para consultar!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto consultado com sucesso!\n" +
                        "Código: " + codigo + "\n(Neste exemplo, os dados estão fixos)");
            }
        }
    }

    private class BotaoVoltarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }

    private void limparCampos() {
        tfCodigoProduto.setText("");
        tfNomeProduto.setText("");
        tfValorProduto.setText("");
        tfEstoqueProduto.setText("");
    }
}
