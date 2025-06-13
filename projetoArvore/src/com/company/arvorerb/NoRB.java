package com.company.arvorerb;

public class NoRB {
    int valor;
    NoRB direita,esquerda, pai;
    Cores cor;

    public NoRB(int valor){
        this.valor = valor;
        esquerda = direita = null;
        this.cor = Cores.vermelho;
    }
}
