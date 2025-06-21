class PermutationInString {
   
       
     /**
          **Same as chek for anagram**         
     
      * make a frequency map of characters in str p
      * create a window start, end at 0 and expand till it reaches length of p
      * while expanding check if each char in str s is present in freq. array. Decrement it 
      * Decrement the count if char matches with the freq array and is >= 1
      * match is found when count == 0
      * if not, expand the window further by pushing start to the right and bump up the freq arr for each car. 
      * If char is part of p, then bump the count as well.    
        
    **/
    
    
    public boolean checkInclusion(String s1, String s2) {
        
                
        // Generate a freq map
        int[] freq = new int[26];
        
        // Freq Map
        for(char ch : s1.toCharArray()){
            freq[ch - 'a']++;                     
        }
        
        int left = 0, right = 0, count = s1.length();
                    

        while(right < s2.length()){
            
            char ch = s2.charAt(right);            
                        
            if(freq[ch - 'a'] > 0) count--;                         
        
            freq[ch - 'a']--;         
            right++;   
                          
            if(count == 0) return true;
            
            // Once window size equals lenght of string s1 and we haven't found a permurtation, time to move the left pointer
            if(right - left == s1.length()){

                char leftCh =  s2.charAt(left);          

                if(freq[leftCh - 'a'] >= 0) count++;
                                
                freq[leftCh - 'a']++;
                left++;                
            }
            
        }
        
        return false;
        
    }
}