public class Main {
    public static void main(String[] args) {
        int a = 5, b = 6, c = 7;
        int tempB, tempC;

        tempB = b;
        tempC = c;
        b = a;
        c = tempB;
        a = tempC;
        
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}