package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ArvoreAVL {
    int fb;
    No raiz;


    //Estrutura de Árvore AVL{

    public int altura(No no){
        return no == null ? 0 : (no.altura);
    }

    public int atualizarAltura(No no){
        return no.altura + Math.max(altura(no.esquerda),altura(no.direita));
    }

    public int fatorBalanceamento(No no){
        return no == null ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    No inserir(No no, int valor){
        if (no == null)
            return new No(valor); //Caso o nó atual for nulo, cria um novo nó com o valor

        //Insere recursivamente o nó na subárvore esquerda ou direita dependendo do valor do nó
        if (valor < no.valor)
            no.esquerda = inserir(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = inserir(no.direita, valor);
        else
            return no; //Caso o valor seja duplicado, retorna o nó

        //Atualiza a altura do nó após a inserção
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        //Calcula o fator de balanceamento
        int balanceamento = fatorBalanceamento(no);

        // Caso 1: desbalanceamento à esquerda (Esquerda-Esquerda - LL)
        if (balanceamento > 1 && valor < no.esquerda.valor) {
            return rotacaoADireitaLL(no);
        }

        // Caso 2: desbalanceamento à esquerda (Esquerda-Direita - LR)
        if (balanceamento > 1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoAEsquerdaRR(no.esquerda);
            return rotacaoADireitaLL(no);
        }

        // Caso 3: desbalanceamento à direita (Direita-Direita - RR)
        if (balanceamento < -1 && valor > no.direita.valor) {
            return rotacaoAEsquerdaRR(no);
        }

        // Caso 4: desbalanceamento à direita (Direita-Esquerda - RL)
        if (balanceamento < -1 && valor < no.direita.valor) {
            no.direita = rotacaoADireitaLL(no.direita);
            return rotacaoAEsquerdaRR(no);
        }

        //Retorna o nó (podendo ou não ter havido ajuste)
    return no;
    }

    //Estrutura de Árvore AVL}

    //Rotações{

    public No rotacaoAEsquerdaRR(No x){
        //considerando a ordem dos nós como x,y,z
        //Nó y vai ser o filho direito de z
        No y = x.direita;

        //Nó esquerdo de y, que no fim do método será x
        No n2 = y.esquerda;

        //rotação
        y.esquerda = x;
        x.direita = n2;

        //Atualiza a altura dos nós
        atualizarAltura(y);
        atualizarAltura(x);

        return y;
    }

    public No rotacaoDireitaEsquerdaRL(No no) {
        no.direita = rotacaoADireitaLL(no.direita);
        return rotacaoAEsquerdaRR(no);
    }

    public No rotacaoADireitaLL(No y){
        No x = y.direita;
        No n2 = x.esquerda;


        x.direita = y;
        y.esquerda = n2;


        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    public No rotacaoADireitaLR(No no){
        return no;
    }

    public No rotacaoEsquerdaDireitaLR(No no) {
        no.esquerda = rotacaoAEsquerdaRR(no.esquerda);
        return rotacaoADireitaLL(no);
    }

    //Rotações}



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

    // Método para remover um nó na árvore AVL
    private No removerNo(No raiz, int chave) {
        // 1. Remoção padrão da BST
        if (raiz == null)
            return raiz;

        if (chave < raiz.valor) {
            raiz.esquerda = removerNo(raiz.esquerda, chave);
        } else if (chave > raiz.valor) {
            raiz.direita = removerNo(raiz.direita, chave);
        } else {
            // Nó encontrado
            if ((raiz.esquerda == null) || (raiz.direita == null)) {
                No temp = (raiz.esquerda != null) ? raiz.esquerda : raiz.direita;

                if (temp == null) {
                    // Sem filhos
                    raiz = null;
                } else {
                    // Um filho
                    raiz = temp;
                }
            } else {
                // Nó com dois filhos: encontra o menor valor na subárvore direita
                No temp = raiz.direita;
                while (temp.esquerda != null) {
                    temp = temp.esquerda;
                }

                // Copia o valor do sucessor in-order para o nó atual
                raiz.valor = temp.valor;

                // Remove o sucessor in-order na subárvore direita
                raiz.direita = removerNo(raiz.direita, temp.valor);
            }
        }

        // Se a árvore ficou vazia após a remoção
        if (raiz == null)
            return raiz;

        // 2. Atualiza altura
        raiz.altura = 1 + Math.max(altura(raiz.esquerda), altura(raiz.direita));

        // 3. Calcula o fator de balanceamento
        int balanceamento = fatorBalanceamento(raiz);

        // 4. Aplica rotações se necessário

        // Caso LL
        if (balanceamento > 1 && fatorBalanceamento(raiz.esquerda) >= 0)
            return rotacaoADireitaLL(raiz);

        // Caso LR
        if (balanceamento > 1 && fatorBalanceamento(raiz.esquerda) < 0) {
            raiz.esquerda = rotacaoAEsquerdaRR(raiz.esquerda);
            return rotacaoADireitaLL(raiz);
        }

        // Caso RR
        if (balanceamento < -1 && fatorBalanceamento(raiz.direita) <= 0)
            return rotacaoAEsquerdaRR(raiz);

        // Caso RL
        if (balanceamento < -1 && fatorBalanceamento(raiz.direita) > 0) {
            raiz.direita = rotacaoADireitaLL(raiz.direita);
            return rotacaoAEsquerdaRR(raiz);
        }

        return raiz;
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
