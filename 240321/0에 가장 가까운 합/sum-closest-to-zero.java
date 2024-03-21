import java.util.*;
import java.io.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int[] arr;

    public static void main(String[] args) {
        N = sc.nextInt();

        arr = new int[N+1];
        for(int i=1; i<=N; i++) 
            arr[i] = sc.nextInt();

        Arrays.sort(arr, 1, N+1);

        int bestSum = Integer.MAX_VALUE, R = N, L = 1;

        while(L < R) {
            if(bestSum > Math.abs(arr[R]+arr[L]))
                bestSum = Math.abs(arr[R]+arr[L]);

            if(arr[R]+arr[L] > 0) {
                R--;
            } else {
                L++;
            }
        }

        System.out.println(bestSum);
    }
}