
// LC 1209. Remove All Adjacent Duplicates in String II
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

public class RemoveDuplicatesII {

     // O(n) time and O(n) space

     public String removeDuplicates(String s, int k) {

        StringBuilder sb = new StringBuilder(s);
       
        int count[] = new int[sb.length()];
       
        for(int i = 0; i < sb.length(); i++){ // Note : Do not use a variable 'len' to store sb.length() 

            if(i == 0 || sb.charAt(i - 1) != sb.charAt(i)){                
                count[i] = 1;
            }           

            else {
                count[i] = count[i - 1] + 1;              
                
            }  

            if(count[i] == k){

                    sb.delete(i - k + 1, i  + 1 );
                    
                    i = i - k;
            }   

             
        }

        return sb.toString();
    }

}
