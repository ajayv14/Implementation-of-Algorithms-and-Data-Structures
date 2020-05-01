//credits : Kevin Naughton Jr. https://www.youtube.com/watch?v=jZBnFxIe4Y8
// https://leetcode.com/problems/verifying-an-alien-dictionary/

/*
        logic :  1) compare each word with every next word in dictionary, 2) take min length of  two words under comparison and check each char for lexicographical ordering
                 3) some corner cases like apple, app..after min length lexi order. check, check if longer word has a positiion brfore smaller word (a violation of lexi. ordering...app should come before apple)
*/


class AlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {
        
        int[] alphabets = new int[26];
        
        int m = 0;
        for(char c : order.toCharArray()){
            alphabets[c - 'a'] = m;
            m++;
        }
        
        
        for(int i = 0; i < words.length; i++){
            for(int j = i + 1; j < words.length; j++){
                
                 // take min length of two words under comparison
                int min = Math.min(words[i].length(), words[j].length());
                
                for(int k = 0; k < min; k++){
                    
                    char c1 = words[i].charAt(k);
                    char c2 = words[j].charAt(k);
                    
                    if(alphabets[c1 - 'a'] < alphabets[c2 - 'a']) break; // the first char is smaller than first char of other word..so it is indeed sorted lexi..
                    
                    else if(alphabets[c1 - 'a'] > alphabets[c2 - 'a']) return false; // not lexi. sorted
                    
                    // shorter word appearing after longer word..even though they appear to be sorted..like apple, app
                    else if (k == min - 1 && words[i].length() > words[j].length()) return false;  // when comparing last char in loop min, compare the lengths                            
                }                
                
            }      
        }        
        return true;
        
    }
}