package com.company;

public class No {
    int valor;
    No esquerda, direita;
    int altura;

    public No(int valor){
        this.valor = valor;
        esquerda = direita = null;
        this.altura = 1;
    }
}
