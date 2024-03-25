import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] str = br.readLine().split("-");
        int month = Integer.parseInt(str[0]);
        int day = Integer.parseInt(str[1]);
        int year = Integer.parseInt(str[2]);
        
        System.out.println(year + "." + month + "." + day);
    }
}