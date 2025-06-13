public class GasStation {

    //LC 134 : https://leetcode.com/problems/gas-station/

    /*
        Approach : 
        
        Choose starting point as 0 and proceed to see if tank has enough gas to reach next station.
        If not, we have to reset and start from next station again.

        We also need another variable to keep track of gas remaining overall (Positive overall gas proves the circular path exists from start to end )       
     
     */

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int remainingGas = 0; // To find starting point 
        int totalGas = 0; // To check overall if the loop is possible
        int start = 0;

        for(int i = 0; i < cost.length; i++ ){

            remainingGas += gas[i] - cost[i];
            totalGas += gas[i] - cost[i];

            if(remainingGas < 0){
                start = i + 1; // pick the next start point
                remainingGas = 0; // reset
            }            
        }   

        return (totalGas >= 0) ? start : -1;
    }

}
