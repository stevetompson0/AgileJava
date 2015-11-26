package main.java.com.util;

import static org.junit.Assert.*;

import java.io.File;

public class TestUtil {
	
	public static void assertGone(String... filenames) {
		for (String filename: filenames) {
			assertFalse(new File(filename).exists());
		}
	}
	
	public static void delete(String filename) {
		File file = new File(filename);
		if (file.exists())
			assertTrue(new File(filename).delete());
	}

}
