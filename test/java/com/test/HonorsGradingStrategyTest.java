package test.java.com.test;

import org.junit.Test;

import main.java.com.model.HonorsGradingStrategy;
import main.java.com.model.GradingStrategy;
import main.java.com.model.Student;

import static org.junit.Assert.assertEquals;

public class HonorsGradingStrategyTest {
	
	@Test
	public void testGetGradePoints(){
		GradingStrategy strategy = new HonorsGradingStrategy();
		assertEquals(5, strategy.getGradePointsFor(Student.Grade.A));
		assertEquals(4, strategy.getGradePointsFor(Student.Grade.B));
		assertEquals(3, strategy.getGradePointsFor(Student.Grade.C));
		assertEquals(2, strategy.getGradePointsFor(Student.Grade.D));
		assertEquals(0, strategy.getGradePointsFor(Student.Grade.F));
	}

}