import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class 2DMatrixAntiClockwiseKRotation {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        
        int M = sc.nextInt();
        int N = sc.nextInt();
        int R = sc.nextInt();
        
        /*array to hold feed value*/
        
        int[][] a = new int[M][N];
        
        /*load values*/
        
        for(int i = 0; i < M; i++){
            
            for(int j = 0; j < N; j++){
                
                a[i][j] = sc.nextInt(); 
                
            }            
        }
        
        
        for(int r = 0; r < R; r++){
            
            rotateArray(a);
        }
                
        printArray(a);     
      }
    
    
    public static void rotateArray(int[][] a){
        
        int row = 0;
        int col = 0;
        int rowEnd = a.length - 1;
        int colEnd = a[0].length - 1;
        
        
        /*Spiral outer to inner*/
        
        while(row < rowEnd && col < colEnd){ 
            
            
            /* <-------- */
            
          int temp = a[row][col];            
            
          for(int i = col; i < colEnd; i++){
                
               a[row][i] = a[row][i + 1];
                
          }
           
       /*
        ^
        |        |
        */      
       
        
        for(int i = row; i < rowEnd; i++){//changed
            
            a[i][colEnd] = a[i + 1][colEnd];
            
        }
            
        
        /*----->*/
        
               
        for(int i = colEnd; i > col; i--){
            
            a[rowEnd][i] = a[rowEnd][i - 1];
            
        }    
            
           
            
         /*
         |
         '      
         */
        
               
        for(int i = rowEnd; i > row; i--){
            
            a[i][col] = a[i - 1][col];
            
        }
        
       
            
        row++;
        a[row][col] = temp;
        
        col++;        
        rowEnd--;        
        colEnd--;
            
        }      
        
        
    }   
    
    
    
    public static void printArray(int[][] a){
        
        for(int i = 0; i <= a.length - 1; i++){
            
            for(int j = 0; j <= a[0].length - 1; j++){
                
                System.out.print(a[i][j] + " ");
                
            }
            
             System.out.println(" ");
        }
        
    }
    
    
}