import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] str = br.readLine().split(":");
        int hour = Integer.parseInt(str[0]);
        int minute = Integer.parseInt(str[1]);
        
        System.out.println((hour+1) + ":" + minute);
    }
}