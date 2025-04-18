import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class InsertionSortPart1 {
    
    

    public static void insertIntoSorted(int[] a) {
        // Fill up this function  
        
        int n = a.length - 1;
        
        int temp = a[n];
                
        boolean completed = false;
        
        for(int i = n - 1 ; i >= 0; i--){
            
                   
                if(a[i] > temp){
             
                    a[i + 1] = a[i];
                     printArray(a);
                    
                } 
                
                
                else if (a[i] < temp) {
                    
                    a[i + 1] = temp;
                    printArray(a);                        
                    completed = true; 
                    break;
                }     
                
        }
        
        
        if(!completed){
            
            a[0] = temp;
            printArray(a); 
                    
        }
               
    }
    
       
    
    
    
/* Tail starts here */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] ar = new int[s];
        for(int i=0;i<s;i++){
            ar[i]=in.nextInt(); 
        }
        insertIntoSorted(ar);
    }
    
    
    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
 
