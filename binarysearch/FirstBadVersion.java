package com.app.binarysearch;

// Using minimization template

public class firstBadVersion {

    /*
        Uisng the binary search template :

        Pick it it is minimization or maxization problem.
        (In array of bad versions,
         do we move from right to left - minimization - high moved to mid 
          or move left to right - maximization - left moves to mid ?)

         Accordingly we return low or high based on which direction it moves.
         Left moves towards mid - return low
         Right moved towards mid - return high/ 
   
    */     


    public int firstBadVersion(int n) {

        int low = -1,  high = n;    

        while(low + 1 < high){

            double mid = low + Math.floor(high - low) /2;

            if(isBadVersion((int)mid)) {
                high = mid;
            }

            else {
                low = mid;
            }
        }

        return high;        
    }
}