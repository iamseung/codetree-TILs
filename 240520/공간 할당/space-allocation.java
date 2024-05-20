import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] satisfactions, spaces;
    
    public static void main(String[] args) throws IOException {
        input();
        simulate();
    }

    static void input() throws IOException  {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물건의 개수
        M = Integer.parseInt(st.nextToken()); // 만족 총합 M 이상
        
        st = new StringTokenizer(br.readLine());

        satisfactions = new int[N];
        spaces = new int[N];

        for(int i=0; i<N; i++) {
            satisfactions[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            spaces[i] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void simulate() {
        // 만족도의 최대 가능한 합을 계산
        int S_max = Arrays.stream(satisfactions).sum();

        // 최대 만족도는 m과 계산된 S_max 중 더 큰 값으로 설정합니다.
        S_max = Math.max(S_max, M);

        int[] dp = new int[S_max+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i=0; i<N; i++) {
            int satisfaction = satisfactions[i];
            int space = spaces[i];

            for(int j=S_max; j>= satisfaction; j--) {
                if(dp[j - satisfaction] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], dp[j - satisfaction] + space);
            }
        }

        // m 이상의 만족도를 가진 경우 중 최소 공간을 찾습니다.
        int minSpace = Integer.MAX_VALUE;
        for (int j = M; j <= S_max; j++) {
            if (dp[j] < minSpace) {
                minSpace = dp[j];
            }
        }

        System.out.println(minSpace == Integer.MAX_VALUE ? -1 : minSpace);
    }
}