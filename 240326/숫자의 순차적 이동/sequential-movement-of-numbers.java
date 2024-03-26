import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
    static int N,M;
    static int[][] arr;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new Node[N*N+1];

        arr = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                nodes[arr[i][j]] = new Node(j, i); // x, y
            }
        }

        while (M-- >0) {
            process();
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sb.append(arr[i][j]+" ");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void process() {
        // 각 원소별 탐색
        for(int i=1; i<=N*N; i++) {
            Node cur = nodes[i];

            // 가장 큰 원소를 찾음
            Node findedNode = findBigestNode(cur.x, cur.y);

            // 위치 변경
            switchNode(cur, findedNode);
        }
        
    }

    static Node findBigestNode(int x, int y) {
        int max = 0;
        int findX = 0, findY = 0;

        for(int k=0; k<8; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];

            if(nx<0 || ny<0 || nx>=N || ny>=N) continue;

            if(arr[ny][nx] > max) {
                max = arr[ny][nx];
                findX = nx; findY = ny;
            }
        }

        return new Node(findX, findY);
    }

    static void switchNode(Node cur, Node target) {
        int before = arr[cur.y][cur.x];
        int after = arr[target.y][target.x];

        // 노드간 좌표 스위칭
        nodes[before] = new Node(target.x, target.y);
        nodes[after] = new Node(cur.x, cur.y);

        // 맵 좌표 스위칭
        arr[target.y][target.x] = before;
        arr[cur.y][cur.x] = after;
    }
}