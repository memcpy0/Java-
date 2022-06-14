package pim_view;

import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;

import pim_model.*;
import tools.Resources;

/**
 * <p>��Ŀ���ƣ�PIM GUI<p>
 * �����ƣ�CalendarBlock
 * ����ʱ�䣺2022��5��28�� <br>
 * ������������������������Ĳ���
 * @author���źꆴ
 */
class CalendarBlock extends JLabel implements MouseListener {
	private PIMFrame topFrame;
	private boolean marked;
	private LocalDate date;
	private PIMEntity todayItem;

	// ���췽�����������÷���
	public CalendarBlock(String text, LocalDate date, int horizontalAlignment, PIMFrame topFrame) {
		super(text, Resources.calendarBlockCommon, horizontalAlignment);
		this.date = date;
		this.topFrame = topFrame;
		marked = existsItem();
		if (marked) { // ��ʾ��һ������¼�; �����������������ʱЧ��һ��
			this.setIcon(Resources.calendarBlockMarked);
		}
	}

	// ��д������¼�������
	@Override
	public void mouseEntered(MouseEvent e) { // ���������ʱ
		if (this.marked || date != null) // �б��(�����¼�)��÷����������
			this.setIcon(Resources.calendarBlockChosen);
	}

	@Override
	public void mouseExited(MouseEvent e) { // ������Ƴ�ʱ
		if (this.marked) {
			this.setIcon(Resources.calendarBlockMarked); // �б�Ǿͼ�����ǵ���ķ���
		} else {
			this.setIcon(Resources.calendarBlockCommon);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (date != null) { // �÷����������
			this.setIcon(Resources.calendarBlockChosen); // ���ݵ����Ƿ������¼�,�ֱ𵯳����ֵ���
			if (this.marked) { // �������е�itemչʾ����showItems
				showItems();
			} else { // �����½��¼�����newItem
				newItem();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}
	
	private boolean existsItem() { // �����Ƿ�����¼�
		ArrayList<PIMEntity> itemList = topFrame.getPIMManager().getItemList();
		for (PIMEntity p : itemList) {
			if ((p.getType().equals("Appointment") && ((PIMAppointment)p).getDate().equals(date))
				|| (p.getType().equals("Todo") && ((PIMTodo)p).getDate().equals(date))
			) {
				todayItem = p;
				return true;
			}
		}
		return false;
	}

	// �����������,�������û���¼�,�򵯳��½��¼�����(����ѡ��)
	private void newItem() {
        int idx = JOptionPane.showOptionDialog(topFrame, "No Personal Information Exists. Now You Choose To...", 
            	"Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, Resources.infoMsgIco,
            	new Object[] {"Create New Appointment", "Create New Todo"}, "Appointment");
        if (idx == -1) return; // ѡ��-1��ʾ�رնԻ���
        else if (idx == 0) { // ѡ��0��ʾ����Appointment
        	topFrame.getMenuArea().newAppointment(date); // �����¼��󷽸��仯
            topFrame.refresh(); // ���ܻ�ͬʱˢ�������Ҳ����
        } else if (idx == 1) { // ѡ��1��ʾ����Todo
        	topFrame.getMenuArea().newTodo(date); 
            topFrame.refresh(); // ���ܻ�ͬʱˢ�������Ҳ����
        }
	}
	  
	// ��������¼�,����������չʾ
	private void showItems() {
		ArrayList<PIMEntity> itemList = topFrame.getPIMManager().getItemList();
		ArrayList<PIMEntity> tmpList = new ArrayList<>();
		for (PIMEntity p : itemList) {
			if (
				(p.getType().equals("Appointment") && ((PIMAppointment)p).getDate().equals(date))
			 || (p.getType().equals("Todo") && ((PIMTodo)p).getDate().equals(date))
			) tmpList.add(p);
		}
		topFrame.getItemListArea().refreshLabelList(tmpList);
	}
}
