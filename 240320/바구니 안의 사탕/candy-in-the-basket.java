import java.util.Scanner;
import java.util.Arrays;

class Candy implements Comparable<Candy> {
    int x, cnt;

    public Candy(int x, int cnt) {
        this.x = x;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Candy c) {
        return this.x - c.x;        // x 기준 오름차순 정렬
    }
}

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static Candy[] candies = new Candy[MAX_N + 1];
    public static int n, k;
    
    // 해당 내용물의 위치를 반환합니다.
    public static int getPosOfCandy(int candyIdx) {
        return candies[candyIdx].x;
    }
    
    // 해당 내용물에 들어 있는 사탕 수를 반환합니다.
    public static int getNumOfCandy(int candyIdx) {
        return candies[candyIdx].cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        k = sc.nextInt();
        for(int i = 1; i <= n; i++) {
            int cnt = sc.nextInt();
            int x = sc.nextInt();
            candies[i] = new Candy(x, cnt);
        }

        // x순으로 오름차순 정렬해줍니다.
        Arrays.sort(candies, 1, n + 1);

        // 가능한 구간 중 최대 사탕의 수를 구합니다.
        int ans = 0;
        
        // 구간을 잡아봅니다.
        // 구간 내에 있는 사탕의 수를 계속 계산하여 관리해줍니다.
        int totalNums = 0;
        int R = 0;
        for(int L = 1; L <= n; L++) {
            // 구간의 크기가 2K보다 같거나 작은 경우에 한하여 최대로 진행합니다.
            while(R + 1 <= n && getPosOfCandy(R + 1) - getPosOfCandy(L) <= 2 * k) {
                totalNums += getNumOfCandy(R + 1);
                R++;
            }
            
            // 현재 구간 [i, j]는 
            // i를 시작점으로 하는
            // 가장 긴 구간이므로
            // 구간 내 최대 사탕의 수를 갱신해줍니다.
            ans = Math.max(ans, totalNums);

            // 다음 구간으로 넘어가기 전에
            // L번째에 해당하는 사탕을 구간에서 제외시킵니다.
            totalNums -= getNumOfCandy(L);
        }

        System.out.print(ans);
    }
}