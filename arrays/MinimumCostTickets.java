

public class MinimumCostTickets {

    /*
     * There are 2 arrays which denote departing and returning flights with the respective indexes being 
     * time and the values of the array being the cost it takes for the flight.
     *  Return the minimum cost for a round trip provided the return flight can only be taken at a time post departing flight time 
     * (i.e if departing at time i, one can catch a returning flight only from time (i+1) onwards). 
     * For eg departing = [1,2,3,4] and returning = [4,3,2,1], 
     * the minimum cost for round trip will be 2 i.e departing[0] + returning[3]. Solve this is O(n) time
     * 
     */

     public int findMinCost(int[] departing, int[] returning) {

        int n = departing.length;
        int minReturn = Integer.MAX_VALUE;
        int minCost = Integer.MAX_VALUE;
    
        // Traverse backwards from the end to track minimum returning[i] for j > i
        for (int i = n - 1; i > 0; i--) {
            // Update minReturn before using it, since i is for departing
            minReturn = Math.min(minReturn, returning[i]);
            int tripCost = departing[i - 1] + minReturn;
            minCost = Math.min(minCost, tripCost);
        }
    
        return minCost;
    }
    
    public static void main(String[] args) {

        int[] departing = {1, 2, 3, 4};
        int[] returning = {4, 3, 2, 1};
        
        MinimumCostTickets obj = new MinimumCostTickets();

        int cost = obj.findMinCost(departing, returning);
        
        System.out.println("Expected : 2 " + "actual : " + cost);
    }
}
