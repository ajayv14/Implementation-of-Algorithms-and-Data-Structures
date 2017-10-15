class SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
     
        /*Commented taking n=3 as example*/
        
        int[][] M = new int[n][n];
     
        
        
        int number = 1; /*starting number, increment going further */
        
        int  rowStart = 0;
        int  rowEnd = n-1;
        
        int colStart = 0;
        int colEnd = n-1;
      
        
        
        
    while(rowStart <= rowEnd && colStart <= colEnd){    /* <= to include edge case n=1*?
        
        /* 1st row elements from left to right  ----> 1 2 3..  */
        
        for(int i = colStart; i <= colEnd ; i++){
            
            M[rowStart][i] = number++;            
            
        }
        
        /*right col elements from up to down for every row--  4 5 */
        
        for(int j = rowStart + 1 ; j <= rowEnd ; j++){ //rowStart + 1 as the 1st row - colEnd(last element ) is already filled i e 3
            
            M[j][colEnd] = number++;
            
        }
        
        /*last row elements from right to left -- 6 7 */ 
        
        for(int k = colEnd - 1; k >= colStart; k-- ){ // colEnd - 1 as the last row - colEnd element is already filled i.e 5
            
            M[rowEnd][k] = number++;
            
        }
        
        /*move upwards for each row and fill the first column element -- 8*/        
            
        for(int l = rowEnd - 1; l >= rowStart + 1; l-- ){
            
           M[l][colStart] = number++;
        }
        
        
        /* Now the next inner loop i.e to fill -- 9, increase the rowStart and colStart by 1 and reduce rowEnd and colEnd by 1 each time*/
        
        rowStart++;
        rowEnd--;
        colStart++;
        colEnd--;
        
    }   
        
        
        return M;
        
    }
}