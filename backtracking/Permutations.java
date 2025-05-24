
import java.util.List;
import java.util.ArrayList;

class Permutations {
    
    public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();        
        permutations(res, new ArrayList<>(),nums);        
        return res;        
    }  
    
    
    public void permutations(List<List<Integer>> res, List<Integer> temp, int[] nums){
        
        if(temp.size() == nums.length) res.add(new ArrayList<>(temp));
        
        
            
        for(int num : nums){
                
            if(temp.contains(num)) continue;    //skip elements already present    
                
            temp.add(num); 
            permutations(res, temp, nums);
            temp.remove(temp.size() - 1);        
        }          
                
    }   


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