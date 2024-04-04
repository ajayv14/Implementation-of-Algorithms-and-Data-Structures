public class FlippingImage {
    public int[][] flipAndInvertImage(int[][] A) {
    
      int m = A[0].length;
        
      for(int[] row : A){   // loop thro rows
                    
        for(int c = 0; c < (m + 1)/2; c++){ 
            int temp = row[c] ^ 1;
            row[c] = row[m - 1 - c] ^ 1;
            row[m - 1 - c] = temp;
        }          
      }
        return A;
    } 
}


/* (m+1)/2 is to stop the loop at the center including the center while reversing teh 1D array

    5 - 4 - 3 - 2 - 1
    
    1 - 4 - 3 - 2 - 5
    
    1 - 2 - 3 - 4 - 5 

*/