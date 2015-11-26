package main.java.com.model;

import java.io.*;
import java.util.*;

public class StudentUI {
	public final static String MENU = "(A)dd or (Q)uit?";
	public final static String ADD_OPTION = "A";
	public final static String QUIT_OPTION = "Q";
	public final static String NAME_PROMPT = "Name: ";
	public final static String ADDED_MESSAGE = "Added";
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private List<Student> students = new ArrayList<Student>();
	
	public StudentUI(){
		this.reader = new BufferedReader(new InputStreamReader(System.in));
		this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	
	public List<Student> getAddedStudents(){
		return students;
	}
	
	public void run() throws IOException {
		String line;
		do {
			write(StudentUI.MENU);
			line = reader.readLine();
			if (line.equals(StudentUI.ADD_OPTION))
				addStudent();
			
		} while (!line.equals(StudentUI.QUIT_OPTION));
	}
	
	private void addStudent() throws IOException{
		write(StudentUI.NAME_PROMPT);
		String name = reader.readLine();
		students.add(new Student(name));
		writeln(StudentUI.ADDED_MESSAGE);
	}
	
	private void write(String line) throws IOException {
		writer.write(line, 0, line.length());
		writer.flush();
	}
	
	private void writeln(String line) throws IOException {
		writer.write(line);
		writer.newLine();
		writer.flush();
	}
	
	public static void main(String[] args) throws IOException{
		new StudentUI().run();
	}
	

}
