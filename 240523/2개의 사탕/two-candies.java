import java.util.*;
import java.io.*;

class Pair {
    int x, y;
    
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSame(Pair p) {
        return this.x == p.x && this.y == p.y;
    }
}

class Candies {
    Pair red, blue;

    public Candies(Pair red, Pair blue) {
        this.red = red;
        this.blue = blue;
    }
}

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_M = 10;
    public static final int MAX_N = 10;
    
    // 전역 변수 선언:
    public static int n, m;
    public static char[][] a = new char[MAX_N][MAX_M];
    
    // 빨간색, 파란색 사탕의 위치를 저장할 변수입니다. 
    public static Pair redPos;  
    public static Pair bluePos;
    
    
    // bfs에 필요한 변수들 입니다.
    public static Queue<Candies> q = new LinkedList<>();
    public static boolean[][][][] visited = new boolean[MAX_N + 1][MAX_M + 1][MAX_N + 1][MAX_M + 1];
    public static int[][][][] step = new int[MAX_N + 1][MAX_M + 1][MAX_N + 1][MAX_M + 1];
    
    // MAP 밖으로 나왔다는 의미로 
    // 편의상 절대 맵 안에서는 될 수 없는 위치인 (MAX_N, MAX_M)로 설정했습니다.
    public static final Pair OUT_OF_MAP = new Pair(MAX_N, MAX_M);
    public static int ans = INT_MAX;

    // 파란색이 맵에 남아있는지 판단합니다.
    public static boolean blueExist() {
        return bluePos != OUT_OF_MAP;
    }
    
    // 빨간색이 맵에 남아있는지 판단합니다.
    public static boolean redExist() {
        return redPos != OUT_OF_MAP;
    }

    // 상자를 dir방향으로 기울였을 때
    // 파란색 보다 빨간색을 무조건 먼저 움직여야 하는지 판단합니다.
    public static boolean redMustFirst(int dir) {
        int rx = redPos.x, ry = redPos.y;
        int bx = bluePos.x, by = bluePos.y;
    
        if(dir == 0)
            return (ry == by && rx < bx);
        else if(dir == 1)
            return (ry == by && rx > bx);
        else if(dir == 2)
            return (rx == bx && ry < by);
        else
            return (rx == bx && ry > by);
    }
    
    // (x, y)로 진행이 가능한지 판단합니다.
    // 더 진행하기 위해서는 해당 위치에 벽이나 사탕이 없어야 합니다.
    public static boolean canGo(int x, int y) {
        return a[x][y] != '#' && !new Pair(x, y).isSame(redPos) 
               && !new Pair(x, y).isSame(bluePos);
    }
    
    // pos(x, y)에서 dir 방향으로 이동했을 때 
    // 최종적으로 도착하는 위치를 반환합니다.
    public static Pair getDestination(Pair pos, int dir) {
        int[] dx = new int[]{-1, 1,  0, 0};
        int[] dy = new int[]{ 0, 0, -1, 1};
    
        int nx = pos.x + dx[dir], ny = pos.y + dy[dir];
    
        if(!canGo(nx, ny))
            return pos;
        
        if(a[nx][ny] == 'O')
            return OUT_OF_MAP;
    
        return getDestination(new Pair(nx, ny), dir);
    }
    
    // dir 0, 1, 2, 3는 각각 상하좌우를 의미합니다.
    public static void tilt(int dir) {
        if(redMustFirst(dir)) {
            redPos = getDestination(redPos, dir);
            bluePos = getDestination(bluePos, dir);
        }
        else {
            bluePos = getDestination(bluePos, dir);
            redPos = getDestination(redPos, dir);
        }
    }
    
    // 새로운 (빨간색 사탕, 파란색 사탕) 정보를 추가합니다. 
    public static void push(Pair redPos, Pair bluePos, int newStep) {
        q.add(new Candies(redPos, bluePos));
        visited[redPos.x][redPos.y][bluePos.x][bluePos.y] = true;
        step[redPos.x][redPos.y][bluePos.x][bluePos.y] = newStep;
    }
    
    // BFS를 통해 최소 이동 횟수를 구합니다.
    public static void findMin() {
        while(!q.isEmpty()) {
            redPos = q.peek().red;
            bluePos = q.peek().blue;
            int currStep = step[redPos.x][redPos.y][bluePos.x][bluePos.y];
    
            q.poll();
    
            // 이미 둘 중 하나의 사탕이 상자 안에 없다면 더 이상 진행할 필요가 없습니다.
            if(!blueExist() || !redExist())
                continue;
    
            // 4 방향 중 한 방향을 정하여 움직입니다.
            for(int dir = 0; dir < 4; dir++) {
                // Tilt를 하면 blue, red 사탕의 위치가 바뀌게 되므로, 
                // tilt전 위치를 저장해 놓습니다.
                Pair tempRed = redPos;
                Pair tempBlue = bluePos;
                
                // dir 방향으로 기울여 사탕의 위치를 변경합니다.
                tilt(dir);
                
                // 아직 방문한 적이 없는 상태라면, 새로 queue에 넣어줍니다.
                if(!visited[redPos.x][redPos.y][bluePos.x][bluePos.y])
                    push(redPos, bluePos, currStep + 1);
    
                // Tilt 전 상태로 복원하여 그 다음 방향으로도 동일한 기회를
                // 주도록 합니다.
                redPos = tempRed;
                bluePos = tempBlue;
            }
        }
    
        // 빨간색은 상자 밖으로 나갔지만 파란색은 아직 상자 안에 있는 경우 중
        // 최솟값을 구해줍니다.
        for(int bx = 0; bx < n; bx++)
            for(int by = 0; by < m; by++)
                if(visited[OUT_OF_MAP.x][OUT_OF_MAP.y][bx][by])
                    ans = Math.min(ans, 
                              step[OUT_OF_MAP.x][OUT_OF_MAP.y][bx][by]);
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 0; i < n; i++) {
            String row = sc.next();
            for(int j = 0; j < m; j++) {
                a[i][j] = row.charAt(j);

                // 사탕의 경우 위치를 저장해두고, 맵에서는 지워줍니다.
                if(a[i][j] == 'R') {
                    a[i][j] = '.';
                    redPos = new Pair(i, j);
                }
                if(a[i][j] == 'B') {
                    a[i][j] = '.';
                    bluePos = new Pair(i, j);
                }
            }
        }

        // bfs를 이용해 최소 이동 횟수를 구합니다.
        push(redPos, bluePos, 0);
        findMin();

        // 출력:
        if(ans > 10) // 10번 이내로 답을 구할 수 없다면
            ans = -1; // -1을 답으로 넣어줍니다.
        
        System.out.print(ans);
    }
}