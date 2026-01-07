import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Painel extends JFrame {

    // Componentes
    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JLabel resultadoLabel;

    public Painel() {
        // Configurações da janela
        setTitle("Painel de Controle - Java");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza
        setLayout(new GridLayout(6, 1, 10, 10));

        // Título
        JLabel titulo = new JLabel("Painel de Soma", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo);

        // Campo número 1
        campoNumero1 = new JTextField();
        campoNumero1.setBorder(BorderFactory.createTitledBorder("Número 1"));
        add(campoNumero1);

        // Campo número 2
        campoNumero2 = new JTextField();
        campoNumero2.setBorder(BorderFactory.createTitledBorder("Número 2"));
        add(campoNumero2);

        // Resultado
        resultadoLabel = new JLabel("Resultado: ", JLabel.CENTER);
        resultadoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(resultadoLabel);

        // Botão Somar
        JButton botaoSomar = new JButton("Somar");
        add(botaoSomar);

        // Botão Limpar
        JButton botaoLimpar = new JButton("Limpar");
        add(botaoLimpar);

        // Ação do botão Somar
        botaoSomar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(campoNumero1.getText());
                    double num2 = Double.parseDouble(campoNumero2.getText());
                    double soma = num1 + num2;
                    resultadoLabel.setText("Resultado: " + soma);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Digite apenas números!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Limpar
        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoNumero1.setText("");
                campoNumero2.setText("");
                resultadoLabel.setText("Resultado: ");
            }
        });
    }

    public static void main(String[] args) {
        // Abre o painel
        SwingUtilities.invokeLater(() -> {
            new Painel().setVisible(true);
        });
    }
}