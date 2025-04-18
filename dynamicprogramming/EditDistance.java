package com.app.dynamicprogramming;

import com.app.common.CommonUtil;

// LC 72
class EditDistance {

    // Credits :
    // https://www.youtube.com/watch?v=XYi2-LPrwm4&list=PLot-Xpze53lcvx_tjrr_m2lgD2NsRHlNO&index=25

    /**
     * Approach :
     * Example word1 : "anc" --> word 2 : "abc".
     * 1. i points to 'a' in word1. j poiunts to 'a' in word2.
     * Since 'a' == 'a', we can ignore and take up sub problem "nc" --> "bc",
     * by incrementing i+1 and j+1. Hence this position denoted by [i + 1][j + 1]
     * 
     * 2. Now if 'n' != 'b'. We can do 3 possible operations :
     * a. Insert a new character 'b' before 'n', to match with 'b' where j is
     * pointing to.
     * Here i remains at n and j pointer to j + 1 as the inserted char matches.
     * This posiiton --> 1 + [i][j + 1], where 1 is operation cost.
     * 
     * b. Delete character 'n' as 'n' != 'b'. hence i moves on to 'c' in word1 --> i
     * + 1
     * J remains at 'c '
     * This position --> [i + 1][j]
     * 
     * c. Replace 'n' with 'd', now 'd' == 'd' in word2. hence both i and j can move
     * a step
     * forward to solve next sub problem.
     * This position --> [i + 1][j + 1]
     * 
     * dp matrix
     * 
     * a b c ""
     * a 3
     * n 2
     * c 1
     * "" 3 2 1 0
     */

    // Bottom up dp
    public int minDistance(String word1, String word2) {

        // Base case / optimization
        if (word1 == null || word1.length() == 0)
            return word2.length();
        if (word2 == null || word2.length() == 0)
            return word1.length();

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int m = 0; m < word1.length() + 1; m++) {

            dp[m][word2.length()] = word1.length() - m;
        }

        for (int n = 0; n < word2.length() + 1; n++) {

            dp[word1.length()][n] = word2.length() - n;
        }

        for (int i = word1.length() - 1; i >= 0; i--) {

            for (int j = word2.length() - 1; j >= 0; j--) {

                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                }

                else {

                    // Can be optimized by not adding 1 at each step and adding a
                    // one to Math.min value;
                    // insert
                    int insert = 1 + dp[i][j + 1];

                    // delete
                    int delete = 1 + dp[i + 1][j];

                    // replace
                    int replace = 1 + dp[i + 1][j + 1];

                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }

            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {

        EditDistance obj = new EditDistance();

        String word1 = "anc", word2 = "abc";
        CommonUtil.runExample(word1 + "-->" + word2, "1", obj.minDistance(word1, word2) + "");

        String word11 = "horse", word22 = "ros";
        CommonUtil.runExample(word11 + "-->" + word22, "3", obj.minDistance(word11, word22) + "");

        String word111 = "intention", word222 = "execution";
        CommonUtil.runExample(word111 + "-->" + word222, "5", obj.minDistance(word111, word222) + "");

    }

}