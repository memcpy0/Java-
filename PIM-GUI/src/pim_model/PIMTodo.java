package pim_model;

import java.time.LocalDate;

/**
 * <p>��Ŀ���ƣ�PIM GUI</p>
 * <p>�����ƣ�PIMTodo</p>
 * ����ʱ�䣺2022��5��30�� <br>
 * ��������PIMEntity�Ĵ�����������
 * @author����ƽ
 */
public class PIMTodo extends PIMEntity implements SharedDate {
	LocalDate date;
	String todoText;
	
	public PIMTodo() {
		type = "Todo";
	}
	 
	public PIMTodo(LocalDate date, String todo, String prior) {
		type = "Todo";
		this.date = date;
		this.todoText = todo;
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
	
	// ����ToDo���ı�
	public String getTodoText() {
		return todoText;
	}
	// ����ToDo���ı�
	public void setTodoText(String todoText) {
		this.todoText = todoText;
	}
	
	public String toString() {
		return ("TODO " + priority + " " + date + " " + todoText);
	}
	
    public boolean equals(PIMEntity p) {
    	if (p.getType().equals(type)) {
    		PIMTodo pa = (PIMTodo)p;
    		return pa.date.equals(date) && pa.todoText.equals(todoText)
    				&& pa.priority.equals(priority);
    	}
    	return false;
    }
   
    public void setEntity(PIMEntity p) {
    	PIMTodo pa = (PIMTodo)p;
    	this.date = pa.date;
    	this.todoText = pa.todoText;
    	this.priority = pa.priority;
    }
}