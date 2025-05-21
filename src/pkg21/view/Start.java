package pkg21.view;

import control.LoginController;
import javax.swing.*;
import java.awt.*;

public class Start extends JFrame {

    CardLayout cardLayout;
    JPanel container;
    JPanel topPanel; // Painel para o topo
    private boolean logado = false; // Exemplo de estado de login
    // Não precisamos mais de 'currentRightPanel' se BarraLogadoView for o único componente à direita

    public Start() {
        super("Tela inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // --- Painel Superior ---
        topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY); // Cinza claro
        topPanel.setPreferredSize(new Dimension(getWidth(), 40)); // 100px de altura
        topPanel.setLayout(new BorderLayout()); // Usa BorderLayout para o topPanel

        // Painel para o link "Ver Rank" (esquerda)
        JPanel rankPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rankPanel.setOpaque(false);
        JButton rankLink = new JButton("Ver Rank");
        rankLink.setOpaque(false);
        rankLink.setContentAreaFilled(false);
        rankLink.setBorderPainted(false);
        rankLink.setForeground(Color.BLACK);
        rankLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rankLink.addActionListener(e -> mostrarTela("Rank"));
        rankPanel.add(rankLink);
        topPanel.add(rankPanel, BorderLayout.WEST);

        //login
        boolean estaLogado = LoginController.estaLogado();
        this.setLogado(estaLogado);
        JPanel painelLogin = new BarraLogadoView(this.logado);
        painelLogin.setBackground(Color.LIGHT_GRAY);
        topPanel.add(painelLogin, BorderLayout.EAST);

        // --- Container Principal (onde as telas do jogo aparecerão) ---
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        container.add(new TelaInicial(this), "telaInicial");
        container.add(new CriarConta(this), "criarConta");
        container.add(new JogoView(this), "jogo");
        container.add(new Rank(this), "Rank");
        container.add(new LoginView(this), "Login");

        add(topPanel, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);
        setVisible(true);
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    // controla a exibição da tela
    public void mostrarTela(String nometela) {
        cardLayout.show(container, nometela);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Start::new);
    }
}
