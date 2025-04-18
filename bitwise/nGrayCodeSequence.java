class nGrayCodeSequence {
    public List<Integer> grayCode(int n) {
       /* 
          outer loop - //1<<n left shift 1 left shifted n times, loop will run for: 2 times for n=1 --> to help generate [0,1]
                                                                                    4       for n=2 --> [00,01,10,11]
                                                                                    8       for n=3 --> ....
                                                                                    
       */
        
        //List to hold the outputs
        List<Integer> op = new ArrayList<>();
        
        for(int i=0;i< 1<<n;i++){  
               op.add(i^i/2); // or (i^i>>1)//i>>1 right shift divides by 2 // gray code formula i XOR i/2                                                           
        }
        
        
        return op;
        
    }
}