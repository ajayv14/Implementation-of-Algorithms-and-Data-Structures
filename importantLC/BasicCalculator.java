import java.util.Stack;


// LC 224 : https://leetcode.com/problems/basic-calculator

public class BasicCalculator {

    public int calculate(String s) {
        
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operator = new Stack<>();

        int num = 0;
        int res = 0;
        char sign = '+';

        for(int i = 0; i < s.length(); i++){

            char c = s.charAt(i);

            if(Character.isDigit(c)){

                num = num * 10 + (c - '0');  

                //System.out.println("num : " + num);              
            }

            else if(!Character.isDigit(c) || c == ' ' || i == s.length() - 1){              

                if(c == '+' || c == '-'){           

                    int op = sign == '+' ? 1 : -1;    

                    res += op * num;
                    num = 0; // reset
                    sign = c;  // update previous sign
                } 

                else if(c == '('){

                    numStack.push(res);
                    operator.push(sign);
                    
                    res = 0; // reset
                    sign = '+'; // reset
                }
                else if(c == ')'){

                    // pop and evaluate
                    res += (sign == '+' ? 1 : -1) * num;

                    int op = operator.pop() == '+' ? 1 : -1;

                    res *= op;
                    res += numStack.pop();

                    num = 0;
                }                

            }         
            
        }

        int op = sign == '+' ? 1 : -1; 
        res += op * num;

        return res;  
    
    }
  
}
