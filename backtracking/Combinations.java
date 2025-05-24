

import java.util.List;
import java.util.ArrayList;


// LC 77 : https://leetcode.com/problems/combinations/

//Input: n = 4, k = 2
// Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
// There are 4 choose 2 = 6 total combinations.
// combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
class Combinations {
    
   public List<List<Integer>> combine(int n, int k) {
     
        List<List<Integer>> res = new ArrayList<>();
        combinations(res, new ArrayList<>(), 1, n, k);        
        return res;
    }
    
    public static void  combinations(List<List<Integer>> res, List<Integer> list, int start, int end, int k){
                
        if(list.size() == k) res.add(new ArrayList<Integer>(list)); // limit the combinations to size of k, add the list to main list
               
            
        for(int i = start; i <= end; i++){
                
            list.add(i);  
            combinations(res, list, i + 1, end, k); //note its i + 1 and not start + 1
            list.remove(list.size() - 1); // remove the last element of list after recursive call        
            
        }       
    } 
    
    /*--------------------------------------------------------------------------------------*/

    public static void main(String[] args){
        //sample
        Combinations obj = new Combinations();

                
        CommonUtil.runExample("Input : n = 4, k = 2", "Expected : [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]", obj.combine(4,2));

        CommonUtil.runExample("Input : n = 1, k = 1", "Expected : [[1]]", obj.combine(1,1));

    }
    
}