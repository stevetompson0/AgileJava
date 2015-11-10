package main.java.com.model;
import java.util.Date;

public class CourseSession extends Session{
	
	private CourseSession(Course course, Date startDate){
		super(course, startDate);
	}
	
	public static CourseSession create(Course course, Date startDate){
		return new CourseSession(course, startDate);
	}
	
	
	@Override
	protected int getSessionLength(){
		return 16;
	}

}
