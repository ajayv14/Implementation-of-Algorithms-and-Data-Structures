package com.app.arrays;

import java.util.Arrays;


// LC   3075
/**
 * Intuition
Need to sort array. Find a way to decrease value of other elements by 1 each iteration.

Approach
In the end, we need to pick k elements, we don't have to process rest.
Each element other than picked one, decreases by a factor of 1 in each iteration.

Sort the array,Pick last k elements by running k iterations.
In each iteration:
Iteration 1 : factor = 0; value of picked element : value
Iteration 2 : factor = 1; value of picked element : value - 1
Iteration 3 : factor = 3; value of picked element : value - 3 and so on.

This 'factor', can be computed with from current iteration count.

Complexity
Time complexity:
O(nlogn) + O(k)
Space complexity:
O(1)
 * 
 */

public class MaxHappinessChildren {


    public long maximumHappinessSum(int[] happiness, int k) {
        
        long maxHappiness = 0;
        Arrays.sort(happiness);

        //Optimization : i < happiness.length

        for(int i = 0; i < k && i < happiness.length; i++){

            long happy = happiness[happiness.length - 1 - i] - i; // -i is a factor by which other elements decrease in each iteration

            maxHappiness += (happy > 0) ? happy : 0;       
             
        }
        return maxHappiness;
    }

    public static void main(String[] args) {
        int [] h4 = new int[]{2135218,73431904,92495076,77528042,82824634,3036629,28375907,65220365,40948869,58914871,57169530,89783499,19582915,19676695,11932465,21770144,49740276,22303751,80746555,97391584,95775653,43396943,47271136,43935930,59643137,64183008,8892641,39587569,85086654,5663585,82925096,24868817,95900395,48155864,74447380,7618448,63299623,91141186,33347112,81951555,52867615,92184410,7024265,85525916,29846922,59532692,47267934,6514603,1137830,97807470};
        int k4 = 41;
        // Expected = 2517853814



    }

}
