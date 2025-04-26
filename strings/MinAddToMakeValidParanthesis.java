import java.util.Stack;

public class MinAddToMakeValidParanthesis {

    // LC 921 : https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
    
    public int minAddToMakeValid(String s) {


        int count = 0;
        
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()){

            if(c == '(') stack.push(c);

            else if(c == ')') {

                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();

                else {
                    stack.push(c);
                    count++;
                }
            }

            
        }

        return stack.size();
        
    }
}
