package pkg21.view;

import java.awt.*;
import javax.swing.*;

public class CriarConta extends PanelBackground {

    public CriarConta(Start frame) {
        setLayout(new GridBagLayout());

        JPanel painelRegistro = new JPanel();
        painelRegistro.setLayout(new BoxLayout(painelRegistro, BoxLayout.Y_AXIS));
        painelRegistro.setOpaque(false);

        // Campos
        JTextField nomeField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JPasswordField senhaField = new JPasswordField(15);

        painelRegistro.add(new JLabel("Nome completo:"));
        painelRegistro.add(nomeField);

        painelRegistro.add(new JLabel("E-mail:"));
        painelRegistro.add(emailField);

        painelRegistro.add(new JLabel("Senha:"));
        painelRegistro.add(senhaField);

        // Botões
        JButton btnEnviar = new JButton("Enviar");
        JButton btnVoltar = new JButton("Voltar");

        JPanel botoesPanel = new JPanel(new FlowLayout());
        botoesPanel.setOpaque(false);
        botoesPanel.add(btnEnviar);
        botoesPanel.add(btnVoltar);

        painelRegistro.add(Box.createRigidArea(new Dimension(0, 10)));
        painelRegistro.add(botoesPanel);

        // Centraliza o painelRegistro
        add(painelRegistro, new GridBagConstraints());

        // Torna os labels brancos
        for (Component c : painelRegistro.getComponents()) {
            if (c instanceof JLabel) {
                c.setForeground(Color.WHITE);
            }
        }
        
         // Ação do botão Voltar
         btnVoltar.addActionListener(e -> frame.mostrarTela("telaInicial"));
    }
}
