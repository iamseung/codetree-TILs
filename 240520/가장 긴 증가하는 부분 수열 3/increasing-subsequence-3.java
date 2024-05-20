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
        int min = arr[0];
        int count = 1;
        for(int i=1; i<N-1; i++) {
            if(min <= arr[i] && arr[i] <= arr[i+1]) {
                min = arr[i];
                count++;
            }
        }

        if(arr[N-1] >= min) count++;

        System.out.println(count);
    }
}