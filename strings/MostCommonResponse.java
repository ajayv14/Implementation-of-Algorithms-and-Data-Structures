import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// LC 3527 https://leetcode.com/problems/find-the-most-common-response/

public class MostCommonResponse {



    /*
        Approach : 
        Process each list ad add unique words to freq counter.
        unique words obtained by HashSet
        pick top 1 word by occurence. Use lexi order to break tie
    */


    public String findCommonResponse(List<List<String>> responses) {

        // String, occurence
        Map<String, Integer> freq = new HashMap<>();    

        Set<String> tempSet = new HashSet<>();

        // Split each response list and find freq of each word
        // Ignore repeated words by using a  set.
        for(List<String> resp : responses){

            for(String s : resp){

                if(!tempSet.contains(s)){

                    tempSet.add(s);

                    freq.put(s, freq.getOrDefault(s,0) + 1);

                }  
                
            }      

            tempSet.clear();
        }


        int max = -1;

        String res = null;

        // Get top 1 by value
        for(String key : freq.keySet()){

            int count = freq.get(key) ;
 
            if(count > max) {

                max = count;
                res = key;
            }

            // // tie breaker
            else if(count == max && res.compareTo(key) > 0){

                res = key;            
            }
            
        }    

        return res;
       

    }

}
