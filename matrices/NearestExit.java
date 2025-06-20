

// LC 1926 https://leetcode.com/problems/nearest-exit-from-entrance-in-maze

class NearestExit {


    public int nearestExit(char[][] maze, int[] entrance) {
        

        int [][] dirs = new int [][] {{-1,0}, {0,1}, {1,0}, {0,-1}};

        Queue<int[]> q = new LinkedList<>(); // BFS queue

        int currentDist = 0;
   
        q.add(new int[] {entrance[0], entrance[1], 0}); // 0 is the current distance

        // mark entrance as visited
        maze[entrance[0]][entrance[1]] = '+';

        while(!q.isEmpty()){

            int[] currentPos = q.remove();

            // check for neighbor cells on left, right, top and bottom
            for(int[] dir : dirs){
                 
                 int newRow = currentPos[0] + dir[0];
                 int newCol = currentPos[1] + dir[1];
                 
                 currentDist = currentPos[2];

                // check boundaries and if cell contains "."
                if(newRow < 0 || newRow >= maze.length || newCol < 0 || newCol >= maze[0].length ||
                                     maze[newRow][newCol] != '.') continue;



                // exit found when cell is touching boundary                    
                if(newRow == 0 || newRow == maze.length - 1 || newCol == 0 || newCol == maze[0].length - 1){
                        return currentDist + 1;
                }                    

                // mark visited
                maze[newRow][newCol] = '+';
                
                q.add(new int[] {newRow, newCol, currentDist + 1});                        
                                      

            }
        }

        return -1;

    }
}