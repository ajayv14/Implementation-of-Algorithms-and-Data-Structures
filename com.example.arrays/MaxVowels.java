

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaxVowels {


    public int maxVowels(String s, int k) {

        //Sliding window Soln

        //Vowel Set
        Set<Character> vowels = new HashSet<>();
        vowels.addAll(Arrays.asList('a','e','i','o','u'));

        int curVowelCount = 0;
        int maxVowelCount = Integer.MIN_VALUE;

        // Create window of size K
        for(int i = 0; i < k ; i++){

             if(vowels.contains(s.charAt(i))){
               curVowelCount++;     
             }
        }
        maxVowelCount = Math.max(maxVowelCount, curVowelCount);
        
        // Slide window
        int left = 0; // Start of window
        int right = k; // End of window + one step

        while(right < s.length()){
            
            if(vowels.contains(s.charAt(right)) && vowels.contains(s.charAt(left))){

                //No change in count technically
                left++;                
            }

            else if(!vowels.contains(s.charAt(right)) && vowels.contains(s.charAt(left))){
                
                // Decrease in cur count
                curVowelCount--;
                left++;                
            }

            else if (vowels.contains(s.charAt(right)) && !vowels.contains(s.charAt(left))) {

                curVowelCount++;
                left++;
                maxVowelCount = Math.max(maxVowelCount, curVowelCount); 

             }

             else left++; 

            right++;
        } 
        return maxVowelCount;
    }


    public static void main(String[] args) {
        MaxVowels obj = new MaxVowels();

        String s1 = "abciiidef"; 
        int k1 = 3;

        System.out.println("Expected : 3");
        System.out.println("Actual : " + obj.maxVowels(s1, k1));

        String s2 = "leetcode";
        int k2 = 3;
        System.out.println("Expected : 2");
        System.out.println("Actual : " + obj.maxVowels(s2, k2));

    }

}
