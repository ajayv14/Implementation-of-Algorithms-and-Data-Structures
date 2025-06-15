
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class LongestSubstringNonRepeating {


    // LC 3 : https://leetcode.com/problems/longest-substring-without-repeating-characters/  
    
        
        
    // "abcabcbb" , expected : 3
    //  "dvdf" , 3
    // "bbbbb", 1


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


    /**
     Approach :

     Update count in map and keep expaning sliding window to right.
     We may hit a duplicate char, sometimes even adjacent ones like bb in acbbk
     To remove duplicate char, keep on removing chars from left until count of duplicate reaches 1.
     
     edge cases : "bbbbb" , "pwwkew : 3 -> wke"
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