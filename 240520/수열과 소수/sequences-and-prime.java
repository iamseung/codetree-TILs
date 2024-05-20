import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr;
    static final int MAX_PRIME = 1000001;
    static boolean[] prime = new boolean[MAX_PRIME];
    
    public static void main(String[] args) throws IOException {
        input();
        simulate();
    }

    static void input() throws IOException  {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
    }
    
    static void simulate() {
        makePrimeArray();
        int answer = Arrays.stream(arr)
                        .filter(i->isPrime(i))
                        .sum();

        System.out.println(answer);
    }

    static void makePrimeArray() {
        prime[0] = prime[1] = true;
        for(int i=2; i<=Math.sqrt(MAX_PRIME); i++) {
            if(prime[i]) continue;

            for(int j = i * i; j < MAX_PRIME; j += i) {
                prime[j] = true;
            }
        }
    }

    static boolean isPrime(int x) {
        return !prime[x];
    }
}