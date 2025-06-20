package com.company.arvorerb;

import com.company.No;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ArvoreRB {
    NoRB raiz;

    public void inserir(int valor) {
        NoRB no = new NoRB(valor);
        if (raiz  == null){
            raiz = no;
            raiz.cor = Cores.preto;
        } else {
            inserirRecursivo(raiz, no);
            balancear(no);
        }
    }

    public void inserirRecursivo(NoRB atual, NoRB novo){
        if (novo.valor < atual.valor){
            if (atual.esquerda == null){
                atual.esquerda = novo;
                novo.pai = atual;
            } else {
                inserirRecursivo(atual.esquerda, novo);
            }
        } else {
            if (atual.direita == null){
                atual.direita = novo;
                novo.pai =atual;
            } else {
                inserirRecursivo(atual.direita,novo);
            }
        }
    }

    private void balancear(NoRB no){
        while (no != raiz && no.pai.cor ==  Cores.vermelho){
            if (no.pai == no.pai.pai.esquerda){
                NoRB avo = no.pai.pai;
                if (no == no.pai.direita){
                    no = no.pai;
                    //rotacaoAesquerda(no)
                }
                //PAREI AQUI . NÃO FUNCIONA
            }
        }
    }

    public int contarNos(NoRB node){
        if (node == null) {
            return 0;
        }
        return 1 + contarNos(node.esquerda) + contarNos(node.direita);
    }

    public void percorrerPreOrdem(NoRB node){
        if (node != null){
            System.out.print(node.valor + " ");
            percorrerPreOrdem(node.esquerda);
            percorrerPreOrdem(node.direita);
        }
    }


    public void preOrdemSemRecursividade() {
        if (raiz == null) return;
        Stack<NoRB> pilha = new Stack<>();
        pilha.push(raiz);
        while (!pilha.isEmpty()) {
            NoRB atual = pilha.pop();
            System.out.println(atual.valor);

            if (atual.direita != null) {
                pilha.push(atual.direita);
            }

            if (atual.esquerda != null) {
                pilha.push(atual.esquerda);
            }
        }
    }

    public void percorrerEmOrdem(NoRB node){
        if (node != null){
            percorrerPreOrdem(node.esquerda);
            System.out.print(node.valor + " ");
            percorrerPreOrdem(node.direita);
        }
    }

    public void emOrdemSemRecursividade(){
        Stack<NoRB> pilha = new Stack<>();
        NoRB atual = raiz.esquerda;

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

    public void percorrerPosOrdem(NoRB node){
        if (node != null){
            percorrerPosOrdem(raiz.esquerda);
            percorrerPosOrdem(raiz.direita);
            System.out.println(raiz.valor + " ");
        }
    }

    public void posOrdemSemRecursividade(){
        Stack<NoRB> pilha = new Stack<>();
        NoRB atual = raiz;
        NoRB ultimo =null;

        while (atual != null || !pilha.isEmpty()){
            while (atual != null){
                pilha.push(atual);
                atual = atual.esquerda;
            }
            NoRB topo = pilha.peek();
            if (topo.direita == null || topo.direita == ultimo){
                System.out.println(topo.valor + "");
                ultimo = pilha.pop();
                atual = null;
            } else {
                atual = topo.direita;
            }
        }
    }

    public int contarNosFolha(NoRB raiz){
        Queue<NoRB> fila = new LinkedList<>();
        int contador = 0;
        fila.add(raiz);
        while (!fila.isEmpty()){
            NoRB atual = fila.poll();
            if (atual.esquerda == null && atual.direita == null){
                contador ++;
            }
            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }
        return contador;
    }

    public int contarNosFila(NoRB raiz){
        Queue<NoRB> fila = new LinkedList<>();
        fila.add(raiz);
        int contador = 0;
        while (!fila.isEmpty()){
            NoRB atual = fila.poll();
            contador ++;
            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }
        return contador;
    }

    public int contarNosPilha(){
        if (raiz == null) return 0;

        Stack<NoRB> pilha = new Stack<>();
        pilha.push(raiz);

        int contador = 0;

        while (!pilha.isEmpty()){
            NoRB atual = pilha.pop();
            contador++;

            if (atual.direita != null) pilha.push(atual.direita);
            if (atual.esquerda != null) pilha.push(atual.esquerda);
        }
        return contador;
    }
}
