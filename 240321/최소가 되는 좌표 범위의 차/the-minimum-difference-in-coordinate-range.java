import java.util.*;
import java.io.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int N, D;
    static Dot[] dots;

    public static void main(String[] args) {
        N = sc.nextInt();
        D = sc.nextInt();

        dots = new Dot[N];
        for(int i = 0; i < N; i++)
            dots[i] = new Dot(sc.nextInt(), sc.nextInt());

        Arrays.sort(dots);

        int result = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        int L = 0, R = 0;

        while(R < N) {
            // Update minY and maxY within the current window
            minY = Math.min(minY, dots[R].y);
            maxY = Math.max(maxY, dots[R].y);

            // Check if the current window satisfies the condition
            if(maxY - minY >= D) {
                result = Math.min(result, dots[R].x - dots[L].x);
                // Try to shrink the window from the left to find smaller ranges
                while(L < R && maxY - minY >= D) {
                    minY = Integer.MAX_VALUE;
                    maxY = Integer.MIN_VALUE;
                    L++;
                    // Recalculate minY and maxY after removing L
                    for(int i = L; i <= R; i++) {
                        minY = Math.min(minY, dots[i].y);
                        maxY = Math.max(maxY, dots[i].y);
                    }
                    if(maxY - minY >= D) {
                        result = Math.min(result, dots[R].x - dots[L].x);
                    }
                }
            }

            R++;
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}

class Dot implements Comparable<Dot>{
    int x;
    int y;

    Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Dot o) {
        return x - o.x;
    }
}