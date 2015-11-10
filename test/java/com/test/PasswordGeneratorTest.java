package test.java.com.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import main.java.com.model.PasswordGenerator;

public class PasswordGeneratorTest {
	
	@SuppressWarnings("serial")
	class MockRandom extends Random {
		private int i;
		
		MockRandom(char startValue){
			i = startValue - PasswordGenerator.LOW_END_PASSWORD_CHAR;
		}
		
		@Override
		protected int next(int bits){
			return i++;
		}
	}
	
	@Test
	public void testGeneratePassword(){
		PasswordGenerator generator = new PasswordGenerator();
		generator.setRandom(new MockRandom('A'));
		assertEquals("ABCDEFGH", generator.generatePassword());
		generator.setRandom(new MockRandom('C'));
		assertEquals("CDEFGHIJ", generator.generatePassword());
		
	}

}
