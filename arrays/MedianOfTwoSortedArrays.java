package com.app.array;

public class MedianOfTwoSortedArrays {

    // https://leetcode.com/problems/median-of-two-sorted-arrays
    // LC 4. Median of Two Sorted Arrays
    // Time : O Log (m + n ) solution 

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int n1 = nums1.length, n2 = nums2.length;

        int total = (n1 + n2 + 1) / 2;

        if(n1 > n2) return findMedianSortedArrays(nums2, nums1);
        
        int low = 0, high = n1;

        while(low <= high){

            int mid1 = low + (high - low) / 2;
            int mid2 = total - mid1;

            int l1 = mid1 - 1 >= 0 ? nums1[mid1 - 1] : Integer.MIN_VALUE;
                
            int r1 = mid1 < n1 ? nums1[mid1] : Integer.MAX_VALUE;

            int l2 = mid2 - 1 >= 0 ? nums2[mid2 - 1] : Integer.MIN_VALUE;
                
            int r2 = mid2 < n2 ? nums2[mid2] : Integer.MAX_VALUE;

            
            if(l1 <= r2 && l2 <= r1){

                if((n1 + n2) % 2 == 0) return (Math.max(l1,l2) + Math.min(r1,r2)) / 2.0; 

                else return Math.max(l1, l2);

            }

            else if(l1 > r2) high = mid1 - 1;

            else  low = mid1 + 1;             
                
        }

        
        return -1;

    }

   
   
    
    
    /*public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        
        int[] merged = merge(nums1,nums2);

        int n = merged.length;

        if(n % 2  == 0){

            return (merged[n/2 - 1] + merged[n/2])/2.0;
        }  

        else return merged[n / 2];

    }



    private int[] merge(int[] nums1, int[] nums2){

        int l1 = nums1.length, l2 = nums2.length;

        int[] res = new int[l1 + l2];

        int i = 0, j = 0, k = 0;

        while(i < l1 && j < l2 && k < res.length){

            if(nums1[i] < nums2[j]) res[k++] = nums1[i++];

            else res[k++] = nums2[j++];
        }

        while(i < l1){
            res[k++] = nums1[i++];
        }

        while(j < l2){
            res[k++] = nums2[j++];
        }

        return res;

    }*/
}
