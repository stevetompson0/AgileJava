package main.java.com.model;

import java.util.Random;

public class PasswordGenerator {
	private String password;
	private static final int PASSWORD_LENGTH = 8;
	private Random random = new Random();
	
	
	public static final char LOW_END_PASSWORD_CHAR = 48;
	public static final char HIGH_END_PASSWORD_CHAR = 122;
	
	public void setRandom(Random random){
		this.random = random;
	}
	
	public String generatePassword(){
		StringBuilder buffer = new StringBuilder();
		for (int i=0; i < PasswordGenerator.PASSWORD_LENGTH; i++){
			buffer.append(getRandomChar());
		}
		return buffer.toString();
	}
	
	private char getRandomChar(){
		final char max = HIGH_END_PASSWORD_CHAR - LOW_END_PASSWORD_CHAR;
		return (char)(LOW_END_PASSWORD_CHAR + random.nextInt(max));
	}

}
