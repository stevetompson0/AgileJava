package test.java.com.test;

import org.junit.Before;
import org.junit.Test;

import main.java.com.db.DataFile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import org.junit.After;

public class DataFileTest {
	private static final String ID1= "12345";
	private static final String ID2= "23456";
	private static final String FILEBASE = "DataFileTest";
	
	private DataFile db;
	private TestData testData1;
	private TestData testData2;
	
	@Before
	public void setUp() throws IOException {
		db = DataFile.create(FILEBASE);
		assertEquals(db.size(), 0);
		
		testData1 = new TestData(ID1, "datum1a", "1");
		testData2 = new TestData(ID2, "datum2a", "2");
	}
	
	@After
	public void tearDown() throws IOException {
		db.close();
		db.remove();
	}
	
	@Test
	public void testAdd() throws IOException {
		db.add(ID1, testData1);
		assertEquals(db.size(), 1);
		
		db.add(ID2, testData2);
		assertEquals(db.size(), 2);
		
		assertTestDataEquals(testData1, (TestData) db.findBy(ID1));
		assertTestDataEquals(testData2, (TestData) db.findBy(ID2));
		
	}
	
	@Test
	public void testPersistence() throws IOException {
		db.add(ID1, testData1);
		db.add(ID2, testData2);
		db.close();
		
		db = DataFile.open(FILEBASE);
		assertEquals(db.size(), 2);
		
		assertTestDataEquals(testData1, (TestData) db.findBy(ID1));
		assertTestDataEquals(testData2, (TestData) db.findBy(ID2));
		
		db = DataFile.create(FILEBASE);
		assertEquals(db.size(), 0);
	}
	
	@Test
	public void testKeyNotFound() throws IOException {
		assertNull(db.findBy(ID2));
	}
	
	private void assertTestDataEquals(TestData expected, TestData actual) {
		assertEquals(expected.id, actual.id);
		assertEquals(expected.field1, actual.field1);
		assertEquals(expected.field2, actual.field2);
	}
	
	static class TestData implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		String id;
		String field1;
		String field2;
		
		TestData(String id, String field1, String field2) {
			this.id = id;
			this.field1 = field1;
			this.field2 = field2;
		}
		
	}
	
	
	
	

}
