package fionaApp;
import junit.framework.TestCase;


public class TestPeakAnno extends TestCase {
public void testMultiplications(){
	Dollar five =new Dollar(5);
	five.times(2);
	assertEquals(10,five.amount);

}
	protected void setUp() throws Exception {
		super.setUp();
	}

}
