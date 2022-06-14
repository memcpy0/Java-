package bank;

public class CreditAccount extends BankAccount {
	// ���ÿ��˻���¼�����ʽ���ˮ,���⻹Ӧ��һ�����ö��
	private double ceiling;		// ���ö��
	private double remainder;   // ʣ����
	
	public CreditAccount(int id, String name, String password, double balance, double ceiling) {
		super(id, name, password, balance);
		this.ceiling = ceiling;
		this.remainder = ceiling;
	}
	
	public double getCeiling() {
		return ceiling;
	}
	public void setCeiling(double ceiling) {
		this.ceiling = ceiling;
	}
	
	@Override
	public void withdraw(double change) { // Credit�˻�����͸֧���,�����Ҫ��д�����withdraw����
		if (change > getBalance() + remainder)
			throw new IllegalArgumentException("illegal withdrawal");
		if (change <= remainder) remainder -= change;
		else {
			setBalance(getBalance() - (change - remainder));
			remainder = 0; // ������
		}
		addTransaction("Withdraw", "-" + String.valueOf(change));
	}
	@Override
	public void checkBalance() { // ��д�����checkBalance����
		System.out.println("�˻���" + String.format("%.1f", getBalance()));
		System.out.println(String.format("���ö�ȣ�%.1f��ʣ���ȣ�%.1f", ceiling, remainder));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getAccountName() + "'s CreditAccount: ");
		sb.append(String.valueOf(getBalance()));
		return sb.toString();
	}
}