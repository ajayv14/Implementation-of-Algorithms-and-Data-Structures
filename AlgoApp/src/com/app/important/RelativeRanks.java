package com.app.important;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//LC 506
public class RelativeRanks {

    public String[] findRelativeRanks(int[] score) {
          
               
        List<Integer> sortedList = Arrays.stream(score).boxed().collect(Collectors.toList());
        Collections.sort(sortedList, (a, b) -> b - a);

        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < sortedList.size(); i++){
            map.put(sortedList.get(i), i + 1);
        }
        
        String[] result = new String[score.length];

        System.out.print(map);

        for(int i = 0; i < score.length; i++){

           int rank = map.get(score[i]);

           System.out.print(rank); 
           
           if(rank == 1) result[i] = "Gold Medal";

           else if(rank == 2) result[i] = "Silver Medal";

            else if(rank == 3) result[i] = "Bronze Medal";
            
            else result[i] = String.valueOf(rank);
        }
          
        return result;
    }

}
