package pim_view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pim_model.*;
import tools.Resources;

/**
 * <p>��Ŀ���ƣ�PIM GUI
 * <p>�����ƣ�ItemListPanel
 * ����ʱ�䣺2022��6��3�� <br>
 * ���������¼��б�������
 * @author����ƽ
 */
public class ItemListArea extends JPanel {
	private static final int DEFAULT_WIDTH = 270;  // �б��ڵĿ�� 
	private static final int DEFAULT_HEIGHT = 750; // �б��ڵĸ߶�
	
	private PIMFrame topFrame;
    private JScrollPane itemListScrollPane; // ��ӻ�����
    private Box itemListBox; // �����Ŀ�б�����
    
	public ItemListArea(PIMFrame topFrame) {
		this.topFrame = topFrame;
		itemListBox = Box.createVerticalBox();
		 
		itemListScrollPane = new JScrollPane(itemListBox);
		itemListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // �����д�ֱ������
		itemListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		itemListScrollPane.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		itemListScrollPane.setAutoscrolls(true);
		add(itemListScrollPane, BorderLayout.CENTER);
		refreshAll();
	}
	
	public void refreshLabelList(ArrayList<PIMEntity> pl) {
		itemListBox.removeAll(); // ���֮ǰ��JLabel�б�
		itemListBox.revalidate();
		itemListScrollPane.revalidate();

		for (PIMEntity pe : pl) {
			JLabel tmpLabel = new JLabel("", Resources.itemViewerBorder, JLabel.CENTER);
			tmpLabel.setIconTextGap(-230);
			tmpLabel.setOpaque(true);
			switch (pe.getType()) {
			case "Appointment":
				PIMAppointment pa = (PIMAppointment) pe;
				String a = pa.getDescription();
				if (a.length() > 20) a = a.substring(0, 20) + "...";
				tmpLabel.setText(
					"<html><font weight:bold>" 
						+ "<body>Appointment:<br>"  
							+ Resources.sdf.format(pa.getDate()) + "<br>" + a 
						+ "</body></font></html>"
				);
				tmpLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        showAppointment(pa);
                    }
                });
				break;
			case "Contact": 
				PIMContact pc = (PIMContact) pe;
				tmpLabel.setText(
					"<html><body>Contact: " + pc.getFirstName() + " " + pc.getLastName() + "<br>" + 
						pc.getEmail() + "<body></html>"
				);
				tmpLabel.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mousePressed(MouseEvent e) {
	                    super.mousePressed(e);
	                    showContact(pc);
	                }
	            });
				break;
			case "Todo": 
				PIMTodo pt = (PIMTodo) pe;				
				String b = pt.getTodoText();
				if (b.length() > 20) b = b.substring(0, 20) + "...";
				tmpLabel.setText(
				"<html><font weight:bold>"
					+ "<body>Todo:<br>"  
						+ Resources.sdf.format(pt.getDate()) + "<br>" + b 
					+ "<body></font></html>"
				);
				tmpLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        showTodo(pt);
                    }
                });
				break;
			case "Note":
				String c = ((PIMNote) pe).getNoteText();	 
				if (c.length() > 20) c = c.substring(0, 20) + "...";
				tmpLabel.setText("<html><body>Note:" + "<br>" + c + "<body></html>");
				tmpLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        showNote((PIMNote) pe);
                    }
                });
				break;
			}
			itemListBox.add(Box.createVerticalStrut(5));
			itemListBox.add(tmpLabel); // �������JLabel
		}
        itemListBox.repaint();
        itemListBox.revalidate();
        itemListScrollPane.repaint();
        itemListScrollPane.revalidate();
	}
	
	public void refreshAll() {
		refreshLabelList(topFrame.getPIMManager().getItemList());
	}
	
    // �����Ŀ�����ĵ���(������չʾ���༭��ɾ����Ŀ)
	private void showAppointment(PIMAppointment p) {
		JPanel panel = new JPanel();
		Box box = Box.createVerticalBox();
        box.add(new JLabel(p.getType() + ":"));
        box.add(Box.createVerticalStrut(20));

        LocalDate d = p.getDate();
        box.add(new JLabel(Resources.sdf.format(d) + " " + p.getPriority()));
        box.add(Box.createVerticalStrut(10));
        
        box.add(new JLabel(p.getDescription()));
        box.add(Box.createVerticalStrut(40));
	    panel.add(box); // ������Щ��Ϣ
	    
		ShowDialog sd = new ShowDialog("Show and Edit", true, p, panel);
		sd.setVisible(true);
	}
	
	private void showContact(PIMContact p) {
		JPanel panel = new JPanel();
		Box box = Box.createVerticalBox();
		box.add(new JLabel(p.getType() + ":"));
        box.add(Box.createVerticalStrut(20));
   
        box.add(new JLabel("Name: " + p.getFirstName() + " " + p.getLastName()));
        box.add(Box.createVerticalStrut(10));

        box.add(new JLabel("Email Address: " + p.getEmail())); 
        box.add(Box.createVerticalStrut(40));
	    panel.add(box); // ������Щ��Ϣ
		ShowDialog sd = new ShowDialog("Show and Edit", true, p, panel);
		sd.setVisible(true);
	}

	private void showTodo(PIMTodo p) {
		JPanel panel = new JPanel();
		Box box = Box.createVerticalBox();
		box.add(new JLabel(p.getType() + ":"));
        box.add(Box.createVerticalStrut(20));
   
        LocalDate d = p.getDate();
        box.add(new JLabel(Resources.sdf.format(d) + " " + p.getPriority()));
        box.add(Box.createVerticalStrut(10));

        box.add(new JLabel(p.getTodoText())); 
        box.add(Box.createVerticalStrut(40));
	    panel.add(box); // ������Щ��Ϣ
		ShowDialog sd = new ShowDialog("Show and Edit", true, p, panel);
		sd.setVisible(true);
	}
	
	private void showNote(PIMNote p) {
		JPanel panel = new JPanel();
		Box box = Box.createVerticalBox();
		box.add(new JLabel(p.getType() + ":"));
        box.add(Box.createVerticalStrut(20));
    
        box.add(new JLabel(p.getPriority()));
        box.add(Box.createVerticalStrut(10));

        box.add(new JLabel(p.getNoteText())); 
        box.add(Box.createVerticalStrut(40));
	    panel.add(box); // ������Щ��Ϣ
		ShowDialog sd = new ShowDialog("Show and Edit", true, p, panel);
		sd.setVisible(true);
	}
	
	class ShowDialog extends JDialog { // ����չʾ��Ϣ����
		public ShowDialog(String title, boolean modal, PIMEntity p, JPanel infos) {
			super(topFrame, title, modal);
			setSize(Resources.DIALOG_DEFAULT_WIDTH, Resources.DIALOG_DEFAULT_HEIGHT);
			setResizable(true); // �ɵ����Ի����С,�Ա�ۿ�������Ϣ
			setLocationRelativeTo(topFrame);
			
			JPanel panel = new JPanel();
			JButton comfirmButton = new JButton("Confirm");
			comfirmButton.addActionListener(event -> dispose()); // ���ȷ�ϰ�ť���˳�
			JButton editButton = new JButton("Edit"); // ����༭��ť����༭����
			editButton.addActionListener(event -> {
				dispose();  
				editEntity(p);
			});
			JButton deleteButton = new JButton("Delete"); // ���ɾ����ťɾ����Ŀ
			deleteButton.addActionListener(event -> {
				topFrame.getPIMManager().getItemList().remove(p);
				topFrame.refresh(); // ���ܲ�ˢ�½���,��ֻˢ��������,��ȫ��ˢ��
				dispose();
			});
			
			Box box = Box.createHorizontalBox();
			box.add(comfirmButton);
			box.add(Box.createHorizontalStrut(10));
			box.add(editButton);
			box.add(Box.createHorizontalStrut(10));
			box.add(deleteButton);
			panel.add(box);
			
			add(infos, BorderLayout.CENTER);
			add(panel, BorderLayout.SOUTH);
		}
	}
	
	private void editEntity(PIMEntity ex) { 
		PIMEntity p = topFrame.getMenuArea().editPanel(ex);
		// ˵���û����Cancel��ť,û����������;����Confirm��ť,���༭ʹ�����ݴ���,��Ϊ��
		// ����ԭ����ex���,��û���޸�
		if (p == null) return; // ֱ�ӷ���
		// ����ȷ�������޸�
		ex.setEntity(p);
		topFrame.refresh(); // ���ܲ�ˢ�½���,��ֻˢ��������,��ȫ��ˢ��
	}
}
