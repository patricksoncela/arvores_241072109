package com.company;

import com.company.arvorerb.ArvoreRB;

import java.sql.SQLOutput;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        No no1 = new No(1);
        No no2 = new No(2);
        No no3 = new No(3);
        No no4 = new No(4);
        No no5 = new No(5);
        No no6 = new No(6);

        int[] valores = {10,20,30,15,5,25};



        no1.esquerda = no2;
        no1.direita = no3;
        no2.esquerda = no4;
        no2.direita = no5;
        no3.direita = no6;

        Arvore arvore  = new Arvore();
        ArvoreAVL arvore1 = new ArvoreAVL();
        arvore.raiz = no1;

        System.out.println("O total de nós na árvore é: " + arvore.contarNos(arvore.raiz));
        System.out.print("Percorrer em pré-ordem: ");
        arvore.percorrerPreOrdem(arvore.raiz);
        System.out.print("Percorrer em ordem: ");
        arvore.percorrerEmOrdem(arvore.raiz);

        arvore.preOrdemSemRecursividade();
        arvore.emOrdemSemRecursividade();
        arvore.posOrdemSemRecursividade();

        for (int valor : valores){
            arvore.raiz =  arvore1.inserir(arvore.raiz, valor);
        }

        ArvoreRB tree = new ArvoreRB();

    }

}
