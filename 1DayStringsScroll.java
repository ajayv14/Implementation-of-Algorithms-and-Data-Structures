class ValidAnagram {
    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length()) return false;

        int[] alp = new int[26];

        for(int i = 0; i < s.length(); i++){
            
            alp[s.charAt(i) - 'a']++;
            alp[t.charAt(i) - 'a']--;    
        }        

        for(int i = 0; i < 26; i++){
            
            if(alp[i] != 0) return false;
        }
        
        return true;
    }
}

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


public class MinRemoveToValidParanthesis {


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



// LC 643 https://leetcode.com/problems/maximum-average-subarray-i

public class MaxAverageSubArray {

    public double findMaxAverage(int[] nums, int k) {

        double windowSum = 0; // Method returns double, hence need for precesion.
        
        double maxAvg = Integer.MIN_VALUE; // edge case input [-1] & k = 1
        
        double avg = 0;        

        // When window is smaller than k, expand.
        for(int i = 0; i < k; i++){
            windowSum += nums[i];
        }

        avg = windowSum/k;        
        maxAvg = Math.max(maxAvg, avg);

        
        for(int i = k; i < nums.length; i++){   
            
            // Slide = add new value and remove leftmost value
            windowSum += nums[i] - nums[i - k]; 

            avg = windowSum/k;
            maxAvg = Math.max(maxAvg, avg);           
                 
        }

        return (double)maxAvg;        
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


class ValidNumber {

    public boolean isNumber(String s) {

        int i = 0; 

        boolean seenExponent = false;
        boolean seenDecimal = false;
        boolean seenDigit = false;

        while( i < s.length()){
            
            char c = s.charAt(i);

            if(Character.isDigit(c)){
                seenDigit = true;
            }

            // Either has to be at index 0 or should preceed with e or E 
            else if(c == '+' || c == '-'){

                // i != 0     
                if((i > 0 && (s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E'))){
                      return false;
                }            
            }

            // e and E can occur once    
            else if(c == 'e' || c == 'E'){

                if(seenExponent || !seenDigit) return false;
                
                seenExponent = true;
                seenDigit = false;
            }

            else if (c == '.'){

                if(seenDecimal || seenExponent) return false;

                seenDecimal = true;
            }

            else return false; // Important - use case "D80"

            
            i++;
        }        
        
        return seenDigit; // can't simply return true - use case :  "."
    }
}


// https://leetcode.com/problems/palindromic-substrings
class CountPalindromicSubstrings {

    // time O(n)^2
    // space = O(1)
    
        public int countSubstrings(String s) {
            
            int count = 0;
    
    
           // Expand around center index 
           for(int i = 0; i < s.length(); i++){
    
    
                // Odd length - left and right are same index 
                count += countPalindrome(s,i,i);
                
                // Even length
                count += countPalindrome(s,i,i+1);
    
           }
    
           return count;
        }
    
        public int countPalindrome(String s, int i, int j){
            
            int count = 0;
    
            // Boundary check 
            while(i >= 0 && j < s.length()){
    
                if(s.charAt(i) != s.charAt(j)){
                    
                    break;                        
                }
    
                count++;           
    
                // Expand from center
    
                i--;
                j++;
            }
            return count;
        }
    }



public class MinimumWindowSubstring {


    class Solution {
    public String minWindow(String s, String t) {

        if (t.length() > s.length()) return "";
        
        // Frequency of occurence map
        Map<Character, Integer> freq = new HashMap<Character, Integer>();

        // initialize map
        for (char c : t.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int matches = 0, left = 0, right = 0, minWindow = Integer.MAX_VALUE;
        
        int start = 0;
       

        for(right = 0 ; right < s.length(); right++) {

            char rightChar = s.charAt(right);
            
            //Decrement freq and increase count if a valid char is found
            if (freq.containsKey(rightChar)){
                
                freq.put(rightChar, freq.get(rightChar) - 1);

                if(freq.get(rightChar) >= 0) matches++;

            } 
                                    
            // Substring window found where all characters in String t are present. Try shrinking window now.  
            while (matches == t.length()) {

                if(minWindow > right - left + 1){

                    minWindow = right - left + 1;

                    start = left; // Begin to shrink window, mark start pos
                }

                char leftCur = s.charAt(left); 

                if(freq.containsKey(leftCur)){

                     freq.put(leftCur, freq.get(leftCur) + 1);
                     
                     if(freq.get(leftCur) > 0) matches--;    
                }                

                left++;          
            }              
        }

        return minWindow == Integer.MAX_VALUE ? "" : s.substring(start, start + minWindow);
    }



        /*
            Pre-populate map with character count in t.
            
            For each character encountered, if present in the freq map, decrement freq and increment matches count.

            Once count becomes equal to the length of string t, it means we have found a window that contains all characters of t. 
               
            Now try shrinking this window by moving left pointer to right. Mark current pos of left pointer using start. For each character it
            encounters, it increments its frequency in the freq map and decrements matches count if the frequency becomes positive.
              
            Shrink example : 

            s = "ADOBACODEBANC", t = "ABC"

            initial match - ADOBAC
            shrink match - BAC    
        * */

}
}




// LC : 249 : https://leetcode.com/problems/group-shifted-strings/



public class GroupShiftedStrings {

    /*
       Compute distance between each character in string. Find similary strings with same distance
       efg can be turned into abc in 4 shift operations : efg -> def -> cde -> bcd -> abc
       But always distance btw each character remains same : f - e = 1, g - f = 1, so represent efg : 1->1
       Similarly acf : c - a = 2, e - c = 3,  acf : 2->3. A silimar string wud be : bdg.

       Edge case -  ba left shift -> az
       
       but az : 26 - 1 = 25  and ba : 1 - 2 = -1; So handle -ve by adding 26 (circular array)

    */

   public List<List<String>> groupStrings(String[] strings) {

       List<List<String>> res = new ArrayList<>();

       // Something like an inverted index
       // <DistanceOfEach chars in string, Matching strings>    
       Map<String, List<String>> distanceMap = new HashMap<>();

       
       for(String word : strings ){

           String distKey = computeDistance(word);

           distanceMap.putIfAbsent(distKey, new ArrayList<>());
               
           distanceMap.get(distKey).add(word);        
       }

               System.out.println(distanceMap);

       distanceMap.keySet().forEach(k -> res.add(distanceMap.get(k)));

       return res;
               
   }

   private String computeDistance(String word){

       StringBuilder sb = new StringBuilder();
       
       for(int i = 1; i < word.length(); i++){

           int dist = word.charAt(i) - word.charAt(i - 1);

           if(dist < 0) dist = dist + 26; 
           
           sb.append(String.valueOf(dist)+"->");
       }

       return sb.toString();
   }

}



// LC 1209. Remove All Adjacent Duplicates in String II
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

public class RemoveDuplicatesII {

    // O(n) time and O(n) space

    public String removeDuplicates(String s, int k) {

       StringBuilder sb = new StringBuilder(s);
      
       int count[] = new int[sb.length()];
      
       for(int i = 0; i < sb.length(); i++){ // Note : Do not use a variable 'len' to store sb.length() 

           if(i == 0 || sb.charAt(i - 1) != sb.charAt(i)){                
               count[i] = 1;
           }           

           else {
               count[i] = count[i - 1] + 1;              
               
           }  

           if(count[i] == k){

                   sb.delete(i - k + 1, i  + 1 );
                   
                   i = i - k;
           }   

            
       }

       return sb.toString();
   }

}




class LongestSubstringNonRepeating {


    // LC 3 : https://leetcode.com/problems/longest-substring-without-repeating-characters/  
    
        
        
    // "abcabcbb" , expected : 3
    //  "dvdf" , 3
    // "bbbbb", 1


    /**
     Approach :

     Update count in map and keep expaning sliding window to right.
     We may hit a duplicate char, sometimes even adjacent ones like bb in acbbk
     To remove duplicate char, keep on removing chars from left until count of duplicate reaches 1.
     
     edge cases : "bbbbb" , "pwwkew"
     */

     public int lengthOfLongestSubstring(String s) {
        
        // frequency map : <character, count>
        Map<Character,Integer> map = new HashMap<>();

        int maxLen = 0;

        int left = 0;

        for(int right = 0; right < s.length(); right ++){

            char c = s.charAt(right);

            map.put(c, map.getOrDefault(c, 0) + 1);      

            // why not "if" ?? -> "pwwkew" : pww -> ww is incorrect. 

            // keep on removing char from left
            while(map.get(c) > 1){

                // Remove leftmost char
                char l = s.charAt(left);
                map.put(l, map.get(l) - 1);
                left++;        
            }

            // max btw max and cure window len
            maxLen = Math.max(maxLen, right - left + 1);
        }           
        
        return maxLen;
    }
}   



class LongestPalindromicSubstring {
   
  
    public String longestPalindromeSubString(String s) {
       
        int n = s.length();
         
        String longestPalindrome=null;
          
        // dp[i][j] indicates whether substring s starting at index i and ending at j is palindrome  
        boolean[][] dp = new boolean[n][n];     
          
           
          for(int i = n-1; i >= 0; i--){ // keep increasing the possible palindrome string
              
              for(int j = i; j < n; j++){ // find the max palindrome within this window of (i,j)
       
                   //check if substring between (i,j) is palindrome         
                   dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                  // chars at i and j should match
                  // if window is less than or equal to 3, just end chars should match
                  // if window is > 3, substring (i+1, j-1) should be palindrome too
                                                  
                  if (dp[i][j] && (longestPalindrome==null || j - i + 1 > longestPalindrome.length())){      
                            
                       longestPalindrome = s.substring(i, j + 1); //update max palindrome string
                 }
      }
    }
      
    return longestPalindrome;
          
          
      }
  }




class LetterCombinationsPhoneNum {
    public List<String> letterCombinations(String digits) {
        
        /*Logic - for each digit, make a char array of the associated string in map array and 
                  then insert each char into a FIFO queue - linked list.
                  Then under conditions, remove each char from list and then add that with the characters of next digit
                  2 - [abc], 3-[def] list--> a-->b-->c
                                     remove a and add a + chars of string associated with next digit --> b-->c-->ad-->ae-->af
                                     continue till --> ad-->ae-->af-->bd-->be-->bf-->cd-->ce-->cf
                    */
        
        String[] map = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno" , "pqrs", "tuv", "wxyz"};
        
        if(digits == null || digits.length() == 0) return new LinkedListImplementation<String>();
                   
        LinkedListImplementation<String> list = new LinkedListImplementation<String>();
        list.add("");
        
        for(int i = 0; i < digits.length(); i++){
            
           //int num = Integer.parseInt(digits.charAt(i));// does not work with character
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


class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        /*return empty list if input string is null*/
        
        if(strs == null || strs.length == 0) return new ArrayList<List<String>>();
        
        Map<String, List<String>> map = new HashMap<>();
        
        for(String s : strs){
            
            char[] ch = s.toCharArray();
           
            Arrays.sort(ch);
            //System.out.println(ch);
            
            /*insert into HashMap*/
            
            /*create a key for HashMap put operation*/
            
            String key = String.valueOf(ch); /*convert sorted char array to string to be used as key*/
           // System.out.println("key "+key);
            
            /*insert*/ 
            
            if(!map.containsKey(key)){
                
               List<String> list = new ArrayList<>();
               list.add(s); 
               map.put(key,list);
            }
            
            else{
                
                map.get(key).add(s);
                
            }
            
            
        }
        
        
        return new ArrayList<List<String>>(map.values());
        
        
        
        
        
        
    }
}

class LengthOfLastWord {
    
    // "   fly me   to   the moon  "
    
    public int lengthOfLastWord(String s) {
        
        int length = 0;
        
        if(s.length() == 0) return 0;
        
        int idx  = s.length() - 1;

        // Skip empty chars from end and count non empty
        while(idx > 0){

            
            if(s.charAt(idx) != ' '){

                length++;                
            } 

            
            else if (length > 0) return length;

            idx--;
        }
        
        return -1;
        
    }
}