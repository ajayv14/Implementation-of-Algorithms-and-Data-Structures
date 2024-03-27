import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
  *
  * logic : 
  *  - Check if there is a match with cells of board array vs first character in a word. 
  *  - Do a dfs on top, bottom, left and right cells, recursively.  
  *  
**/

class WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {
        
        /*why not ArrayList ? one a word is discovered, the code does not stop, it may re-discover world elsewhere*/ 
        Set<String> result = new HashSet<>();
        
              
        for(String word : words){
            
            /*[["a","a"]] find ["aaa"], will need to mark cells as visited, if not will re discover same cells*/
            int[][] visited = new int[board.length][board[0].length];
                     
            char c = word.charAt(0);
                                    
            for(int i = 0; i < board.length; i++){
              for(int j = 0; j < board[0].length; j++){
                
                 if(c == board[i][j] && search(word,0,board, i, j, visited)){                    
                        result.add(word);                        
                 }                 
              }
            }          
            
        }           
            
        return new ArrayList<>(result);    
                
    }
    
    /*dfs*/
    private boolean search(String word, int wordIndex, char[][] board, int i, int j, int[][] visited){
        
                        
        if(wordIndex == word.length()) return true; 
        
        // screening condition
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || 
                               board[i][j] != word.charAt(wordIndex) || visited[i][j] == 1 ) return false;
        
        visited[i][j] = 1;     
       
        
        if(search(word,wordIndex + 1,board, i, j + 1, visited) ||
           search(word,wordIndex + 1,board, i, j - 1, visited) ||
           search(word,wordIndex + 1,board, i + 1, j, visited) ||
           search(word,wordIndex + 1,board, i - 1, j, visited) ){
            
            return true;
        }
                    
        visited[i][j] = 0; // note   
          
        return false;
    }
    
}
