import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;




public class TwoCharacters_Hackerrank {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        String s = in.next();
        
        
        /*add unique characters to a list*/
        List<Character> list = new ArrayList<>();
        
        int maxLen = 0; /*to return alternating string with max length*/
        
        for(char ch : s.toCharArray()){
            
            if(!list.contains(ch)) list.add(ch);
                
        }
        
        /*take combinations of two and format string*/
        
        for(int i = 0; i < list.size(); i++){
            for(int j = i + 1; j  < list.size(); j++){
             
                
               char c1 = list.get(i);
               char c2 = list.get(j);
                
               /*format and remove all other characters except c1 and c2*/        
               String str = formatString(s, c1, c2); /*returns string after removing two characters*/
                
               // System.out.println(str);
                
                /*check for alternating*/
                boolean alternating = true;
                
                for(int k = 0; k < str.length() - 1; k++){
                    
                    if(str.charAt(k) == str.charAt(k + 1)) alternating = false ;
                    
                }
                
                if(alternating == true) {
                    
                    maxLen = (str.length() > maxLen)?str.length(): maxLen;            
                    
                }
                
                
            }            
        }
                
        System.out.println(maxLen);
             
    }
    
    /*format and remove all other characters except c1 and c2*/     
    
    public static String formatString(String s, char c1, char c2){
        
        StringBuilder formatted = new StringBuilder();
           
        for(int l = 0; l < s.length(); l++){
            
            if(s.charAt(l) == c1 || s.charAt(l) == c2) formatted.append(s.charAt(l));
                                
        } 
        
        return formatted.toString();
    }
    
    
}
