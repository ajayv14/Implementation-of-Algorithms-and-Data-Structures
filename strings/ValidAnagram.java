class ValidAnagram {
    
// LC 242 https://leetcode.com/problems/valid-anagram/

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