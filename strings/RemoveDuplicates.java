
// LC 1047 https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string

public class RemoveDuplicates {

    public String removeDuplicates(String s) {

        StringBuilder sb = new StringBuilder(s);       

        int i = 0;

        while(i < sb.length() - 1){

            if(sb.charAt(i) == sb.charAt(i + 1)){

                sb.delete(i, i + 2);                

                if (i > 0) {
                    i--; // move back to previous character
                }
            }
            else i++;
        }
        return sb.toString();

    }

}
