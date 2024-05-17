import java.util.*;
import java.io.*;
import java.math.BigInteger;

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
        BigInteger twoNFactorial = factorial(2 * N);
        BigInteger nFactorial = factorial(N);
        BigInteger nPlusOneFactorial = factorial(N + 1);

        // C_n = (2n)! / (n! * (n + 1)!)
        BigInteger result = twoNFactorial.divide(nFactorial.multiply(nPlusOneFactorial));
        System.out.println(result);
    }

    static BigInteger factorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int factor = 2; factor <= number; factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }
        return result;
    }
}