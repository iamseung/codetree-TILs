import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] str = br.readLine().split("-");
        String args1 = str[0];
        String args2 = str[1];
        String args3 = str[2];
        
        System.out.println(args1+"-"+args3+"-"+args2);
    }
}