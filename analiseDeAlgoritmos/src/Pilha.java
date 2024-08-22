public class Pilha {
    private int elementos[] = new int[10];
    private int topo;

    public Pilha(){
        elementos = new int[10];
        topo = -1;
    }

    public void push(int e){
        if(topo != elementos.length - 1){
            topo++;
            elementos[topo] = e;
        }
        else{
            System.out.println("Sua lista está cheia");
        }
    }

    public int pop(){
        if(isEmpty()){
            System.out.println("Não há elementos para remover");
        }else{
            topo--;
        }
        return elementos[topo];
    }

    public int top(){
        if (isEmpty()) {
            System.out.println("A pilha está vazia");
        }
            return elementos[topo];
        }
    
    public boolean isEmpty(){
        if (topo == -1) {
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        if (isEmpty()) {
            return 0;
        }
        return topo;
    }

    public void clear(){
        topo = -1;
    }

    public Pilha trocaSinal(Pilha s){
        for (int i = 0; i <= s.topo; i++) {
            s.elementos[i] *= -1;
        }
        return s;
    }

    public String toString() {
        if (topo == -1) {
            return "Pilha vazia";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= topo; i++) {
            sb.append(elementos[i]).append(" ");
        }
        return sb.toString();
    }
}
