package pkg21.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BarraLogadoView extends JPanel {

    public BarraLogadoView(Boolean logado) {

        if (logado) {
            // Painel para os botões Login e Logout (direita)
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rightPanel.setOpaque(false); // Torna o painel transparente
            JButton logoutButton = new JButton("Logout");
            logoutButton.setBackground(Color.LIGHT_GRAY);
            rightPanel.add(logoutButton);
            add(logoutButton);
        }

        if (!logado) {
            // Painel para os botões Login e Logout (direita)
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rightPanel.setOpaque(false); // Torna o painel transparente
            JButton loginButton = new JButton("Login");
            loginButton.setBackground(Color.LIGHT_GRAY);
            rightPanel.add(loginButton);
            add(loginButton);
        }

    }

}
