package test.java.com.test;

import org.junit.Test;

import main.java.com.model.Student;
import main.java.com.model.StudentDirectory;

import org.junit.Before;
import static org.junit.Assert.assertEquals;


public class StudentDirectoryTest {
	private StudentDirectory dir;
	
	@Before
	public void setUp(){
		dir = new StudentDirectory();
	}
	
	@Test
	public void testStoreAndRetrieve(){
		final int numberOfStudents = 10;
		for (int i=0; i < numberOfStudents; i++){
			addStudent(dir, i);
		}
		
		for (int i=0; i < numberOfStudents; i++){
			verifyStudentLookup(dir, i);
		}
	}
	
	private void addStudent(StudentDirectory directory, int i){
		String id = "" + i;
		Student student = new Student(id);
		student.setId(id);
		student.addCredits(i);
		directory.add(student);
	}
	
	private void verifyStudentLookup(StudentDirectory directory, int i){
		String id = "" + i;
		Student student = directory.findById(id);
		assertEquals(id, student.getLastName());
		assertEquals(i, student.getCredits());
		assertEquals(id, student.getId());
	}
	

}
