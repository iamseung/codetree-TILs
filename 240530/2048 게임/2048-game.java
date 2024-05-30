import java.util.Scanner;

public class Main {
    public static final int NONE = -1;
    public static final int NUM_MOVES = 5;
    public static final int MAX_N = 20;
    
    public static int n;
    
    public static int[][] grid = new int[MAX_N][MAX_N];
    public static int[][] nextGrid = new int[MAX_N][MAX_N];
    public static int[][] temp = new int[MAX_N][MAX_N];
    
    public static int[] moveDirs = new int[NUM_MOVES];
    
    public static int ans;
    
    public static int getMaxBlockNum() {
        int maxNum = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                maxNum = Math.max(maxNum, grid[i][j]);
        
        return maxNum;
    }
    
    // grid를 시계방향으로 90' 회전시킵니다.
    public static void rotate() {
        // nextGrid를 0으로 초기화합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = 0;
        
        // 90' 회전합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = grid[n - j - 1][i];
        
        // nextGrid를 grid에 옮겨줍니다.
         for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = nextGrid[i][j];
    }
    
    // 아래로 숫자들을 떨어뜨립니다.
    public static void drop() {
        // nextGrid를 0으로 초기화합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = 0;
        
        // 아래 방향으로 떨어뜨립니다.
        for(int j = 0; j < n; j++) {
            // 같은 숫자끼리 단 한번만
            // 합치기 위해 떨어뜨리기 전에
            // 숫자 하나를 keep해줍니다.
            int keepNum = NONE, nextRow = n - 1;
            for(int i = n - 1; i >= 0; i--) {
                if(grid[i][j] == 0) continue;
                
                // 아직 떨어진 숫자가 없다면, 갱신해줍니다.
                if(keepNum == NONE)
                    keepNum = grid[i][j];
                // 가장 최근에 관찰한 숫자가 현재 숫자와 일치한다면
                // 하나로 합쳐주고, keep 값을 비워줍니다.
                else if(keepNum == grid[i][j]) {
                    nextGrid[nextRow--][j] = keepNum * 2;
                    keepNum = NONE;
                }
                // 가장 최근에 관찰한 숫자와 현재 숫자가 다르다면
                // 최근에 관찰한 숫자를 실제 떨어뜨려주고, keep 값을 갱신해줍니다.
                else {
                    nextGrid[nextRow--][j] = keepNum;
                    keepNum = grid[i][j];
                }
            }
            
            // 전부 다 진행했는데도 keep 값이 남아있다면
            // 실제로 한번 떨어뜨려줍니다.
            if(keepNum != NONE)
                nextGrid[nextRow--][j] = keepNum;
        }
        
        // nextGrid를 grid에 옮겨줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = nextGrid[i][j];
    }
    
    // moveDir 방향으로 기울이는 것을 진행합니다.
    // 회전을 규칙적으로 하기 위해
    // 아래, 오른쪽, 위, 왼쪽 순으로 dx, dy 순서를 가져갑니다.
    public static void tilt(int moveDir) {
        // Step 1.
        // moveDir 횟수만큼 시계방향으로 90'회전하는 것을 반복하여
        // 항상 아래로만 숫자들을 떨어뜨리면 되게끔 합니다.
        for(int i = 0; i < moveDir; i++)
            rotate();
    
        // Step 2.
        // 아래 방향으로 떨어뜨립니다.
        drop();
        
        // Step 3.
        // 4 - moveDir 횟수만큼 시계방향으로 90'회전하는 것을 반복하여
        // 처음 상태로 돌아오게 합니다. (총 360' 회전)
        for(int i = 0; i < 4 - moveDir; i++)
            rotate();
    }
    
    public static void simulate() {
        // 시뮬레이션 전 상황을 저장해놓습니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                temp[i][j] = grid[i][j];
        
        // 각 방향으로 기울이는 것을 진행합니다.
        for(int i = 0; i < NUM_MOVES; i++)
            tilt(moveDirs[i]);
        
        // 답을 갱신합니다.
        ans = Math.max(ans, getMaxBlockNum());
        
        // grid를 시뮬레이션 전 상황으로 되돌립니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = temp[i][j];
    }
    
    public static void searchMaxNum(int cnt) {
        // 5번 이동할 방향을 정했다면
        // 직접 시뮬레이션을 진행합니다.
        if(cnt == NUM_MOVES)  {
            simulate();
            return;
        }
    
        // 4 방향 중 이동할 방향을 선택합니다.
        for(int i = 0; i < 4; i++) {
            moveDirs[cnt] = i;
            searchMaxNum(cnt + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        
        searchMaxNum(0);
        
        System.out.print(ans);
    }
}