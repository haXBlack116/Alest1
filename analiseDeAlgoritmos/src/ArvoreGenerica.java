import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class ArvoreGenerica {

    private class Nodo {
        public Nodo pai;
        public int valor;
        public List<Nodo> filhos;

        public Nodo(int v) {
            pai = null;
            valor = v;
            filhos = new ArrayList<>();
        }
    }

    private Nodo raiz;
    private int nElementos;

    public ArvoreGenerica() {
        raiz = null;
        nElementos = 0;
    }

    // retorna true se a árvore está vazia
    public boolean isEmpty() {
        return raiz == null;
    }

    // size(): retorna o número de elementos armazenados na árvore
    int size() {
        return nElementos;
    }

    // clear(): remove todos os elementos da árvore
    void clear() {
        raiz = null;
        nElementos = 0;
    }

    // altera o elemento armazenado na raiz
    void setRoot(Integer e) {
        if (e == null)
            throw new IllegalArgumentException("O valor deve ser diferente de nulo");

        if (raiz == null) {
            raiz = new Nodo(e);
            nElementos = 1;
        } else {
            raiz.valor = e;
        }
    }

    // retorna o elemento armazenado na raiz
    public int getRoot() {
        if (raiz != null)
            return raiz.valor;
        else
            throw new RuntimeException("A raiz não foi criada");
    }

    // retorna true se o elemento e está armazenado na raiz
    public boolean isRoot(Integer e) {
        if (e == null)
            throw new IllegalArgumentException("O argumento não pode ser nulo");
        if (raiz == null)
            throw new RuntimeException("A árvore não possui raiz");

        return (e == raiz.valor);
    }

    public String toString() {
        String result;
        StringBuilder texto = new StringBuilder();

        if (raiz == null)
            result = "Árvore vazia";
        else {
            result = "Árvore com nodos\n";
            result += "graph G {";
            caminhamentoEmProfundidadePreOrdem(raiz, texto);
            result += texto.toString();
            result += "}";
        }
        return result;
    }

    private void caminhamentoEmProfundidadePreOrdem(Nodo nodoRef, StringBuilder txt) {
        if (nodoRef.filhos.size() > 0) {
            // AÇÃO ANTES DOS FILHOS
            for (Nodo filho : nodoRef.filhos) {
                txt.append("  " + nodoRef.valor + " -- " + filho.valor + ";\n");
                System.out.println(txt);
            }

            // CAMINHAMENTO PARA OS FILHOS
            for (Nodo filho : nodoRef.filhos)
                caminhamentoEmProfundidadePreOrdem(filho, txt);
        }
    }

    // insere o elemento e como filho de father
    public boolean add(Integer e, Integer father) {
        if (father == null) {
            if (raiz == null) {
                setRoot(e);
                return true;
            }
            return false;
        } else {
            if (e == null)
                throw new IllegalArgumentException("O argumento não pode ser nulo");
            if (raiz == null)
                throw new RuntimeException("A árvore não possui raiz para ter nodos incluídos");

            Nodo pai = findNode(raiz, father);
            if (pai != null) {
                Nodo aux = new Nodo(e);
                aux.pai = pai;
                pai.filhos.add(aux);
                nElementos++;
                return true;
            }
            return false;
        }
    }

    private Nodo findNode(Nodo nodoRef, int elemento) {
        if (nodoRef.valor == elemento)
            return nodoRef;
        else {
            Nodo aux = null;
            for (Nodo filho : nodoRef.filhos) {
                aux = findNode(filho, elemento);
                if (aux != null)
                    return aux;
            }
            return null;
        }
    }

    // retorna true se a árvore contém o elemento e
    public boolean contains(Integer e) {
        if (e == null)
            throw new IllegalArgumentException("O argumento não pode ser nulo");
        if (raiz == null)
            return false;

        return (findNode(raiz, e) != null);
    }

    // retorna true se o elemento está armazenado em um nodo interno
    public boolean isInternal(Integer e) {
        if (e == null)
            throw new IllegalArgumentException("O argumento não pode ser nulo");

        if (raiz == null)
            throw new RuntimeException("A árvore está vazia");

        Nodo aux = findNode(raiz, e);

        if (aux == null)
            throw new RuntimeException("O valor " + e + " não está na árvore");

        return (aux.filhos.size() > 0);
    }

    // retorna true se o elemento está armazenado em um nodo externo
    public boolean isExternal(Integer e) {
        return !isInternal(e);
    }

    // retorna o pai do elemento e
    public Integer getParent(Integer e) {
        if (e == null)
            throw new IllegalArgumentException("O argumento não pode ser nulo");

        if (raiz == null)
            throw new RuntimeException("A árvore está vazia");

        Nodo aux = findNode(raiz, e);

        if (aux != raiz)
            return aux.pai.valor;
        else
            return null;

    }

    // remove o elemento e e seus filhos
    boolean removeBranch(Integer e){
        if (e == null) {
            throw new IllegalArgumentException("O argumento não pode ser nulo");
        }
        if (raiz == null) {
            throw new RuntimeException("A árvore está vazia");
        }else{
        Nodo aux = findNode(raiz, e);
        if (aux == null) {
            return false;
        }if (aux == raiz) {
            raiz = null;
            nElementos = 0;
            return true;
        }
        Nodo auxPai = aux.pai;
        auxPai.filhos.remove(aux);
        for (Nodo filho : aux.filhos) {
            filho.pai = null;
        }
        nElementos--;
        return true;
    }   
}

    // positionsPre(): retorna uma lista com todos os elementos da árvore na ordem
    // pré fixada
    public LinkedList<Integer> positionsPre() {
        LinkedList<Integer> result = new LinkedList<>();
        if (raiz == null) {
            return result;
        }
        Stack<Nodo> stack = new Stack<>();
        stack.push(raiz);
        while (!stack.isEmpty()) {
            Nodo current = stack.pop();
            result.add(current.valor);
            // Adiciona os filhos na pilha em ordem inversa para manter a ordem correta na saída
            for (int i = current.filhos.size() - 1; i >= 0; i--) {
                stack.push(current.filhos.get(i));
            }
        }
        return result;
    }

    // positionsPos(): retorna uma lista com todos os elementos da árvore na ordem
    // pos fixada
    public LinkedList<Integer> positionsPos() {
        LinkedList<Integer> result = new LinkedList<>();
        if (raiz == null) {
            return result;
        }
        Stack<Nodo> stack = new Stack<>();
        Stack<Nodo> output = new Stack<>();
        stack.push(raiz);
        while (!stack.isEmpty()) {
            Nodo current = stack.pop();
            output.push(current);
            // Adiciona os filhos na pilha em ordem direta para manter a ordem correta na saída
            for (Nodo filho : current.filhos) {
                stack.push(filho);
            }
        }
        while (!output.isEmpty()) {
            result.add(output.pop().valor);
        }
        return result;
    }

    // positionsWidth(): retorna uma lista com todos os elementos da árvore com um
    // caminhamento em largura
    public LinkedList<Integer> positionsWidth() {
        LinkedList<Integer> result = new LinkedList<>();
        if (raiz == null) {
            return result;
        }
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(raiz);
        while (!queue.isEmpty()) {
            Nodo current = queue.poll();
            result.add(current.valor);
            // Adiciona os filhos na fila para continuar a travessia em largura
            for (Nodo filho : current.filhos) {
                queue.add(filho);
            }
        }
        return result;
    }
}