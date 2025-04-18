import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SherlockandArray {

    static String solve(int[] a){
        // Complete this function
        
        /*remove from one end and add from other*/
        
        int start = 0;
        int end = a.length - 1;
        int sum = 0;
        
        while(start < end){
            
            if(sum >= 0){
                
                sum -= a[start];
                start++;
                
            }
            
            
            else{
                
                sum += a[end];
                end--;
            }
                   
        }
        
        return sum == 0 ? "YES" : "NO" ;
                
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int a0 = 0; a0 < T; a0++){
            int n = in.nextInt();
            int[] a = new int[n];
            for(int a_i=0; a_i < n; a_i++){
                a[a_i] = in.nextInt();
            }
            String result = solve(a);
            System.out.println(result);
        }
    }
}
