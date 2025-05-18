package pkg21.view;
import javax.swing.*;
import java.awt.*;

public class Start extends JFrame {
    CardLayout cardLayout;
    JPanel container;

    public Start() {
        super("Tela inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        
       //todas as telas do jogo aqui
        container.add(new TelaInicial(this), "telaInicial");
        container.add(new CriarConta(this), "criarConta");
        container.add(new JogoView(this), "jogo");
        container.add(new Rank(this), "Rank");
        container.add(new LoginView(this), "Login");


        //mostra a tela
        setContentPane(container);
        setVisible(true);
    }
    
    //controla a exibição da tela
    public void mostrarTela(String nometela) {
        cardLayout.show(container, nometela);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Start::new);
    }
}
