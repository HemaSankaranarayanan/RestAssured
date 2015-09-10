package Hema.restassured;

public class LandLord {
	
	private String firstName;
	private String lastName;
	private boolean trusted;
	
	public LandLord() {
		
	}
	
	public LandLord(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public LandLord(String firstName, String lastName, boolean trusted) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.trusted = trusted;
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean getTrusted() {
		return trusted;
	}

	public void setTrusted(boolean trusted) {
		this.trusted = trusted;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	

}
