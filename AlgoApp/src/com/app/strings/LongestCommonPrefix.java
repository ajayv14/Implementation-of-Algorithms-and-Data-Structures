// credits: https://www.youtube.com/watch?v=1YQmI7F9dJ0

class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        
        String longestCommonPrefix = "";
        
        if(strs.length <= 0 || strs == null) return longestCommonPrefix;       
        
        
        /*for each character check if same character is present in all other words in an array*/        
               
        for(int i = 0; i < strs[0].length(); i++ ){ // each character from 1st string            
            
            for(int j = 1; j < strs.length; j++){   // loop through strings starting from second string 
                 
                 String str = strs[j];
                
                 if(i >= str.length() || str.charAt(i) != strs[0].charAt(i)) return longestCommonPrefix;                              
            }           
            
            longestCommonPrefix += strs[0].charAt(i);            
        }
        
        return longestCommonPrefix;        
    }
    
    
    
    
    public static void main(String[] args){
      
      //String[] strs = {"a"};
      //String[] strs = {"d", "d"};
      String[] strs = {"love", "local", "lowflying"};
      
      LongestCommonPrefix obj = new LongestCommonPrefix();  
         String res = obj.longestCommonPrefix(strs);
         System.out.println(res);
    }  
    
}