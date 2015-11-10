package main.java.com.model;

import main.java.com.model.Student.Grade;

public class BasicGradingStrategy implements GradingStrategy{

	@Override
	public int getGradePointsFor(Grade grade) {
		return grade.getPoints();
	}
}
