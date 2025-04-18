import java.io.*;
import java.util.*;

public class UncoupledInteger {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(",");
        
        
        /*actual code*/
        int x = 0;    /*just xor with all other numbers*/
        for(int i = 0; i < input.length; i++){
            x = x ^ Integer.parseInt(input[i]);
        }
        
        System.out.println(x);    
    }
}