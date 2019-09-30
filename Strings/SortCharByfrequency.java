import java.util.*;
/*451 on Leetcode */
class SortCharByFrequency {

    public String frequencySort(String s) {
        
        int[] freq = new int[128]; //freq of char in the string
        StringBuilder sb = new StringBuilder(); // to return string result  
        
        for(int i = 0; i < s.length(); i++){  
            int ascii = (int)s.charAt(i);
            freq[ascii]++;
        }       

        /*to hold <freq, list of characters> sorted by descending map */
        Map<Integer, List<Character>> map = new TreeMap<>(Collections.reverseOrder());
                        
        for(int j = 0; j < freq.length; j++){
            
            
            /*add the non zero elements to map with their respective freq as keys*/
            if(freq[j] != 0){
                
                if(map.containsKey(freq[j])){
                    List<Character> list = map.get(freq[j]);
                    list.add((char)j);
                    map.put(freq[j],list);
                }
                else{
                    List<Character> list = new ArrayList<Character>();
                    list.add((char)j);
                    map.put(freq[j],list);    
                }            
            }            
         }
            
        /*loop thro the reverse sorted keysets and build a string with number of occurences of characters*/
        for(int n : map.keySet()){                  
            List<Character> ls = map.get(n);
            
            for(char ch : ls){                
                for(int repeat = 0; repeat < n; repeat++){
                  sb.append(ch);  
                }              
            }
        }      
        
        return sb.toString();
    }

  
   /*Old approach*/
    public String frequencySortOld(String s) {
    
     /*Step 1 - create a map and add all characters in the string*/
        
     Map<Character,Integer> map = new HashMap<>();
        
        for(char c : s.toCharArray()){
            
            if(map.containsKey(c)) map.put(c,map.get(c)+1);
            
            else map.put(c,1);
        }
        
     /*Step-2 Create buckets Array of Lists */   
        
        List<Character>[] bucket = new List[s.length() + 1];
        
        /* for all unique characters in map*/
        
        for(char key : map.keySet()){
            
            int freq = map.get(key); /*will get the value- frequency of occurence for the key(character)*/            
            
            if(bucket[freq] == null) bucket[freq] = new ArrayList<Character>(); /*Character List inside the array*/
            
            /*insert the character into the  list inside array*/
            
            bucket[freq].add(key);
        }
        
        /*Step 3 - loop each list in array from end (highest first) and return the final apended string*/
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = bucket.length-1; i >= 0; i--){
            
          if(bucket[i]!=null){
            
            for(char ch : bucket[i]){
                /*now append the character the number of times t has appeared */
                
                for(int repeat = 0; repeat < i ; repeat++ ){
                       
                    sb.append(ch);
                }
                
            }            
          
              
          }   
            
        }
        
        return sb.toString();
        
        
    }
    
    
    public static void main(String[] args){
      String s = "tree";
      SortCharByFrequency obj = new SortCharByFrequency();
      System.out.println(obj.frequencySort(s)); // expected o/p "eert"
    }
    
    
}