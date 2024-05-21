import java.util.*;
import java.io.*;

public class Main {
    static class Dice {
        int front = 0;
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;
        int back = 0;

        public void move(int k) {
            switch (k) {
                case 0:
                    moveRight();
                    break;
                case 1:
                    moveLeft();
                    break;
                case 2:
                    moveTop();
                    break;
                case 3:
                    moveBottom();
                    break;
                default:
                    break;
            }
        }

        public void moveRight() {
            int xfront = this.front;
            this.front = this.left;
            this.left = this.back;
            this.back = this.right;
            this.right = xfront;
        }

        public void moveLeft() {
            int xfront = this.front;
            this.front = this.right;
            this.right = this.back;
            this.back = this.left;
            this.left = xfront;
        }

        public void moveTop() {
            int xfront = this.front;
            this.front = this.bottom;
            this.bottom = this.back;
            this.back = this.top;
            this.top = xfront;
        }

        public void moveBottom() {
            int xfront = this.front;
            this.front = this.top;
            this.top = this.back;
            this.back = this.bottom;
            this.bottom = xfront;
        }

        public int getBack() {
            return this.back;
        }

        public void setBack(int v) {
            this.back = v;
        }

        public int getFront() {
            return this.front;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M,SX,SY,D;
    static int[][] map, dir = {{0,1},{0,-1},{-1,0},{1,0}};
    static List<Integer> dices = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        input();
        simulate();
    }

    static void input() throws IOException  {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        SX = Integer.parseInt(st.nextToken());
        SY = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            dices.add(Integer.parseInt(st.nextToken())-1);
        }
    }
    
    static void simulate() {
        StringBuilder sb = new StringBuilder();
        Dice dice = new Dice();

        map[SY][SX] = 0;

        for(int d : dices) {
            int[] direction = dir[d];
            int nx = SX + direction[1];
            int ny = SY + direction[0];

            if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

            SX = nx; SY = ny;
            // 주사위 움직임
            dice.move(d);

            if(map[ny][nx] == 0) {
                // 칸이 0이면 주사위 뒷면 복사
                map[ny][nx] = dice.getBack();
            } else {
                // 칸이 0이 아니면 주사위에 수 복사, 칸 초기화
                dice.setBack(map[ny][nx]);
                map[ny][nx] = 0;
            }

            sb.append(dice.getFront()+"\n");
        }

        System.out.println(sb.toString());
    }
}