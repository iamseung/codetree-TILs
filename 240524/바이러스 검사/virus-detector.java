import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    static int N, LDR, MBR;
    static int[] store;
    
    public static void main(String[] args) throws IOException {
        input();
        simulate();
    }

    static void input() throws IOException  {
        N = Integer.parseInt(br.readLine());
        store = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            store[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        // 사팀장이 검사할 수 있는 최대 고객 수
        LDR = Integer.parseInt(st.nextToken());
        //  검사팀원이 검사할 수 있는 최대 고객 수
        MBR = Integer.parseInt(st.nextToken()); 
    }
    
    static void simulate() {
        long answer = 0;
        for(int s : store) {
            if(s <= LDR) {
                answer += 1;
                continue;
            }

            answer += 1;
            s -= LDR;

            if(s <= MBR) {
                answer += 1;
                continue;
            }

            answer += s/MBR;
            if(s%MBR > 0)
                answer++;
            }

        System.out.println(answer);
    }
}