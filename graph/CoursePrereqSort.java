package com.app.graph;
import java.util.*;

import ArrayList;

class CoursePrereqSort{
  
      // perform a topoloigical sort to sort all the subjects according to the dependencies (pre-reqs)   
      
      public String topoSort(Map<String, String> map){
         
         List<String> list = new ArrayList<>();
         
         for(String key : map.keySet()){
            checkPreReq(map, key ,list);
         }     
         
         return list.get(0);
           
      } 
      
      
      private void checkPreReq(Map<String, String> map, String key, List<String> list){
                             
            if(key.length() <= 0) return; // empty string
            
            String preReqs = map.get(key); // key - the subjects, get their list of pre reqs
                       
            System.out.println(key);
            System.out.println("list : " + list);                        
            
            if(preReqs == null && !list.contains(key)) list.add(key); // add the subject to list if it has no more pre reqs
            
            else{
               
               String[] preReqsArray = preReqs.split(","); // break down the comma separated values in string
               
               for(String p : preReqsArray){                  
                  
                  if(!list.contains(p)){                        
                      checkPreReq(map, p, list); // check each array value(subject), if it has any pre reqs and make a recursive call                                            
                  }                                 
               }
               
               if(!list.contains(key))  list.add(key); // add the key after running recursive call on all of its preReqs above                                             
                       
         }
      
      }     
      
  public static void main(String[] args){
           /*Map containing subjects and pre reqs*/
           
            Map map = new HashMap<String, String>();
             map.put("CS006", "CS002,CS004,CS005");
             map.put("CS005", "CS002,CS004");
             map.put("CS004", "CS002,CS001");
             map.put("CS001", "");
             map.put("CS002", "CS001");
             
             CoursePrereqSort obj = new CoursePrereqSort();              
             System.out.println(obj.topoSort(map));           
      }      
}