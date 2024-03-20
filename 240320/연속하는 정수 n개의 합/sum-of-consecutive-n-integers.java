import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
       process();
    }

    static void process() throws IOException {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        arr = new int[N+1];
        for(int i=1; i<=N; i++)
            arr[i] = in.nextInt();
        
        int R=0, SUM=0, ANSWER=0;
        for(int L=1; L<=N; L++) {
            while(R+1 <=N && SUM < M) {
                SUM += arr[R++ + 1];
            }

            if(SUM == M)
                ANSWER++;
            
            SUM -= arr[L];
        }

        System.out.println(ANSWER);
    }
}