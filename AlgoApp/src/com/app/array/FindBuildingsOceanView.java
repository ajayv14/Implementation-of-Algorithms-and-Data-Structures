package com.app.array;

import java.util.ArrayList;
import java.util.List;

public class FindBuildingsOceanView {



    public int[] findBuildings(int[] heights) {

        List<Integer> res = new ArrayList<>();

        int maxSoFar = 0;

        if(heights == null || heights.length == 0) return new int[0];
        if(heights.length == 1) return new int[] {0};

        res.add(heights.length - 1);
        maxSoFar = heights[heights.length - 1];

        for(int i = heights.length - 2; i >= 0; i--){

            if(heights[i] > maxSoFar) {
                res.add(i);

                maxSoFar = Math.max(maxSoFar, heights[i]);
            }
        }

        // return results in list in reverse order
        int[] result = new int[res.size()];
        for(int i = 0; i < res.size(); i++) {
            result[i] = res.get(res.size() - 1 - i);
        }
        
        return result;
    }

}
