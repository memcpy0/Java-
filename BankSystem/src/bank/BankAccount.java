package bank;

public abstract class BankAccount {
	private int id;				// �˻�ID
	private String acName;		// �˻�����
	private String password;	// �˻�����
	private double balance;		// ���
	String[] transactions;		// ��¼���6��������Ϣ
	
	public BankAccount(int id, String name, String password, double balance) {
		this.id = id;
		this.acName = name;
		this.password = password;
		this.balance = balance;
		transactions = new String[6];
	}
	
	public int getId() {
		return id;
	}
	public String getAccountName() {
		return acName;
	}
	public String getPassword() {
		return password;
	}
	public double getBalance() {
		return balance;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setAccountName(String name) {
		this.acName = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	// ���
	public void deposit(double money) {
		if (money < 0) throw new IllegalArgumentException("negative deposit");
		this.balance += money;
		addTransaction("Deposit", "+" + String.valueOf(money)); // ���Ӵ������
	}
	// ȡ��
	public void withdraw(double money) {
		if (money < 0 || money > balance) 
			throw new IllegalArgumentException("illegal withdrawal");
		this.balance -= money;
		addTransaction("Withdraw", "-" + String.valueOf(money));
	}
	// �ı��˻�����
	public void changeAccountName(String name) {
		setAccountName(name);
	}
	// ��ѯ�������
	public void checkBalance() { // ����һλС����ʽ
		System.out.println("�˻���" + String.format("%.1f", balance));
	}
	// ��¼����,�������
	public void addTransaction(String type, String change) {
		int i;
		for (i = 0; i < 6; ++i) { 
			if (transactions[i] == null) {
				transactions[i] = "��������: " + type + ",���仯: " + change;
				break;
			}
		}
		if (i == 6) { // �޿մ�ʱ,��������ǰ��һλ
			for (int j = 0; j < 5; ++j)
				transactions[j] = transactions[j + 1];
			transactions[5] = "��������: " + type + ", ���仯: " + change;
		}
		
	}
	// �����¼������
	public void printTransactions() {
		System.out.println("�����6������������ʾ:");
		for (int i = 0; i < 6; ++i) {
			if (transactions[i] == null) break;
			System.out.println("����" + i + ": " + transactions[i]);
		}
	}
	
	// �������,��������
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(acName + "'s Acount: ");
		sb.append(String.valueOf(balance));
		return sb.toString();
	}
}

