package test.java.com.test;
import main.java.com.model.Course;
import main.java.com.model.CourseSession;
import main.java.com.model.Session;
import main.java.com.model.Student;
import main.java.com.util.DateUtil;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

import java.util.Date;

public class CourseSessionTest extends SessionTest{
	
	@Test
	public void testCourseDates(){
		Date sixteenWeeksOut = DateUtil.createDate(2003, 4, 25);
		assertEquals(sixteenWeeksOut, session.getEndDate());
		
	}
	
	@Test
	public void testCount(){
		CourseSession.resetCount();
		createSession(createCourse(), startDate);
		assertEquals(1, CourseSession.getCount());
		createSession(createCourse(), startDate);
		assertEquals(2, CourseSession.getCount());
	}
	
	@Override
	protected Session createSession(Course course, Date startDate){
		return CourseSession.create(course, startDate);
	}
	
	private Course createCourse(){
		return new Course("ENGL", "101");
	}
	

	
	
	

}
