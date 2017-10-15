
class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        /*return empty list if input string is null*/
        
        if(strs == null || strs.length == 0) return new ArrayList<List<String>>();
        
        Map<String, List<String>> map = new HashMap<>();
        
        for(String s : strs){
            
            char[] ch = s.toCharArray();
           
            Arrays.sort(ch);
            //System.out.println(ch);
            
            /*insert into HashMap*/
            
            /*create a key for HashMap put operation*/
            
            String key = String.valueOf(ch); /*convert sorted char array to string to be used as key*/
           // System.out.println("key "+key);
            
            /*insert*/ 
            
            if(!map.containsKey(key)){
                
               List<String> list = new ArrayList<>();
               list.add(s); 
               map.put(key,list);
            }
            
            else{
                
                map.get(key).add(s);
                
            }
            
            
        }
        
        
        return new ArrayList<List<String>>(map.values());
        
        
        
        
        
        
    }
}