package bank;

public class CheckingAccount {
	private static final double eps = 1e-8;
	// С�ں���,���ڸ������Ƚ�
	private static boolean less(double a, double b) {
		return (a - b) < (-eps); // ֻ��С��b-eps����a�����ж�ΪС��b
	}
	
	// ���ں���,���ڸ������Ƚ�
	private static boolean greater(double a, double b) {
		return (a - b) > eps; // ֻ�д���b+eps����a�����ж�Ϊ����b
	}
	
	// ����Ƿ�洢�����������˻�
	public static void checkNegativeDeposit(double amount) {
		if (less(amount, 0.0)) {
			throw new IllegalArgumentException("A negative amount is deposited");
		}
	}
	
	// ���ʵ�����˻�����ʱ���Ƿ��Ը������/��ȹ���
	public static void checkConstructor(double amount) {
		if (less(amount, 0.0)) {
			throw new IllegalArgumentException("The account is constructed with a negative amount");
		}
	}
	
	// ��飨����/���ã��˻��Ƿ����ȡ��
	public static void checkOverdrawn(double change, double amount) {
		if (greater(change, amount)) {
			throw new IllegalArgumentException("The account is overdrawn");
		}
	}
	
	// ���ȡ�����Ƿ�Ϊ����
	public static void checkNegativeWithdraw(double amount) {
		if (less(amount, 0.0)) { 
			throw new IllegalArgumentException("A negative amount is withdrawn");
		}
	}
}

