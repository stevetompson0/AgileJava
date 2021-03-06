package test.java.com.test;
import main.java.com.model.HonorsGradingStrategy;
import main.java.com.model.Student;
import main.java.com.model.StudentNameFormatException;
import main.java.com.model.TestHandler;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.logging.Handler;
import java.util.logging.Logger;


public class StudentTest{
	private static final double GRADE_TOLERANCE = 0.05;
	
	@Test
	public void testCreate(){
		final String firstStudentName = "Jane Doe";
		
		Student firstStudent = new Student(firstStudentName);
		assertEquals(firstStudent.getName(), firstStudentName);
		assertEquals("Jane", firstStudent.getFirstName());
		assertEquals("Doe", firstStudent.getLastName());
		assertEquals("", firstStudent.getMiddleName());
		
		final String secondStudentName = "Blow";
		Student secondStudent = new Student(secondStudentName);
		assertEquals(secondStudent.getName(), secondStudentName);
		assertEquals("", secondStudent.getFirstName());
		assertEquals("Blow", secondStudent.getLastName());
		assertEquals("", secondStudent.getMiddleName());
		
		final String thirdStudentName = "Steve Middle Xu";
		Student thirdStudent = new Student(thirdStudentName);
		assertEquals(thirdStudent.getName(), thirdStudentName);
		assertEquals("Steve", thirdStudent.getFirstName());
		assertEquals("Xu", thirdStudent.getLastName());
		assertEquals("Middle", thirdStudent.getMiddleName());
		
	}
	
	@Test
	public void testFullTime(){
		Student student = new Student("a");
		assertFalse(student.isFullTime());
		
	}
	
	@Test
	public void testCredits(){
		Student student = new Student("a");
		assertEquals(0, student.getCredits());
		
		student.addCredits(3);
		assertEquals(3, student.getCredits());
		
		student.addCredits(4);
		assertEquals(7, student.getCredits());
		
	}
	
	@Test
	public void testStudentStatus(){
		Student student = new Student("a");
		assertEquals(0, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(3);
		assertEquals(3, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(4);
		assertEquals(7, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(5);
		assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredits());
		assertTrue(student.isFullTime());
	}
	
	@Test
	public void testInState(){
		Student student = new Student("a");
		assertFalse(student.isInState());
		student.setState
		(Student.IN_STATE);
		assertTrue(student.isInState());
		student.setState("MD");
		assertFalse(student.isInState());
	}
	
	@Test
	public void testCalculateGpa(){
		Student student = new Student("a");
		assertGpa(student, 0.0);
		student.addGrade(Student.Grade.A);
		assertGpa(student, 4.0);
		student.addGrade(Student.Grade.B);
		assertGpa(student, 3.5);
		student.addGrade(Student.Grade.C);
		assertGpa(student, 3.0);
		student.addGrade(Student.Grade.D);
		assertGpa(student, 2.5);
		student.addGrade(Student.Grade.F);
		assertGpa(student, 2.0);		
	}
	
	private void assertGpa(Student student, double expectedGpa){
		assertEquals(expectedGpa, student.getGpa(), GRADE_TOLERANCE);
	}
	
	@Test
	public void testCalculateHonorsStudentGpa(){
		assertGpa(createHonorsStudent(), 0.0);
		assertGpa(createHonorsStudent(Student.Grade.A), 5.0);
		assertGpa(createHonorsStudent(Student.Grade.B), 4.0);
		assertGpa(createHonorsStudent(Student.Grade.C), 3.0);
		assertGpa(createHonorsStudent(Student.Grade.D), 2.0);
		assertGpa(createHonorsStudent(Student.Grade.F), 0.0);
		
	}
	
	private Student createHonorsStudent(Student.Grade grade){
		Student student = createHonorsStudent();
		student.addGrade(grade);
		return student;
	}
	
	private Student createHonorsStudent(){
		Student student = new Student("a");
		student.setGradingStrategy(new HonorsGradingStrategy());
		return student;
	}
	
	@Test
	public void testBadlyFormattedName(){
		Handler handler = new TestHandler();
		Student.logger.addHandler(handler);
		
		final String invalidStudentName = "a b c d";
		try {
			Student student = new Student(invalidStudentName);
			fail("expect StudentNameException for 4-part name");
		}
		catch (StudentNameFormatException success) {
			String message = String.format(Student.TOO_MANY_NAME_PARTS_MSG, invalidStudentName, Student.MAX_NUMBER_OF_NAME_PARTS);
			assertEquals(message, success.getMessage());
			assertEquals(message, ((TestHandler)handler).getMessage());
		}
		
	}
	
}
