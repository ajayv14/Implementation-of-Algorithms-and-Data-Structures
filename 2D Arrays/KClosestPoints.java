class KClosestPoints {
    
    /*Logic : distance to a point from  origin for (a1,a2) = a1*a1 + *a2*a2 . 
              Use priority Queue - max heap to add all points, then pick kth point*/
    
    public int[][] kClosest(int[][] points, int K) {
        
        int[][] result = new int[K][2];        
           
        //PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]));
        //PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b)->( Math.sqrt( (b[1] - a[1]) * (b[1] - a[1])  + (b[0] - a[0]) * (b[0] - a[0])  ) ) );
        
        
       PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            
            
            public int compare(int[] a, int[] b){
                
                return ( ( (b[0]*b[0]) + (b[1]*b[1]) ) - ( (a[0]*a[0]) + (a[1]*a[1]) )  );
                
            }            
            
            
        });
        
        
        
        for(int[] point : points){
            pq.add(point);
            
            if(pq.size() > K) pq.remove(); 
        }
        
        for(int i = 0; i < K; i++){
            result[i] = pq.remove();            
        }      
        
        return result;        
    }
}