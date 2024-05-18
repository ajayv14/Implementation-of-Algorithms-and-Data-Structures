
package com.app.backtracking;


import java.util.List;

import com.app.common.CommonUtil;

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
        CommonUtil.runExample("Input : n = 4, k = 2", "Expected : [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]", obj.combine(4,2));

        CommonUtil.runExample("Input : n = 1, k = 1", "Expected : [[1]]", obj.combine(1,1));

    }
    
}