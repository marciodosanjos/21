
package model;


public class Jogo {
    
    Baralho baralho = new Baralho();
    int pontuacao = 0;
    Boolean encerrado = false;
    
    public Jogo() {
        baralho.embaralhar();
    }
    
    public Carta comprarCarta() {
       
        Carta ultimaCarta = baralho.getUltimaCarta();
        this.pontuacao += ultimaCarta.getValor(); 
        
        return ultimaCarta;
        
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    public Boolean encerrarJogo() {
        
        //System.out.println("A pontuacao é " + getPontuacao());
        
        if(this.getPontuacao()>21) {
          System.out.println("A pontuacao é maior que 21");
          this.encerrado = true; 
          System.out.println("Jogo encerrado? " + encerrado);

          return encerrado;
        }
        
        return false;
        
    
    }
    
   
}
