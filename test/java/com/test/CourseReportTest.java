package test.java.com.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import main.java.com.model.*;


public class CourseReportTest {
	
	@Test
	public void testReport(){
		final Date date = new Date();
		CourseReport report = new CourseReport();
		report.add(create("ENGL", "101", date));
		report.add(create("CZEC", "200", date));
		report.add(create("ITAL", "410", date));
		report.add(create("CZEC", "220", date));
		report.add(create("ITAL", "330", date));
		
		assertEquals(
				String.format(
				"CZEC 200%n" +
				"CZEC 220%n" +
				"ENGL 101%n" +
				"ITAL 330%n" +
				"ITAL 410%n"),
				report.text());
		
	}
	
	private Session create(String department, String number, Date startDate){
		return CourseSession.create(new Course(department, number), startDate);
	}

}
