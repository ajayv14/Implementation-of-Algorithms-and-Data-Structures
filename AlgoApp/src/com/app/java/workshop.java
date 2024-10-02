package com.app.java;

public class workshop {



    public void getAverage(int a, int b, int c) {
        int average = (a + b + c) / 3;
        System.out.println("Average of three numbers is " + average);
    }

    // Create unit test for the above method
    public static void main(String[] args) {
        workshop w = new workshop();
        w.getAverage(10, 20, 30);
    }

    // create unit test for the above method with Mockito

    public static void main1(String[] args) {
        workshop w = mock(workshop.class);
        when(w.getAverage(10, 20, 30)).thenReturn(20);
        int result = w.getAverage(10, 20, 30);
        assertEquals(20, result);
    }

    //Implement cloneable interface
    

}
