package com.company;

import java.util.Stack;

public class Arvore {
    No raiz;

    public int contarNos(No node){
        if (node == null) {
            return 0;
        }
        return 1 + contarNos(node.esquerda) + contarNos(node.direita);
    }

    public void percorrerPreOrdem(No node){
        if (node != null){
            System.out.print(node.valor + " ");
            percorrerPreOrdem(node.esquerda);
            percorrerPreOrdem(node.direita);
        }
    }


    public void preOrdemSemRecursividade(){
            Stack<No> pilha = new Stack<>();
            pilha.push(raiz);
            while (!pilha.isEmpty());
                No atual = pilha.pop();
                System.out.println(atual.valor);

                if (atual.direita != null){
                    pilha.push(atual.direita);
                }

                if (atual.esquerda != null){
                    pilha.push(atual.esquerda);
                }
            }

    public void percorrerEmOrdem(No node){
        if (node != null){
            percorrerPreOrdem(node.esquerda);
            System.out.print(node.valor + " ");
            percorrerPreOrdem(node.direita);
        }
    }

    public void emOrdemSemRecursividade(){
        Stack<No> pilha = new Stack<>();
        while(!pilha.isEmpty());
        No atual = raiz.esquerda;

        if (atual.direita != null){
            pilha.push(atual.direita);
        }


    }

}


