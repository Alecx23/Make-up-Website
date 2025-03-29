package obiecte;

public class Form {

	private String address;
	private String phoneNumber;
	
	public Form() {
		
	}
	
	public Form(String address, String phoneNumber) {
		super();
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
