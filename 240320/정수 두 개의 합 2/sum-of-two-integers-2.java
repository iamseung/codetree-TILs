import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int[] arr = new int[MAX_N + 1];
    public static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        k = sc.nextInt();

        for(int i = 1; i <= n; i++)
            arr[i] = sc.nextInt();

        // two pointer를 적용하기 위해
        // 먼저 주어진 숫자들을 정렬해줍니다.
        Arrays.sort(arr, 1, n + 1);

        // 2개의 원소 합이 k 이하인
        // 서로 다른 가짓수를 구해줍니다.
        int ans = 0;

        // 숫자 i에 대해
        // arr[i] + arr[j] <= k를 만족하는 j중
        // 최대의 j가 잡히도록
        // two pointer를 진행합니다.
        int R = n;
        for(int L = 1; L <= n; L++) {
            // 구간 내 합이 k보다 크면 계속 진행합니다.
            while(R != 1 && arr[L] + arr[R] > k)
                R--;

            // R가 L보다 같거나 작아져야만 두 숫자의 합이 k 이내가 된다면
            // 더 이상 진행이 의미가 없으므로 종료합니다.
            if(R <= L)
                break;

            // 현재 숫자 arr[L]에 대해
            // [L + 1, R] 내에 있는 모든 숫자가 정확히
            // arr[L]와의 합이 k 이하가 되는 경우임을
            // 확신할 수 있으므로 R - L를 답에 더해줍니다.
            ans += R - L;
        }

        System.out.print(ans);
    }
}