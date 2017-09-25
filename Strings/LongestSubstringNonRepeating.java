

class LongestSubstringNonRepeating {
    public int lengthOfLongestSubstring(String s) {
        
        int[] map = new int[128];
        
        int start=0,end=0,counter=0,d=0;
        
        while(end<s.length()){ //outer loop
            
            if(map[s.charAt(end)]>0) counter++; 
              
             map[s.charAt(end)]++; // increament char value in map
             end++; // move the end pointer
            
            while(counter>0){  
                
                if(map[s.charAt(start)]>1) counter--; // more than one repeat character  
                   
                map[s.charAt(start)]--; // decrement char value to reset map  array value to 1 
                start++; // move start pointer to slide window
                              
            }
                        
            d = Math.max(d,(end-start)); // current max substring length
                       
        }
        
        return d;
        
    }
}