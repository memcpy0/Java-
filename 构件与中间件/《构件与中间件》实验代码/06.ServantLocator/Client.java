import Bank.*;

public class Client
{
	public static void main(String[] args)
	{
		//��ʼ��ORB
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		//����POAȫ��������ʶ"BankManager"�����˻�����Ա
		System.out.println("Using Zhang3 first...");
		AccountManager manager = Bank.AccountManagerHelper.bind(
			orb, "/ServantLocatorPOA", "Zhang3".getBytes());
			
		Account account_ssl = manager.open("1088-ssl");
		System.out.println("1088-ssl�����Ϊ:" + account_ssl.getBalance() + "Ԫ��");
		account_ssl = manager.open("1088-ssl");
		System.out.println("1088-ssl�����Ϊ:" + account_ssl.getBalance() + "Ԫ��");
/*
		System.out.println("Using Li4...");
		AccountManager manager1 = Bank.AccountManagerHelper.bind(
			orb, "/ServantLocatorPOA", "Li4".getBytes());
		account_ssl = manager1.open("1088-ssl");
		System.out.println("1088-ssl�����Ϊ:" + account_ssl.getBalance() + "Ԫ��");

		System.out.println("Sleep 10 seconds...");

		try{
			Thread.currentThread().sleep(10000);
		}catch(Exception exc){
			System.out.println("sleep failed");
			System.exit(1);
		}

		System.out.println("Using Zhang3 again...");
	*/	account_ssl = manager.open("1088-ssl");
		System.out.println("1088-ssl�����Ϊ:" + account_ssl.getBalance() + "Ԫ��");
	}
}
			