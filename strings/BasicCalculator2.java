package com.app.strings;

public class BasicCalculator2 {

    public int calculate(String s) {
                
        int res = 0;

        int lastNumber = 0;
        int num = 0;

        char operator = '+';

        for(int i = 0; i < s.length(); i++){
            
            Character ch = s.charAt(i);

           // if(Character.isWhitespace(ch)) continue;

            if(Character.isDigit(ch)){
                num = (num * 10) + Character.getNumericValue(ch); 
            }           
           
            
            if(!Character.isDigit(ch) && !Character.isWhitespace(ch)|| i == s.length() - 1){

               if(operator == '+') {
                       
                    res += lastNumber;
                    lastNumber = num;
                }

                else if(operator == '-') {
                    res += lastNumber;
                    lastNumber = - num;
                }         
                else if(operator == '/') {
                    lastNumber /= num; 
                }

                else if(operator == '*'){
                    lastNumber *= num;
                }                
                
                operator = ch;
                num = 0;                
            }
        }

        res += lastNumber;
        return res;       

        
    }

}
