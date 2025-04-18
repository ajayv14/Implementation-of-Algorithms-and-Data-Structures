public class BinaryTreeFromString {


    public TreeNode str2tree(String s) {

        if(s == null || s.length() == 0) return null;

        Pair<TreeNode, Integer> result = buildTree(s,0);        

        return result.key;
                
    }

    // Construct tree - returns TreeNode and index in a list
    private Pair<TreeNode,Integer> buildTree(String s, int index){
     
        if(index == s.length()) return new Pair(null, index);

        // returns val for root and next index to be processed
        Pair<Integer,Integer> NumAndIndex =  getNumber(s, index);

        int num = NumAndIndex.key;
        index = NumAndIndex.value;

        // Build tree root node first
        TreeNode root = new TreeNode(num);

        // Check for left and rigth child
        // Next immediate value will be the left child
        
        if(index < s.length() && s.charAt(index) == '('){

              // Make a recursive call here to construct another subtree  
              Pair<TreeNode,Integer> leftChildInfo =  buildTree(s,index + 1);
                           
              root.left = leftChildInfo.key;
              // Update index
              index = leftChildInfo.value;
        }

        // Now check if we can find '(' - Right child    

        if(root.left != null && index < s.length() && s.charAt(index) == '('){

            // Make a recursive call here to construct another subtree  
            Pair<TreeNode,Integer> rightChildInfo =  buildTree(s,index + 1);
            root.right = rightChildInfo.key;
            index = rightChildInfo.value;
        }    

        // Construct result
        // root node and index for this subtree 
        Pair<TreeNode, Integer> res = new Pair();    

        res.key = root; // root of tree

        index = (index < s.length() && s.charAt(index) == ')' ? index + 1 : index);    
        
        res.value = index;

        return res;
    }




    // process numbers in string, return num and index
    private Pair<Integer,Integer> getNumber(String s, int index){

        boolean isNegative = false;

        if(s.charAt(index) == '-'){
            isNegative = true;
            index++;
        } 

        int num = 0;
        while(index < s.length() && Character.isDigit(s.charAt(index))){

            num = num * 10 + (s.charAt(index) - '0');
            index++;
        }

        if(isNegative) num = -num;
        
        return new Pair(num, index);    
    }




    class Pair<K,V> {
        
        K key;
        V value;

        public Pair(){                     
        }

        public Pair(K k, V v){
            this.key = k;
            this.value = v;            
        }
    }

}
