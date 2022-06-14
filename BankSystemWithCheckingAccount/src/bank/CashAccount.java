package bank;

public class CashAccount extends BankAccount {
	// �����˻���¼�����ʽ���ˮ,������BankAccountû������
	public CashAccount(int id, String name, String password, double balance) {
		super(id, name, password, balance);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getAccountName() + "'s CashAccount: ");
		sb.append(String.valueOf(getBalance()));
		return sb.toString();
	}
}
