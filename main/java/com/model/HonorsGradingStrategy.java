package main.java.com.model;

import java.io.Serializable;

import main.java.com.model.Student.Grade;

public class HonorsGradingStrategy extends BasicGradingStrategy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getGradePointsFor(Grade grade) {
		int points = super.getGradePointsFor(grade);
		if (points > 0)
			points += 1;
		return points;
	}

}
