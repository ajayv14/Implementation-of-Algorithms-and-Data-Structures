import com.app.stack.LinkedList;

class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        // bfs problem
        Set<String> set =  new HashSet<>();
        for(String s : wordList){
            set.add(s);
        }
        
        if(!set.contains(endWord)) return 0; // sequence is not present
        
        //typical Bfs
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int level = 1;
        
        while(!q.isEmpty()){
            
            int size = q.size();            
            
            for(int i = 0; i < size; i++){
                String cur = q.poll();
                char[] curWordChar = cur.toCharArray();
                // construct a new string by varying just one character 
                
                for(int j = 0; j < curWordChar.length; j++){
                    char originalChar = curWordChar[j]; // re-assign after the loop    
                    for(char c = 'a'; c < 'z'; c++){
                                                
                        if(curWordChar[j] == c) continue;
                        curWordChar[j] = c;
                        // convert char[] to a new string                        
                        String generatedWord = String.valueOf(curWordChar);
                        
                        if(generatedWord.equals(endWord)) return level + 1; 
                        if(set.contains(generatedWord)) {
                            q.offer(generatedWord); //transformation found
                            set.remove(generatedWord); // to avoid duplicates
                        }
                    }                     
                    curWordChar[j] = originalChar;
                }                   
            }     
           
            level++;         
        }
        
        return 0;
        
    }
}