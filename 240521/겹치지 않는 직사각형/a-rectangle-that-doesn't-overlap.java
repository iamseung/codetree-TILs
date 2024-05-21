import java.util.*;
import java.io.*;

public class Main {
    static class Rectangle {
        int x1, y1, x2, y2;

        Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        boolean isInside(Rectangle other) {
            return x1 >= other.x1 && y1 >= other.y1 && x2 <= other.x2 && y2 <= other.y2;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static List<Rectangle> rectangles = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        input();
        simulate();
    }

    static void input() throws IOException  {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            rectangles.add(new Rectangle(x1, y1, x2, y2));
        }
    }
    
    static void simulate() {        
        int count = 0;
        outer: for (int i = 0; i < N; i++) {
            Rectangle current = rectangles.get(i);
            for (int j = 0; j < N; j++) {
                if (i != j && rectangles.get(j).isInside(current)) {
                    continue outer;
                }
            }
            count++;
        }

        System.out.println(count);
    }
}