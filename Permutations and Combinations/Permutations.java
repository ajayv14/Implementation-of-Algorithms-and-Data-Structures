import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        permutations(result, new ArrayList<>(), nums);
        return result;       
    }   
    
    /*backtracking helper function*/
    public void permutations(List<List<Integer>> result, List<Integer> list, int[] nums){
        
        /*make sure the temp list has equal elements that of original array (coz permutations)*/

        if(list.size() == nums.length) result.add(new ArrayList<>(list));
                             
        else {
            for(int i = 0; i < nums.length; i++){
               
               /* skip element if already present */
               if(list.contains(nums[i])) continue;  
                
                else{
                  list.add(nums[i]);
                  permutations(result,list,nums);
                  list.remove(list.size() - 1);
                }             
            }        
        }    
    }

 /*--------------------------------------------------------------------------------------*/

    public static void main(String[] args){
        //sample
        int[] nums = new int[3];
        // initialization
        nums[0] = 3; nums[1] = 6; nums[2] = 9;
        Permutations obj = new Permutations();
        List<List<Integer>> res = obj.permute(nums);
        obj.printResult(res);
    }

    public void printResult(List<List<Integer>> res){
        for(List l : res){
          System.out.println(l);
        }
    }   
    
}