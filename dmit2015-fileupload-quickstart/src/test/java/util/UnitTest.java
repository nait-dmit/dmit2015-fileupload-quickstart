package util;

import org.junit.*;					// for @Test
import static org.junit.Assert.*;	// for assertXXX methods

public class UnitTest {
	
	@Test
	public void testPowerOperator() {
		//assertEquals(8, 2^3, 0);
		assertEquals(8, Math.pow(2, 3), 0);
	}
	
	@Test
	public void testDivision()  {
		// 4/5 should be 0.8
		assertEquals( 0.8, 4.0 / 5, 0);
	}
	
	@Test
	public void testStringCompare() {
		assertTrue( "dmit2015" == "dmit2015");
		//assertTrue( "dmit2015" == "DMIT2015");
		//assertEquals("dmit2015","DMIT2015");
		assertTrue( "dmit2015".equalsIgnoreCase("DMIT2015") );
	}
	
	@Test(expected=ArithmeticException.class)
	public void testForException() {
		assertEquals( 0, 3/0 );
	}
	
//	@Test(timeout=5000)
//	public void testForInfiniteLoop() {
//		while( true ) {
//			
//		}
//	}

}
