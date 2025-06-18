class Roman2Integer {

    
    public int romanToInt(String s) {
        
        Map<Character,Integer> map = new HashMap<>();

        map.put('I',1);
         map.put('V',5);
          map.put('X',10);
           map.put('L',50);
            map.put('C',100);
             map.put('D',500);
              map.put('M',1000);


        int res = 0;

        int cur = 0;    
        int prev = 0;

        for(int i = s.length() - 1; i >= 0; i--){

            char c = s.charAt(i);

            cur = map.get(c);

            if(cur < prev) res -= cur; 
            
            // IV from right to left. Initially we process 'V' = 5, then later "I", so subtract 1 from result to compensate 

            else res += cur;

            prev = cur;
            
        }      

        return res;

    }

    
}