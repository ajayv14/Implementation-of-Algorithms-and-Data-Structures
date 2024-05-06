class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        
               
        int[] Alphabet = new int[26];
                
               
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        
        
        for(int i=0;i<s.length();i++){
           Alphabet[s.charAt(i)-'a']++;                     
        }
        
        for(int j=0;j<t.length();j++){
            Alphabet[t.charAt(j) - 'a']--;
        }
        
       for(int k=0;k<26;k++){
           if(Alphabet[k] !=0) return false;
       }
                
       return true;
        
    }
}