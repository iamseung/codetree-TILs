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

        while(!volumes.isEmpty()) {
            boolean is = false;

            for(int m : machines) {
                if(volumes.isEmpty()) break;
                
                if(m >= volumes.peek()) {
                    volumes.poll();
                    is = true;
                }
            }

            if(!is) break;
            time++;
        }

        System.out.println(volumes.isEmpty() ? time : -1);
    }
}