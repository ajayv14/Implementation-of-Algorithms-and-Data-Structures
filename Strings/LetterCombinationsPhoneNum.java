class LetterCombinationsPhoneNum {
    public List<String> letterCombinations(String digits) {
        
        /*Logic - for each digit, make a char array of the associated string in map array and 
                  then insert each char into a FIFO queue - linked list.
                  Then under conditions, remove each char from list and then add that with the characters of next digit
                  2 - [abc], 3-[def] list--> a-->b-->c
                                     remove a and add a + chars of string associated with next digit --> b-->c-->ad-->ae-->af
                                     continue till --> ad-->ae-->af-->bd-->be-->bf-->cd-->ce-->cf
                    */
        
        
        if(digits == null || digits.length() == 0) return new LinkedList<String>();
        
        String[] map = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno" , "pqrs", "tuv", "wxyz"};
        
        
               
        LinkedList<String> list = new LinkedList<String>();
        
                 
        list.add("");
        
        for(int i = 0; i < digits.length(); i++){
            
          //  int num = Integer.parseInt(digits.charAt(i));// does not work with character
            int num = Character.getNumericValue(digits.charAt(i));
                       
            while(list.peek().length() == i) { 
                
                String s = list.remove();
                
                for(char c : map[num].toCharArray()){
                   
                    list.add(s+c);
                    
                }     
                
                
                
            }
            
            
            
            
            
        }
        
        return list;
        
        
    }
}