import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    
    public static void main(String[] args) throws IOException {
        input();
        int answer = simulate();
        System.out.println(answer);
    }

    static void input() throws IOException  {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }
    
    static int simulate() {
        boolean[] removed = new boolean[N+1];
        int count = 0;

        for(int p=2; p<=N; p++) {
            if(removed[p]) continue;

            for(int gap = p; gap <= N; gap += p) {
                if(removed[gap]) continue;

                removed[gap] = true;
                count++;

                if(count == K)
                    return gap;
            }
        }

        return -1;
    }
}