package model;

public class Carta {
    private final String naipe;
    private final int valor;
    private String face;
    private Boolean isFace = false;

  
    public Carta(int valor, String naipe, String... extras) {
        this.naipe = naipe;
        this.valor = valor;
        
        if(extras.length >0) {
           this.isFace = true;
           this.face = extras[0];
        }
    }

    public String getImagemPath() {
        
        if(isFace) {
          return "/resources/" + this.getFace() + "_of_" + this.getNaipe() + ".png";

        }
        return "/resources/" + this.getValor() + "_of_" + this.getNaipe() + ".png";
    }
    
    public int getValor() {
        return this.valor;
    }
    
      public String getNaipe() {
        return this.naipe;
    }
      

    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
    
      public String getFace() {
        return face;
    }
      

    public void setFace(String face) {
        this.face = face;
    }
}
