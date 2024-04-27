package com.app.arrays;

class BestTimeToBuyStock2 {
    public int maxProfit(int[] prices) {

        if (prices.length == 0 || prices == null)
            return 0;

        int profit = 0;

        for (int i = 0; i < prices.length - 1; i++) { // compare prices i with i + 1

            if (prices[i + 1] > prices[i]) {
                profit += prices[i + 1] - prices[i];
            }

        }
        return profit;
    }

    public static void main(String[] asrgs) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        BestTimeToBuyStock2 obj = new BestTimeToBuyStock2();
        System.out.println(obj.maxProfit(prices)); // expected o/p is 7

    }
}