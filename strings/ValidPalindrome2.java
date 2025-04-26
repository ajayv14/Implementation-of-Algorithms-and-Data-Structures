public class ValidPalindrome2 {

    public boolean validPalindrome(String s) {
                
        
        int low = 0, high = s.length() - 1;
        
        while(low < high){
            
            if(s.charAt(low) != s.charAt(high)){
                
                // Keep or skip character  
                return isPalindrome(low + 1, high, s) || isPalindrome(low, high - 1, s);
                
            }
            
            else{
                low++;
                high--;
            }                       
        }        
        return true;        
    }
    
    // check if a string is a palindrome
    public boolean isPalindrome(int i, int j, String s){        
                        
        while(i < j){
            
            if(s.charAt(i) != s.charAt(j)) return false;
            
            else {
                i++;
                j--;
            }
        }        
        
        return true;
    }
}
