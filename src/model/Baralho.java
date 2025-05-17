package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Baralho {
    
    ArrayList<Carta> baralho = new ArrayList();
    int posicao = 0; 

    public Baralho() {
    
     ArrayList<String> naipe = new ArrayList<>(Arrays.asList("diamonds", "hearts", "clubs", "spades"));
    
     for(int i =0; i < naipe.size(); i++) {
         
        for(int j = 1; j <14; j++) {
           this.baralho.add(new Carta(j, naipe.get(i)));
        }
     }
       
    };

    public ArrayList<Carta> getBaralho() {
        return baralho;
    }
    
    
    public ArrayList<Carta> embaralhar() {
        Collections.shuffle(baralho);
        System.out.println(baralho);
        return baralho;
    }
    
    public int size() {
    return baralho.size();
    }
    
     public Carta getUltimaCarta() {
         posicao++;
        return this.baralho.get(this.size()-posicao);
        
    }
    

   
}
