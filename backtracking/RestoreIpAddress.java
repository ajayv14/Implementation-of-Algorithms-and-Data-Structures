/*
    Approach : 

    Split the ip address string into 4 segments.
    Use backtracking to simulate different possibilities.
    Validate the correct ip address and return a list

    Example : 25525511135
    ignored sample : 4 segments but incomplete & invalid string: 2.5.5.25
    valid sample : 4 segments, complete & valid 255.255.11.135
 */

// LC 93 : https://leetcode.com/problems/restore-ip-addresses/


import java.util.ArrayList;
import java.util.List;

class RestoreIpAddress {

    public List<String> restoreIpAddresses(String s) {

          List<String> result = new ArrayList<>();  

          backtrack(s, 0, new ArrayList<>(), result); 

          return result;
    }


    private void backtrack(String s, int start, List<String> path,  List<String> result){

        // Termination condition
        if(path.size() == 4) {
                    

            // reached end of string
            if(start == s.length()){
                result.add(String.join(".", path));  
                
                System.out.println("valid : " + String.join(".", path));                
            }

            //Ignored segments
            else System.out.println("ignored : " + String.join(".", path));    

            return;
        } 
                 

        // Which ever ends first
        int newLen = Math.min(start + 3, s.length());

        for(int end = start + 1; end <= newLen; end++){

            String segment = s.substring(start,end);

            if(segmentValidityCheck(segment)){

                path.add(segment);

                System.out.println(segment);
                
                backtrack(s, end, path, result);

                path.remove(path.size() - 1);

            }
        }   

    }


    private boolean segmentValidityCheck(String segment){

        // Segment og length > 1 but starts with 0 -> 065 
        if (segment.length() > 1 && segment.startsWith("0")) {
            return false;
        }

        // Range check 0 - 255
        int num = Integer.parseInt(segment);
        return num >= 0 && num <= 255;        
    }


}