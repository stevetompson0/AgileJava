package main.java.com.model;

import java.io.*;

public class RosterReport {
	public static final String ROSTER_REPORT_HEADER = "Student%n----%n";
	public static final String ROSTER_REPORT_FOOTER = "%n# students = %d%n";
	
	private Session session;
	private Writer writer;
	
	public RosterReport(Session session){
		this.session = session;
	}
	
	public void writeReport(Writer writer) throws IOException{
		this.writer = writer;
		writeHeader();
		writeBody();
		writeFooter();
	}
	
	public void writeReport(String filename) throws IOException{
		Writer bufferedWriter = new BufferedWriter(new FileWriter(filename));
		
		try {
			writeReport(bufferedWriter);
		} 
		finally{
			bufferedWriter.close();
		}
		
		
	}
	
	private void writeHeader() throws IOException{
		writer.write(String.format(ROSTER_REPORT_HEADER));
	}
	
	private void writeBody() throws IOException{
		for (Student student: session.getAllStudents()){
			writer.write(String.format("%s%n", student.getName()));
		}
	}
	
	private void writeFooter() throws IOException{
		writer.write(String.format(ROSTER_REPORT_FOOTER, session.getAllStudents().size()));
	}

}
