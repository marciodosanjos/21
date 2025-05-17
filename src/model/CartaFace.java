package model;
public class CartaFace extends Carta {
    
    private String face; 
    
      public CartaFace(int valor, String naipe, String face) {
          super(valor, naipe);
          this.face = face;

      }
      
      @Override
      public String getImagemPath() {
        
        return "/resources/" + this.getFace() + "_of_" + super.getNaipe() + ".png";
        }

    @Override
    public String toString() {
        return "CartaFace{" + "face=" + face + '}';
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getFace() {
        return face;
    }
    
}
