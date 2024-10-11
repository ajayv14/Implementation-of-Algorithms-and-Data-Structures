package com.app.graph;

//LC 684 ; https://leetcode.com/problems/redundant-connection/

    /* Intution - Union Find can help identifying the redundant edge as both vertices of an edge can't have same  
     * parent. 
     * Straight forward implementation of union find.
     */

public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {

        
        UnionFind uF = new UnionFind(1001); // Mentioned in constraints

        for(int[] edge : edges){
            if(!uF.union(edge[0], edge[1])) return edge;
        }

        return null;
    }

    class UnionFind {

        int[] parent;
        int[] rank;

        public UnionFind(int size){

            parent = new int[size];
            rank = new int[size];

            for(int i = 0; i < size; i++){
                parent[i] = i;
            }

        }



        public int find(int i){

            if(parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }


        public boolean union(int x, int y){

            int rootX = find(x);
            int rootY = find(y);

            if(rootX == rootY){
                return false;
            }

            else if(rank[rootX] > rank[rootY]){
                parent[rootY] = rootX;
            }    

            else if(rank[rootY] > rank[rootX]){
                parent[rootX] = rootY;
            }

            else if(rank[rootX] == rank[rootY]){
                parent[rootX] = rootY;
                rank[rootX]++;
            }

            return true;

        }


    }





}
