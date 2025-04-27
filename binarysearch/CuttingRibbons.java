

public class CuttingRibbons {

    // LC 

    // Binary search optimized 
    public int maxLength(int[] ribbons, int k) {

        // k is not the length, but number of ribbons   
   
           // min len is 1, so left boundary is 1, right boundary ?? below
           int max = 0;
   
           for(int i = 0; i < ribbons.length; i++ ){
   
               max = Math.max(max, ribbons[i]);
           }    
   
           // Now length of ribbon is btw 1 and max. We optimize this value by binary search.
   
           // Binary search
           // Maximization problem
           // min length = 1 and max len = max
           // invalid range to begin with 
           // in maximization problem return lo       
           int low = 0, high = max + 1;
   
           while(low + 1 < high){
   
               int mid = low + (high - low)/2;
   
               boolean isPossible = isMaxLenPossible(ribbons, mid, k);
   
               if(isPossible) low = mid;
   
               else high = mid;
           }    
           
           return low;
       }
   
       private boolean isMaxLenPossible(int[] ribbons, int len, int k){
   
           int sum = 0;
   
           for(int r = 0; r < ribbons.length; r++){
   
               sum += Math.floor(ribbons[r] / len);
           }
   
           return sum >= k ? true : false;
   
       }




    // Brute force  
    public int maxLength2(int[] ribbons, int k) {

        // k is not the length, byt number of ribbons   
          
   
           // min len is 1, so left boundary is 1, right boundary ?? below
           int max = 0;
   
           for(int i = 0; i < ribbons.length; i++ ){
   
               max = Math.max(max, ribbons[i]);
           }    
   
           // length of ribbons possible
           for(int l = max; l > 0; l--){
   
               int sum = 0;
   
               for(int j = 0; j < ribbons.length; j++){
   
                   sum += Math.floor(ribbons[j]/l);
               }
   
               if(sum >= k) return l;
               
           }     
   
           return 0;
           
       }
}
