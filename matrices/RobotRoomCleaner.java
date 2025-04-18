package com.app.matrices;

import java.util.HashSet;
import java.util.Set;

import com.app.models.Pair;

public class RobotRoomCleaner {



    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    Set<Pair<Integer, Integer>> visited = new HashSet<>();

    public void cleanRoom(Robot robot) {

        dfs(robot, 0, 0, 0);             
       
    }

    private void dfs(Robot robot, int row, int col, int direction){

        visited.add(new Pair(row, col));
        robot.clean(); // current cell


        // From the current direction, we have 4 directions that the bot can move
        for(int i = 0; i < 4; i++){

            int newDirection = (direction + i) % 4; // % 4 to keep newDirection within bounds
            
            int newRow = row + dirs[newDirection][0];
            int newCol = col + dirs[newDirection][1];

            // Not visited ?? Then call robot.move() and move into new cell.
            // Start the dfs again to expolre this path
            if(!visited.contains(new Pair(newRow, newCol)) && robot.move()){
                
                     dfs(robot, newRow, newCol, newDirection);                   
                     // When all 4 surrounding cells are visited/closed off, we got to step back 
                     goTopreviousCell(robot);       
            } 

            // Note : the actual dir of robot is controlled here.
            robot.turnRight(); // Turn direction each time at 90 deg            
            
        } 
    }

    // Sort of stepping back
    private void goTopreviousCell(Robot robot){

        robot.turnRight(); // 90 degree
        robot.turnRight();
        
         //move into new cell - we don't check for return tru as we know 180 degree will lead top previous cell.
        robot.move();

        // Stop and point to same direction as it started - UP
        robot.turnRight(); 
        robot.turnRight(); 
    }

}
