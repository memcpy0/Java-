package pim_model;

import java.time.LocalDate;

/**
 * <p>��Ŀ���ƣ�PIM GUI</p>
 * <p>�����ƣ�PIMAppointment</p>
 * ����ʱ�䣺2022��5��30�� <br>
 * ��������PIMEntity����Լ����
 * @author����ƽ
 */
public class PIMAppointment extends PIMEntity {
	LocalDate date;
	String description;
	
	public PIMAppointment() {
		type = "Appointment";
	}
	
	public PIMAppointment(LocalDate date, String desc, String prior) {
		type = "Appointment";
		this.date = date;
		this.description = desc;
		this.priority = prior;
	}
	
	// ��������ֵ
	public LocalDate getDate() {
		return date;
	}
	// ��������ֵ
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	// ����Appointment������
	public String getDescription() {
		return description;
	}
	// ����Appointment������
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return ("APPOINTMENT " + priority + " " + date + " " + description);
	}

    public boolean equals(PIMEntity p) {
    	if (p.getType().equals(type)) {
    		PIMAppointment pa = (PIMAppointment)p;
    		return pa.date.equals(date) && pa.description.equals(description)
    				&& pa.priority.equals(priority);
    	}
    	return false;
    }
    
    public void setEntity(PIMEntity p) {
    	PIMAppointment pa = (PIMAppointment)p;
    	this.date = pa.date;
    	this.description = pa.description;
    	this.priority = pa.priority;
    }
}
	