package com.app.matrix;

public class FloodFill {
    
    // logic: similar to sinking island,
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        
        if(image[sr][sc] == newColor) return image; //already filled 
                
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }    
    
    private void dfs(int[][] image, int i, int j, int currentColor,int newColor){
                     
        //check boundaries
        if( i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != currentColor) {       
             return;            
        }    
       
        //System.out.println(i + " " + j + " " + targetColor + " " + image[i][j]);
        
        image[i][j] = newColor;
               
        dfs(image, i - 1, j, currentColor, newColor);
        dfs(image, i + 1, j, currentColor, newColor);
        dfs(image, i, j - 1, currentColor, newColor);
        dfs(image, i, j + 1, currentColor, newColor);                      
        
    }
}