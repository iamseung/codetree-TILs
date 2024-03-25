import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M,T;
    static int[][] arr, move;
    static int[][] dir = {{-1, 0},{1, 0},{0, -1},{0, 1}};
    static Queue<Integer> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        input();
        process();
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        // Mapping
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move = new int[N][N];

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            que.add(x); que.add(y);
        }
    }

    static void process() {
        while(T-- >0) {
            // 구슬의 위치 POP
            while (!que.isEmpty()) {
                int x = que.poll();
                int y = que.poll();

                int max = 0, direction = 0;

                for(int k=0; k<4; k++) {
                    int ny = y + dir[k][0];
                    int nx = x + dir[k][1];

                    if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                    
                    // 이동할 방향 조정
                    if(arr[ny][nx] >= max) {
                        direction = k;
                        max = arr[ny][nx];
                    }
                }

                // 구슬 이동
                move[y+ dir[direction][0]][x+ dir[direction][1]]++;
            }

            // 구슬 삭제 및 queue INSERT
            clearAndSet();
        }

        System.out.println(que.size()/2);
    }

    static void clearAndSet() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(move[i][j] >= 2)
                    move[i][j] = 0;
                
                if(move[i][j] == 1) {
                    que.add(j); que.add(i);
                }
            }
        }
    }
}