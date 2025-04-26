class Pow_x_n. {
   
    
    public double myPow(double x, int n) {
        
          
       if(n == 0) return 1.0;
        
       if(n == 1) return x;
        
        if(n < 0){
            
            x = 1 / x;
            n = -n;          
        }
        
        return fastPower(x, n);    
    
    }
    
    
    public double fastPower(double x, int n){
        
       if(n == 0) return 1.0;
        
       double res = fastPower(x , n / 2); // calculate half the values
        
       if(n % 2 == 0){ // even
           
           res = res * res;
           
       } 
        
       else  {
           
           res = res * res * x;
       }
        
        return res;
        
    }
    
    
     /*public double myPow(double x, int n) {
        
        //brute force
         
       if(n == 0) return 1.0;
        
        if(n == 1) return x;
        
        if(n < 0){
            
            x = 1 / x;
            n = -n;          
        }
        
        
        double res = 1;
        
        for(int i = 0; i < n; i++) {
            
            res = res * x;     
            
        }
        
        return res;
        
    }*/
    
    
    
        
        
}