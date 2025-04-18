class HappyNumber
 {
    public boolean isHappy(int n) {
                
        while(n != 1){
                                 
            int digitSquaredSum = 0;
            
            /*split digit, square and add it.*/
            
            while(n > 0){
                
                int digit = n % 10;
                digitSquaredSum += digit * digit;
                n = n / 10; 
            }
            
            if(digitSquaredSum == 4) return false; // unhappy number like 2, 11..
            
            n = digitSquaredSum;            
            
        }
        
        return true;
        
    }
}