package abstract_class_ver;
// ����������
public class PIMTodo extends PIMEntity implements SharedDate {
	String date;
	String toDoText;
	
	// ��������ֵ
	public String getDate() {
		return date;
	}
	// ��������ֵ
	public void setDate(String date) {
		this.date = date;
	}
	
	// ����ToDo���ı�
	public String getTodoText() {
		return toDoText;
	}
	// ����ToDo���ı�
	void setTodoText(String toDoText) {
		this.toDoText = toDoText;
	}
	
	public void fromString(String ex) {
		
	}
	
	public String toString() {
		return ("Item " + PIMManager.itemCount + ": TODO " + Priority + " " + date + " " + toDoText);
	}	
}

