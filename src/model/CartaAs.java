
package model;

public class CartaAs extends Carta {

        public CartaAs(int valor, String naipe){
          super(valor, naipe);
        }
        @Override
        public String getImagemPath() {
        
        return "/resources/ace_of_" + super.getNaipe() + ".png";
        }
    
    }

