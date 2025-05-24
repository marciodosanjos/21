package view;

import controller.UsuarioController;
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

        //criar usuario
        btnEnviar.addActionListener(e -> {

            String nome = nomeField.getText();
            String email = emailField.getText();
            String senha = String.valueOf(senhaField.getPassword());

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira as informacoes necessárias", "Erro", JOptionPane.ERROR_MESSAGE);
                return;

            }

            try {

                boolean sucesso = UsuarioController.criarUsuario(nome, email, senha, 0);

                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    frame.mostrarTela("Login");

                }

            } catch (Exception ex) {
                System.err.println("Erro ao criar usuário: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao criar usuário!", "Erro", JOptionPane.ERROR_MESSAGE);

            }

        });

        // Ação do botão Voltar
        btnVoltar.addActionListener(e -> frame.mostrarTela("telaInicial"));
    }
}
