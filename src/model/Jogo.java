
package model;
import java.util.Random;

public class Jogo {
    
    Baralho baralho = new Baralho();
    int pontuacao = 0;
    Boolean encerrado = false;
    Boolean eVencedor = false;
    
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
    
    
    
    public Boolean eVencedor() {
          
        Random random = new Random();
        
        int min = 15;
        int max = 21;
        int pontoPc = random.nextInt((max-min)+1) + min; 
        
        if(pontoPc > this.getPontuacao()) {
          this.eVencedor = false;
          return false;
        }
        
        this.eVencedor = true;
        return true;

         
    }

  

    public void setVencedor(Boolean eVencedor) {
        this.eVencedor = eVencedor;
    }
    
     public Boolean getVencedor() {
        return this.eVencedor;
    }
    
    
    
    
    public Boolean encerrarJogo() {
        
        //System.out.println("A pontuacao é " + getPontuacao());
        
        if(this.getPontuacao()>21) {
          System.out.println("A pontuacao é maior que 21");
          this.encerrado = true; 
          this.eVencedor = false;
          return encerrado;
        }
        
       
        
        return false;
        
    
    }
    
   
}
