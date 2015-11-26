package test.java.com.test;

import org.junit.Test;

import main.java.com.model.Course;
import main.java.com.model.Session;
import main.java.com.model.SummerCourseSession;
import main.java.com.util.DateUtil;

import static org.junit.Assert.assertEquals;

import java.util.Date;

public class SummerCourseSessionTest extends SessionTest{
	@Test
	public void testEndDate(){
		Date startDate = DateUtil.createDate(2003, 6, 9);
		Session session = createSession(new Course("ENGL", "101"), startDate);
		Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
		assertEquals(eightWeeksOut, session.getEndDate());
	}

	@Override
	protected Session createSession(Course course, Date startDate) {
		return SummerCourseSession.create(course, startDate);
	}
}
