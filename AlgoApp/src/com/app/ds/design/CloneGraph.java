package com.app.ds.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

// LC 133. Clone Graph

/*
class Node {
    public int val;
    public List<Node> neighbors;
}*/


// Time : O(N + M) Space : O(N)

public class CloneGraph {

    //<Original node, cloned node>    
    Map<Node,Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {


        if(node == null) return node;

        return dfs(node);
    }


    private Node dfs(Node node){

        if(visited.containsKey(node))return visited.get(node);
        
                
        Node cloneNode = new Node(node.val, new ArrayList<>());

        visited.put(node, cloneNode);

        for(Node n : node.neighbors){
            cloneNode.neighbors.add(dfs(n));
        }      
               
        return cloneNode;


    }

}
