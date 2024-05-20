import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static int[] machines;
    static PriorityQueue<Integer> volumes;
    
    public static void main(String[] args) throws IOException {
        input();
        simulate();
    }

    static void input() throws IOException  {
        N = Integer.parseInt(br.readLine());
        machines = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++)
            machines[i] = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());
    
        st = new StringTokenizer(br.readLine());
    
        volumes = new PriorityQueue<>();

        for(int i=0; i<K; i++)
            volumes.offer(Integer.parseInt(st.nextToken()));
    }
    
    static void simulate() {
        int time = 0;
        Arrays.sort(machines); // 기계들을 용량에 따라 정렬합니다.

        while (!volumes.isEmpty()) {
            boolean processed = false;
            for (int m : machines) {
                if (volumes.isEmpty()) break;
                if (m >= volumes.peek()) {
                    volumes.poll();
                    processed = true;
                }
            }
            
            // 이번 라운드에서 하나도 처리하지 못했다면
            if (!processed) { 
                System.out.println(-1);
                return;
            }
            time++;
        }

        System.out.println(time);
    }
}