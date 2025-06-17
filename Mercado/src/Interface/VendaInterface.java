package Interface;

import mercado.produto.Produto;
import mercado.produto.ProdutoService;
import mercado.vendas.VendaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class VendaInterface extends JFrame {
    private JPanel panel;
    private JTextField tfIDProduto;
    private JTextField tfQuantidade;
    private JTextArea areaCarrinho;  // Adicionando a área do carrinho
    private JTextField tfDocumento;
    private Map<Produto, Integer> produtosComprados = new HashMap<>();
    private ProdutoService produtoService;
    private VendaService vendaService;

    public VendaInterface() throws SQLException {
        setTitle("Venda");
        setLayout(new FlowLayout());

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.panel.setPreferredSize(new Dimension(500, 700));
        add(this.panel);

        vendaService = new VendaService();
        vendaService.ManipulacaoBD();
        produtoService = new ProdutoService();
        produtoService.ManipulacaoBD();



        criarTextFieldDocumento("Documento");
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

        try {
            if (Integer.parseInt(quantidade) <= 0 || Integer.parseInt(produto) <= 0) {
                JOptionPane.showMessageDialog(null, "O código e a quantidade não podem ser negativos");
                return;
            }

            produtosComprados.put(produtoService.consultarProduto(Integer.parseInt(tfIDProduto.getText().trim())),
                    Integer.parseInt(tfQuantidade.getText().trim())
            );
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Código e quantidade devem ser números válidos.");
        }
        String linha = "Produto: " + produto + " | Quantidade: " + quantidade;
        areaCarrinho.append(linha + "\n");



        tfIDProduto.setText("");
        tfQuantidade.setText("");
    }

    private void finalizarVenda() throws SQLException {

        String doc = tfDocumento.getText();
        if(produtosComprados.isEmpty()){
            JOptionPane.showMessageDialog(null, "Seu carrinho esta vazio");
            return;
        }
        try {
            if (doc == null) {
                vendaService.criarVenda(doc, produtosComprados);
                JOptionPane.showMessageDialog(null, "Venda finalizada!\nItens:\n" + areaCarrinho.getText());
            } else {
                vendaService.criarVenda(String.valueOf(doc), produtosComprados);
                JOptionPane.showMessageDialog(null, "Venda finalizada!\nItens:\n" + areaCarrinho.getText());
            }
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "faz direito burrao.");
        }



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
            try {
                finalizarVenda();
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
