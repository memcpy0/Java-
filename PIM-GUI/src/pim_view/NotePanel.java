package pim_view;

import java.awt.*;
import javax.swing.*;

import pim_model.*;

/**
 * <p>��Ŀ���ƣ�PIM GUI
 * <p>�����ƣ�NotePanel
 * ����ʱ�䣺2022��5��31�� <br>
 * ��������������Ϣ��������PIMNote�Ĵ���
 * @author����ƽ
 */
public class NotePanel extends PIMEntityPanel {
	private JTextField note;

	public NotePanel() {
		var panel = new JPanel(); // ���Ĳ���Ĭ����������
		panel.setLayout(new GridLayout(2, 2)); // ����ʹ�����񲼾�
		
		panel.add(new JLabel("    Enter Note Text"));
		panel.add(note = new JTextField(""));

		panel.add(new JLabel("    Choose Priority"));
		panel.add(prior); // ���JComboBox
		add(panel, BorderLayout.CENTER); // ���������ڽ�������
		
		// ��������ť��ӵ��ϲ�
		var buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void setEntity(PIMEntity p) {
		PIMNote pn = (PIMNote)p; // ���öԻ����ı�
		note.setText(pn.getNoteText()); 
		prior.setSelectedItem(pn.getPriority());
	}
	 
	public PIMEntity getEntity() {
		String s1 = note.getText();
		String s2 = (String) prior.getSelectedItem();
		if (s1.isEmpty() || s2.isEmpty() || s2.equals("--Please Choose--")) return null; // ������ϢΪ�ջ�δѡ�����ȼ�ʱ,����null
		return new PIMNote(s1, s2); // ��ȡ�Ի���������,����װΪPIMNote����
	}
}

