package com.app.strings;

import java.util.HashMap;
import java.util.Map;

public class PasswordReplace {
    public static int calc(String password, String ref, int[] cost) {
        int minCost = Integer.MAX_VALUE;

        Map<Character, Integer> count = new HashMap<>();

        // Count occurrences of each character in password
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            count.putIfAbsent(c, 0);
            count.put(c, count.get(c) + 1);
        }

        // Check characters in reference
        for (int j = 0; j < ref.length(); j++) {
            char r = ref.charAt(j); 

            if (count.containsKey(r)) {
                minCost = Math.min(minCost, cost[r - 'a'] * count.get(r)); 
            } 
            
            else return 0;
        }

        return minCost == Integer.MAX_VALUE ? 0 : minCost; // Return 0 if no match found
    }

    public static void main(String[] args) {
        String password1 = "adefgh";
        String reference1 = "hf";
        int[] cost1 = {1, 0, 0, 2, 4, 4, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(calc(password1, reference1, cost1)); // Output: 1

        String password2 = "abcdcbcb";
        String reference2 = "bcb";
        int[] cost2 = {2, 3, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(calc(password2, reference2, cost2)); // Output: 3
    }

}
