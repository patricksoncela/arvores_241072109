package com.company;

public class No {
    String valor;
    No esquerda, direita;
    int altura;

    public No(String valor){
        this.valor = valor;
        esquerda = direita = null;
        this.altura = 1;
    }


}
