package com.app.graph;

import java.util.Arrays;
//https://leetcode.com/problems/connecting-cities-with-minimum-cost/
//1135. Connecting Cities With Minimum Cost

public class ConnectingCitiesMinCost {

     //Kruskal's Algorithm to create MST
    public int minimumCost(int n, int[][] connections) {


        int minCost = 0;

        // Helps in implementing krushkals MST
        UnionFind uf = new UnionFind(n);    

        // Differs from Prim by sorting edges by weights
        Arrays.sort(connections, (c1,c2)->Integer.compare(c1[2],c2[2]));    
        
        int edgeCount = 0;

        for(int[] conn : connections){

            int city1 = conn[0];
            int city2 = conn[1];
            int weight = conn[2];

            if(uf.union(city1,city2)){
                minCost += weight;
                edgeCount++;
            }
        }
        
        return edgeCount == n - 1 ? minCost : -1;

        

    }

    // Standard Union Find approach
    class UnionFind {

        int[] parent;
        int[] rank;

        public UnionFind(int n){
            parent = new int[n + 1];
            rank = new int[n + 1];

            for(int i = 0; i < n + 1; i++){
                parent[i] = i;
            }
        }
               
        // Path compression Purpose: It ensures that the parent of each node points directly to the root of its set. This reduces the time complexity of future find operations by flattening the structure of the tree.
        public int find(int i){

            if(parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public boolean union(int x, int y){

            int rootX = find(x);
            int rootY = find(y);

            if(rootX == rootY) return false;

            else if(rank[rootX] > rank[rootY]){
                parent[rootY] = rootX;
            }

            else if(rank[rootY] > rank[rootX]){
                parent[rootX] = rootY;
            }
            
            /*
             * Union by rank is implemented in the union method.

             Purpose: It ensures that the tree remains as shallow as possible by always attaching the smaller tree under the larger one based on rank.
             * 
             */

            else if(rank[rootX] == rank[rootY]){
                parent[rootX] = rootY;
                rank[rootY]++;
            }

            return true;
        }



    }

}
