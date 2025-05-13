/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg21.view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

   
public class PanelBackground extends JPanel {
    private Image imagemFundo;

    public PanelBackground() {
        // Carrega a imagem
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/mesa.jpg"));
        imagemFundo = icon.getImage();

        // Layout do painel
        setLayout(new GridLayout(4, 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
    }
}

