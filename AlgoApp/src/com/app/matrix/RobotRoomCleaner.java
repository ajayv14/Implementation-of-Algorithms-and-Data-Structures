package com.app.matrix;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner {



    int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};

    Set<Pair<Integer, Integer>> visited = new HashSet<>();

    public void cleanRoom(Robot robot) {

        backtrack(robot, 0, 0, 0);             
       
    }

    private void backtrack(Robot robot, int row, int col, int direction){

        visited.add(new Pair(row, col));
        robot.clean(); // current cell

        for(int i = 0; i < dirs.length; i++){

            int newDirection = (direction + i) % 4; // % 4 to keep newDirection within bounds

            int newRow = row + dirs[newDirection][0];
            int newCol = col + dirs[newDirection][1];


            if(!visited.contains(new Pair(newRow, newCol)) && robot.move()){
                
                backtrack(robot, newRow, newCol, newDirection);
                goToBeginning(robot);
            }       
            
            robot.turnLeft(); // can be right or left.

        }


    }

    private void goToBeginning(Robot robot){

        robot.turnRight(); // 90 degree
        robot.turnRight();
        robot.move();

        // Stop and point to same direction as it started
        robot.turnRight(); 
        robot.turnRight(); 
    }

}
