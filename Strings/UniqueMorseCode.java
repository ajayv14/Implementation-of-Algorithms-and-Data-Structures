import java.util.Set;
import java.util.HashSet;

class UniqueMorseCode {
   
    public int uniqueMorseRepresentations(String[] words) {
        
        String[] morseCodes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
                
        Set<String> patternSet = new HashSet<>();         
        
        for(String word : words){
            
            StringBuilder sb = new StringBuilder();
                       
            for(Character ch : word.toCharArray()){
                sb.append(morseCodes[ch-'a']); // to get index of alphabet a --> 0 , b-->1               
            }          
              
            patternSet.add(sb.toString());                      
        }       
        
        return patternSet.size();
        
    }
}