package view;

import controller.LoginController;
import javax.swing.*;
import java.awt.*;

public class Start extends JFrame {

    CardLayout cardLayout;
    JPanel container;
    JPanel topPanel; 
    private boolean logado = false;
    private BarraLogadoView barraLogadoView;

    public Start() {
        super("Tela inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // --- Painel Superior ---
        topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY); // Cinza claro
        topPanel.setPreferredSize(new Dimension(getWidth(), 40)); // 40px de altura
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

        // Inicializa a barra de login com o estado atual DO CONTROLADOR
        this.logado = LoginController.estaLogado(); 
        
        // Cria a primeira instância da BarraLogadoView
        barraLogadoView = new BarraLogadoView(this.logado, this);
        barraLogadoView.setBackground(Color.LIGHT_GRAY);
        topPanel.add(barraLogadoView, BorderLayout.EAST);

        // --- Container Principal (onde as telas do jogo aparecerão) ---
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        container.add(new TelaInicial(this), "telaInicial");
        container.add(new CriarConta(this), "criarConta");
        container.add(new JogoView(this), "jogo");
        container.add(new Rank(this), "Rank");
        // Garanta que o LoginView também receba 'this' (a instância de Start)
        container.add(new LoginView(this), "Login"); 

        add(topPanel, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);
        setVisible(true);
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
        atualizarBarraLogin(); 
    }

    private void atualizarBarraLogin() {
        topPanel.remove(barraLogadoView);
        
        barraLogadoView = new BarraLogadoView(this.logado, this);
        barraLogadoView.setBackground(Color.LIGHT_GRAY);
        
        topPanel.add(barraLogadoView, BorderLayout.EAST);
        
        topPanel.revalidate();
        topPanel.repaint();
    }

    // controla a exibição da tela
    public void mostrarTela(String nometela) {
        cardLayout.show(container, nometela);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Start::new);
    }
}