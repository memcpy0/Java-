
// ��ϵ����
public class PIMContact extends PIMEntity {
	String firstName;
	String lastName;
	String email;
	
	// ����firstName
	public String getFirstName() {
		return firstName;
	}
	// ����firstName
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// ����lastName
	public String getLastName() {
		return lastName;
	}
	// ����lastName
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// ����email��Ϣ
	public String getEmail() {
		return email;
	}
	// ����email��Ϣ
	void setEmail(String email) {
		this.email = email;
	}
	
	public void fromString(String ex) {
		
	}
	
	public String toString(){
		return ("CONTACT " + Priority + " " + firstName + " " + lastName + " " + email);
	}
}

