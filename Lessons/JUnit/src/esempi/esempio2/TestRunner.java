package esempi.esempio2;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestJunit.class);
      
      for (Failure failure : result.getFailures()) {
         System.out.println("failure: " + failure.toString());
      }
		
      System.out.println("Successful: " + result.wasSuccessful());
      System.out.println("Failed: " + result.getFailureCount());
   }
}  