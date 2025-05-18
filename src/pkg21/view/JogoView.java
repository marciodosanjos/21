package pkg21.view;
import javax.swing.*;
import java.awt.*;
import model.Carta;
import model.Jogo;

public class JogoView extends PanelBackground {
    private JLabel labelPontos;
    private JLabel labelEncerrado;

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
        
        //nova instancia de jogo criada
        Jogo jogo = new Jogo();
        
        // Painel para o resultado
        JPanel resultado = new JPanel();
        resultado.setOpaque(false);
        resultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelEncerrado = new JLabel("Jogo encerrado");
        labelEncerrado.setFont(new Font("Arial", Font.PLAIN, 24));  
        labelEncerrado.setHorizontalAlignment(SwingConstants.CENTER);  
        labelEncerrado.setForeground(Color.WHITE);
        
        int pontuacao = jogo.getPontuacao();
        labelPontos = new JLabel(String.valueOf(pontuacao));
        labelPontos.setFont(new Font("Arial", Font.PLAIN, 24));  
        labelPontos.setHorizontalAlignment(SwingConstants.CENTER);  
        labelPontos.setForeground(Color.WHITE);
        
        resultado.add(labelPontos);
        
        // Painel para a navegação
        JPanel navegacao = new JPanel();
        navegacao.setOpaque(false);
        navegacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnComprarCarta = new JButton("Comprar");
        btnComprarCarta.setBackground(Color.white);
        btnComprarCarta.setPreferredSize(new Dimension(200,50));

        btnComprarCarta.addActionListener(e -> {
            
            //comprar carta
            Carta novaCarta = jogo.comprarCarta();
            panelCartasCompradas.add(new CartaView(novaCarta.getValor(), novaCarta.getNaipe(), novaCarta.getFace()));

            // Atualizar pontuação
            int novaPontuacao = jogo.getPontuacao();
            labelPontos.setText(String.valueOf(novaPontuacao));
            
             // Verificar se o jogo foi encerrado
                boolean encerrado = jogo.encerrarJogo();
                if (encerrado) {
                    // Substituir label de pontuação pelo de encerrado
                    resultado.remove(labelPontos);
                    resultado.add(labelEncerrado);
                    resultado.revalidate();
                    resultado.repaint();

                    // Desativa o botão de comprar
                    btnComprarCarta.setEnabled(false);
                }
            
            // Repaint o painel para mostrar a nova carta
            panelCartasCompradas.revalidate();
            panelCartasCompradas.repaint();
        });
        
        JButton btnEncerrarJogo = new JButton("Encerrar jogo");
        btnEncerrarJogo.setBackground(Color.LIGHT_GRAY);
        btnEncerrarJogo.setPreferredSize(new Dimension(200,50));
        navegacao.add(btnComprarCarta);
        navegacao.add(btnEncerrarJogo);

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
