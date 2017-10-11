class SortCharByFrequency {
    public String frequencySort(String s) {
    
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
}