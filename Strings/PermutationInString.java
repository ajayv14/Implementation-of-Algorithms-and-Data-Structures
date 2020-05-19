class PermutationInString {
    
    /**
          **Same as finding all anagrams in a string**         
     
      * make a frequency map of characters in str p
      * create a window start, end at 0 and expand till it reaches length of p
      * while expanding check if each char in str s is present in freq. array. Decrement it 
      * Decrement the count if char matches with the freq array and is >= 1
      * match is found when count == 0
      * if not, expand the window further by pushing start to the right and bump up the freq arr for each car. 
      * If char is part of p, then bump the count as well.    
        
    **/
    
    
    public boolean checkInclusion(String s1, String s2) {
        
        
         
        
        int[] freq = new int[26];
        
        /*build a freq map of chars in s1*/
        for(char ch : s1.toCharArray()){
            freq[ch - 'a']++;                     
        }
        
        int start = 0, end = 0, count = s1.length();
        
        /*loop thgrough s2*/        
        while(end < s2.length()){
            
            char ch = s2.charAt(end);            
                        
            if(freq[ch - 'a'] >= 1) count --;
            
            freq[ch - 'a']--;
            end++;    
                          
            if(count == 0) return true;
            
            /*window size == length of s1, expand*/
            if(end - start == s1.length()){
                
                if(freq[s2.charAt(start) - 'a'] >= 0){
                    count++;
                }
                
                freq[s2.charAt(start) - 'a']++;
                start++;                
            }
            
        }
        
        return false;
        
    }
}