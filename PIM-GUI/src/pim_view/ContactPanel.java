package pim_view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import pim_model.*;

/**
 * <p>��Ŀ���ƣ�PIM GUI
 * <p>�����ƣ�ContactPanel
 * ����ʱ�䣺2022��5��31�� <br>
 * ��������������Ϣ��������PIMContact�Ĵ���
 * @author����ƽ
 */
public class ContactPanel extends PIMEntityPanel {
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email; 
	
	public ContactPanel() {
		var panel = new JPanel(); // ���Ĳ���Ĭ����������
		panel.setLayout(new GridLayout(3, 2)); // ����ʹ�����񲼾� 
		
		panel.add(new JLabel("    FirstName"));
		panel.add(firstName = new JTextField(""));
		 
		panel.add(new JLabel("    LastName"));
		panel.add(lastName = new JTextField(""));
		
		panel.add(new JLabel("    Email Address"));
		panel.add(email = new JTextField(""));
		add(panel, BorderLayout.CENTER); // ���������ڽ�������
		
		// ��������ť��ӵ��ϲ�
		var buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
	} 
	
	public void setEntity(PIMEntity p) {
		PIMContact pc = (PIMContact)p;
		firstName.setText(pc.getFirstName()); // ���öԻ����ı� 
		lastName.setText(pc.getLastName());
		email.setText(pc.getEmail());
	}
	 
	public PIMEntity getEntity() {
		String s1 = firstName.getText();
		String s2 = lastName.getText();
		String s3 = email.getText();
		if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) return null; // ������ϢΪ��ʱ,����null
		return new PIMContact(s1, s2, s3); // ��ȡ�Ի���������,����װΪPIMContact����
	}
}