class IsSubSequence {
    public boolean isSubsequence(String s, String t) {
        
        /*non dp solution*/
        
       //if(t == null || t.length() < 1) return false;
        
        if(s.length() == 0) return true;
        
        int ptrS = 0;
        int ptrT = 0;
        
        while(ptrT < t.length()){ /*for every character in t*/
            
            if(t.charAt(ptrT) == s.charAt(ptrS)){
                
                ptrS++;
                
                if(ptrS == s.length()) return true;
                                
            }
            
            ptrT++;            
            
        }        
        
        return false;
               
        
    }




}


