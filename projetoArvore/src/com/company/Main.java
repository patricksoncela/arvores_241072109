package com.company;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
        No no1 = new No("A");
        No no2 = new No("B");
        No no3 = new No("C");
        No no4 = new No("D");
        No no5 = new No("E");
        No no6 = new No("F");


        no1.esquerda = no2;
        no1.direita = no3;
        no2.esquerda = no4;
        no2.direita = no5;
        no3.direita = no6;

        Arvore arvore  = new Arvore();
        arvore.raiz = no1;

        System.out.println("O total de nós na árvore é: " + arvore.contarNos(arvore.raiz));
        System.out.print("Percorrer em pré-ordem: ");
        arvore.percorrerPreOrdem(arvore.raiz);
        System.out.println("");
        System.out.print("Percorrer em ordem: ");
        arvore.percorrerEmOrdem(arvore.raiz);
    }

}
