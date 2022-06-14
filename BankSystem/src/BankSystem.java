import java.util.ArrayList;
import java.util.Scanner;
import bank.*;

public class BankSystem {
	private int acCount;
	private ArrayList<BankAccount> bankAccountList;
	
	public BankSystem() {
		acCount = 0;
		bankAccountList = new ArrayList<>();
	}
	
	// ����
	public void openAccount(Scanner sc) { 
		System.out.print("ѡ���˻�����(1: CashAccount, 2: CreditAccount)��");
		int choice = sc.nextInt();
		int id;
		String name, password;
		double balance, ceiling;
		
		switch (choice) {
		case 1: // CashAccount
			id = acCount; // �˻�ID�濪����������
			System.out.print("�����ֽ��˻����ƣ�");
			name = sc.next();
			boolean hasSameName = false;
			for (BankAccount ba : bankAccountList) { // ����Ƿ�����ͬ���˻�����
				if (ba.getAccountName().equals(name)) {
					hasSameName = true;
					break;
				}
			}
			if (hasSameName) {
				System.out.println("��һ�˻������ѱ�ʹ�ã�");
			} else {
				System.out.print("�����˻����룺");
				password = sc.next();
				System.out.print("�����ʼ�����");
				balance = sc.nextDouble();
				if (balance >= 0) {
					bankAccountList.add(new CashAccount(id, name, password, balance));
					System.out.println("�����˻��ѳɹ��������˻�IDΪ" + id + "��");
					++acCount;
				} else System.out.println("�����˴���Ĵ���������´����˻���");
			}
			break;
		case 2: // CreditAccount
			id = acCount;
			System.out.print("���������˻����ƣ�");
			name = sc.next();
			boolean hasSameName2 = false;
			for (BankAccount ba : bankAccountList) { // ����Ƿ�����ͬ���˻�����
				if (ba.getAccountName().equals(name)) {
					hasSameName2 = true;
					break;
				}
			}
			if (hasSameName2) {
				System.out.println("��һ�˻������ѱ�ʹ�ã�");
			} else {
				System.out.print("�����˻����룺");
				password = sc.next();
				System.out.print("�����ʼ�����");
				balance = sc.nextDouble();
				System.out.print("�������ö�ȣ�");
				ceiling = sc.nextDouble();
				if (balance >= 0 && ceiling >= 0) {
					bankAccountList.add(new CreditAccount(id, name, password, balance, ceiling));
					System.out.println("�����˻��ѳɹ��������˻�IDΪ" + id + "��");
					++acCount;
				} else System.out.println("�����˴���Ľ������´����˻���");
			}
			break;
		default:
			System.out.println("�������룡");
			break;
		}
	}
	
	// ��¼�˻�
	public void login(Scanner sc) {
		System.out.print("�����˻����ƣ�");
		String name = sc.next();
		System.out.print("�����˻����룺");
		String password = sc.next();
		
		BankAccount yourBA = null;
		for (BankAccount ba : bankAccountList) {
			if (ba.getAccountName().equals(name) && ba.getPassword().equals(password)) { 
				yourBA = ba;
				break;
			}
		}
		if (yourBA == null) {
			System.out.println("�����˴�����û��������룡");
		} else {
			System.out.println("�ɹ���¼��");
			int choice;
			double change;
			do {
				System.out.println("ѡ��Ҫʹ�õĹ��ܣ�");
				System.out.println("1. ���");
				System.out.println("2. ȡ��");
				System.out.println("3. ����˻����");
				System.out.println("4. �ı��˻�����");
				System.out.println("5. ��ӡ���6������");
				System.out.println("6. �ǳ��˻�"); // �ǳ��˻�
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.print("�������");
					change = sc.nextDouble();
					yourBA.deposit(change);
					break;
				case 2:
					System.out.print("����ȡ���");
					change = sc.nextDouble();
					yourBA.withdraw(change);
					break;
				case 3:
					yourBA.checkBalance();
					break;
				case 4:
					System.out.print("�����µ��˻����ƣ�");
					name = sc.next();
					yourBA.changeAccountName(name);
					break;
				case 5:
					yourBA.printTransactions();
					break;
				case 6:
					break;
				default:
					System.out.println("�������룡");
					break;
				}
				
			} while (choice != 6);
		}
	}
	
	// ��������ϵͳ,�൱���û�����
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		BankSystem bs = new BankSystem();
		int choice;
		do {
			System.out.println("ѡ��Ҫʹ�õĹ��ܣ�");
			System.out.println("1. ����");
			System.out.println("2. ��¼�˻�");
			System.out.println("3. �˳�ϵͳ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				bs.openAccount(sc);
				break;
			case 2:
				bs.login(sc);
				break;
			case 3:
				break;
			default:
				System.out.println("�������룡");
				break;
			}
		} while (choice != 3);
		sc.close();
	}
}
