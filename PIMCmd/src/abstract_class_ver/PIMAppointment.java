package abstract_class_ver;
// ��Լ��
public class PIMAppointment extends PIMEntity {
	String date;
	String description;
	
	// ��������ֵ
	public String getDate() {
		return date;
	}
	// ��������ֵ
	public void setDate(String date) {
		this.date = date;
	}
	
	// ����Appointment������
	public String getDescription() {
		return description;
	}
	// ����Appointment������
	void setDescription(String description) {
		this.description = description;
	}
	
	public void fromString(String ex) {
		
	}
	
	public String toString() {
		return ("Item " + PIMManager.itemCount + ": APPOINTMENT " + Priority + " " + date + " " + description);
	}
}
	