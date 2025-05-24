package view;

import controller.LoginController;
import java.awt.*;
import javax.swing.*;

public class LoginView extends PanelBackground {

    public LoginView(Start frame) {
        setLayout(new GridBagLayout());

        JPanel painelRegistro = new JPanel();
        painelRegistro.setLayout(new BoxLayout(painelRegistro, BoxLayout.Y_AXIS));
        painelRegistro.setOpaque(false);

        // Campos
        JTextField emailField = new JTextField(15);
        JPasswordField senhaField = new JPasswordField(15);

        painelRegistro.add(new JLabel("E-mail:"));
        painelRegistro.add(emailField);

        painelRegistro.add(new JLabel("Senha:"));
        painelRegistro.add(senhaField);

        // Botões de Login/Voltar
        JButton btnEnviar = new JButton("Entrar");
        JButton btnVoltar = new JButton("Voltar");

        JPanel botoesPanel = new JPanel(new FlowLayout());
        botoesPanel.setOpaque(false);
        botoesPanel.add(btnEnviar);
        botoesPanel.add(btnVoltar);

        painelRegistro.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento
        painelRegistro.add(botoesPanel);

        // Botão de Cadastro
        JPanel cadastroPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cadastroPanel.setOpaque(false);
        JButton btnCadastro = new JButton("Cadastrar");
        btnCadastro.setOpaque(false);
        btnCadastro.setContentAreaFilled(false);
        btnCadastro.setBorderPainted(false);
        btnCadastro.setForeground(Color.white);
        btnCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastro.addActionListener(e -> frame.mostrarTela("criarConta"));
        cadastroPanel.add(btnCadastro);

        // Adiciona o painel de cadastro ao painel de registro, logo abaixo dos botões
        painelRegistro.add(Box.createRigidArea(new Dimension(0, 5))); // Espaçamento entre os grupos de botões
        painelRegistro.add(cadastroPanel);


        // Centraliza o painelRegistro (que agora contém tudo)
        add(painelRegistro, new GridBagConstraints());

        // Torna os labels brancos
        for (Component c : painelRegistro.getComponents()) {
            if (c instanceof JLabel) {
                c.setForeground(Color.WHITE);
            }
        }
        
        btnEnviar.addActionListener(e-> {
            String email = emailField.getText();
            String senha = String.valueOf(senhaField.getPassword());
            
            if(email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira as informacoes necessárias para o login", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            try {
                boolean sucesso = LoginController.login(email, senha);
                
                if(sucesso) {
                    JOptionPane.showMessageDialog(null, "Usuário logado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    frame.setLogado(true);
                    frame.mostrarTela("jogo");
                }
            
            } catch(Exception ex) {
                System.err.println("Erro ao logar usuário: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao logar usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Ação do botão Voltar
        btnVoltar.addActionListener(e -> frame.mostrarTela("telaInicial"));
    }
}