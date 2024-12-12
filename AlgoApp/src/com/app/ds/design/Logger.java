package com.app.ds.design;

import java.util.HashMap;
import java.util.Map;



//LC 359
class Logger {

    private Map<String, Integer> messageTimeMap; 

    public Logger() {
        
        messageTimeMap = new HashMap<>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        
       
        Integer existingTimestamp = messageTimeMap.get(message);

        if(existingTimestamp != null){

            if((timestamp - existingTimestamp) >= 10){

                messageTimeMap.put(message, timestamp);
                return true;
            } 

            else return false;
        }                
        
        else {

            messageTimeMap.put(message, timestamp);
            return true;
        }        
    }


    public static void main(String[] args) {
        
        Logger obj = new Logger();

        String[][] inputs =  {{"1","foo"},{"2","bar"},{"3","foo"},{"8","bar"},{"10","foo"},{"11","foo"}};


        System.out.println("Expected : true,true,false,false,false,true");
        System.out.print("Actual : ");
        for(String[] input : inputs){

            System.out.print(obj.shouldPrintMessage(Integer.valueOf(input[0]), input[1]) + ",");

        }       
    }




}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
