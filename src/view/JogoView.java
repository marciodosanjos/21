package view;

import controller.LoginController;
import javax.swing.*;
import java.awt.*;
import model.Carta;
import model.Jogo;

public class JogoView extends PanelBackground {

    private JLabel labelPontos;
    private JLabel labelEncerrado;
    private JLabel labelVencedor;
    private JLabel labelPerdedor;
    private JLabel labelPontosPc;
    private Jogo jogo;

    public JogoView(Start frame) {
        // Configura o layout principal como BoxLayout vertical
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Painel para as cartas viradas
        JPanel panelCartasViradas = new JPanel();
        panelCartasViradas.setOpaque(false);
        panelCartasViradas.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCartasViradas.add(new CartaVirada());
        panelCartasViradas.add(new CartaVirada());
        panelCartasViradas.add(new CartaVirada());
        panelCartasViradas.add(new CartaVirada());
        panelCartasViradas.add(new CartaVirada());

        // Painel para as cartas compradas
        JPanel panelCartasCompradas = new JPanel();
        panelCartasCompradas.setOpaque(false);
        panelCartasCompradas.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel para o resultado
        JPanel resultado = new JPanel();
        resultado.setOpaque(false);
        resultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultado.setLayout(new BoxLayout(resultado, BoxLayout.Y_AXIS));

        //nova instancia de jogo criada
        jogo = new Jogo();

        labelEncerrado = new JLabel("Jogo encerrado.");
        labelEncerrado.setFont(new Font("Arial", Font.PLAIN, 24));
        labelEncerrado.setHorizontalAlignment(SwingConstants.CENTER);
        labelEncerrado.setForeground(Color.WHITE);
        labelEncerrado.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza horizontalmente

        labelPontos = new JLabel(String.valueOf(jogo.getPontuacaoJogador()));
        labelPontos.setFont(new Font("Arial", Font.PLAIN, 24));
        labelPontos.setHorizontalAlignment(SwingConstants.CENTER);
        labelPontos.setForeground(Color.WHITE);
        labelPontos.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza horizontalmente

        resultado.add(labelPontos);

        // Painel para a navegação
        JPanel navegacao = new JPanel();
        navegacao.setOpaque(false);
        navegacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //comprar carta btn
        JButton btnComprarCarta = new JButton("Comprar");
        btnComprarCarta.setBackground(Color.white);
        btnComprarCarta.setPreferredSize(new Dimension(200, 50));
        
        //encerrar btn
        JButton btnEncerrarJogo = new JButton("Encerrar jogo");
        btnEncerrarJogo.setBackground(Color.LIGHT_GRAY);
        btnEncerrarJogo.setPreferredSize(new Dimension(200, 50));
        
        //nova rodada btn
        JButton btnNovaRodada = new JButton("Nova Rodada");
        btnNovaRodada.setBackground(Color.LIGHT_GRAY);
        btnNovaRodada.setPreferredSize(new Dimension(200, 50));
        


        btnComprarCarta.addActionListener(e -> {

            //System.out.println(this.pontuacao);
            //só consegue comprar se estiver logado
            if (!LoginController.estaLogado()) {
                JOptionPane.showMessageDialog(null, "É preciso estar logado para jogar", "Erro", JOptionPane.ERROR_MESSAGE);
                frame.mostrarTela("Login");
                return;
            }

            //comprar carta
            Carta novaCarta = jogo.comprarCarta();
            panelCartasCompradas.add(new CartaView(novaCarta.getValor(), novaCarta.getNaipe(), novaCarta.getFace()));

            // Atualizar pontuação
            int pontuacaoJogador = jogo.getPontuacaoJogador();
            labelPontos.setText(String.valueOf(jogo.getPontuacaoJogador()));

            // Verificar se o jogo foi encerrado
            boolean encerrado = jogo.getEncerrado();
            boolean vencedor = jogo.getVencedor();

            if (pontuacaoJogador > 21) {
                jogo.setEncerrado(true);
                jogo.encerrarJogo();

            }

            if (encerrado && vencedor) {
                // Substituir label de pontuação pelo de encerrado
                resultado.remove(labelPontos);
                resultado.add(labelEncerrado);
                //voce venceu
                labelVencedor = new JLabel("Você venceu o jogo com " + jogo.getPontuacaoJogador() + " pontos.");
                labelVencedor.setFont(new Font("Arial", Font.PLAIN, 24));
                labelVencedor.setHorizontalAlignment(SwingConstants.CENTER);
                labelVencedor.setForeground(Color.WHITE);
                labelVencedor.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza horizontalmente

                resultado.add(labelVencedor);

                //pontospc
                labelPontosPc = new JLabel("Pontos do PC: " + jogo.getPontuacaoPc() + " pontos.");
                labelPontosPc.setFont(new Font("Arial", Font.PLAIN, 20));
                labelPontosPc.setHorizontalAlignment(SwingConstants.CENTER);
                labelPontosPc.setForeground(Color.LIGHT_GRAY);
                labelPontosPc.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza horizontalmente

                resultado.add(labelPontosPc);

                resultado.revalidate();
                resultado.repaint();

                // Desativa o botão de comprar
                btnComprarCarta.setEnabled(false);
            }

            if (encerrado && !vencedor) {
                // Substituir label de pontuação pelo de encerrado
                resultado.remove(labelPontos);
                resultado.add(labelEncerrado);
                //voce perdeu
                labelPerdedor = new JLabel("Você perdeu o jogo com " + jogo.getPontuacaoJogador() + " pontos.");
                labelPerdedor.setFont(new Font("Arial", Font.PLAIN, 24));
                labelPerdedor.setHorizontalAlignment(SwingConstants.CENTER);
                labelPerdedor.setForeground(Color.WHITE);
                labelPerdedor.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza horizontalmente

                resultado.add(labelPerdedor);

                //pontospc
                labelPontosPc = new JLabel("Pontos do PC: " + jogo.getPontuacaoPc() + " pontos.");
                labelPontosPc.setFont(new Font("Arial", Font.PLAIN, 20));
                labelPontosPc.setHorizontalAlignment(SwingConstants.CENTER);
                labelPontosPc.setForeground(Color.LIGHT_GRAY);
                labelPontosPc.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza horizontalmente

                resultado.add(labelPontosPc);

                resultado.revalidate();
                resultado.repaint();

                // Desativa o botão de comprar
                btnComprarCarta.setEnabled(false);
                btnEncerrarJogo.setEnabled(false);

            }

            // Repaint o painel para mostrar a nova carta
            panelCartasCompradas.revalidate();
            panelCartasCompradas.repaint();
        });

        btnEncerrarJogo.addActionListener(e -> {
            //encerrar jogo
            jogo.setEncerrado(true);
            jogo.encerrarJogo();

            //verificar se jogador venceu
            boolean vencedor = jogo.getVencedor();

            if (vencedor) {
                // Substituir label de pontuação pelo de encerrado
                resultado.remove(labelPontos);
                resultado.add(labelEncerrado);
                //voce venceu
                labelVencedor = new JLabel("Você venceu o jogo com " + jogo.getPontuacaoJogador() + " pontos.");
                labelVencedor.setFont(new Font("Arial", Font.PLAIN, 24));
                labelVencedor.setHorizontalAlignment(SwingConstants.CENTER);
                labelVencedor.setForeground(Color.WHITE);
                labelVencedor.setAlignmentX(Component.CENTER_ALIGNMENT);

                resultado.add(labelVencedor);

                //pontospc
                labelPontosPc = new JLabel("Pontos do PC: " + jogo.getPontuacaoPc() + " pontos.");
                labelPontosPc.setFont(new Font("Arial", Font.PLAIN, 20));
                labelPontosPc.setHorizontalAlignment(SwingConstants.CENTER);
                labelPontosPc.setForeground(Color.LIGHT_GRAY);
                labelPontosPc.setAlignmentX(Component.CENTER_ALIGNMENT);

                resultado.add(labelPontosPc);

                resultado.revalidate();
                resultado.repaint();

                // Desativa o botão de comprar
                btnComprarCarta.setEnabled(false);
                btnEncerrarJogo.setEnabled(false);
            }

            if (!vencedor) {
                // Substituir label de pontuação pelo de encerrado
                resultado.remove(labelPontos);
                resultado.add(labelEncerrado);

                //voce perdeu
                labelPerdedor = new JLabel("Você perdeu o jogo com " + jogo.getPontuacaoJogador() + " pontos.");
                labelPerdedor.setFont(new Font("Arial", Font.PLAIN, 24));
                labelPerdedor.setHorizontalAlignment(SwingConstants.CENTER);
                labelPerdedor.setForeground(Color.WHITE);
                labelPerdedor.setAlignmentX(Component.CENTER_ALIGNMENT);

                resultado.add(labelPerdedor);

                //pontospc
                labelPontosPc = new JLabel("Pontos do PC: " + jogo.getPontuacaoPc() + " pontos.");
                labelPontosPc.setFont(new Font("Arial", Font.PLAIN, 20));
                labelPontosPc.setHorizontalAlignment(SwingConstants.CENTER);
                labelPontosPc.setForeground(Color.LIGHT_GRAY);
                labelPontosPc.setAlignmentX(Component.CENTER_ALIGNMENT);

                resultado.add(labelPontosPc);

                resultado.revalidate();
                resultado.repaint();

                // Desativa o botão
                btnComprarCarta.setEnabled(false);
                btnEncerrarJogo.setEnabled(false);
            }

            // Repaint o painel para mostrar a nova carta
            panelCartasCompradas.revalidate();
            panelCartasCompradas.repaint();

        });

        
        btnNovaRodada.addActionListener(e-> {
         
         panelCartasCompradas.removeAll();
         panelCartasCompradas.revalidate();
         panelCartasCompradas.repaint();
         resultado.removeAll();
         resultado.revalidate();
         resultado.repaint();
         // reativa o botão
         btnComprarCarta.setEnabled(true);
         btnEncerrarJogo.setEnabled(true);
         jogo.novaRodada();
          labelPontos.setText(String.valueOf(jogo.getPontuacaoJogador()));
         resultado.add(labelPontos);
         jogo = new Jogo();
        
        });
        
        navegacao.add(btnComprarCarta);
        navegacao.add(btnEncerrarJogo);
        navegacao.add(btnNovaRodada);

        // Adiciona os painéis ao painel principal
        add(panelCartasViradas);
        add(Box.createVerticalStrut(10)); // Espaçamento entre os painéis
        add(panelCartasCompradas);
        add(Box.createVerticalStrut(10));
        add(resultado);
        add(Box.createVerticalStrut(10));
        add(navegacao);
    }

}
