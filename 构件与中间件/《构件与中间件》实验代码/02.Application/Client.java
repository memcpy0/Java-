public class Client
{
	public static void main(String[] args)
	{
		//��ʼ��ORB
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		//����POAȫ��������ʶ"BankManager"�����˻�����Ա
		Bank.AccountManager manager = Bank.AccountManagerHelper.bind(
			orb, "/BankPOA", "BankManager".getBytes());//,"192.168.118.3", new com.inprise.vbroker.CORBA.BindOptions());
		String name = args.length > 0? args[0]:"SHLSong";
		Bank.Account account = manager.open(name);
		
		System.out.println(name + "���˻����Ϊ" + account.getBalance() + "Ԫ");
		account.deposit(400);
		System.out.println("���400Ԫ�����Ϊ" + account.getBalance() + "Ԫ");
		if(account.withdraw(500)){
			System.out.println("ȡ��500Ԫ�����Ϊ" + account.getBalance() + "Ԫ");
		}else{
			System.out.println("����500Ԫ��ȡ��ʧ�ܣ����ֲ���");
		}
	}
}
			