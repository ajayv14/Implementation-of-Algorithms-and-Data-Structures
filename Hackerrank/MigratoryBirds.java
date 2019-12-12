import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MigratoryBirds {
    //https://www.hackerrank.com/challenges/migratory-birds/problem  
    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {
    
         /*create a map<id, count> and insert all count values from list
           [2,3,4,3,3,4,2,4]
           
            id   count
            1     0
            2     2
            3     3
            4     3
            
            Now find max value by looping through values, which is 3
            find the correspinding key, which is also 3, using Map.Entry -- entrySet()
         */

         Map<Integer, Integer> map = new HashMap<Integer,Integer>();  
         int max = 0;
         int maxId = 0;            

         for(int i = 0; i < arr.size(); i++){

             int key = arr.get(i);
             if(!map.containsKey(key)) map.put(key,1);

             else map.put(key,map.get(key)+1);
         }   


        for(Map.Entry<Integer, Integer> m : map.entrySet()){

             int value = m.getValue();
             if(value > max){
                 max = value;
                 maxId = m.getKey();
             }    
         }  

        return maxId;
    }
    
    
    
    
    

    public static void main(String[] args) throws IOException {
            
            
        int[] arr = new int[]{2,3,4,3,3,4,2,4};        
        List<Integer> list = new LinkedList<>();  
        
        for(int n : arr){
            list.add(n);
        }         
        int result = migratoryBirds(list);
        System.out.println(result);      
    }
}
