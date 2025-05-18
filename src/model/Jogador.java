
package model;

public class Jogador extends Pessoa {
    
    private int pontos;
    
    public Jogador(String nome, String email, String senha, int pontos) {
        super(nome, email, senha);
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }
    
    public void setPontos(int pontos) {
    this.pontos = pontos;
    }

    
     @Override
    public String toString() {
        return "Jogador{" + "pontos=" + pontos + '}';
    }
    
}
