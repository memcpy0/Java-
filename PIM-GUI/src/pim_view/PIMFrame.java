package pim_view;

import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import pim_model.*;
import tools.Resources;

/**
 * <p>��Ŀ���ƣ�PIM GUI
 * <p>�����ƣ�PIMFrame
 * ����ʱ�䣺2022��5��27�� <br>
 * ��������PIM GUI����Ķ��㴰��
 * @author����ƽ
 */
public class PIMFrame extends JFrame { 
	private static final int DEFAULT_WIDTH = 1010;  // ���㴰�ڵĿ�� 
	private static final int DEFAULT_HEIGHT = 810; // ���㴰�ڵĸ߶�
	
	private MenuArea menuArea; // �˵�����
	private ItemListArea itemListArea; // ��Ϣ�б�����
    private CalendarArea calendarArea; // ��������
    private JPanel itemListPanel, calendarPanel; 
    
    private PIMManager pimManager;
    private LocalDate date;
    
    public PIMManager getPIMManager() {
    	return pimManager;
    }
    
	public PIMFrame() throws IOException {
        date = LocalDate.now();
		
        pimManager = new PIMManager();
		setTitle("PIM GUI");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setIconImage(Resources.pimFrameIcon.getImage());
		 
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width; // �õ���Ļ�Ŀ��
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height; // �õ���Ļ�ĸ߶�
        setBounds((screenWidth - getWidth()) / 2, (screenHeight - getHeight()) / 2, DEFAULT_WIDTH, DEFAULT_HEIGHT); // ʹ���򴰿ھ���
        
		// ���㴰��,�����ǲ˵���
		// �����ʾPIMEntity��Ϣ,�ұ���ĳ�µ�����,�м���һ���ָ���
        menuArea = new MenuArea(this);

        calendarPanel = new JPanel(new GridLayout());
        itemListPanel = new JPanel(new GridLayout());

		JSplitPane mainViewSeperator = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, itemListPanel, calendarPanel); // ���ҷָ��������� 
        add(mainViewSeperator);
        mainViewSeperator.setDividerLocation(285);
        mainViewSeperator.setDividerSize(5);
        mainViewSeperator.setEnabled(false);
        
        // ����������
        calendarArea = new CalendarArea(date, this);
        calendarPanel.add(calendarArea);
        // ��Ŀ�б�������
        itemListArea = new ItemListArea(this);
        itemListPanel.add(itemListArea);
     
        this.setResizable(false); // �����С���ɱ�
		pack();
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
    // Refreshˢ����ʵ���б������������
    public void refresh() {
        System.out.println("Refreshed.");
        // ˢ����ʵ���б�
        // itemListPanel.remove(itemListArea);
        // itemListArea = new ItemListArea(this);
        // itemListPanel.add(itemListArea);
        itemListArea.refreshAll();
        // ˢ����������
        calendarPanel.remove(calendarArea);
        calendarArea = new CalendarArea(date, this);
        calendarPanel.add(calendarArea);
        
        repaint();
        revalidate();
        setVisible(true);
    }
    
    public MenuArea getMenuArea() {
    	return menuArea;
    }

    public ItemListArea getItemListArea() {
    	return itemListArea;
    }
}