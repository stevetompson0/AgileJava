package test.java.com.test;

import org.junit.Test;
import org.junit.Before;

import main.java.com.model.Scorer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ScoreTest {
	private Scorer scorer;
	
	@Before
	public void setUp(){
		scorer = new Scorer();
	}
	
	
	@Test
	public void testCaptureScore(){
		assertEquals(75, scorer.score("75"));
		
	}
	
	@Test
	public void testEnterBadScore(){
		try {
			scorer.score("abc");
			fail("expected NumberFormatException on bad input");
		}
		catch (NumberFormatException success){
			
		}
	}
	
	@Test
	public void testIsValid(){
		assertTrue(scorer.isValid("54"));
		assertFalse(scorer.isValid("ab"));
		
	}
	

}
