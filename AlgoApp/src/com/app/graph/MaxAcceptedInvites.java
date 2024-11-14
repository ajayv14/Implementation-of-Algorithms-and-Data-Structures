package com.app.graph;

import java.util.Arrays;

// LC : 1820. Maximum Number of Accepted Invitations
// https://leetcode.com/problems/maximum-number-of-accepted-invitations/

public class MaxAcceptedInvites {

    public int maximumInvitations(int[][] grid) {

        // Track of which girl is matched with a boy
        int[] girlMatches = new int[grid[0].length];
        Arrays.fill(girlMatches,-1);

        int maxInvites = 0;

        // Rows represent boys
        for(int boy = 0; boy < grid.length; boy++){

            boolean[] visited = new boolean[grid[0].length];

            if(dfs(boy, visited, grid, girlMatches)) maxInvites++;    
        }

        return maxInvites;        
    }

    private boolean dfs(int boy,boolean[] visited, int[][] grid,int[] girlMatches){

        // Columns represent girls
        for(int girl = 0; girl < grid[0].length; girl++){

            if(grid[boy][girl] == 1 && !visited[girl]){
                
                visited[girl] = true;

                // Girl is not matched or if she is matched,proceed to be rematched
                // dfs(girlMatches[girl] contains the boy previously matched.
                if(girlMatches[girl] == -1 || dfs(girlMatches[girl], visited, grid, girlMatches)){
                    girlMatches[girl] = boy;
                    return true;
                }            
            }
        }
        return false;
    }

}
