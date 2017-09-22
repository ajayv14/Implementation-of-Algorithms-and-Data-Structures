class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        
        int[] alphabets = new int[26]; // array to store freq
        char[] ch = s.toCharArray();
        
        for(int j=0;j<s.length();j++){
           alphabets[ch[j] - 'a']++; //'a' has a value of 001 , hence to start from 0 index
            
        }
        
        //run to the length of string
        for(int k=0;k<s.length();k++){
            if(alphabets[ch[k]-'a']==1) return k;
        }
        
        return -1;
        
    }
}