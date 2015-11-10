package test.java.com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import main.java.com.model.Course;

public class CourseTest {
	
	@Test
	public void testCreate(){
		Course course = new Course("CMSC", "120");
		assertEquals("CMSC", course.getDepartment());
		assertEquals("120", course.getNumber());		
	}
	
	@Test
	public void testEquality(){
		Course courseA = new Course("NUS", "201");
		Course courseAPrime = createCourseACopy();
		assertEquals(courseA, courseAPrime);
		
		Course courseB = new Course("ARTH", "330");
		assertFalse(courseA.equals(courseB));
		
		//reflectivity
		assertEquals(courseA, courseA);
		
		//transitivity
		Course courseAPrime2 = createCourseACopy();
		assertEquals(courseA, courseAPrime);
		assertEquals(courseAPrime, courseAPrime2);
		assertEquals(courseA, courseAPrime2);
		
		//symmetry
		assertEquals(courseAPrime, courseA);
		
		//consistency
		assertEquals(courseA, courseAPrime);
		
		//compare to null
		assertFalse(courseA.equals(null));
		
		List<Course> list = new ArrayList<Course>();
		list.add(courseA);
		assertTrue(list.contains(courseAPrime));
		
		
		
	}
	
	@Test
	public void testHashCode(){
		Course courseA = createCourseACopy();
		Course courseAPrime = createCourseACopy();
		
		Map<Course, String> map = new HashMap<Course, String>();
		map.put(courseA, "");
		assertTrue(map.containsKey(courseAPrime));
		
		assertEquals(courseA.hashCode(), courseAPrime.hashCode());
		assertEquals(courseA.hashCode(), courseA.hashCode());
		
	}
	
	private Course createCourseACopy(){
		return new Course("NUS", "201");
	}
	
	@Test
	public void testHashCodePerformance(){
		final int count = 10000;
		long start = System.currentTimeMillis();
		Map<Course, String> map = new HashMap<Course, String>();
		for (int i = 0; i < count; i++ ){
			map.put(new Course("NU" + i, "" + i), "");
		}
		long stop = System.currentTimeMillis();
		long elapse = stop - start;
		final long arbitraryThreshold = 200;
		assertTrue("elapse time is "+elapse, elapse < arbitraryThreshold);
		
	}
	
}
