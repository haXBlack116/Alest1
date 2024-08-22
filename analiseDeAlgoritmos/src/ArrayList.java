public class ArrayList {
    private int[] elementos;
    private int count;

    public ArrayList(){
        elementos = new int[10];
        count = 0;
    }

    public ArrayList(int initialCapacity){
        if (initialCapacity <= 1) {
            initialCapacity = 10;
        }
        elementos = new int[initialCapacity];
        count = 0;
    }

    private void grow(){
        int[] novaLista = new int[elementos.length*2];
        for (int i = 0; i < elementos.length; i++) {
            novaLista[i] = elementos[i];
            elementos = novaLista;
        }
    }

    public boolean add(int element){ //O(N)
        if (count == elementos.length){
            grow();
        }
           elementos[count] = element;
           count++;
           return true;  
    }

    public void add(int index, int element){
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException("Posicao invalida na lista!");
        }
        for (int i = count; i > index; i--) {
            elementos[i] = elementos[i-1];
            elementos[index] = element;
            count++;
        }
    }

    public void clear(){
        elementos = new int[10];
        count =0;
    }

    public boolean contains(int element){
        for (int i = 0; i < count; i++) {
            if (elementos[i] == element) {
                return true;
            }
        }
        return false;
    }

    public int get(int index){
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException("Posicao invalida na lista!");
        }
        return elementos[index];
    }

    public int indexOf(int element){
        for (int i = 0; i < count; i++) {
            if (elementos[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty(){
        return (count == 0);
    }

    public void shrink(){
        int[] novaLista = new int[elementos.length*2];
        for (int i = 0; i < elementos.length; i++) {
            novaLista[i] = elementos[i];
            elementos = novaLista;
        }
    }

    public int remove(int index) throws Exception{   //O(N)
        if ((index < 0) || (index >= count)) {
            throw new Exception("Posicao invalida na lista!");
        }
        int value = elementos[index];
        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = elementos[i+1];
            count--;
            if ((elementos.length > 10)&&(count <= ((elementos.length/4)+1))) {
                shrink();
            }
        }
        return value;
    }

    public int set(int index, int element) throws Exception{
        if ((index < 0) || (index >= count)) {
            throw new Exception("Posicao invalida na lista!");
        }
        int aux = elementos[index];
        elementos[index] = element;
        return aux;
    }

    public int size(){
        return count;
    }

    public int capacity(){
        return elementos.length;
    }

    public int lastIndexOf(int element){
        for (int i = elementos.length-1; i >= 0; i--) {
            if (elementos[i] == element) {
                return i;
            }
        }
        return -1;
    }
}
