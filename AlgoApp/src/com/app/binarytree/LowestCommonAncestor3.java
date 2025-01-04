package com.app.binarytree;


// LC 1650. Lowest Common Ancestor of a Binary Tree III

// Time : O(H), height of the tree.
// Space : O(1)

public class LowestCommonAncestor3 {

    // Silimar to finding linked list intersection. Intutive algo
    public Node lowestCommonAncestor(Node p, Node q) {

        Node a = p;
        Node b = q;


        if(p.parent == q) return q;
        else if(q.parent == p) return p;

        while(a != b){

            a = (a.parent != null) ? a.parent : q;
            b = (b.parent != null) ? b.parent : p;

        } 

        return a;   // or b
    }

}
