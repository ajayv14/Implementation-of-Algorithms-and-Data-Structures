package com.app.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 1584. Min Cost to Connect All Points
//https://leetcode.com/problems/min-cost-to-connect-all-points



public class MinCostConnPoints {

     public int minCostConnectPoints(int[][] points) {

        //Here we have coordinates, but we have to calculate weight(euclid dist) between 2 sets of points. 


        List<int[]> adjList = new ArrayList();

        for(int i = 0; i < points.length; i++){
            
            for(int j = 1; j < points.length; j++){

                int euclidDist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);

                int[] edge = {i,j,euclidDist}; // Note, we use row and col position (i,j).

                adjList.add(edge);   

            }                  

        }

        //Part of Krushkal's algo to pick smallest edges
        Collections.sort(adjList, (a,b) -> Integer.compare(a[2],b[2]));  

        UnionFind uf = new UnionFind(adjList.size());    

        int cost = 0;

            for(int[] edge : adjList){

                int source = edge[0];
                int dest = edge[1];
                int euclidDist = edge[2];

                if(uf.union(source, dest)) cost += euclidDist; // Create a connected component - MST
            }      

        return cost;

        
    }




    class UnionFind {

        int[] parent;
        int[] rank;

        public UnionFind(int size){

            parent = new int[size];
            rank = new int[size];

            for(int i = 0; i < parent.length; i++){
                parent[i] = i;
            }
        }


        public boolean union(int x, int y){

            int rootX = find(x);
            int rootY = find(y);

            if(rootX == rootY) return false;

            if(rank[rootX] > rank[rootY]){
                parent[rootY] = rootX;
            }

            else if (rank[rootY] > rank[rootX]){
                parent[rootX] = rootY;
            }

            else {
                rank[rootX]++;
                parent[rootY] = rootX;
            } 

            return true;    
                        
        }

        public int find(int a){

            if(parent[a] != a) parent[a] = find(parent[a]);
            return parent[a];
        }

    }

}
