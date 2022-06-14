package pim_view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import pim_model.*;
import tools.Resources;

/**
 * <p>��Ŀ���ƣ�PIM GUI
 * <p>�����ƣ�MenuFrame
 * ����ʱ�䣺2022��5��30�� <br>
 * ��������PIM GUI���㴰��(PIMFrame)�еĲ˵�������
 * @author����ƽ
 */
public class MenuArea extends JPanel {
	private static final int DEFAULT_WIDTH = 600;  // �˵����ڵĿ�� 
	private static final int DEFAULT_HEIGHT = 400; // �˵����ڵĸ߶�
	
	// �����ĸ��ִ���
	private TodoPanel todoPanel = null;
	private AppointmentPanel appointPanel = null;
	private NotePanel notePanel = null;
	private ContactPanel contactPanel = null;
	private JFileChooser chooser = null; // �ļ��Ի���
	
	// �Զ��㴰�������
	private PIMFrame topFrame = null;
	
	public MenuArea(PIMFrame topFrame) {
		this.topFrame = topFrame;
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		/* �ļ��˵� */
		var fileMenu = new JMenu("File"); 
		fileMenu.setMnemonic('F'); // Ϊ�˵�������Ƿ�,ѡ����Щ����˵��ɰ���ALT+������ĸ
		
		// �ļ��˵��������桢���ء����Ϊ�����ļ����ء��˳��⼸���˵���		
		var saveItem = new JMenuItem("Save", Resources.saveIco); // ���浽Ĭ�������ļ�
		saveItem.setMnemonic('S');
		saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S")); // ���ü��̼�����,����Ctrl S����
		saveItem.setToolTipText("Click to save all.");
		saveItem.setIcon(Resources.saveIco);
		
		var loadItem = new JMenuItem("Load", Resources.loadIco); // ��Ĭ�������ļ��м��ض�������
		loadItem.setMnemonic('L');
		loadItem.setAccelerator(KeyStroke.getKeyStroke("ctrl L")); // ���ü��̼�����,����Ctrl L����
		loadItem.setToolTipText("Click to load from local files.");
		
		var saveAsItem = new JMenuItem("Save As...", Resources.saveAsIco); // ���Ϊָ���������ļ�
		saveAsItem.setToolTipText("Click to save all to selected"
				+ " file.");
		
		var loadFromItem = new JMenuItem("Load From...", Resources.loadFromIco); // ��ָ���������ļ��м��ض���
		loadFromItem.setToolTipText("Click to load from selected file.");
 		
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.add(saveAsItem); 
		fileMenu.add(loadFromItem);
		fileMenu.addSeparator(); // ���ļ��˵�����ӷָ���
		var exitItem = fileMenu.add(new AbstractAction("Exit") { // ����˳��˵�����˳�����
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		exitItem.setDisplayedMnemonicIndex(1); // ����xΪ���Ƿ�
		exitItem.setAccelerator(KeyStroke.getKeyStroke("ctrl X")); // ���ü��̼�����,����Ctrl X�˳�����
		exitItem.setToolTipText("Click to exit.");

		chooser = new JFileChooser(); // �ڶ������������һ���ļ�ѡ����
		chooser.setCurrentDirectory(new File(".")); // ʹ�õ�ǰ����Ŀ¼
		chooser.setSelectedFile(new File(topFrame.getPIMManager().getDataFilePath())); // Ĭ��ѡ����ĿĿ¼�µ�Ĭ�����ݴ洢�ļ�
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // ֻ�����ļ�ѡ����ѡ���ļ���Ŀ¼
		chooser.setFileFilter(new FileNameExtensionFilter("dat", "DAT")); // ֻ�����ļ�ѡ������.dat�ļ���ѡ��
		chooser.setAcceptAllFileFilterUsed(false); // ����All files������
		
		/* �༭�˵� */
		var editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E'); // Ϊ�˵�������Ƿ�
		
		// �༭�˵������½����������ϵ�ˡ���ʾ�Ȳ˵���
		var newAppointItem = new JMenuItem("New Appointment", Resources.newAppointmentIco);
		newAppointItem.setDisplayedMnemonicIndex(4);
		newAppointItem.setToolTipText("Click to create a new appointment.");
		newAppointItem.setAccelerator(KeyStroke.getKeyStroke("ctrl A")); // ���ü��̼�����,����Ctrl A�½�Լ��
		
		var newContactItem = new JMenuItem("New Contact", Resources.newContactIco);
		newContactItem.setDisplayedMnemonicIndex(4);
		newContactItem.setToolTipText("Click to create a new contact.");
		newContactItem.setAccelerator(KeyStroke.getKeyStroke("ctrl C")); // ���ü��̼�����,����Ctrl C�½�Լ��
		
		var newNoteItem = new JMenuItem("New Note", Resources.newNoteIco);
		newNoteItem.setDisplayedMnemonicIndex(4);
		newNoteItem.setToolTipText("Click to create a new note.");
		newNoteItem.setAccelerator(KeyStroke.getKeyStroke("ctrl N")); // ���ü��̼�����,����Ctrl N�½���ʾ
		
		var newTodoItem = new JMenuItem("New Todo", Resources.newTodoIco);
		newTodoItem.setDisplayedMnemonicIndex(4);
		newTodoItem.setToolTipText("Click to create a new todo.");
		newTodoItem.setAccelerator(KeyStroke.getKeyStroke("ctrl T")); // ���ü��̼�����,����Ctrl T�½�TODO
		
		editMenu.add(newAppointItem);
		editMenu.add(newContactItem);
		editMenu.add(newNoteItem);
		editMenu.add(newTodoItem);
				
        /* �����˵� */
		var navigateMenu = new JMenu("Navigate");
		navigateMenu.setMnemonic('N'); // Ϊ�˵�������Ƿ�

		// �����˵���������������ת��ָ�����¡���ת�����ա���ʾ���м�¼/��������/��ϵ��/Լ��/�Ȳ˵���
		var jumpToDateItem = new JMenuItem("Jump to Date", Resources.jumpToDateIco);
		jumpToDateItem.setMnemonic('J');
		jumpToDateItem.setToolTipText("Click to jump to date assigned.");
		jumpToDateItem.setAccelerator(KeyStroke.getKeyStroke("ctrl J")); // ���ü��̼�����,����Ctrl L����
		
		var jumpToTodayItem = new JMenuItem("Jump to Today", Resources.jumpToTodayIco);
		jumpToTodayItem.setToolTipText("Click to jump to today."); 

		var showAllItem = new JMenuItem("Show All", Resources.showAllIco);
		showAllItem.setMnemonic('S');
		showAllItem.setToolTipText("Click to show all your information."); 

		var showAppointmentsItem = new JMenuItem("Show appointments", Resources.showAppointsIco);
		showAppointmentsItem.setToolTipText("Click to show all your appointments."); 
		showAppointmentsItem.setAccelerator(KeyStroke.getKeyStroke("ctrl shift A"));
		
		var showContactsItem = new JMenuItem("Show Contacts", Resources.showContactsIco);
		showContactsItem.setToolTipText("Click to show all your contacts."); 
		showContactsItem.setAccelerator(KeyStroke.getKeyStroke("ctrl shift C"));
		
		var showNotesItem = new JMenuItem("Show Notes", Resources.showNotesIco);
		showNotesItem.setToolTipText("Click to show all your notes."); 
		showNotesItem.setAccelerator(KeyStroke.getKeyStroke("ctrl shift N"));
		
		var showTodosItem = new JMenuItem("Show Todos", Resources.showTodosIco);
		showTodosItem.setToolTipText("Click to show all your todos."); 
		showTodosItem.setAccelerator(KeyStroke.getKeyStroke("ctrl shift T"));
		
		navigateMenu.add(jumpToDateItem);
		navigateMenu.add(jumpToTodayItem);
		navigateMenu.addSeparator(); // ���ļ��˵�����ӷָ���
		navigateMenu.add(showAllItem);
		navigateMenu.add(showAppointmentsItem);
		navigateMenu.add(showContactsItem);
		navigateMenu.add(showNotesItem);
		navigateMenu.add(showTodosItem);
		
		/* �����˵� */
		var helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H'); // Ϊ�˵�������Ƿ�
		
		var aboutItem = new JMenuItem("About PIM GUI", Resources.aboutIco);
		aboutItem.setMnemonic('A');
		aboutItem.setToolTipText("Click to see how to use this program.");
		
		helpMenu.add(aboutItem);
		
		/* �˵��� */
		var menuBar = new JMenuBar();
		menuBar.add(fileMenu); // ������˵���ӵ��˵�����
		menuBar.add(editMenu);
		menuBar.add(navigateMenu);
		menuBar.add(helpMenu);
		
		topFrame.setJMenuBar(menuBar); // ���㴰������Ӳ˵���

		/* �˵�������,Ϊ�����˵�������¼� */
		saveItem.addActionListener(event -> save());
		saveAsItem.addActionListener(event -> saveAs());
		loadItem.addActionListener(event -> load());
		loadFromItem.addActionListener(event -> loadFrom());
		
		newAppointItem.addActionListener(event -> newAppointment(LocalDate.now())); // ������Ӧ�Ķ�����
		newContactItem.addActionListener(event -> newContact());
		newNoteItem.addActionListener(event -> newNote());
		newTodoItem.addActionListener(event -> newTodo(LocalDate.now()));
		
		jumpToDateItem.addActionListener(event -> { jumpToDate(); });
		jumpToTodayItem.addActionListener(event -> { jumpToToday(); });
		showAllItem.addActionListener(event -> { topFrame.getItemListArea().refreshAll(); }); // ˢ�����JLabel�б�
		showAppointmentsItem.addActionListener(event -> {
			topFrame.getItemListArea().refreshLabelList(
				getType(topFrame.getPIMManager().getItemList(), "Appointment"));
		});
		showContactsItem.addActionListener(event -> {
			topFrame.getItemListArea().refreshLabelList(
				getType(topFrame.getPIMManager().getItemList(), "Contact"));
		});
		showNotesItem.addActionListener(event -> {
			topFrame.getItemListArea().refreshLabelList(
				getType(topFrame.getPIMManager().getItemList(), "Note"));
		});
		showTodosItem.addActionListener(event -> {
			topFrame.getItemListArea().refreshLabelList(
				getType(topFrame.getPIMManager().getItemList(), "Todo")); 
		});
		
		aboutItem.addActionListener(event -> { // ��������
			JFrame jf = new JFrame("About PIM GUI");
			jf.setIconImage(Resources.aboutIco.getImage());
			jf.setSize(420, 180);
			jf.setLayout(new BorderLayout());
			Box b = Box.createVerticalBox();
			b.add(new JLabel("Personal Information Manager with Graphic User Interface"));
			b.add(Box.createVerticalStrut(10));
			b.add(new JLabel("ʵ�����ߣ���ƽ, �źꆴ"));
			b.add(new JLabel("���ʱ�䣺2022��6��3��"));
			b.add(new JLabel("ʹ�÷�����ͨ���˵�����/�������ݡ��½���¼�����������鿴��Ϣ�����"));
			b.add(new JLabel("����б��еļ�¼���б༭��ɾ��������������񴴽���ʱ����صļ�¼"));
			jf.add(new JLabel(" "), BorderLayout.NORTH);
			jf.add(new JLabel(" "), BorderLayout.SOUTH);
			jf.add(new JLabel(" "), BorderLayout.WEST);
			jf.add(new JLabel(" "), BorderLayout.EAST);
			jf.add(b, BorderLayout.CENTER);
			jf.setResizable(false);
			jf.setLocationRelativeTo(topFrame);
			jf.setVisible(true);
		});
//		menuBar.setVisible(true);
	}
	
	/*
	 * Ĭ������ĿĿ¼�±���Ϊ"PIMDatabase.dat"
	 * ���ù�saveAs()����ܻ�ı�Ĭ�ϱ���·��
	 */
	private void save() { 
        System.out.println("Saving...");
        try {
        	topFrame.getPIMManager().saveData();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        // ��ʾ�Ի���
        String[] tmp = topFrame.getPIMManager().getDataFilePath().split("\\\\");
        String filePath = tmp[tmp.length - 1];
        JOptionPane.showMessageDialog(topFrame, "Saving Data Into " + filePath, 
        	"Save", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Save Finished.");
	}
	
	/*
	 * Ĭ�ϼ�����ĿĿ¼�µ�"PIMDatabase.dat"
	 * ���ù�loadFrom����ܻ�ı�Ĭ�ϼ���·��
	 */
	private void load() { 
        System.out.println("Loading...");
        try {
        	topFrame.getPIMManager().loadData();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        // ��ʾ�Ի���
        String[] tmp = topFrame.getPIMManager().getDataFilePath().split("\\\\");
        String filePath = tmp[tmp.length - 1];
        JOptionPane.showMessageDialog(topFrame, "Loading Data From " + filePath,
        	"Load", JOptionPane.INFORMATION_MESSAGE);
		topFrame.refresh(); 
		// ���ļ��м��غ�ˢ��(���ļ�������ͬʱ��Ч��;�����ļ����ݻḲ���ڴ�����)
		System.out.println("Load Finished.");
	}
	
	/*
	 * ����ѡ��Ŀ¼,���Զ��ڸ�Ŀ¼���½�һ��PIMDatabase.dat
	 * ����ѡ���Ѵ��ڵ�dat�ļ�,������ɾ�����ļ�,���½�ͬ���ļ�
	 * ���������.dat��չ�������ļ���,�������ļ�
	 */
	private void saveAs() {
		try {
			JOptionPane.showMessageDialog(null, "Please Input Extension Name "
					+ "'.dat' Manually", "Tip", JOptionPane.WARNING_MESSAGE);
			int result = chooser.showSaveDialog(topFrame); // ��ʾ����Ի���
			if (result == JFileChooser.APPROVE_OPTION) {
				String filename = null;
				File file = chooser.getSelectedFile();
                if (file.isDirectory()) { // ѡ����һ��Ŀ¼,���ڸ�Ŀ¼���½�һ�������ļ�
                    filename = chooser.getSelectedFile().getPath() + "PIMDatabase.dat";
                } else if ( // ѡ����һ���ļ����ļ���.dat��β,��������һ������.dat���ļ���
                		new FileNameExtensionFilter("dat", "DAT").accept(file)) {
                	if (file.isFile()) // ���ļ�������ɾ��,�������½�ͬ���ļ�
                		file.delete();
                    filename = file.getPath();
                }
				topFrame.getPIMManager().setDataFilePath(filename);
				save(); // ����save����
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Something Wrong Happens...", "Wrong",
				JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void loadFrom() {
		try {
			int result = chooser.showOpenDialog(topFrame); // ��ʾ�򿪶Ի���
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				if (file.isFile() && // ѡ����һ���ļ�
            		new FileNameExtensionFilter("dat", "DAT").accept(file)) { // ���ѡ����ļ���.dat��β
					topFrame.getPIMManager().setDataFilePath(file.getPath());
					load(); // ����load����,���ڲ�����ˢ��
				}
            }
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Something Wrong happens...", "Wrong",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* --- */
	private PIMEntity callPanel(PIMEntity ex, String title, PIMEntityPanel pep) {
		if (pep == null) {
			switch (ex.getType()) {
			case "Appointment": pep = new AppointmentPanel(); break;
			case "Contact": pep = new ContactPanel(); break;
			case "Note": pep = new NotePanel(); break;
			case "Todo": pep = new TodoPanel(); break;
			} // ��ʱ����ʾ����
		}
		pep.setEntity(ex); // ex�������ڽ�������
		if (pep.showDialog(topFrame, title)) { // ����showDialogչʾ�Ի��򴰿�
			// �û���������,���Confirm��ť����˴�,�õ�һ��PIMEntity����
			return pep.getEntity(); // ����Ϊ��(���û������������/������)��ex��ȵĶ���(��Editʱ),��������ȷ����
		}
		return null; // ����û��������Cancel��ť,�򷵻�null 
	}
	
	public void newContact() { // ����Ĭ�϶Ի���
		PIMContact ex = new PIMContact("your firstname", "your lastname", "xxxxxx@xxxx");
		PIMContact p = (PIMContact)callPanel(ex, "New Contact", contactPanel);
		if (p != null && !p.equals(ex)) { // ��������޴�����Ĭ��ֵ��ͬ
			topFrame.getPIMManager().addPIMEntity(p);
			topFrame.refresh();
		}
	}
	 
	public void newNote() { // ����Ĭ�϶Ի���
		PIMNote ex = new PIMNote("your note text", "--Please Choose--");
		PIMNote p = (PIMNote)callPanel(ex, "New Note", notePanel);
		if (p != null) { // ��������޴�(һ����Ĭ��ֵ��ͬ)
			topFrame.getPIMManager().addPIMEntity(p);
			topFrame.refresh();
		}
	}
	
	public void newAppointment(LocalDate d) { // ����Ĭ�϶Ի���,�û�������������ʵ��		
		PIMAppointment ex = new PIMAppointment(d, "your description", "--Please Choose--");		
		PIMAppointment p = (PIMAppointment)callPanel(ex, "New Appointment", appointPanel);
		if (p != null) { // ��������޴�(һ����Ĭ��ֵ��ͬ)
			topFrame.getPIMManager().addPIMEntity(p);
			topFrame.refresh();
		}
	}
	
	public void newTodo(LocalDate d) { // ����Ĭ�϶Ի���
		PIMTodo ex = new PIMTodo(d, "your todo text", "--Please Choose--");
		PIMTodo p = (PIMTodo)callPanel(ex, "New Todo", todoPanel);
		if (p != null) { // ��������޴�(һ����Ĭ��ֵ��ͬ)
			topFrame.getPIMManager().addPIMEntity(p);
			topFrame.refresh();
		}
	}
	
	public PIMEntity editPanel(PIMEntity ex) { // ���ñ༭�Ի���
		switch (ex.getType()) { // ����ex�Ĳ�ͬ����,���ò�ͬ��edit�Ի���
		case "Appointment": return callPanel(ex, "Edit Appointment", todoPanel);
		case "Contact": return callPanel(ex, "Edit Contact", contactPanel);
		case "Note": return callPanel(ex, "Edit Note", notePanel);
		case "Todo": return callPanel(ex, "Edit Todo", appointPanel);
		}
		return null; // ����ִ��
	}
	
	/* -- */
	public void jumpToDate() {
        String targetDate = (String)JOptionPane.showInputDialog(topFrame, "Please Input Target Date Like 2022-06", 
        		"Input", JOptionPane.INFORMATION_MESSAGE); 
		LocalDate d = null;
		try {
			if (targetDate == null) return; // �û����ȡ��
			if (targetDate.equals("")) { // �û�ʲô��û����
				JOptionPane.showMessageDialog(topFrame, "Please Input a NONEMPTY Date", 
					"Tip", JOptionPane.WARNING_MESSAGE); // ��������
			} else {
				d = LocalDate.from(Resources.sdf.parse(targetDate + "-01")); // �û�����ĳ��-ĳ��
				topFrame.setDate(d); // ������쳣,����ת������-��
				topFrame.refresh();
			}
		} catch (Exception e) { // ����������
			JOptionPane.showMessageDialog(null, "Something Wrong Happens...", "Wrong",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void jumpToToday() {
		topFrame.setDate(LocalDate.now());
		topFrame.refresh();
	}
	
	private ArrayList<PIMEntity> getType(ArrayList<PIMEntity> ls, String type) {
		// �õ�ĳ�����͵Ķ��󼯺�
		ArrayList<PIMEntity> ans = new ArrayList<>();
		for (PIMEntity p : ls) {
			if (p.getType().equals(type)) {
				ans.add(p);
			}
		}
		return ans;
	}
}
