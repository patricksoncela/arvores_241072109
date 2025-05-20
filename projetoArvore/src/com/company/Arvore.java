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
        if (node != null){
            System.out.print(node.valor + " ");
            percorrerPreOrdem(node.esquerda);
            percorrerPreOrdem(node.direita);
        }
    }

    public void percorrerEmOrdem(No node){
        if (node != null){
            percorrerPreOrdem(node.esquerda);
            System.out.print(node.valor + " ");
            percorrerPreOrdem(node.direita);
        }
    }

}


