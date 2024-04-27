package com.app.arrays2d;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AntiClockwiseKRotation {

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
        
        
     
            
        rotateArray(a, M, N, R);       
                
        printArray(a);     
      }
    
    
    public static void rotateArray(int[][] a, int M, int N, int R){
        
       /*Number of perimeter rings*/
        
        int numPRings = Math.min(M,N)/2;
        
        int rowEnd = M - 1;
        int colEnd = N - 1;
        
        /*Spiral outer to inner*/
       
        for(int i = 0; i < numPRings; i++){
            
            int rotations =  R%(2*(M + N - 4*i) - 4); 
        
        
            for(int r = 0; r < rotations; r++){ 
            
            
              /* --------> */
              /*together the row++ and colEnd -- is taken care off*/
                 for(int j = i; j < colEnd-i; j++){
                
                     int tmp = a[i][j];
                     a[i][j] = a[i][j + 1];
                     a[i][j + 1] = tmp;
                
                 }
      
       
                /*
               |
               '      
               */
        
               
                for(int j = i; j < rowEnd-i; j++){
            
                     int tmp = a[j][colEnd - i];
                     a[j][colEnd - i] = a[j + 1][colEnd - i];
                     a[j + 1][colEnd - i] = tmp;
            
                }
                
                /*<-----*/    
               for(int j = colEnd-i; j > i; j--){
            
                    int tmp = a[rowEnd-i][j];
                    a[rowEnd-i][j] = a[rowEnd-i][j - 1];
                    a[rowEnd-i][j - 1] = tmp;
                   
               }
        
                /*
               ^
               |                      */
       
               for(int j = rowEnd-i; j > i+1; j--){
            
                   int tmp = a[j][i];
                   a[j][i] = a[j - 1][i];
                   a[j - 1][i] = tmp;
            
              } 
         
    
            
        }      
        
       } // end of outer perimeter loop
        
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