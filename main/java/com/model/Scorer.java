package main.java.com.model;

public class Scorer {
	public int score(String score){
		if (isValid(score))
			return Integer.parseInt(score);
		else
			throw new NumberFormatException();
	}
	
	public boolean isValid(String score){
		try {
			Integer.parseInt(score);
			return true;
		}
		catch (NumberFormatException e){
			return false;
		}
	}
}
