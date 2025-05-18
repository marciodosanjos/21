package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Baralho {
    
    ArrayList<Carta> baralho = new ArrayList();
    int posicao = 1; 

    public Baralho() {
    
     ArrayList<String> naipe = new ArrayList<>(Arrays.asList("diamonds", "hearts", "clubs", "spades"));
    
     for(int i =0; i < naipe.size(); i++) {
         
        for(int j = 1; j <14; j++) {
           
            if(j==1) {
            this.baralho.add(new CartaAs(1, naipe.get(i)));
            }
            if(j>1 && j<10) {
            this.baralho.add(new Carta(j, naipe.get(i)));
            }
            
            if(j==10) {
            this.baralho.add(new CartaFace(j, naipe.get(i), "jack"));
            }
            
            if(j==11) {
            this.baralho.add(new CartaFace(j, naipe.get(i), "queen"));
            }
            
             if(j==12) {
            this.baralho.add(new CartaFace(j, naipe.get(i), "king"));
            }
            
            
        }
     }
       
    };

    public ArrayList<Carta> getBaralho() {

        return baralho;
    }
    
    
    public ArrayList<Carta> embaralhar() {
       // System.out.println("antes " + baralho);

        Collections.shuffle(baralho);
        /* baralho.forEach(carta -> {
            if(carta.getPontos()> 10) {
            System.out.println(carta.getImagemPath());
            }
        });*/
        System.out.println("depois " + baralho);
        return baralho;
    }
    
    public int size() {
    return baralho.size();
    }
    
    public Carta getUltimaCarta() {
        int ultimaCartaIndex = this.size();
        Carta ultimaCarta = this.baralho.get(ultimaCartaIndex-posicao);
        //System.out.println("posicao antes " + posicao);
        posicao++;
        //System.out.println("posicao depois " + posicao);

        return ultimaCarta;
        
    }
    

   
}
