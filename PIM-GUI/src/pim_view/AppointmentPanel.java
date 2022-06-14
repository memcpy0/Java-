package pim_view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;

import pim_model.*;
import tools.Resources;

/**
 * <p>��Ŀ���ƣ�PIM GUI
 * <p>�����ƣ�AppointmentPanel
 * ����ʱ�䣺2022��5��31�� <br>
 * ��������������Ϣ��������PIMAppointment�Ĵ���
 * @author����ƽ
 */
public class AppointmentPanel extends PIMEntityPanel {
	private JTextField date;
	private JTextField desc;
	
	public AppointmentPanel() {
		var panel = new JPanel(); // ���Ĳ���Ĭ����������
		panel.setLayout(new GridLayout(3, 2)); // ����ʹ�����񲼾�
		
		panel.add(new JLabel("    Enter Date"));
		panel.add(date = new JTextField(""));
		
		panel.add(new JLabel("    Enter Description"));
		panel.add(desc = new JTextField(""));
		
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
		PIMAppointment pa = (PIMAppointment)p; 
		desc.setText(pa.getDescription()); // ���öԻ����ı�
		date.setText(Resources.sdf.format(pa.getDate()));
		System.out.println(Resources.sdf.format(pa.getDate()));
		prior.setSelectedItem(pa.getPriority());
	}

	public PIMEntity getEntity() {
		String s1 = desc.getText();
		String s2 = (String) prior.getSelectedItem();
		if (s1.isEmpty() || s2.isEmpty() || s2.equals("--Please Choose--")) return null; // ������ϢΪ�ջ�δѡ�����ȼ�ʱ,����null
		
		LocalDate d;
		try {
			d = LocalDate.from(Resources.sdf.parse(date.getText()));
		} catch (Exception e) {
			return null; // ���ڽ�������,�򷵻�null
		}
		return new PIMAppointment(d, s1, s2); // ��ȡ�Ի���������,����װΪPIMAppointment����
	}
}