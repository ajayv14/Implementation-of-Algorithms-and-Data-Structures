package com.app.binarysearch;

// Using minimization template

public class firstBadVersion {

    public int firstBadVersion(int n) {

        int low = 0; int high = n;    

        while(low <= high){

            int mid = low + (high - low) /2;

            if(isBadVersion(mid)) {
                high = mid - 1;
            }

            else {
                low = mid + 1;
            }
        }
        return low;        
    }
}