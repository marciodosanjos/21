
package model;


public class Jogo {
    
    Baralho baralho = new Baralho();
    int pontuacao = 0;
    
    public Jogo() {
        
        baralho.embaralhar();
    
    }
    
    
    public Carta comprarCarta() {
       
        return baralho.getUltimaCarta();
        
    
    }
    
    
}
