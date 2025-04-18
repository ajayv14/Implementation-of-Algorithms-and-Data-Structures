
/*
     Asteriods with same spin co-exist. One with oppotite spin smashes into other asteriods :
     1. Both are of equal weight and get destroyed in process
     2. Asterioid with -ve spin is tiny and gets destroyed.
     3. Asteriod with  -ve spin is heavier and destroyes the smaller +ve spin one, but it doesn't stop
        It doesn't lose weight in the process, continues to destoy other +ve ones.

        Use monotonic stack. 
    */

    // LC 735 :  https://leetcode.com/problems/asteroid-collision
    // Time O(n), Space : O(n)
    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> stack = new Stack<>();
    
        for(int i = 0; i < asteroids.length; i++){

            boolean destroyed = false;

            // +ve spin meets negative spin
            while(!stack.isEmpty() && (stack.peek() > 0 && asteroids[i] < 0)){
                
                // Note : After exploding the smaller one, Surviving asteroid doesn't lose weight
           
                // stack top is smaller, hence will explode and put the next asterioid in line
                
                if(Math.abs(stack.peek()) < Math.abs(asteroids[i])) {
                    stack.pop();
                    continue;
                }

                // stack top is equal to aster. Both will explods
                else if(Math.abs(stack.peek())== Math.abs(asteroids[i])){
                    stack.pop();                                    
                } 

                destroyed = true; 
                break;  

                                
            }    

            if(!destroyed)stack.push(asteroids[i]);
        }


        // Build result from stack
        int res[] = new int[stack.size()];     
        int k = res.length - 1;
        while(!stack.isEmpty()){

            res[k--] = stack.pop();
        }
        
        return res;        
    }
}
