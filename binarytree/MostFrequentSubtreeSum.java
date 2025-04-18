//credits - Most Frequent Subtree Sum : https://leetcode.com/problems/most-frequent-subtree-sum/

import com.app.models.TreeNode;

class MostFrequentSubtreeSum {
    
    /*logic : Do in order traversal - left - root - right. For each node, compute sum of its subtree, insert the sum into map
              Now find max value in <key,value> in map. Then again traverse the map to find all sum with value == max.
    */
    
    
    //<sum,freq>
    Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    
    public int[] findFrequentTreeSum(TreeNode root) {
        
        // to hold result
        List<Integer> result = new ArrayList<>();
        
        /*computes subtree sum for each root-node(subtree)*/
        subTreeSum(root); 
        
        //System.out.println(map);        
        int max = Integer.MIN_VALUE;
        
        /*find max value of value in <key,value>*/
        for(int value : map.values()){            
           max = Math.max(max, value);                  
        }
        
        /*populate list with items == max value*/
        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            
           if(e.getValue() == max){
              result.add(e.getKey());               
            }          
        }              
      
        
       /*convert list to array*/ 
       int[] res = new int[result.size()];
       
       for(int i = 0; i < result.size(); i++){
            res[i] = result.get(i);            
        }
        
        return res;        
    }
    
    
    
    private int subTreeSum(TreeNode root){
                        
        if(root == null) return 0; 
            
        int left = 0, right = 0;
                
        left = subTreeSum(root.left);            
        
        right = subTreeSum(root.right);
        
        int sum = root.val + left + right; 
        
        map.put(sum, map.getOrDefault(sum,0) + 1);
        
        return sum;           
        
        
    } 
    
}