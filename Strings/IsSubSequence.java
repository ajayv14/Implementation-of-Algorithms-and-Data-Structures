//credits: Kevin Naughton Jr. https://www.youtube.com/watch?v=UulHAjxjZ4o
// https://leetcode.com/problems/is-subsequence/
class IsSubSequence {
    public boolean isSubsequence(String s, String t) {
           
        
    /*logic - pointer approach : run a loop from 0 -> len of t, then if there is a character match, increment the pointer, if pointer >= length of string s, then all chars are present in the same order, hence is subsequence */
    
        
    public boolean isSubsequence(String s, String t) {
        
        
        if(s.equals("")) return true;
        
        
        int index = 0;
        
        for(int i = 0; i < t.length(); i++){
            
            if(s.charAt(index) == t.charAt(i)){
                index++;
            }  
            
            if(index >= s.length()) return true;           
        }        
        
        return  false;        
    }
}

/*Runtime: time complexity - O(n)--> string t and space O(n) */
       
       
       
       
       
       
       
       
       
       
       
        
       
        
       /*
        
        if(s.length() == 0) return true;
        
        int ptrS = 0;
        int ptrT = 0;
        
        while(ptrT < t.length()){ //for every character in t
            
            if(t.charAt(ptrT) == s.charAt(ptrS)){
                
                ptrS++;
                
                if(ptrS == s.length()) return true;
                                
            }
            
            ptrT++;            
            
        }        
        
        return false;
       */
       
       
                      
        
    }




}


