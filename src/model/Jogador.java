
package model;

public class Jogador extends Pessoa {
    
    private int pontos;
    
    public Jogador(String nome, String sobrenome, String email, String senha, int pontos) {
        super(nome, sobrenome, email, senha);
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
