class CourseSchedule2 {

     /*logic : 

     1) Topological sort - We need a map to map all neighbors of a vertex--[a,b]--> a is neighbor of b.
     2) indegree array to store number of incoming edges for each vertex   
     3) check if any vertex has 0 indegree and add it to a Queue
        Now remove from the queue, add to result. Check if there are any neighbors and 
        decrement the indegree of neigh. coz the current vertex is removed from queue (in-coming edge- indegree is removed) . 
        If indegree is 0, enqueue them. 
     */           


    public int[] findOrder(int numCourses, int[][] prerequisites) {
     
        
        // kahn's Topological sorting
        
        int[] result = new int[numCourses];
        int count = 0; // pointer for result array and check cyclical dependency
        
        //<k,v> vertex, neighbors 
        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        
        int[] indegree = new int[numCourses]; // number of incoming edges
        
        for(int[] pre : prerequisites){
            
            List<Integer> list = adjacency.getOrDefault(pre[1], new ArrayList<>());
            list.add(pre[0]);
            indegree[pre[0]]++;
            adjacency.put(pre[1],list);
        } 
        
        //System.out.println(adjacency);
        
        Queue<Integer> queue = new LinkedList<>();
        
        // pick the ones that have zero indegree and add to queue
        for(int i = 0; i < indegree.length; i++ ){
            //System.out.println(indegree[i]);
            if(indegree[i] == 0) queue.add(i); // value of indegree will be 0, so add i to queue             
        }
        
               
        while(!queue.isEmpty()){
            
             int cur = queue.poll();
                       
             result[count++] = cur; 
             
                          
             // does not contain in keyset -- has no neighbors, but cud be a neighbor of some vertex 
              if(!adjacency.containsKey(cur)) continue;
             
             
              // add 0 indegree neighbors to queue, otherwise decrease indegree as the link is broken when a vertex is removed from queue
             //else {
                 
                 List<Integer> neighbors = adjacency.get(cur);
                 
                 for(int neighbor : neighbors){
                     
                     indegree[neighbor]--;
                     
                     if(indegree[neighbor] == 0) queue.add(neighbor);
                     
                 }
                 
                 
             //}        
              
        }
        
         return (count == numCourses)?result: new int[0]; // acts as count to check cyclical dependency 
                 
        
    }
}