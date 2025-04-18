import java.util.Map;
import java.util.HashMap;
class SingleRowKeyboard {

   public int timeTaken(String keyboard, String word){
      
            
      Map<Character,Integer> dict = new HashMap<>();      
      int time = 0;
      
      for(int i = 0; i < keyboard.length(); i++){                
         dict.put(keyboard.charAt(i),i);              
      }
      
      int prev = 0;
      int current = 0;
      
      for(char c : word.toCharArray()){
         current = dict.get(c);
         time += Math.abs(current - prev);
         
         prev = current;         
      }
      
      return time;    
   }


   public static void main(String[] args){
      
      SingleRowKeyboard obj = new SingleRowKeyboard();
      
      String keyboard = "abcdefghijklmnopqrstuvwxyz";
      String word = "cba";  // expected time = 4
      
      //String keyboard = "pqrstuvwxyzabcdefghijklmno";
      //String word = "leetcode"; // expected time  = 73
         
      int res = obj.timeTaken(keyboard,word);
      System.out.println(res);
   }

}