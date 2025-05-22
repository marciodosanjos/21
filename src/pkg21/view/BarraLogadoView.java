package pkg21.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BarraLogadoView extends JPanel {

    // Painel para os botões Login e Logout (direita)
    public BarraLogadoView(Boolean logado, Start frame) {

        if (logado) {
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rightPanel.setOpaque(false); // Torna o painel transparente
            JButton logoutButton = new JButton("Logout");
            logoutButton.setBackground(Color.LIGHT_GRAY);
            rightPanel.add(logoutButton);
            add(logoutButton);
        }

        if (!logado) {
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rightPanel.setOpaque(false); // Torna o painel transparente
            JButton loginButton = new JButton("Login");
            loginButton.setBackground(Color.LIGHT_GRAY);
            
            loginButton.addActionListener(e -> {   
            frame.mostrarTela("Login");
                     
            });
            
            
            rightPanel.add(loginButton);
            add(loginButton);
        }

    }

}
