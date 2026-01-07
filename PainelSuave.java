import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelSuave extends JFrame {

    private boolean suavizacaoAtiva = false;
    private DesenhoPainel painelDesenho;

    public PainelSuave() {
        setTitle("Painel - Suavizar Mira (Fake)");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Fundo principal
        getContentPane().setBackground(Color.BLACK);

        // Painel superior (título)
        JLabel titulo = new JLabel("PAINEL DE CONTROLE", JLabel.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // Painel central (desenho)
        painelDesenho = new DesenhoPainel();
        painelDesenho.setBackground(new Color(40, 40, 40));
        add(painelDesenho, BorderLayout.CENTER);

        // Painel inferior (botão)
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(Color.BLACK);

        JButton botaoSuavizar = new JButton("Suavizar Mira (Fake)");
        botaoSuavizar.setFocusPainted(false);
        botaoSuavizar.setBackground(new Color(80, 80, 80));
        botaoSuavizar.setForeground(Color.WHITE);
        botaoSuavizar.setFont(new Font("Arial", Font.BOLD, 14));

        painelBotoes.add(botaoSuavizar);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ação do botão
        botaoSuavizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suavizacaoAtiva = !suavizacaoAtiva;
                painelDesenho.setSuavizacao(suavizacaoAtiva);
            }
        });

        // Timer para animação suave
        Timer timer = new Timer(16, e -> painelDesenho.animar());
        timer.start();
    }

    // Painel de desenho interno
    class DesenhoPainel extends JPanel {

        private float y = 200;
        private boolean suavizar = false;

        public void setSuavizacao(boolean ativado) {
            this.suavizar = ativado;
        }

        public void animar() {
            if (suavizar) {
                // Movimento suave (fake)
                y += (150 - y) * 0.05f;
            } else {
                y += (200 - y) * 0.1f;
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            // Suavização visual
            if (suavizar) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
            }

            // Desenho da "mira fake"
            g2.setColor(Color.WHITE);
            g2.fillOval(getWidth() / 2 - 6, (int) y - 6, 12, 12);

            // Texto de status
            g2.setFont(new Font("Arial", Font.PLAIN, 14));
            g2.drawString(
                    suavizar ? "Suavização ATIVADA" : "Suavização DESATIVADA",
                    10,
                    20
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PainelSuave().setVisible(true);
        });
    }
}