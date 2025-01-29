package demo;

import org.testng.Assert;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

public class Assertion_ex {
@Test
	public void sample() {
		System.out.println("step 1");
		System.out.println("step 2");
		
		//validate//hard assert
		Assert.assertEquals(false, true);
		//soft
		//SoftAssert sa=new SoftAssert;
		//sa.assertEquals(false, false);
		
		System.out.println("step 3");
		System.out.println("step 4");
	}
}
