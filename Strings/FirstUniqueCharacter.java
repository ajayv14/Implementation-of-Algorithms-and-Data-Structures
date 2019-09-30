import java.util.Map;
import java.util.HashMap;

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
    
    /*using hashmap*/
    public int firstUniqCharUsingMap(String s) {
        
        // add char count to a hashmap
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(!map.containsKey(ch)){
                map.put(ch,1);
            }
            else{
                map.put(ch, map.get(ch) + 1);
            }
        }
        
        // run thro length of string and see which value is first unique from hashmap
        for(int j = 0; j < s.length(); j++){
            if(map.get(s.charAt(j)) == 1) return j;              
        }
        
        return -1;
        
    }        
    
}