package main.java.com.model;

import java.io.Serializable;

import main.java.com.model.Student.Grade;

public class BasicGradingStrategy implements GradingStrategy, Serializable{

	@Override
	public int getGradePointsFor(Grade grade) {
		return grade.getPoints();
	}
}
