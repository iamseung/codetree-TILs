import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr;
    
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
        int[] dp = new int[N];

        for(int i=0; i<N; i++) {
            // 각 요소의 최소 LIS 길이를 1로 초기화
            dp[i] = 1;

            for(int j=0; j<i; j++) {
                // 현재 요소가 이전 요소보다 크고, 더 긴 부분 수열을 찾은 경우
                if(arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int max = Arrays.stream(dp)
                    .max()
                    .getAsInt();
        System.out.println(max);
    }
}