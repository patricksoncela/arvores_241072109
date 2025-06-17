package com.company;

import java.util.LinkedList;
import java.util.Queue;
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


    public void preOrdemSemRecursividade() {
        if (raiz == null) return;
        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);
        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            System.out.println(atual.valor);

            if (atual.direita != null) {
                pilha.push(atual.direita);
            }

            if (atual.esquerda != null) {
                pilha.push(atual.esquerda);
            }
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
        No atual = raiz.esquerda;

        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual);
                atual = atual.esquerda;
            }
            atual = pilha.pop();
            System.out.print(atual.valor + " ");
            atual = atual.direita;
        }
    }

    public void percorrerPosOrdem(No node){
        if (node != null){
            percorrerPosOrdem(raiz.esquerda);
            percorrerPosOrdem(raiz.direita);
            System.out.println(raiz.valor + " ");
        }
    }

    public void posOrdemSemRecursividade(){
        Stack<No> pilha = new Stack<>();
        No atual = raiz;
        No ultimo =null;

        while (atual != null || !pilha.isEmpty()){
            while (atual != null){
                pilha.push(atual);
                atual = atual.esquerda;
            }
            No topo = pilha.peek();
            if (topo.direita == null || topo.direita == ultimo){
                System.out.println(topo.valor + "");
                ultimo = pilha.pop();
                atual = null;
            } else {
                atual = topo.direita;
            }
        }
    }

    public int contarNosFolha(No raiz){
        Queue<No> fila = new LinkedList<>();
        int contador = 0;
        fila.add(raiz);
        while (!fila.isEmpty()){
            No atual = fila.poll();
            if (atual.esquerda == null && atual.direita == null){
                contador ++;
            }
            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }
        return contador;
    }

    public int contarNosFila(No raiz){
        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);
        int contador = 0;
        while (!fila.isEmpty()){
            No atual = fila.poll();
            contador ++;
            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }
        return contador;
    }

    public int contarNosPilha(){
        if (raiz == null) return 0;

        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);

        int contador = 0;

        while (!pilha.isEmpty()){
            No atual = pilha.pop();
            contador++;

            if (atual.direita != null) pilha.push(atual.direita);
            if (atual.esquerda != null) pilha.push(atual.esquerda);
        }
        return contador;
    }
}


