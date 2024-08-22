
public class App {
    public static void main(String[] args) throws Exception {
        LinkedList lista1 = new LinkedList();
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);
        lista1.add(-5);
        lista1.add(-9);

        System.out.println(lista1.soPositivos());
        System.out.println(lista1.soNegativos());
        
}
}