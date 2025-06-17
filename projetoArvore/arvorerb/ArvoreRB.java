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
                    //??leftRotate();
                }
                //PAREI AQUI . NÃO FUNCIONA (12/06)
            }
        }
    }

    public void inserirRavache(int key){
        NoRB no= new NoRB(key);
        no.esquerda = no.direita = no.pai = null;

        NoRB y = null;
        NoRB x = raiz;

        while (x != null){
            y = x;
            if (no.valor < x.valor) x = x.esquerda;
            else x = x.direita;

        }

        no.pai = y;
        if (y == null) raiz = no;
        else if (no.valor < y.valor) y.esquerda = no;
        else y.direita = no;

        no.esquerda = null;
        no.direita = null;
        no.cor  = Cores.vermelho;

        insertFix(no);
    }

    private void insertFix(NoRB k){
        while (k.pai != null && k.pai.cor == Cores.vermelho ){
            if (k.pai == k.pai.pai.esquerda) {
                NoRB u = k.pai.pai.direita;
                if (u.cor== Cores.vermelho){
                    k.pai.cor = Cores.preto;
                    u.cor= Cores.preto;
                    k.pai.pai.cor= Cores.vermelho;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.direita){
                        k = k.pai;
                        leftRotate(k);
                    }
                    k.pai.cor = Cores.preto;
                    k.pai.pai.cor = Cores.vermelho;
                    rightRotate(k.pai.pai);
                }
            }
        }
    }

    private void transplant(NoRB u, NoRB v){
        if (u.pai == null) raiz = v;
        else if (u == u.pai.esquerda) u.pai.esquerda = v;
        else u.pai.direita = v;
        v.pai = u.pai;
    }

    private NoRB minimum(NoRB no){
        while (no.esquerda != null) no = no.esquerda;
        return no;
    }

    public void delete(int valor){
        NoRB z =  buscarArvore(raiz, valor);
        if (z == null) return;

        NoRB y = z;
        Cores yOriginalCor = y.cor;
        NoRB x;

        if(z.esquerda == null){
            x = z.direita;
            transplant(z, z.direita);
        } else {
            y = minimum(z.direita);
            yOriginalCor = y.cor;
            x = y.direita;
            if (y.pai == z) x.pai = y;
            else {
                transplant(y, y.direita);
                y.direita = z.direita;
                y.direita.pai = y;
            }
            transplant(z,y);
            y.esquerda = z.esquerda;
            y.esquerda.pai = y;
            y.cor = z.cor;
        }
    }

    private NoRB buscarArvore(NoRB no, int valor){
        if (no == null || valor == no.valor) return no;
        if (valor < no.valor) return buscarArvore(no.esquerda, valor);
        return buscarArvore(no.direita, valor);
    }

    public void inOrder(){
        inOrderHelper(raiz);
        System.out.println();
    }

    private void inOrderHelper(NoRB no){
        if (no != null){
            inOrderHelper(no.esquerda);
            String colorSufix = (no.cor == Cores.vermelho) ? "R" : "B";
            System.out.println(no.valor + colorSufix + "");
            inOrderHelper(no.direita);
        }
    }

    private void deleteFix(NoRB x){
        NoRB w;
        while (x != raiz && x.cor == Cores.preto){
            if (x == x.pai.esquerda){
                w = x.pai.direita;
                if (w.cor == Cores.vermelho){
                    w.cor =Cores.preto;
                    x.pai.cor = Cores.vermelho;
                    leftRotate(x.pai);
                    w = x.pai.direita;
                }
                if (w.esquerda.cor == Cores.preto && w.direita.cor == Cores.preto){
                    w.cor = Cores.vermelho;
                    x = x.pai;
                } else {
                    if (w.direita.cor == Cores.preto){
                        w.esquerda.cor = Cores.preto;
                        w.cor = Cores.vermelho;
                        rightRotate(w);
                        w = x.pai.direita;
                    }
                    w.cor = x.pai.cor;
                    x.pai.cor = Cores.preto;
                    w.direita.cor = Cores.preto;
                    leftRotate(x.pai);
                    x = raiz;
                }
            } else {
                w = x.pai.esquerda;
                if (w.cor== Cores.vermelho);
                w.cor = Cores.preto;
                x.pai.cor = Cores.vermelho;
                rightRotate(x.pai);
                w = x.pai.esquerda;
            }
            if (w.direita.cor == Cores.preto && w.esquerda.cor == Cores.preto){
                w.cor = Cores.vermelho;
                x = x.pai;
            } else {
                if (w.esquerda.cor == Cores.preto){
                    w.direita.cor = Cores.preto;
                    w.cor = Cores.vermelho;
                    leftRotate(w);
                    w = x.pai.esquerda;
                }
                w.cor = x.pai.cor;
                x.pai.cor = Cores.preto;
                x.esquerda.cor = Cores.preto;
                rightRotate(x.pai);
                x = raiz;
            }
        }
        x.cor = Cores.preto;
    }

    private void leftRotate(NoRB x){
        NoRB y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != null) y.esquerda.pai = x;

        y.pai = x.pai;

        if (x.pai == null) raiz = y;
        else if (x == x.pai.esquerda) x.pai.esquerda = y;
        else x.pai.direita = y;

        y.esquerda = x;
        x.pai = y;
    }

    private void rightRotate(NoRB y){
        NoRB x = y.esquerda;
        y.esquerda = x.direita;
        if (x.direita != null) x.direita.pai = y;

        x.pai = y.pai;

        if (y.pai == null) raiz = x;
        else if (y == y.pai.direita) y.pai.direita = x;
        else y.pai.esquerda = x;

        x.direita = y;
        y.pai = x;
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
