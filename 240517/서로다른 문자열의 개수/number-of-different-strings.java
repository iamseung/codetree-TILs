import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    
    public static void main(String[] args) throws IOException {
        input();
        simulate();
    }

    static void input() throws IOException  {
        N = Integer.parseInt(br.readLine());
    }
    
    static void simulate() {
        long twoNFactorial = factorial(2 * N);
        long nFactorial = factorial(N);
        long nPlusOneFactorial = factorial(N + 1);

        // C_n = (2n)! / (n! * (n + 1)!)
        long result = twoNFactorial / (nFactorial * nPlusOneFactorial);
        System.out.println(result);
    }

    static long factorial(int number) {
        long result = 1;
        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }
        return result;
    }
}