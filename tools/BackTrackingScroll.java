public class BackTrackingScroll {



// Pay attention to : res.add(new ArrayList<>(temp));

// input : [1,2,3] 
//expected op : [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

class Subsets {
   
    public List<List<Integer>> subsets(int[] nums) {
        
        /*Final List to be returned*/
        
        List<List<Integer>> MainList = new ArrayList<>();       
        //Arrays.sort(nums); not necessary, if order of subsets is not important
        
        /*backtracking helper function*/
        
        backtracking(MainList, nums, new ArrayList<>(),0); /*with start index 0*/
        
        return MainList;
        
    }
    
    
    public void backtracking(List<List<Integer>> MainList, int[] nums, List<Integer> tempList, int start){
        
        MainList.add(new ArrayList<>(tempList)); /*initially add empty list*/
        
        /*run from start to nums.length - 1*/
        for(int i = start;  i < nums.length; i++){
            
            tempList.add(nums[i]);
            backtracking(MainList,nums,tempList, i+1);
            tempList.remove(tempList.size() - 1); /*remove last element from list*/
                     
        }             
        
        
    }   
}


// LC 90 : https://leetcode.com/problems/subsets-ii/

// nums may contain duplicates, set must not contain duplicate subsets

// Input: nums = [1,2,2]
// Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

/* we have duplicates, 

    so [1,2,2] will have multiple duplicate subsets like 
    [[],[1],[1,2],[1,2,2],[1,2],[2],[2,2],[2]]
                
    to avoid it, we check if nums[i] == nums[i -1] and continue if so.
                
    at index i = 0, i - 1 = -1 will throw arrayOut of bounds exception.
                
    i > start prevents index out of bounds exception, continue - breaks and moves to next iteration of loop*/


class Subsets2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // necessary when duplicates are present
        backtracking(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    
           
    private void backtracking(List<List<Integer>> res, List<Integer> temp, int[] nums, int start) {

        res.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {

            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
           
            temp.add(nums[i]);
            backtracking(res, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}



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




// Input: nums = [1,2,3]
// Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
class Permutations {
    
   public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();        
        permutations(res, new ArrayList<>(),nums);        
        return res;        
    }  
    
    
    public void permutations(List<List<Integer>> res, List<Integer> temp, int[] nums){
        
        if(temp.size() == nums.length) res.add(new ArrayList<>(temp));
                
            
        for(int i = 0; i < nums.length; i++){
                
            if(temp.contains(nums[i])) continue;    //skip elements already present    
                
            temp.add(nums[i]); 
            permutations(res, temp, nums);
            temp.remove(temp.size() - 1);        
        }          
                
    }   
    
}


// nums = [1,1,2]
// Output: [[1,1,2], [1,2,1], [2,1,1]]
public class Permutations2 {

    boolean[] track; 

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> results = new ArrayList<>();

        Arrays.sort(nums);

        track = new boolean[nums.length];

        backtrack(results, new ArrayList<>(), nums);

        return results;
        
    }

    /*
    i > 0: Ensures we're not looking at the first element

    nums[i] == nums[i-1]: Checks if the current element is a duplicate of the previous element

    !used[i - 1]: Checks if the previous duplicate element is NOT used in the current permutation path
    
    Like [1,1,2]), we only want to use the second "1" if we've already used the first "1" in the current permutation path.

    If we encounter the second "1" and the first "1" is not used 
    (meaning we've backtracked from a path that used the first "1"),
    we skip the second "1" to avoid generating the same permutation again.    
    */
    private void backtrack( List<List<Integer>> results, List<Integer> tempList, int[] nums){

        if(tempList.size() == nums.length) {

            results.add(new ArrayList<>(tempList));

        }

        for(int i = 0; i < nums.length; i++){

            if(track[i] || i > 0 && nums[i] == nums[i - 1] && !track[i - 1]) continue;

            tempList.add(nums[i]);

            track[i] = true;

            backtrack(results, tempList, nums);    

            tempList.remove(tempList.size() - 1);

            track[i] = false;
        }
    }
}





    /*
        Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
        Output: ["cats and dog","cat sand dog"]     
     */
    class WordBreak2 {

        // Time 2 ^ n, space 2 ^ n
        Set<String> dict;

        public List<String> wordBreak(String s, List<String> wordDict) {

                dict = new HashSet<>(wordDict);

                List<String> results = new ArrayList<>();

                recurse(s, 0, new StringBuilder(), results);

                return results;
            
        }


        private void recurse(String s, int right, StringBuilder sb, List<String> results){

            // reached end of input string 
            if(right == s.length()){
                
                System.out.println("End of string reached, returning");
                results.add(sb.toString().trim());

                return;
            }

            for(int left = right + 1; left <= s.length(); left++){

                String sub = s.substring(right, left);

                System.out.println(sub);    

                if(dict.contains(sub)){

                    System.out.println("Found in dict, calling recursion : " + sub); 
                    
                    // Backtracking - restore the original sentence after recursion, 
                    // much like trimming list in permutaions, combi, subsets template
                    
                    int originalLen = sb.length();

                    sb.append(sub).append(" ");
                
                    recurse(s, left, sb, results);

                    sb.setLength(originalLen);
                }   
            }  

            System.out.println("End of recurse method");  

        }
    }
}
