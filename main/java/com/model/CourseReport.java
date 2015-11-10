package main.java.com.model;

import java.util.ArrayList;
import java.util.Collections;


public class CourseReport {
	private ArrayList<Session> sessions = new ArrayList<Session>();
	
	public void add(Session session){
		sessions.add(session);
	}
	
	public String text(){
		Collections.sort(sessions);
		StringBuilder builder = new StringBuilder();
		for (Session session: sessions){
			builder.append(
				String.format("%s %s%n",
					session.getDepartment(),
					session.getNumber()
				)
			);
		}
		return builder.toString();
	}
}
