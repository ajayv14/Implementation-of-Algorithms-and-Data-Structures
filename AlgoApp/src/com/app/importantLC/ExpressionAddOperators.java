
// LC 282 : https://leetcode.com/problems/expression-add-operators/description/?

import java.util.List;

class ExpressionAddOperators {

    
    // Generte all combinations
        List<String> finalResult = new ArrayList<>();
        String nums = null;
        float target;
    
        public List<String> addOperators(String num, int target) {
    
            
                if(num == null || num.length() == 0) return new ArrayList<>();
    
                this.nums = num;
                this.target = target;
    
                recursive(0,0,0,0,new ArrayList<>());
    
                return finalResult;
        }
    
    
    
        private void recursive(int index, long previous, long current, long value, List<String> operations){
    
            System.out.println(operations);    
    
            // end of string, check target
            if(index == nums.length()) {
    
                if(value == this.target && current == 0){
    
                    StringBuilder sb = new StringBuilder();
    
                    // Ignore sign + : [+, 1, -, 2, -, 3]
                    for(int i = 1; i < operations.size(); i++){
                        sb.append(operations.get(i));
                    }   
    
                    finalResult.add(sb.toString());
                }     
                
                return;
            }
            
            // Extend operations by one digit
    
            current = (current * 10) + nums.charAt(index) - '0';
            String current_str = Long.toString(current);
    
            //No op, extend the num by a digit each time - 123 -> 12 + 3 or 105 -> 10 + 5
            if(current > 0) {
    
                recursive(index + 1, previous, current, value, operations);
            }
    
    
            // Why start with addition ? 
            // qns : to insert the binary operators '+', '-', and/or '*' between the digits of num
    
            // Addition
            operations.add("+");
            operations.add(current_str);
    
            recursive(index + 1, current, 0, value + current, operations);
    
            operations.remove(operations.size() - 1);
            operations.remove(operations.size() - 1);
    
            // Order of sub and mul can be changed
            if(operations.size() > 0) {
    
                // Subtraction 
                operations.add("-");
                operations.add(current_str);
    
                recursive(index + 1, -current, 0, value - current, operations);
    
                operations.remove(operations.size() - 1);
                operations.remove(operations.size() - 1);
    
                // Multiplication
                operations.add("*");
                operations.add(current_str);
    
                recursive(index + 1, current * previous, 0, value - previous + (current * previous), operations);
    
                operations.remove(operations.size() - 1);
                operations.remove(operations.size() - 1);
    
            }        
        }   
    
    }


    /*  List<String> operations call stack
    
    123, target = 6
    []
    [+, 1]
    [+, 1, +, 2]
    [+, 1, +, 2, +, 3]
    [+, 1, +, 2, -, 3]
    [+, 1, +, 2, *, 3]
    [+, 1, -, 2]
    [+, 1, -, 2, +, 3]
    [+, 1, -, 2, -, 3]
    [+, 1, -, 2, *, 3]
    [+, 1, *, 2]
    [+, 1, *, 2, +, 3]
    [+, 1, *, 2, -, 3]
    [+, 1, *, 2, *, 3]
    */
    
    /*
    
    Adding no-op to generate additional combinations
    123, target = 123
    []
    []
    []
    []
    [+, 123]
    [+, 12]
    [+, 12]
    [+, 12, +, 3]
    [+, 12, -, 3]
    [+, 12, *, 3]
    [+, 1]
    [+, 1]
    [+, 1]
    [+, 1, +, 23]
    [+, 1, -, 23]
    [+, 1, *, 23]
    [+, 1, +, 2]
    [+, 1, +, 2]
    [+, 1, +, 2, +, 3]
    [+, 1, +, 2, -, 3]
    [+, 1, +, 2, *, 3]
    [+, 1, -, 2]
    [+, 1, -, 2]
    [+, 1, -, 2, +, 3]
    [+, 1, -, 2, -, 3]
    [+, 1, -, 2, *, 3]
    [+, 1, *, 2]
    [+, 1, *, 2]
    [+, 1, *, 2, +, 3]
    [+, 1, *, 2, -, 3]
    [+, 1, *, 2, *, 3]
    
     */
    
    /* 105 and target 5 
    
    []
    []
    []
    []
    [+, 105]
    [+, 10]
    [+, 10]
    [+, 10, +, 5]
    [+, 10, -, 5]
    [+, 10, *, 5]
    [+, 1]
    [+, 1, +, 0]
    [+, 1, +, 0]
    [+, 1, +, 0, +, 5]
    [+, 1, +, 0, -, 5]
    [+, 1, +, 0, *, 5]
    [+, 1, -, 0]
    [+, 1, -, 0]
    [+, 1, -, 0, +, 5]
    [+, 1, -, 0, -, 5]
    [+, 1, -, 0, *, 5]
    [+, 1, *, 0]
    [+, 1, *, 0]
    [+, 1, *, 0, +, 5]
    [+, 1, *, 0, -, 5]
    [+, 1, *, 0, *, 5]
    */