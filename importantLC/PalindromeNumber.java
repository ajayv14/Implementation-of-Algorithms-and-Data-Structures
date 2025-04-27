
// LC 9 https://leetcode.com/problems/palindrome-number

public class PalindromeNumber {

    public boolean isPalindrome(int x) {
                        
        int t = x;     
        
        int newNumber = 0, rem = 0;
        
        while(x > 0){
            
            rem = x % 10;            
            newNumber = newNumber * 10 + rem;           
            x = x/10;    
        } 
        
        return (t - newNumber == 0)? true : false;
        
    }    

}
