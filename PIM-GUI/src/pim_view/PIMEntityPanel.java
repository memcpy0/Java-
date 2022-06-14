package pim_view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;

import javax.swing.*;

import pim_model.PIMEntity;
import tools.Resources;

/**
 * <p>��Ŀ���ƣ�PIM GUI
 * <p>�����ƣ�PIMEntityPanel
 * ����ʱ�䣺2022��6��1�� <br>
 * ������������/�༭PIMEntity�ĸ���������ĳ�����
 * @author����ƽ
 */
public abstract class PIMEntityPanel extends JPanel {
	JComboBox prior; // ÿ��PIMEntity����һ�����ȼ�
	JButton confirmButton; // ÿ��������༭PIMEntity�Ľ��涼��ȷ�ϡ�ȡ����ť��һ���Ի���
	JButton cancelButton;
	JDialog dialog;
	boolean changed; 
	
	PIMEntityPanel() {
		setLayout(new BorderLayout());

		prior = new JComboBox(); // ����JComboBox,��һ��ʹ��
		prior.addItem("--Please Choose--");
		prior.addItem("High Priority");
		prior.addItem("Medium Priority");
		prior.addItem("Low Priority"); 
		confirmButton = new JButton("Confirm");
		
		confirmButton.addActionListener(event -> { // ����󴥷���һ�¼�
			changed = true;
			dialog.setVisible(false); // ���ش���
		});
		
		cancelButton = new JButton("Cancel"); // �����ͬ�����ش���
		cancelButton.addActionListener(event -> dialog.setVisible(false));
	}
	
	/*
	 * ��ȡ�Ի���������,����װΪPIMEntity����
	 */
	abstract public PIMEntity getEntity();
	
	/*
	 * ����PIMEntity���öԻ����ı�
	 */
	abstract public void setEntity(PIMEntity p);
	
	/*
	 * �������ڶԻ�����չʾ��ǰpanel
	 * @param parent�������ߴ���,title�ǶԻ������
	 */
	public boolean showDialog(Component parent, String title) {
		changed = false;
		Frame owner = null;
		if (parent instanceof Frame)
			owner = (Frame)parent;
		else
			owner = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent);
		// ����ǵ�һ�λ��������߸ı�,�򴴽��Ի���,ʹ����Ӧ�Ի������ֻ����һ��
		if (dialog == null || dialog.getOwner() != owner) {
			dialog = new JDialog(owner, true);
			dialog.setIconImage(Resources.newShowEditIco.getImage());
			dialog.setSize(Resources.DIALOG_DEFAULT_WIDTH, Resources.DIALOG_DEFAULT_HEIGHT);
			dialog.add(this); // ����ǰpanel��ӽ��Ի���
			dialog.getRootPane().setDefaultButton(confirmButton);
			dialog.pack();
		}
  
		dialog.setTitle(title);
        dialog.setLocationRelativeTo(owner); // ��������λ��
		dialog.setSize(Resources.DIALOG_DEFAULT_WIDTH, Resources.DIALOG_DEFAULT_HEIGHT);
		dialog.setVisible(true); // ʹ�Ի���ɼ�,���û��ر�����Ի���֮ǰ������
		return changed;
	}
}
