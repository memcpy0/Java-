package pim_model;

/**
 * <p>��Ŀ���ƣ�PIM GUI</p>
 * <p>�����ƣ�PIMContact</p>
 * ����ʱ�䣺2022��5��30�� <br>
 * ��������PIMEntity����ϵ������
 * @author����ƽ
 */
public class PIMContact extends PIMEntity {
	String firstName;
	String lastName;
	String email;
	
	public PIMContact() {
		type = "Contact";
	}
	
	public PIMContact(String f, String l, String e) {
		type = "Contact";
		firstName = f;
		lastName = l;
		email = e;
	}
	
	public PIMContact(String f, String l, String e, String prior) {
		this(f, l, e);
		priority = prior;
	}
	
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
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		return ("CONTACT " + priority + " " + firstName + " " + lastName + " " + email);
	}
	
    public boolean equals(PIMEntity p) {
    	if (p.getType().equals(type)) {
    		PIMContact pa = (PIMContact)p;
    		return pa.firstName.equals(firstName) && pa.lastName.equals(lastName) 
    			&& pa.email.equals(email) && pa.priority.equals(priority);
    	}
    	return false;
    }
    
    public void setEntity(PIMEntity p) {
    	PIMContact pa = (PIMContact)p;
    	this.firstName = pa.firstName;
    	this.lastName = pa.lastName;
    	this.email = pa.email;
    	this.priority = pa.priority;
    }
}