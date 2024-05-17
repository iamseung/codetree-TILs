import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
    static class Item {
        int value; // 만족도
        int size; // 공간의 크기

        public void setValue(int v) {
            this.value = v;
        }

        public void setSize(int s) {
            this.size = s;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static List<Item> items;
    
    public static void main(String[] args) throws IOException {
        input();
        simulate();
    }

    static void input() throws IOException  {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물건의 개수
        M = Integer.parseInt(st.nextToken()); // 만족 총합 M 이상

        items = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            Item item = new Item();
            item.setValue(Integer.parseInt(st.nextToken()));
            items.add(item);
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            Item item = items.get(i);
            item.setSize(Integer.parseInt(st.nextToken()));
        }
    }
    
    static void simulate() {
        /*
         * int value; // 만족도
         * int size; // 공간의 크기
         * 만족도의 총합이 m 이상이 되도록 할 때 공간 크기의 총합의 최솟값을 출력
         */

        Collections.sort(items, (e1, e2) -> {
            if(e1.size == e2.size) return e1.value - e2.value;
            return e1.size - e2.size;
        });

        int index = 0, sum = 0, answer = 0;
        while(sum < M && index < N) {
            Item cur = items.get(index);
            sum += cur.value;
            answer += cur.size;
            index++;
        }
        
        System.out.println(sum < M ? -1 : answer);
    }
}