import java.util.Collections;
import java.util.HashMap;

// Use sliding window with HashMap to track last position of each character.
//When map size exceeds 2, find leftmost character's last position, remove it and update left pointer. 
//Track max length

// LC 159
public class LongestSubstringTwoDistinctChars {

    /**
     * 
     * Sliding window.
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int left = 0;
        int right = 0;
        int maxLen = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        // At each character
        for (right = 0; right < s.length(); right++) {

            map.put(s.charAt(right), right);

            // Remove leftmost character last seen (in map)
            if (map.size() > 2) {

                int lastIndex = Collections.min(map.values()); // To get smallest (leftmost) of last seen index

                map.remove(s.charAt(lastIndex));

                left = ++lastIndex;

            }

            maxLen = Math.max(maxLen, right - left + 1);

        }

        return maxLen;
    }



}
