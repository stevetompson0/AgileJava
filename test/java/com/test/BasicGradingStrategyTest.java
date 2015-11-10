package test.java.com.test;

import org.junit.Test;

import main.java.com.model.BasicGradingStrategy;
import main.java.com.model.GradingStrategy;
import main.java.com.model.Student;

import static org.junit.Assert.assertEquals;

public class BasicGradingStrategyTest {
	
	@Test
	public void testGetGradePoints(){
		GradingStrategy strategy = new BasicGradingStrategy();
		assertEquals(4, strategy.getGradePointsFor(Student.Grade.A));
		assertEquals(3, strategy.getGradePointsFor(Student.Grade.B));
		assertEquals(2, strategy.getGradePointsFor(Student.Grade.C));
		assertEquals(1, strategy.getGradePointsFor(Student.Grade.D));
		assertEquals(0, strategy.getGradePointsFor(Student.Grade.F));
	}

}
