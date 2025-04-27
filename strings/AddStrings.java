// LC 415 https://leetcode.com/problems/add-strings/?

public class AddStrings {

    public String addStrings(String num1, String num2) {
 
        StringBuilder sb = new StringBuilder();
    
        int remainder = 0;    
    
        int len1 = num1.length(), len2 = num2.length();
    
        int len  =  len1 > len2 ? len1 : len2;    
    
        for(int i = 0; i < len; i++){
    
            int l1 =  i >= len1  ? 0 : num1.charAt(len1 - 1 - i) - '0';
            int l2 =  i >= len2 ? 0 : num2.charAt(len2 - 1 - i) - '0';
    
            //System.out.println("l1 : " + l1 + " l2 : " + l2);
    
            int res = l1 + l2 + remainder;
    
            //System.out.println("res : " + res);
    
            sb.append(res % 10); 
            remainder = res / 10;           
            
        }
    
        if(remainder > 0) sb.append(remainder);
        return sb.reverse().toString();
    
        }
}
