package com.app.imortantinterview;


// LC 1414  Difficulty : 4/10
public class MinFIbonacciSumK {


    public int findMinFibonacciNumbers(int k) {

        // Compute fib series
        int[] arr = computeFibUptoK(k);

        int j = arr.length - 1;

        int sum = 0;
        int count = 0;

        // Greedy approach
        /*
         * - Pick the largest element equal to or lesser than k. (Most optimal count for
         * this num)
         * - Subtract number from k. Now find most optimal count for this num.
         */
        while (j >= 0) {

            if (arr[j] == k)
                return count + 1;

            if (arr[j] > k || arr[j] == 0)
                j--; // Find an element smaller than k

            // arr[j] < k
            else {
                k = k - arr[j];
                count++;
                j--;
            }
        }

        return count;
    }

    private int[] computeFibUptoK(int k) {

        int[] arr = new int[47]; // 46th is greates fib number

        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < 47; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];

            if (arr[i] >= k)
                break; // Stop computing fib greater than k
        }
        return arr;
    }



    public static void main(String[] args) {
        
        MinFIbonacciSumK obj = new MinFIbonacciSumK();

        int k1 = 10;
        int k2 = 19;
        int k3 = 645157245;


        System.out.println("Expected : 2");
        System.out.println("Actual : " + obj.findMinFibonacciNumbers(k1));
        
        System.out.println("Expected : 3");
        System.out.println("Actual : " + obj.findMinFibonacciNumbers(k2));

        System.out.println("Expected : 13");
        System.out.println("Actual : " + obj.findMinFibonacciNumbers(k3));
    }

}
