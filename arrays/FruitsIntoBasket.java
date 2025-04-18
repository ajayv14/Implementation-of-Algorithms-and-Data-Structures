package com.app.array;

// credit : https://www.youtube.com/watch?v=za2YuucS0tw

// leetcode 904. Fruits into basket

import java.util.HashMap;
import java.util.Map;

class FruitsIntoBasket {

    /**
     * Logic : Maintain a hashmap with max of two entries.
     * Update the map with last seen index of a fruit tree. Once map size exceeds 2
     * i.e the third tree is found, remove the first entry and compute max value
     */

    public int totalFruit(int[] tree) {

        if (tree == null || tree.length <= 0)
            return 0;

        // <tree id,count>
        Map<Integer, Integer> map = new HashMap<>();
        int max = 1;
        int i = 0;
        int j = 0; //

        while (j < tree.length) {

            if (map.size() <= 2) {
                map.put(tree[j], j++);
            }

            if (map.size() > 2) {
                int min = tree.length - 1;

                for (int value : map.values()) {
                    min = Math.min(min, value);
                }

                i = min + 1;
                map.remove(tree[min]);

            }

            max = Math.max(max, j - i);

        }

        return max;

    }
}