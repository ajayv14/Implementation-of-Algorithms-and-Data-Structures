


// LC 1650. Lowest Common Ancestor of a Binary Tree III

// Time : O(H), height of the tree.
// Space : O(1)

public class LowestCommonAncestor3 {

    // Silimar to finding linked list intersection. Intutive algo
    // We don't have root node, we got to find LCA of two nodes
    public Node lowestCommonAncestor(Node p, Node q) {

        Node n = p;
        Node m = q;
       
        while(n != m){

            // Move node to parent, else we have reached root, so let it stay the same;
            n = n.parent != null ? n.parent : p;
            m = m.parent != null ? m.parent : q;

        }
        return n;
                
    }

}
