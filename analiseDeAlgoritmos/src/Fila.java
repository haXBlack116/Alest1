public class Fila {
    private int[] elementos;
    private int back;

    public Fila(){
        elementos = new int[10];
        back = - 1;
    }

    public void enqueue(int e) throws Exception{
        if(back == elementos.length - 1){
            throw new Exception("A fila esta cheia!");
        }
            back++;
            elementos[back] = e;
    }

    public int dequeue() throws Exception{
        if (isEmpty()) {
            throw new Exception("A lista esta vazia!");
        }
        int aux = elementos[0];    
        for (int i = 0; i < back; i++) {
            elementos[i] = elementos[i+1];
            back--;
        }
        return aux;
    }

    public int head() throws Exception{
        if (back == -1) {
            throw new Exception("A lista esta vazia!");
        }
        return elementos[0];
    }

    public int size(){
        return back + 1;
    }

    public boolean isEmpty(){
        if (back == -1) {
            return true;
        } else {
            return false;
        }
    }

    public void clear(){
        back = -1;
    }

}
