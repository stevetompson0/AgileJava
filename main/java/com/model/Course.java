package main.java.com.model;

public class Course  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String department;
	private String number;
	
	public Course(String department, String number){
		this.department = department;
		this.number = number;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public String getNumber(){
		return number;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj==null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Course that = (Course)obj;
		return 
			this.department.equals(that.department) &&
			this.number.equals(that.number);
	}
	
	@Override
	public int hashCode(){
		final int hashMultiplier = 41;
		int result = 7;
		result = result * hashMultiplier + department.hashCode();
		result = result * hashMultiplier + number.hashCode();
		return result;
	}
	
	
	

}
