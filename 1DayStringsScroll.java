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

//Input: s = "lee(t(c)o)de)"
// Output: "lee(t(c)o)de"
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
    // Time O(N), Space O(N)
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


// "()))((" 
// OP : 4

public class MinAddToMakeValidParanthesis {

    // No extra space - Simple soln

    public int minAddToMakeValidOpt(String s) {


        int openBrackets = 0;

        int additions = 0;
        
       
        for(char c : s.toCharArray()){

            if(c == '(') openBrackets++;

            else {

                if(openBrackets > 0) openBrackets--;

                else {
                    additions++;                  
                }
            }
            
        }

        return additions + openBrackets;
        
    }



    // LC 921 : https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
    
    public int minAddToMakeValid(String s) {

              
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()){

            if(c == '(') stack.push(c);

            else if(c == ')') {

                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();

                else {
                    stack.push(c);                    
                }
            }

            
        }

        return stack.size();
        
    }
}

//Input: s = "A man, a plan, a canal: Panama"
//Output: true
//Explanation: "amanaplanacanalpanama" is a palindrome.
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


//  Can be palindrome after deleting at most one character from it.
// kaymak 
public class ValidPalindrome2 {

    public boolean validPalindrome(String s) {
                
        
        int low = 0, high = s.length() - 1;
        
        while(left < high){
            
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


// https://leetcode.com/problems/palindromic-substrings
//Time O(n^2) Space O(1)
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


// Split path by '/'. Use stack to track directories. Skip empty and '.' entries. For '..' pop from stack if not empty. 
//Finally, build path from stack elements with '/' separator.

// LC - 71. Simplify Path
// https://leetcode.com/problems/simplify-path

// Time O(N), Space O(N) - Actually O(2N)
public class SimplifyPath {

    public String simplifyPath(String path) {

        String[] str = path.split("/");

        List<String> components = new LinkedList<>(); // Note

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
//Input: order = "cba", s = "abcd"
//Output: "cbad"
    //Count frequence of occurence of each word in s
    // abbc -> a = 1, b = 2, c = 1
    // Pick order from order string "abcx" using pointer, get freq from map and apply to s -> a, bb, c
    // Converting int to alphabet character : (char) ('a' + num - 1)
public class CustomSortString {


    // 1.  Non - optimized solution

    // Order string -> Add character and its index to a map. 
    // Use this id/index to sort string s using custom comparator based on map values
    public String customSortString(String order, String s) {
        
        // Character, custom id
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

        // Freq of occurence in s
        Map<Character, Integer> freq = new HashMap<>();

        for(char c : s.toCharArray()){
                
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }    

        for(int l = 0; l < order.length(); l++){

            char co = order.charAt(l); 

            if(freq.containsKey(co)) {

                int occurence = freq.get(co);
                freq.remove(co); // Pay attention

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

            // compute hash - 
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

           if(dist < 0) dist = dist + 26; // acount for rotated strings
           
           sb.append(String.valueOf(dist)+"->");
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


    /**
     Approach :
        Brute force - Check all possible substrings
     */

    public int lengthOfLongestSubstringNonOptimized(String s) {
       
        int maxLen = 0;

        for(int i = 0; i < s.length(); i++){

            Set<Character> uniqueCharacters = new HashSet<>();

            int j = 0;

            for(j = i; j < s.length(); j++){

                char c = s.charAt(j);

                if(uniqueCharacters.contains(c)) break;

                uniqueCharacters.add(c);
            }      

            maxLen = Math.max(maxLen, j - i);
        }           
        
        return maxLen;
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

                if(freq.get(rightChar) >= 0) matches++; // Pay attention

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



// LC 1209. Remove All Adjacent Duplicates in String II
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
// k == window size with same chars
//Input: s = "deeedbbcccbdaa", k = 3
//Output: "aa"
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


//https://leetcode.com/problems/longest-palindromic-substring
class LongestPalindromicSubstring {
    
    
    public String longestPalindrome(String s) {
        
        // Start with longest, move on to smaller strings   
        for(int i = s.length() - 1; i >= 0 ; i--){

            System.out.println("i : " + i);

            // Find longest palindromic substring 
            for(int j = 0; j <= s.length() - 1 - i; j++){

                /*System.out.println( "j upper bound : " + (s.length() - 1 - i));

                System.out.println("j : " + j + "  " + " j + i : " +(j + i));

                System.out.println(s.substring(j, j + i + 1));
/*

s.substring(j, j + i + 1):

babad
baba
abad
bab
aba
bad
ba
ab
*/                

                 if(isPalindrome(j, j + i ,s)) return s.substring(j, j + i + 1);  

            }
        }
        
        return null;
    }


    private boolean isPalindrome(int i, int j, String s){
             
        //System.out.println("i : " + i + " j :" + j);

        while(i < j){

            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}


/*Logic - for each digit, make a char array of the associated string in map array and 
        then insert each char into a FIFO queue - linked list.
        Then under conditions, remove each char from list and then add that with the characters of next digit
        2 - [abc], 3-[def] list--> a-->b-->c
        remove a and add a + chars of string associated with next digit --> b-->c-->ad-->ae-->af
        continue till --> ad-->ae-->af-->bd-->be-->bf-->cd-->ce-->cf
*/

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
class LetterCombinationOfPhoneNum {

    public List<String> letterCombinations(String digits) {
       
        
        if(digits == null || digits.length() == 0) return new LinkedList<String>();
        
        String[] map = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno" , "pqrs", "tuv", "wxyz"};
                
               
        LinkedList<String> res = new LinkedList<String>();
        res.add("");
        

        for(int i = 0; i < digits.length(); i++){
         
           
            int num = Character.getNumericValue(digits.charAt(i));  
            
             int size = res.size(); // avoids concurrent mod error in list

             for(int j = 0; j < size; j++) { 
                                                
                String s = res.remove();
                
                char[] chArray =  map[num].toCharArray;

                for(char c : chArray){
                   
                    res.add(s + c);
                    
                }                 
            }           
            
        }
        
        return res;
        
        
    }

            /**
             *  Danerous - concurrent mod
             * 
             * for (String s : res) {
                    for (char c : map[num].toCharArray()) {
                        res.add(s + c);
                    }
                }
             * 
             * 
             * 
             * Alternate : 
             * */    
            


/**
 * Now the while (res.get(0).length() == i) loop:

It processes all existing combinations in res that have length i (the current level).

input is "23". Initial state:  res = [""] (empty string to start building on)

First digit 2:

    i = 0 , num = 2, map[2] = "abc"

The while (res.get(0).length() == 0):

s = "" (remove the only element)

We loop through 'a', 'b', 'c', and add:

"a"
"b"
"c"
Now res = ["a", "b", "c"].

Second digit 3:
i = 1, num = 3, map[3] = "def"


The while (res.get(0).length() == 1):

First:

s = "a"

Add "ad", "ae", "af"

Then:

s = "b"

Add "bd", "be", "bf"

Then:

s = "c"

Add "cd", "ce", "cf"

Now res = ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 
 */





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


// https://leetcode.com/problems/verifying-an-alien-dictionary/
//Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
//Output: true


class AlienDictionaryVerify {

    public boolean isAlienSorted(String[] words, String order) {
        
        
        int[] dict = new int[26];
        
         // build a lookup array
        for(int n = 0; n < order.length(); n++){            
            
            dict[order.charAt(n) - 'a'] = n;
        }
        
          
        for(int i = 0; i < words.length - 1; i++ ){
            
            
            String w1 = words[i];
            String w2 = words[i + 1];
            
            int minLen = Math.min(w1.length(), w2.length()); 
            
            for(int j = 0; j < minLen; j++){
                
                if(dict[w1.charAt(j) - 'a'] < dict[w2.charAt(j) - 'a']) break; // lexicographical order preserved
                
                if(dict[w1.charAt(j) - 'a'] > dict[w2.charAt(j) - 'a']) return false; // not in lexocographical order
                
                // second word smaller than second, like apple, app
                
               // j == min, pointer reached end of smaller word        
                    
                if(j == minLen - 1 && w1.length() > w2.length()) return false;                
            }    
            
        }
        
        return true;        
        
    }
    
}


// Use topo sort

// https://leetcode.com/problems/alien-dictionary/description/
//Input: words = ["wrt","wrf","er","ett","rftt"]
//Output: "wertf"
class AlienDictionaryHard {

    // Kahn's algo - topo sort
    public String alienOrder(String[] words) {

        StringBuilder res = new StringBuilder();

        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character,Integer> inDegree = new HashMap<>();

        // Construct empty graph
        for(String word : words){

            for(char c : word.toCharArray()){
                graph.putIfAbsent(c, new ArrayList<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        for(int i = 0; i < words.length - 1; i++){

            String w1 = words[i], w2 = words[i + 1];
        
            // Check if w2 is not a prefix of w1 -> apple, app. Word 2 is a prefix of word 1 
            if(w1.length() > w2.length() && w1.startsWith(w2)) return "";   // Pay attention 

            // Capture the first non matching char                
            int minLen = Math.min(w1.length(),w2.length());

            for(int j = 0; j < minLen; j++){

                char c1 = w1.charAt(j), c2 = w2.charAt(j);

                if( c1 != c2){

                    graph.get(c1).add(c2);
                    inDegree.put(c2,inDegree.get(c2) + 1);
                    break; // stop at first non matching     // Pay attention
                }   
            }        
        }   

         // Kahn's topo sort   

         Queue<Character> queue = new LinkedList<>();

         for(Character key : inDegree.keySet()){

            if(inDegree.get(key) == 0) queue.add(key);
         }   


        while(!queue.isEmpty()){

            char c = queue.remove();

            res.append(c);

            // Decrease num of incoming edge as the previous connection was removed.
            for(char con : graph.get(c)){

                inDegree.put(con, inDegree.get(con) - 1); 

                if(inDegree.get(con) == 0) queue.add(con);                       
            }

        }

        // res doesn;t contain all characters, hence cyclic dependency is found
        if(res.length() != inDegree.size()) return "";

        return res.toString();

        
    }   

    
}


//394 : https://leetcode.com/problems/decode-string/
//Input: s = "3[a]2[bc]"
// Output: "aaabcbc"
class DecodeString {
   
    public String decodeString(String s) {
        
        
        Stack<Integer> countStack = new Stack<>();
        
        Stack<StringBuilder> stringStack = new Stack<>();
        
        StringBuilder current = new StringBuilder();
        
        int num = 0;

        for (char c : s.toCharArray()) {
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');  // Build multi-digit numbers
            }

            else if (c == '[') {
 
                // Push the current number and string onto stacks
                countStack.push(num);
                stringStack.push(current);
 
                // Reset for new substring
                current = new StringBuilder();
                num = 0;
 
            } 
            
            else if (c == ']') {
                
                // Pop number and previous string
                StringBuilder decodedString = stringStack.pop();
                int count = countStack.pop();
                
                // Repeat the current string `count` times and append to previous
                for (int i = 0; i < count; i++) {
                    decodedString.append(current);
                }

                current = decodedString;  // Now becomes the current string
            } 

            // Pay attention
            else {
                current.append(c);  // Normal character
            }
        }
        return current.toString();
    }
}


// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
class RemoveAllAdjInString2 {

    /**
        A simple count approach can't be used : "deeedbbcccbdaa" -> ddd eventually will be missed. 
        Need some form of memoization
     */


     // O(n) time and O(n) space

    public String removeDuplicates(String s, int k) {

        StringBuilder sb = new StringBuilder(s);
       
        int count[] = new int[sb.length()]; // At each step, set count to 1 if no repearted char, or store count of rep char
       
        for(int i = 0; i < sb.length(); i++){ // Note : Do not use a variable 'len' to store sb.length() 

            if(i == 0 || sb.charAt(i - 1) != sb.charAt(i)){                
                count[i] = 1;
            }           

            else {
                count[i] = count[i - 1] + 1; // Repeated character              
                
            }  

            if(count[i] == k){


                    sb.delete(i - k + 1, i  + 1 );
                    
                    i = i - k; // Operating on stringbuilder buffer queue, not a static string, so reset is needed
            }   

             
        }

        return sb.toString();
    }
}



//https://leetcode.com/problems/string-to-integer-atoi
//Input: s = "1337c0d3"
//Output: 1337
class StringToAtoi {


    
    public int myAtoi(String input) {
        
        int sign = 1;
       
        int result = 0;
       
        int index = 0;
       
        int n = input.length();


        // Discard all spaces from the beginning of the input string.
        while (index < n && input.charAt(index) == ' ') {
            index++;
        }

        // sign = +1, if it's positive number, otherwise sign = -1.
        if (index < n && input.charAt(index) == '+') {
            sign = 1;
            index++;
        } 
        
        else if (index < n && input.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        // Traverse next digits of input and stop if it is not a digit
        while (index < n && Character.isDigit(input.charAt(index))) {
           
            int digit = input.charAt(index) - '0';

            // Check overflow and underflow conditions.
            if (
                (result > Integer.MAX_VALUE / 10) || 
                
                // checking if adding this digit will cause overflow
                (result == Integer.MAX_VALUE / 10 &&  digit > Integer.MAX_VALUE % 10)       // pay attention
            ) {
                // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // Append current digit to the result.
            result = 10 * result + digit;
            index++;
        }

        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        return sign * result;    // Pay attention
    }
}

/*
    Approach : 

    Split the ip address string into 4 segments.
    Use backtracking to simulate different possibilities.
    Validate the correct ip address and return a list

    Example : 25525511135
    ignored sample : 4 segments but incomplete & invalid string: 2.5.5.25
    valid sample : 4 segments, complete & valid 255.255.11.135
 */

// LC 93 : https://leetcode.com/problems/restore-ip-addresses/




class RestoreIpAddress {

    public List<String> restoreIpAddresses(String s) {

          List<String> result = new ArrayList<>();  

          backtrack(s, 0, new ArrayList<>(), result); 

          return result;
    }


    private void backtrack(String s, int start, List<String> path,  List<String> result){

        // Termination condition
        if(path.size() == 4) {
                    

            // reached end of string
            if(start == s.length()){
                result.add(String.join(".", path));  
                
                System.out.println("valid : " + String.join(".", path));                
            }

            //Ignored segments
            else System.out.println("ignored : " + String.join(".", path));    

            return;
        } 
                 

        // Which ever ends first
        int newLen = Math.min(start + 3, s.length());

        for(int end = start + 1; end <= newLen; end++){

            String segment = s.substring(start,end);

            if(segmentValidityCheck(segment)){

                path.add(segment);

                System.out.println(segment);
                
                backtrack(s, end, path, result);

                path.remove(path.size() - 1);

            }
        }   

    }


    private boolean segmentValidityCheck(String segment){

        // Segment og length > 1 but starts with 0 -> 065 
        if (segment.length() > 1 && segment.startsWith("0")) {
            return false;
        }

        // Range check 0 - 255
        int num = Integer.parseInt(segment);
        return num >= 0 && num <= 255;        
    }


}