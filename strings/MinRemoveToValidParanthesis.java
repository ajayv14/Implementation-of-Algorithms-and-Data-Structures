import java.util.Stack;





public class MinRemoveToValidParanthesis {


    // LC 1249. Minimum Remove to Make Valid Parentheses
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses

    /*
       For each '(' push  its index i in stack. 
       When a matching ')' is found, pop index i frm stack and move on

       1. ')' encountered when stack is empty - no matching '('. Insert dummy character at that pos - '*'
       2. End of string is reached, but could contain '(' without matching ')'. Those entries are in stack.
          pop one by one and replace by '*'
       3. Do a one pass and replace all '*' by "".


       Why not perform deletes using index, instead of '*' 
       - Delete messes up index positions afterwards, 
       so using dummy and then replacing with empty space works better        
    */

    public String minRemoveToMakeValid(String s) {

        StringBuilder sb = new StringBuilder(s);

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++){

            char c = s.charAt(i);

            if(c == '(') stack.push(i);
                    

            else if (c == ')') {
                
                if(!stack.isEmpty()){
                    stack.pop(); // matching open and close braces
                }

                else sb.setCharAt(i, '*'); // Deal with '}' that do not have a matching '('
            }               
        
        }

        // Deal with '(' that do not have a matching ')'
        while(!stack.isEmpty()){

            int idx = stack.pop();

            sb.setCharAt(idx, '*');
        }   

                 
        return sb.toString().replaceAll("\\*","");
        
    }

}
