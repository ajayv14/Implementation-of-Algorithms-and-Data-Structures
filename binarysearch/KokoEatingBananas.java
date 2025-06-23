

    /*
        Approach - Min can eat 1 and max it can eat max num of banana in largest pile.
        
        Now we need to optimize this value using the range min - max, using binary search.

        So now use this to determine time taken to complete all piles. 
        
        Use Math.ceil to calculate the time.

     */

       
class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {


        // Binary search minimization

        int max = 0; // 1 banana per hour

        for(int num : piles){

            max = Math.max(max, num);
        }

        // Now we need to optimize num of banana per hour from 1 - max;
        // Can use for loop to test 1 .....max, but can use binary search to optimize 

        int low = 0, high = max;

        while(low + 1 < high){

            int mid = low + (high - low) / 2; // pick a number of banana that can be eaten in an hour 

            boolean yes = canEat(mid, piles, h);

            if(yes) high = mid;

            else low = mid;
        }    

        return high;

    }


    private boolean canEat(int mid, int[] piles, int maxHours){

        int time = 0;

        for(int i = 0; i < piles.length; i++){

            time += Math.ceil((double)piles[i]/mid);
        }

        return time <= maxHours;

    }
}