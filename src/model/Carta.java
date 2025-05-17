package model;

public class Carta {
    private final String naipe;
    private final int valor;

    public Carta(int valor, String naipe) {
        this.naipe = naipe;
        this.valor = valor;
    }

    public String getImagemPath() {
        return "/resources/" + this.valor + "_of_" + this.naipe + ".png";
    }
    
    public int getPontos() {
        return this.valor;
    }
    
      public String getNaipe() {
        return this.naipe;
    }
      

    @Override
    public String toString() {
        return valor + " de " + naipe;
    }

    // getters e setters 
}
