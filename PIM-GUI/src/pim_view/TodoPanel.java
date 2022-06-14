package pim_view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.awt.*;
import javax.swing.*;

import pim_model.*;
import tools.Resources;

/**
 * <p>��Ŀ���ƣ�PIM GUI
 * <p>�����ƣ�TodoPanel
 * ����ʱ�䣺2022��5��31�� <br>
 * ��������������Ϣ��������PIMTodo�Ĵ���
 * @author����ƽ
 */
public class TodoPanel extends PIMEntityPanel {
	private JTextField todo;
	private JTextField date;
	
	public TodoPanel() {
		var panel = new JPanel(); // ���Ĳ���Ĭ����������
		panel.setLayout(new GridLayout(3, 2)); // ����ʹ�����񲼾�
		
		panel.add(new JLabel("    Enter Text for Todo"));
		panel.add(todo = new JTextField(""));
		 
		panel.add(new JLabel("    Enter Date for Todo"));
		panel.add(date = new JTextField(""));
		
		panel.add(new JLabel("    Choose Priority"));
		panel.add(prior);
		add(panel, BorderLayout.CENTER); // ���������ڽ�������
		
		// ��������ť��ӵ��ϲ�
		var buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
 
	public void setEntity(PIMEntity p) {
		PIMTodo pt = (PIMTodo)p;
		todo.setText(pt.getTodoText());  // ���öԻ����ı�
		date.setText(Resources.sdf.format(pt.getDate()));
		prior.setSelectedItem(pt.getPriority());
	}

	public PIMEntity getEntity() {
		String s1 = todo.getText();
		String s2 = (String) prior.getSelectedItem();
		if (s1.isEmpty() || s2.isEmpty() || s2.equals("--Please Choose--")) return null; // ������ϢΪ�ջ�δѡ�����ȼ�ʱ,����null
		
		LocalDate d;
		try {
			d = LocalDate.from(Resources.sdf.parse(date.getText()));
		} catch (Exception e) {
			return null; // ���ڽ�������,�򷵻�null
		}
		return new PIMTodo(d, s1, s2); // ��ȡ�Ի���������,����װΪPIMTodo����
	}
}