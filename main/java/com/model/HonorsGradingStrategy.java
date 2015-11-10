package main.java.com.model;

import main.java.com.model.Student.Grade;

public class HonorsGradingStrategy extends BasicGradingStrategy {

	@Override
	public int getGradePointsFor(Grade grade) {
		int points = super.getGradePointsFor(grade);
		if (points > 0)
			points += 1;
		return points;
	}

}
