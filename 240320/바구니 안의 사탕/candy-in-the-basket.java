import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr;
    static int N, K;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<int[]> temp = new ArrayList<>();

        int max = 0;
        while(N-- >0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            temp.add(new int[]{A,B});
            max = Math.max(max, B);
        }

        arr = new int[max+1];
        for(int[] t : temp) 
            arr[t[1]] = t[0];

        int R=0;
        int sum = 0;
        int ans = -1;
        for(int L=1; L+K<=max; L++) {
            while(R+1 <= max && R-L < 2*K) {
                sum += arr[R++ + 1];
            }

            if(R-L == 2*K)
                ans = Math.max(sum, ans);

            sum -= arr[L];
        }

        System.out.println(ans);
    }
}