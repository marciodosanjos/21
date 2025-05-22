package pkg21.view;

import control.LoginController;
import javax.swing.*;
import java.awt.*;

public class TelaInicial extends PanelBackground {

    public TelaInicial(Start frame) {

        // Usando GridBagLayout para centralizar os componentes
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Criando o JLabel
        JLabel label = new JLabel("Blackjack 21");
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);

        // Criando os botões
        JButton btnJogar = new JButton("Jogar");
        btnJogar.setBackground(Color.white);
        btnJogar.setPreferredSize(new Dimension(200, 50));

        JButton btnRegistrar = new JButton("Criar conta");
        btnRegistrar.setBackground(Color.LIGHT_GRAY);
        btnRegistrar.setPreferredSize(new Dimension(200, 50));

        //abrir tela jogo
        btnJogar.addActionListener(e -> {
            
            Boolean logado = LoginController.estaLogado();
            
            if(!logado) {
            System.out.println("Não estou logado");
            JOptionPane.showMessageDialog(null, "Faça o login ou registre-se para jogar", "Erro", JOptionPane.ERROR_MESSAGE);
            frame.mostrarTela("Login");
            return;
            }
            
            
            frame.mostrarTela("jogo");

            
          
        });

        // abrir tela de criar conta
        btnRegistrar.addActionListener(e -> {
            frame.mostrarTela("criarConta");
            System.out.println("Tela de criar conta aberta");
        });

        // Configuração de GridBagConstraints para centralizar o JLabel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(label, gbc);

        // Configuração de GridBagConstraints para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));  // Centraliza os botões
        painelBotoes.add(btnJogar);
        painelBotoes.add(btnRegistrar);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(painelBotoes, gbc);

    }
}
