/**
 * Logic : Pick a node and perform dfs until connection exists. Once connection ends, 
 increment cluster count. Move to next node and repeat dfs. 
 */
class NumberOfProvinces {
    
    public int findCircleNum(int[][] isConnected) {

    int[] visited = new int[isConnected.length]; 

    int clusters = 0;  


    /** Loop thro cities */
    for(int city = 0 ; city < isConnected.length; city++){

        if(visited[city] != 1){
            clusters++; // new cluster begins
            dfs(city, visited, isConnected); // perform dfs and explore connections to other cities.
        }
    }   
    return clusters;    
    }


    public void dfs(int city, int[] visited, int[][] isConnected){

        visited[city] = 1; // mark visited

        for(int col = 0; col < isConnected.length; col++){

            //cities are connected and new city is unvisited    
            if(isConnected[city][col] == 1 && visited[col] != 1){ 
                dfs(col, visited, isConnected);
            }

        }                

    }


    public static void main(String[] args){

        NumberOfProvinces obj = new NumberOfProvinces();

        int[][] grid1 = new int[][]{{1,1,0},{1,1,0},{0,0,1}}; // Expected result : 2
        int[][] grid2 = new int[][]{{1,0,0},{0,1,0},{0,0,1}}; // Expected result : 3

        System.out.println("Result : " + obj.findCircleNum(grid1));
        System.out.println("Result : " + obj.findCircleNum(grid2));

    }

}