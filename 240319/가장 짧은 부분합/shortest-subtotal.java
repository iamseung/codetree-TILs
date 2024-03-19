import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, S;

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        
        int ans = Integer.MAX_VALUE;
        int sum = 0;

        int R = 0;
        for(int L=1; L<=N; L++) {
            
            while(R+1 <= N && sum < S) {
                sum += arr[R + 1];
                R++;
            }

            if(sum < S)
                break;

            ans = Math.min(ans, R-L+1);

            sum -= arr[L];
        }

        System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
    }
}