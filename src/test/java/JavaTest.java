import junit.framework.*;

public class JavaTest {
   protected int value1, value2;
   
   // assigning the values
   @Before
   protected void setUp(){
      value1=3;
      value2=3;
   }

   // test method to add two values
   @Test
   public void testAdd(){
      double result= value1 + value2;
      assertTrue(result == 6);
   }
}
