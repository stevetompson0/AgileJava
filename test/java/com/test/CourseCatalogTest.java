package test.java.com.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.IOException;
import java.util.List;

import main.java.com.model.*;

public class CourseCatalogTest {
	private CourseCatalog catalog;
	private Session session1;
	private Session session2;
	private Course course1;
	private Course course2;
	
	@Before
	public void setUp(){
		catalog = new CourseCatalog();
		course1 = new Course("a", "1");
		course2 = new Course("a", "1");
		session1 = CourseSession.create(course1, DateUtil.createDate(2005, 1, 15));
		session1.setNumberOfCredits(3);
		
		session2 = CourseSession.create(course2, DateUtil.createDate(2005, 1, 17));
		session2.setNumberOfCredits(5);
		
		catalog.add(session1);
		catalog.add(session2);
	}
	
	@Test
	public void testLoadAndStore() throws IOException{
		final String filename = "CourseCatalogTest.testAdd.txt";
		catalog.store(filename);
		catalog.clearAll();
		
		assertEquals(0, catalog.getSessions().size());
		
		catalog.load(filename);
		List<Session> sessions = catalog.getSessions();
		assertEquals(2, sessions.size());
		assertSession(session1, sessions.get(0));
		assertSession(session2, sessions.get(1));
		
	}
	
	private void assertSession(Session expected, Session retrieved){
		assertNotSame(expected, retrieved);
		assertEquals(expected.getNumberOfCredits(), retrieved.getNumberOfCredits());
		assertEquals(expected.getStartDate(), retrieved.getStartDate());
		assertEquals(expected.getDepartment(), retrieved.getDepartment());
		assertEquals(expected.getNumber(), retrieved.getNumber());
	}

}
