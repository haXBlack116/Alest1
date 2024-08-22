public class LinkedList {
    private Nodo head,tail;
    private int count;

    private class Nodo{
        public int valor;
        public Nodo prox;
        public Nodo(int v){
            valor = v;
            prox = null;
        }
}

    public LinkedList(){
        tail = head = null;
        count = 0;
    }

    public boolean add(int element){
        Nodo novo = new Nodo(element);
        if (isEmpty()) {
            tail = head = novo;
        }else{
            tail.prox = novo;
            tail = novo;
        }
        count++;
        return true;
    }

    public void add(int index, int element){
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException("Posição Inválida");
        }
        Nodo novo = new Nodo(element);
        if (index != 0) {
            index--;
            Nodo navegador = head;
            while (index>0) {
                navegador = navegador.prox;
                index--;
            }            
            novo.prox = navegador.prox;
            navegador.prox = novo;
        }else{
            novo.prox = head;
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
        int count = 0;
        Nodo navegador = head;
        while (index != count) {
            navegador = navegador.prox;
            count++;
        }
        return navegador.valor;
    }
    public int indexOf(int element){
        Nodo navegador = head;
        int count = 0;
        while (navegador != null) {
            if (navegador.valor == element) {
                return count;
            }
            navegador = navegador.prox;
            count++;
        }
        return -1;
    }
    
    public boolean isEmpty(){
        return(count == 0);
    }

    public int remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Posição Inválida");
        }
        
        Nodo navegador = head;
        Nodo die = null; // Declare die fora do bloco if
        
        if (index > 0) {
            index--;
            while (index > 0) {
                navegador = navegador.prox;
                index--;
            }
            die = navegador.prox;
            navegador.prox = die.prox;
            die.prox = null;
            if (die == tail) {
                tail = navegador;
            }
        } else {
            die = head;
            if (head == tail) {
                tail = head = null;
            } else {
                head = head.prox;
                die.prox = null;
            }
        }
        
        count--;
        return die.valor;
    }

    public int set(int index, int element){
        if (index < 0||index >= count) {
            throw new IndexOutOfBoundsException("Posição Invalida");
        }
        int count = 0;
        Nodo navegador = head;
        while (index != count) {
            navegador = navegador.prox;
            count++;
        }
        int aux = navegador.valor;
        navegador.valor = element;
        return aux;
    }

    public int size(){
        return count;
    }

    //1. Crie uma função para calcular a soma dos elementos de uma lista.
    public int somaElementos(){
        int soma = 0;
        Nodo navegador = head;
        while (navegador != null) {
            soma += navegador.valor;
            navegador = navegador.prox;
        }
        return soma;
    }

    //2. Implemente uma função para calcular a média dos elementos de uma lista.
    public double calculaMedia(){
        double soma = 0;
        Nodo navegador = head;
        while (navegador != null) {
            soma += navegador.valor;
            navegador = navegador.prox;
        }
        return soma/count;
    }
    
    //3. Escreva um programa para encontrar o menor e o maior elemento de uma lista.
    public void encontraMenoreMaior(){
        Nodo navegador = head;
        Nodo maior = head;
        Nodo menor = head;
        while (navegador != null) {
            if (navegador.valor > maior.valor) {
                maior = navegador;
            }if (navegador.valor < menor.valor) {
                menor = navegador;
            }
            navegador = navegador.prox;
        }
        System.out.println("Maior Valor:" + maior.valor);
        System.out.println("Menor Valor:" + menor.valor);
    }

    //4. Desenvolva uma função para encontrar o índice do último elemento que é menor que
    //um valor fornecido na lista.
    public int encontraUltimoElementoMenorQue(int num){
        Nodo navegador = head;
        int indiceMenor = 0;
        for(int i = 0; i < count; i++) {
            if (navegador.valor < num) {
                indiceMenor = i;
            }
            navegador = navegador.prox;
        }
        return indiceMenor;
    }

    //5. Implemente uma função para encontrar a soma dos elementos em uma lista entre dois
    //índices fornecidos
    public int somaElementosPorIndice(int index1, int index2){
        Nodo navegador = head;
        int num1 = 0;
        int num2 = 0;
        for(int i = 0; i < count; i++){
            if (index1 == i) {
                num1 = navegador.valor;
            }if (index2 == i) {
                num2 = navegador.valor;
            }
            navegador = navegador.prox;
        }
        return num1+num2;
    }

    //6. Crie uma função para encontrar o segundo maior elemento em uma lista.
    public int encontraSegundoMaior(){
        Nodo navegador = head;
        Nodo greater = head;
        Nodo secondGreater = null;
        while (navegador != null) {
            if (navegador.valor > greater.valor) {
                secondGreater = greater;
                greater = navegador;
            }
            else if (secondGreater == null || navegador.valor > secondGreater.valor && navegador.valor != greater.valor) {
                secondGreater = navegador;
            }
            navegador = navegador.prox;
        }
        return secondGreater.valor;
    }

    //7. Implemente uma função para verificar se uma lista está ordenada. A função retorna 1 se
    //estiver em ordem crescente, -1 se estiver em ordem decrescente e 0 se não estiver
    //ordenado.
    public int isOrdered() {
    Nodo navegador = head;
    boolean crescente = true;
    boolean decrescente = true;

    while (navegador.prox != null) {
        if (navegador.valor < navegador.prox.valor) {
            decrescente = false;
        } else if (navegador.valor > navegador.prox.valor) {
            crescente = false;
        }
        navegador = navegador.prox;
    }
    if (crescente) {
        return 1;
    } else if (decrescente) {
        return -1;
    } else {
        return 0;
    }
    }

    //8. Crie uma função para encontrar o número de inversões em uma lista. Uma inversão
    //ocorre quando dois elementos estão fora de ordem.
    public int numeroDeInversoes(){
        int count = 0;
        Nodo atual = head;
        while (atual != null) {
        Nodo navegador = atual.prox;
        while (navegador != null) {
            if (atual.valor > navegador.valor) {
                count++;
            }
            navegador = navegador.prox;
        }
        atual = atual.prox;
    }
    return count;
}
    
    //9. Desenvolva uma função para remover todos os elementos repetidos adjacentes em uma
    //lista.
    public void removeRepetidos(){
        Nodo navegador = head;
        while (navegador != null && navegador.prox != null) {
            if (navegador.valor == navegador.prox.valor) {
                Nodo proximo = navegador.prox;
                navegador.prox = proximo.prox;
                proximo = null;
            }else{
            navegador = navegador.prox;
        }
        count--;
    }
}


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (!isEmpty()) {
            Nodo navegador = head;
            while (navegador.prox != null) {
                sb.append(navegador.valor + ", ");
                navegador = navegador.prox;
            }
            sb.append(navegador.valor + "");
        }
        sb.append("]");
        return sb.toString();
    }

    //13. Crie uma função para remover todos os elementos duplicados de uma lista.
    public void removeDuplicados() {
        Nodo atual = head;
        while (atual != null) {
            Nodo comparador = atual;
            // Percorre a lista a partir do próximo nodo do atual para encontrar duplicatas
            while (comparador.prox != null) {
                if (comparador.prox.valor == atual.valor) {
                    // Encontrou duplicata, remove o nodo duplicado
                    comparador.prox = comparador.prox.prox;
                    count--;
                } else {
                    // Se não há duplicata, avança para o próximo nodo
                    comparador = comparador.prox;
                }
            }
            // Avança para o próximo nodo na lista principal
            atual = atual.prox;
        }
    }

    //16. Implemente uma função para dividir uma lista em duas partes, de forma que uma parte
    //contenha apenas números pares e a outra apenas números ímpares.
    public void dividePares(){
        Nodo navegador = head;
        while (navegador != null) {
            if (navegador.valor % 2 == 0) {
                System.out.print(navegador.valor + " ");
            }
            navegador = navegador.prox;
        }
    }
    public void divideImpares(){
        Nodo navegador = head;
        while (navegador != null) {
            if (navegador.valor % 2 != 0) {
                System.out.print(navegador.valor + " ");
            }
            navegador = navegador.prox;
        }
    }

    public void listaEmDuasPartes(){
        System.out.println("Pares:");
        dividePares();
        System.out.println();
        System.out.println("Impares:");
        divideImpares();
    }

    public int[] toArray(LinkedList lista){
        Nodo nav = head;
        int[] arr = new int[lista.size()];
        while (nav != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = nav.valor;
                nav = nav.prox;
            }    
        }
        return arr;
    }

    public String soPositivos(){
        Nodo nav = head;
        String positivos = "";
        while (nav != null) {
            if (nav.valor > 0) {
                positivos += " " + nav.valor;
            }
            nav = nav.prox;
        }
        return positivos;
    }

    public String soNegativos(){
        Nodo nav = head;
        String positivos = "";
        while (nav != null) {
            if (nav.valor < 0) {
                positivos += " " + nav.valor;
            }
            nav = nav.prox;
        }
        return positivos;
    }
}
