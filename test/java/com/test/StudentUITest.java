package test.java.com.test;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.List;

import org.junit.Test;

import main.java.com.model.Student;
import main.java.com.model.StudentUI;

public class StudentUITest {
	private static final String name = "Leo Xerces Schmoo";
	
	@Test
	public void testCreateStudent() throws IOException{
		StringBuilder expectedOutput = new StringBuilder();
		StringBuilder input = new StringBuilder();
		setup(expectedOutput, input);
		byte[] buffer = input.toString().getBytes();
		
		InputStream inputStream = new ByteArrayInputStream(buffer);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		OutputStream outputStream = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
		
		StudentUI ui = new StudentUI(reader, writer);
		ui.run();
		
		assertEquals(expectedOutput.toString(), outputStream.toString());
		assertStudents(ui.getAddedStudents());
		
	}
	
	private String line(String input){
		return String.format("%s%n", input);
	}
	
	private void setup(StringBuilder expectedOutput, StringBuilder input){
		expectedOutput.append(StudentUI.MENU);
		input.append(line(StudentUI.ADD_OPTION));
		expectedOutput.append(StudentUI.NAME_PROMPT);
		input.append(line(name));
		expectedOutput.append(line(StudentUI.ADDED_MESSAGE));
		expectedOutput.append(StudentUI.MENU);
		input.append(line(StudentUI.QUIT_OPTION));
		
	}
	
	private void assertStudents(List<Student> students){
		assertEquals(students.size(), 1);
		assertEquals(students.get(0).getName(), name);
	}
	

}
