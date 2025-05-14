package pkg21.view;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Carta extends JLabel {
    
    public Carta() {
    setHorizontalAlignment(SwingConstants.CENTER);
    setVerticalAlignment(SwingConstants.CENTER);
    setOpaque(false);
    
    // Carrega a imagem como recurso
    ImageIcon iconeOriginal = new ImageIcon(getClass().getResource("/resources/A_Copas.jpg"));
    if (iconeOriginal.getImage() != null) {

            Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(50, 80, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(imagemRedimensionada));
        } else {
            System.err.println("Imagem não encontrada");
        }

    }
}
