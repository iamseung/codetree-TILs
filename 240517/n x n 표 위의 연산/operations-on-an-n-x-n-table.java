import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static char[][] map;
    static int[][] dir = {{1,0},{0,1}};
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        input();
        simulate();
    }

    static void input() throws IOException  {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }
    }
    
    static void simulate() {
        int init = map[0][0]-'0';
        visited[0][0] = true;

        dfs(0, 0, init, ' ');
        System.out.println(max + " " + min);
    }

    static void dfs(int x, int y, int cost, char sign) {
        if(x == N-1 && y == N-1) {
            min = Math.min(min, cost);
            max = Math.max(max, cost);
            return;
        }

        for(int k=0; k<2; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];
            int nextCost = cost;

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(visited[ny][nx]) continue;

            if(isSign(map[ny][nx])) {
                // 다음 좌표가 부호인 경우, sign 을 갱신
                sign = map[ny][nx];
            } else {
                // 부호가 아닌 경우, cost 를 갱신
                nextCost = cal(sign, cost, map[ny][nx]-'0');
            }

            visited[ny][nx] = true;
            dfs(nx, ny, nextCost, sign);
            visited[ny][nx] = false;
        }

    }

    static boolean isSign(char c) {
        boolean is = false;
        char[] signs = {'+', '-', 'x'};

        for(char sign : signs)
            if(c == sign) is = true;

        return is;
    }

    static int cal(char sign, int total, int cur) {
        int res = 0;
        switch (sign) {
            case '+':
                res = total + cur;
                break;
            case '-':
                res = total - cur;
            break;
            case 'x':
                res = total * cur;
            break;
            case ' ':
            default:
                res = total;
                break;
        }

        return res;
    }
}