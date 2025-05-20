package com.company;

public class Arvore {
    No raiz;

    public int contarNos(No node){
        if (node == null) {
            return 0;
        }
        return 1 + contarNos(node.esquerda) + contarNos(node.direita);
    }

    public void percorrerPreOrdem(No node){
        if (node == null) {
            System.out.println("Árvore vazia");
        } else {
            System.out.println(node.valor + " ");
            System.out.println(node.esquerda);
            System.out.println(node.direita);
        }
    }

    public void percorrerEmOrdem(No node){

    }

}


