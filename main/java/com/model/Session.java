package main.java.com.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

abstract public class Session implements Comparable<Session>, Serializable{

	private static final long serialVersionUID = 1L;
	private static int count;
	
	private Course course;
	private transient ArrayList<Student> students = new ArrayList<Student>();
	protected Date startDate;
	private int numberOfCredits;
	private URL url;
	
	
	protected Session(Course course, Date startDate){
		this.course = course;
		this.startDate = startDate;
		Session.incrementCount();
	}
	
	public void enroll(Student student){
		students.add(student);
		student.addCredits(numberOfCredits);
	}
	
	public String getDepartment(){
		return course.getDepartment();
	}
	
	public String getNumber(){
		return course.getNumber();
	}
	
	public int getNumberOfStudents(){
		return students.size();
	}
	
	public Student get(int index){
		return students.get(index);
	}
	
	public Date getStartDate(){
		return startDate;
	}
	
	abstract protected int getSessionLength();
	
	public Date getEndDate(){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getStartDate());
		final int daysInWeek = 7;
		final int daysFromFridayToMonday = 3;
		int numberOfDays = getSessionLength() * daysInWeek - daysFromFridayToMonday;
		calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
		return calendar.getTime();

	}
	
	
	public ArrayList<Student> getAllStudents(){
		return students;
	}
	
	public static void resetCount(){
		Session.count = 0;
	}
	
	public static int getCount() {
		return Session.count;
	}
	
	private static void incrementCount(){
		Session.count += 1;
	}
	
	public void setNumberOfCredits(int numberOfCredits){
		this.numberOfCredits = numberOfCredits;
	}
	
	public int getNumberOfCredits(){
		return numberOfCredits;
	}

	@Override
	public int compareTo(Session that) {
		int compare = this.getDepartment().compareTo(that.getDepartment());
		if (compare != 0){
			return compare;
		}
		else {
			return this.getNumber().compareTo(that.getNumber());
		}
	}
	
	public double averageGpaForPartTimeStudents(){
		double total = 0.0;
		int count = 0;
		for (Student student: students){
			if (student.isFullTime())
				continue;
			total += student.getGpa();
			count++;
		}
		if (count == 0)
			return 0.0;
		return total / count;
	}
	
	public void setUrl(String url) throws SessionException{
		try {
			this.url = new URL(url);
		}
		catch (MalformedURLException e){
			log(e);
			throw new SessionException(e);
		}
	}
	
	public URL getUrl(){
		return this.url;
	}
	
	private void log(Exception e){
		e.printStackTrace();
	}
	
	
	private void writeObject(ObjectOutputStream output) throws IOException{
		output.defaultWriteObject();
		output.writeInt(getNumberOfStudents());
		for (Student student: students){
			output.writeUTF(student.getLastName());
		}
	}
	
	private void readObject(ObjectInputStream input) throws ClassNotFoundException, IOException {
		input.defaultReadObject();
		students = new ArrayList<Student>();
		int size = input.readInt();
		for (int i = 0; i < size; i++){
			String lastName = input.readUTF();
			students.add(Student.findStudentByLastName(lastName));
		}
	}

}
