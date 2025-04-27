// LC 151 https://leetcode.com/problems/reverse-words-in-a-string

public class ReverseWords {

    public String reverseWords(String s) {

        String[] words = s.split(" ");

        StringBuilder sb = new StringBuilder();

        for(int i = words.length - 1; i >= 0; i--){

           if(words[i] == "") continue; 

           sb.append(words[i].replaceAll("[^a-zA-Z0-9]", "") + " ");    

        }

        return sb.toString().trim();
        
    }
}
