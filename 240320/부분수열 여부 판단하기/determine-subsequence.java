import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] A, B;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N+1];
        B = new int[M+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int j=1; j<=M; j++)
            B[j] = Integer.parseInt(st.nextToken());

        System.out.println(isSubsequence(1, 1) ? "Yes" : "No");
    }

    public static boolean isSubsequence(int aIdx, int bIdx) {
        // 수열 B의 마지막 원소까지 매칭이 끝났다면
        // 부분수열임을 확신할 수 있습니다.
        if(bIdx == M + 1)
            return true;
        
        // 수열 A의 원소들 중
        // 수열 B의 bIdx번째 원소와 일치하는 경우를 찾으면
        // 그 다음 원소들도 일치하는지를 확인합니다.
        for(int i = aIdx; i <= N; i++)
            if(A[i] == B[bIdx]) {
                // 매칭 가능한 쌍이 있다면
                // 그 다음 원소부터 일치하는지를 확인합니다.
                boolean isPossible = isSubsequence(i + 1, bIdx + 1);
    
                // 가능한 경우가 있다면 부분수열이라는 뜻입니다.
                if(isPossible)
                    return true;
            }
    
        // 전부 확인했음에도 가능한 경우가 없었다면
        // 부분수열이 아니라는 뜻입니다.
        return false;
    }
}