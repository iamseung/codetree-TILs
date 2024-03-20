import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        
        int R=0, S=0, ANS = -1;
        boolean[] check = new boolean[100001];

        for(int L=1; L<=N; L++) {
            while(R+1 <= N && !check[arr[R+1]]) {
                check[arr[R++ + 1]] = true;
            }

            ANS = Math.max(ANS, R-L+1);

            check[arr[L]] = false;
        }

        System.out.println(ANS);
    }
}