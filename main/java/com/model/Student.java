package main.java.com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Student {
	public enum Grade {
		A(4), 
		B(3), 
		C(2), 
		D(1), 
		F(0);
		
		private int points;
		Grade(int points){
			this.points = points;
		}
		int getPoints(){
			return points;
		}
	}
	
	public static final String IN_STATE = "CO";
	public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
	public static final int MAX_NUMBER_OF_NAME_PARTS = 3;
	public static final String TOO_MANY_NAME_PARTS_MSG = "student name '%s' contains more than %d parts";
	public static final Logger logger = Logger.getLogger(Student.class.getName());
	
	private String name;
	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	private int credits;
	private String state = "";
	private List<Grade> grades= new ArrayList<Grade>();
	private GradingStrategy gradingStrategy = new BasicGradingStrategy();
	private String id;

	public Student(String fullName){
		this.name = fullName;
		credits = 0;
		List<String> nameParts = new LinkedList<String>(Arrays.asList(fullName.split(" ")));
		if (nameParts.size() > Student.MAX_NUMBER_OF_NAME_PARTS){
			String message = String.format(Student.TOO_MANY_NAME_PARTS_MSG, fullName, Student.MAX_NUMBER_OF_NAME_PARTS);
			Student.logger.info(message);
			throw new StudentNameFormatException(message);
		}
		setName(nameParts);
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isFullTime(){
		return credits >= Student.CREDITS_REQUIRED_FOR_FULL_TIME;
	}
	
	public int getCredits(){
		return credits;
	}
	
	public void addCredits(int credits){
		this.credits += credits;
	}
	
	public void setState(String state){
		this.state = state.toUpperCase();
	}
	
	public boolean isInState(){
		return state.equals(Student.IN_STATE);
	}
	
	public void addGrade(Grade grade){
		grades.add(grade);
	}
	
	public double getGpa(){
		if (grades.isEmpty())
			return 0.0;
		double total = 0.0;
		for (Grade grade: grades){
			total += gradingStrategy.getGradePointsFor(grade);
		}
		return total / grades.size();
	}
	
	public void setGradingStrategy(GradingStrategy gradingStrategy){
		this.gradingStrategy = gradingStrategy;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getMiddleName(){
		return middleName;
	}
	
	private void setName(List<String> nameParts){
		
		this.lastName = removeLast(nameParts);
		String name = removeLast(nameParts);
		if (nameParts.isEmpty()){
			this.firstName = name;
		}
		else {
			this.middleName = name;
			this.firstName = removeLast(nameParts);
		}
	}
	
	private String removeLast(List<String> list){
		if (list.isEmpty()) 
			return "";
		else 
			return list.remove(list.size() - 1);
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	
}
