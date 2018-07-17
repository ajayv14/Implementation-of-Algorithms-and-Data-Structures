import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ClimbingStairsTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
        int[] cost = new int[]{10,20,15}; 
        
        ClimbingStairs obj = new ClimbingStairs();
        int actual = obj.minCostClimbingStairs(cost);
       
        Assert.assertEquals("fail messgae","helo","hello");
        
   }
}
