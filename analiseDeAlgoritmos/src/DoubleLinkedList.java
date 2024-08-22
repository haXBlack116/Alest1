public class DoubleLinkedList {
    private Nodo head,tail;
    private int count;

    private class Nodo{
        public int valor;
        public Nodo prox;
        public Nodo prev;
        public Nodo(int v){
            valor = v;
            prox = null;
        }
}

    public DoubleLinkedList(){
        tail = head = null;
        count = 0;
    }

    public boolean add(int element) {
        Nodo novo = new Nodo(element);
        if (count == 0) {
            tail = head = novo;
        } else {
            tail.prox = novo;
            novo.prev = tail;
            tail = novo;
        }
        count++;
        return true;
    }

    public void add(int index, int element) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Posição Inválida");
        }
        Nodo navegador = head;
        while (index > 0) {
            navegador = navegador.prox;
            index--;
        }
        Nodo novo = new Nodo(element);
        if (navegador != head) {
            novo.prev = navegador.prev;
            navegador.prev.prox = novo;
            novo.prox = navegador;
            navegador.prev = novo;
        } else {
            novo.prox = head;
            head.prev = novo;
            head = novo;
        }
        count++;
    }

    public void clear(){
        head = null;
        count = 0;
    }

    public boolean contains(int element){
        Nodo navegador = head;
        while (navegador != null) {
            if (navegador.valor == element) {
                return true;
            }
            navegador = navegador.prox;
        }
        return false;
    }

    public int get(int index){
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException("Posição Inválida");
        }
        Nodo navegador = head;
        while (index > 0) {
            navegador = navegador.prox;
            index--;
        }
        return navegador.valor;
    }
    
    public int indexOf(int element){
        Nodo navegador = head;
        int index = 0;

        while (navegador != null) {
            if (navegador.valor == element) {
                return index;
            }
                navegador = navegador.prox;
                count++;
        }
        return -1;
    }
    
    public boolean isEmpty(){
        return(count == 0);
    }

    public int remove(int index){
        if (index < 0||index >= count) {
            throw new IndexOutOfBoundsException("Posição Invalida");
        }
        Nodo navegador = head;
        while (index > 0) {
            navegador = navegador.prox;
            index--;
        }
        if(navegador != head){
        navegador.prev.prox = navegador.prox;
        }else{
            head = navegador.prox;
        }
        if(navegador != tail){
        navegador.prox.prev = navegador.prev;
        }else{
            tail = navegador.prev;
        }
        navegador.prox = null;
        navegador.prev = null;
        count--;
        return navegador.valor;
    }
    
    public int set(int index, int element){
        if (index < 0||index >= count) {
            throw new IndexOutOfBoundsException("Posição Invalida");
        }
        Nodo navegador = head;
        while (index > 0) {
            navegador = navegador.prox;
            index--;
        }
        int aux = navegador.valor;
        navegador.valor = element;
        return aux;
    }

    public int size(){
        return count;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (!isEmpty()) {
            Nodo navegador = head;
            while (navegador.prox != null) {
                sb.append(navegador.valor).append(", ");
                navegador = navegador.prox;
            }
            sb.append(navegador.valor);
        }
        sb.append(" ]");
        return sb.toString();
    }

    public void inverteLista(){
        Nodo previous = null;
        Nodo current = head;
        Nodo next = null;
        tail = head; // After reversing, the original head will become the tail
        while (current != null) {
            next = current.prox;
            current.prox = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }
    
    public void adicionaOrdenado(int element) {
        Nodo novo = new Nodo(element);
        if (isEmpty()) {
            head = novo;
            tail = novo;
        } else if (head.valor >= novo.valor) {
            // Inserção no início
            novo.prox = head;
            head.prev = novo;
            head = novo;
        } else if (tail.valor <= novo.valor) {
            // Inserção no final
            tail.prox = novo;
            novo.prev = tail;
            tail = novo;
        } else {
            // Inserção no meio
            Nodo navegador = head;
            while (navegador.prox != null && navegador.prox.valor < novo.valor) {
                navegador = navegador.prox;
            }
            novo.prox = navegador.prox;
            if (navegador.prox != null) {
                navegador.prox.prev = novo;
            }
            navegador.prox = novo;
            novo.prev = navegador;
        }
        count++;
    }

    //11. Crie uma função para imprimir os elementos de uma lista na ordem normal e na ordem
    //inversa.
    public void imprimeNormal(){
        Nodo primeiro = head;
        while (primeiro != null) {
            System.out.print("["+ primeiro.valor + "], ");
            primeiro = primeiro.prox;
        }
    }

    public void imprimeInverso() {
        Nodo atual = tail;
        while (atual != null) {
            System.out.print("[" + atual.valor + "], ");
            atual = atual.prev;
        }
        System.out.println();
    }

    public void imprimeNormaleInverso() {
        System.out.println("Ordem Normal:");
        imprimeNormal();
        System.out.println();
        System.out.println("Ordem Inversa:");
        imprimeInverso();
    }

    public boolean removeLastOccurrence(int element){
        Nodo nav = tail;
        while (nav != null) {
            if (nav.valor == element) {
                nav.prev.prox = nav.prox;
                nav = nav.prev;
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public void addAll(LinkedList list){
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }    
    }

}

    
