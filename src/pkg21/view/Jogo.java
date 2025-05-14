package pkg21.view;
import javax.swing.*;
import java.awt.*;

public class Jogo extends PanelBackground {

    public Jogo(Start frame) {
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
        panelCartasCompradas.add(new Carta());
        panelCartasCompradas.add(new Carta());
        
    
        // Painel para o resultado
        JPanel resultado = new JPanel();
        resultado.setOpaque(false);
        resultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        // Painel para a navegação
        JPanel navegacao = new JPanel();
        navegacao.setOpaque(false);
        navegacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnComprarCarta = new JButton("Comprar");
        btnComprarCarta.setBackground(Color.white);
        btnComprarCarta.setPreferredSize(new Dimension(200,50));
        
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
