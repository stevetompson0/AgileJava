package test.java.com.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.com.db.KeyFile;
import main.java.com.util.TestUtil;

import static org.junit.Assert.*;

import java.io.IOException;

public class KeyFileTest {
	private static final String FILENAME = "keyfiletest.idx";
	private static final String KEY = "key";
	private static final long POSITION = 1;
	private static final int LENGTH = 100;
	
	private KeyFile keyFile;
	
	@Before
	public void setUp() throws IOException {
		TestUtil.delete(FILENAME);
		keyFile = new KeyFile(FILENAME);
	}
	
	@After
	public void tearDown() throws IOException {
		keyFile.close();
		TestUtil.delete(FILENAME);
	}
	
	@Test
	public void testCreate() {
		assertEquals(0, keyFile.size());
	}
	
	@Test
	public void testAddEntry() {
		keyFile.add(KEY, POSITION, LENGTH);
		
		assertEquals(1, keyFile.size());
		assertTrue(keyFile.containsKey(KEY));
		assertEquals(POSITION, keyFile.getPosition(KEY));
		assertEquals(LENGTH, keyFile.getLength(KEY));
	}
	
	@Test
	public void testReopen() throws IOException {
		keyFile.add(KEY, POSITION, LENGTH);
		keyFile.close();
		
		keyFile = new KeyFile(FILENAME);
		assertEquals(1, keyFile.size());
		assertTrue(keyFile.containsKey(KEY));
		assertEquals(POSITION, keyFile.getPosition(KEY));
		assertEquals(LENGTH, keyFile.getLength(KEY));
		
	}
	
	
	

}
