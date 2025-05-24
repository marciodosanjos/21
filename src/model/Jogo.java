package model;

import java.util.Random;

public class Jogo {

    Baralho baralho = new Baralho();
    int pontuacaoJogador = 0;
    int pontuacaoPc = 0;
    Boolean encerrado = false;
    Boolean vencedor = false;

    public Jogo() {
        baralho.embaralhar();
    }

    public Carta comprarCarta() {

        Carta ultimaCarta = baralho.getUltimaCarta();
        this.pontuacaoJogador += ultimaCarta.getValor();

        if (this.pontuacaoJogador > 21) {

            this.setEncerrado(true);
        }

        return ultimaCarta;

    }

    public int getPontuacaoJogador() {
        return pontuacaoJogador;
    }

    public void setPontuacaoJogador(int pontuacao) {
        this.pontuacaoJogador = pontuacao;
    }

    public void setVencedor(Boolean vencedor) {
        this.vencedor = vencedor;
    }

    public Boolean getVencedor() {
        return this.vencedor;
    }

    public int getPontuacaoPc() {
        return pontuacaoPc;
    }

    public void setPontuacaoPc(int pontuacaoPc) {
        this.pontuacaoPc = pontuacaoPc;
    }
    

    public Boolean getEncerrado() {
        System.out.println(this.encerrado);

        if (this.encerrado) {
            System.out.println("A pontuacao é maior que 21");

        }
        return encerrado;
    }

    public Boolean setEncerrado(Boolean encerrado) {
        this.encerrado = true;
        return encerrado;
    }

    public Boolean encerrarJogo() {
        Random random = new Random();
        int min = 14;
        int max = 20;
        int pontosPc = random.nextInt(max - min + 1) + min;
        this.setPontuacaoPc(pontosPc);

        //pontuacao maior que 21. Jogador perde
        if (this.getPontuacaoPc() > 21) {
            System.out.println("A pontuacao é maior que 21. Você perdeu");
            this.setEncerrado(true);
            this.setVencedor(false);
            return encerrado;
        }

        //pontuacao menor que a do pc. Jogador perde
        if (this.getPontuacaoJogador() > pontosPc) {

            System.out.println("Sua pontuacao foi maior do que a do PC. Você ganhou. Pontos pc: " + pontosPc);
            this.setEncerrado(true);
            this.setVencedor(true);
            return encerrado;
        }

        
         //pontuacao menor que a do pc. Jogador perde
        if (this.getPontuacaoJogador() < pontosPc) {

            System.out.println("Sua pontuacao foi menor do que a do PC. Você perdeu. pontos pc: " + pontosPc);
            this.setEncerrado(true);
            this.setVencedor(false);
            return encerrado;
        }

        return false;

    }

    
    public Boolean novaRodada() {
    
        this.setEncerrado(false);
        this.setPontuacaoJogador(0);
        this.setPontuacaoPc(0);
        this.setVencedor(false);
        this.setEncerrado(false);
        new Jogo();
        
        return true;
    }
}
