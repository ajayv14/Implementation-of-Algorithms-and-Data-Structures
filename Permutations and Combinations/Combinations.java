import java.util.List;
import java.util.ArrayList;
class Combinations {
    
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combinations(res, new ArrayList<>(), 1, n, k);        
        return res;
    }
    
    public void  combinations(List<List<Integer>> res, List<Integer> list, int start, int end, int k){
                
        if(list.size() == k) res.add(new ArrayList<Integer>(list)); // limit the combinations to size of k, add the list to main list
        
        else{
            
            for(int i = start; i <= end; i++){
                if(list.contains(i)) return;
           
                else{
                    list.add(i);  
                    combinations(res, list, i + 1, end, k); //note its i + 1 and not start + 1
                    list.remove(list.size() - 1); // remove the last element of list after recursive call                    
                }
            }          
        }       
    } 
    
    /*--------------------------------------------------------------------------------------*/

    public static void main(String[] args){
        //sample
        Combinations obj = new Combinations();
        List<List<Integer>> res = obj.combine(4,2);
        obj.printResult(res);
    }

    public void printResult(List<List<Integer>> res){
        for(List l : res){
          System.out.println(l);
        }
    }   
    
}