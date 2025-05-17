package pkg21.view;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import model.Carta;
import model.CartaAs;
import model.CartaFace;

public class CartaView extends JLabel {
    
    public CartaView(int valor, String naipe, String... extras) {
    setHorizontalAlignment(SwingConstants.CENTER);
    setVerticalAlignment(SwingConstants.CENTER);
    setOpaque(false);
    
    //instancia nova carta
    if(valor==1) {
     CartaAs cartaAs = new CartaAs(valor, naipe);
     ImageIcon iconeOriginal = new ImageIcon(getClass().getResource(cartaAs.getImagemPath()));
     if (iconeOriginal.getImage() != null) {
            Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(50, 80, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(imagemRedimensionada));
        } else {
            System.err.println("Imagem não encontrada");
        }
    }
    
    if(valor > 1 && valor < 10) {
    Carta carta = new Carta(valor, naipe);

    ImageIcon iconeOriginal = new ImageIcon(getClass().getResource(carta.getImagemPath()));
    if (iconeOriginal.getImage() != null) {
            Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(50, 80, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(imagemRedimensionada));
        } else {
            System.err.println("Imagem não encontrada");
        }

    }
    
    if (valor > 10 && extras.length > 0) {
       
       String face = extras[0];
       CartaFace cartaFace = new CartaFace(valor, naipe, face);

       ImageIcon iconeOriginal = new ImageIcon(getClass().getResource(cartaFace.getImagemPath()));
       if (iconeOriginal.getImage() != null) {
            Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(50, 80, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(imagemRedimensionada));
        } else {
            System.err.println("Imagem não encontrada");
        }
    } 
        
    }
    
    
   
    
    
    
}
