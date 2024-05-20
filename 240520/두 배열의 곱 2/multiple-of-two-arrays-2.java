import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] arr = new int[4][2];
    
    public static void main(String[] args) throws IOException {
        input();
        simulate();
    }

    static void input() throws IOException  {
        for(int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        String str = br.readLine();

        for(int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] *= Integer.parseInt(st.nextToken());
            arr[i][1] *= Integer.parseInt(st.nextToken());
        }
    }
    
    static void simulate() {
        for(int i=0; i<4; i++) {
            for(int a : arr[i]) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}