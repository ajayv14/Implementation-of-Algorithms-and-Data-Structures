public class MinRemoveToValidParanthesis {


    class ValidParanthesis {
        public boolean isValid(String s) {
            
            Stack<Character> stack = new Stack<>();
            char[] ch = s.toCharArray();
    
            for(char c : ch){            
                if(c == '{') stack.push('}');            
                else if(c=='(') stack.push(')');            
                else if(c=='[') stack.push(']');            
                else if(stack.isEmpty() || stack.pop() != c) return false;         
            }     
               
            return stack.isEmpty();    
            
        }
    }

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



//credits: https://leetcode.com/problems/valid-palindrome/

class ValidPalindrome {
   
   
    public boolean isPalindrome(String s){
    
       int start = 0;
       int end = s.length() - 1;
       
       while(start < end){
          
          if(!Character.isLetterOrDigit(s.charAt(start))){
             start++;
          }
          
          if(!Character.isLetterOrDigit(s.charAt(end))){
             end--;
          }
          
          if(Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))){
             return false;
          }
       
          else{
             start++;
             end--;
          }
       }  
        return true;   
    }
} 


public class ValidPalindrome2 {

    public boolean validPalindrome(String s) {
                
        
        int low = 0, high = s.length() - 1;
        
        while(low < high){
            
            if(s.charAt(low) != s.charAt(high)){
                
                // Keep or skip character  
                return isPalindrome(low + 1, high, s) || isPalindrome(low, high - 1, s);
                
            }
            
            else{
                low++;
                high--;
            }                       
        }        
        return true;        
    }
    
    // check if a string is a palindrome
    public boolean isPalindrome(int i, int j, String s){        
                        
        while(i < j){
            
            if(s.charAt(i) != s.charAt(j)) return false;
            
            else {
                i++;
                j--;
            }
        }        
        
        return true;
    }
}





public class ValidWordAbbreviation {

    //408 Valid Word Abreviation 
    // https://leetcode.com/problems/valid-word-abbreviation/description/

    // Time O(m + n) Space O(1)

    // Remember a word can contain a-z only.
    public boolean validWordAbbreviation(String word, String abbr) {

        if(abbr.length() > word.length()) return false;

        int wIndex = 0, abbrIndex = 0;

        while(abbrIndex < abbr.length() && wIndex < word.length()){

            char wC = word.charAt(wIndex);
            char wAbbr = abbr.charAt(abbrIndex);

            if(wC == wAbbr) {
                wIndex++;
                abbrIndex++;
            }

            else if(Character.isDigit(wAbbr)){


                if(wAbbr == '0') return false;

                String num = "";

                while(abbrIndex < abbr.length() && Character.isDigit(abbr.charAt(abbrIndex))){
                    num += abbr.charAt(abbrIndex);
                    abbrIndex++;
                }              

                wIndex += Integer.parseInt(num);
                                               
            }

            

            else return false;

        }

        return wIndex == word.length() && abbrIndex == abbr.length();      
       
        
    }

}




public class MinAddToMakeValidParanthesis {

    // LC 921 : https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
    
    public int minAddToMakeValid(String s) {


        int count = 0;
        
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()){

            if(c == '(') stack.push(c);

            else if(c == ')') {

                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();

                else {
                    stack.push(c);
                    count++;
                }
            }

            
        }

        return stack.size();
        
    }
}




// Split path by '/'. Use stack to track directories. Skip empty and '.' entries. For '..' pop from stack if not empty. 
//Finally, build path from stack elements with '/' separator.

// LC - 71. Simplify Path
// https://leetcode.com/problems/simplify-path

public class SimplifyPath {

    public String simplifyPath(String path) {

        String[] str = path.split("/");

        List<String> components = new LinkedList<>();

        StringBuilder sb = new StringBuilder();    

        for(int i = 0; i < str.length; i++){

            String s = str[i];

            if(s.equals("") || s.equals(".") ){
                continue;
            }
            
            else if(s.equals("..")){

                if(components.size() > 0)  components.removeLast();                 

            }
    

            else {                  
                  components.add(s);
            }

                     
        }

        for(String component : components){
            sb.append("/");
            sb.append(component);
        }
        
        return sb.length() == 0 ? "/" : sb.toString();
    }

}



// LC 791 : https://leetcode.com/problems/custom-sort-string

public class CustomSortString {


    // 1.  Non - optimized solution

    // Order string -> Add character and its index to a map. 
    // Use this id/index to sort string s using custom comparator based on map values
    public String customSortString(String order, String s) {
        
        // Character, Index
        Map<Character, Integer> sortOrder = new HashMap<>();

        int id = 0;

        for(char c : order.toCharArray()){

            sortOrder.put(c, id++);
        }

        char[] arr = s.toCharArray(); // Cant directly sort using Arrays.sort()
        Character[] a = new Character[arr.length];


        for (int i = 0; i < arr.length; i++) {
            a[i] =  arr[i];
        }
       

        Arrays.sort(a,(x,y) -> checkMap(x, sortOrder) - checkMap(y, sortOrder));

        StringBuilder sb = new StringBuilder();

        for(char c : a) sb.append(c);
        
        return sb.toString();

    }

    private int checkMap(char c, Map<Character,Integer> sortOrder){

        if(sortOrder.containsKey(c)) return sortOrder.get(c);

        else return 100;
    }


    

    // 2. Optimized version using frequency hash map
    // Example  order - "abcx" s - "cbdab" ; Expected -> abbcd
    // Approach 

    //Count frequence of occurence of each word in s
    // cbdab -> c = 1, b = 2, a = 1, d = 1 
    // Pick order from order string "abcx" using pointer, get freq from map and apply to s : a  bb  c d
    
    public String customSortStringOpt1(String order, String s) {
        
        StringBuilder res = new StringBuilder();    

        Map<Character, Integer> freq = new HashMap<>();

        for(char c : s.toCharArray()){
                
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }    

        for(int l = 0; l < order.length(); l++){

            char co = order.charAt(l); 

            if(freq.containsKey(co)) {

                int occurence = freq.get(co);
                freq.remove(co);

                for(int i = 0; i < occurence; i++){
                    res.append(co);
                }            
            }
        }    

        for(char key : freq.keySet()){

            int occurence = freq.get(key);

            for(int i = 0; i < occurence; i++){

                res.append(key);
            }
        }

        return res.toString();
    }        


    // 3. Optimized using array to store freq


     //Count frequence of occurence of each word in s
    // abbc -> a = 1, b = 2, c = 1
    // Pick order from order string "abcx" using pointer, get freq from map and apply to s -> a, bb, c
    // Converting int to alphabet character : (char) ('a' + num - 1)
    
    public String customSortStringOpt2(String order, String s) {
        
        StringBuilder res = new StringBuilder();    

        int[] freq = new int[26];

        for(char c : s.toCharArray()){

            freq[c - 'a']++;      
           
        }    

        for(int l = 0; l < order.length(); l++){

            char co = order.charAt(l); 

            if(freq[co - 'a'] > 0) {

                int occurence = freq[co - 'a'];
                
                freq[co - 'a'] = 0;

                for(int i = 0; i < occurence; i++){
                    res.append(co);
                }            
            }
        }    

        for(int j = 0; j < freq.length; j++){

            int occurence = freq[j];

            if(freq[j] > 0){

                for(int i = 0; i < occurence; i++){

                    res.append((char) ('a' + j)); // (char) ('a' + j - 1) will not work as we srat j from 0 not 1
                }
            }            
        }

        return res.toString();
    }        


}
