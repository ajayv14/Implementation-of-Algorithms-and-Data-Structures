package com.app.common;


public class CommonUtil {

    

    public static void printArray(int[] a) {
        
        for (int d = 0; d < a.length; d++) {
            System.out.println(a[d]);
        }
    }

    public static void runExample(String inputValue, String expectedValue, String actualValue) {

        System.out.println("Input value : " + inputValue);
        System.out.println("Expected value : " + expectedValue);
        System.out.println("Actual value : " + actualValue);    
    }
    
}
