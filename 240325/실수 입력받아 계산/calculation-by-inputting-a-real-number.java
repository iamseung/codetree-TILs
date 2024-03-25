import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double A = Double.parseDouble(br.readLine());
        double B = Double.parseDouble(br.readLine());

        System.out.printf("%.2f",B+A);
    }
}