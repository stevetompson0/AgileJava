package test.java.com.test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.com.model.Course;
import main.java.com.model.CourseSession;
import main.java.com.model.RosterReport;
import main.java.com.model.Session;
import main.java.com.model.Student;

import main.java.com.model.DateUtil;

public class RosterReportTest {
	private Session session;
	
	@Before
	public void setUp(){
		session = CourseSession.create(new Course("ENG", "101"), DateUtil.createDate(2003, 1, 6));
		session.enroll(new Student("A"));
		session.enroll(new Student("B"));
	}
	
	@Test
	public void testRosterReport() throws IOException{
		
		Writer writer = new StringWriter();
		new RosterReport(session).writeReport(writer);
		
		String rosterReport = writer.toString();
		assertReportContents(rosterReport);
	}
	
	@Test
	public void testFiledReport() throws IOException{
		final String filename = "testFiledReport.txt";
		
		try{
			delete(filename);
		
			new RosterReport(session).writeReport(filename);
			
			StringBuilder builder = new StringBuilder();
			String line;
			
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null){
				builder.append(String.format(line + "%n"));
			}
			reader.close();
			assertReportContents(builder.toString());
		}
		finally {
			delete(filename);
		}
	}
	
	private void delete(String filename){
		File file = new File(filename);
		if (file.exists())
			assertTrue("unable to delete file " + filename, file.delete());
	}
	
	private void assertReportContents(String rosterReport){
		assertEquals(
				String.format(RosterReport.ROSTER_REPORT_HEADER +
						"A%n" +
						"B%n" + 
						RosterReport.ROSTER_REPORT_FOOTER, 2), 
				rosterReport);
	}

}
