import Bank.*;
public class Client
{
  public static void main(String[] args)
  {
    org.omg.CORBA.ORB orb=org.omg.CORBA.ORB.init(args, null);
    // ����POAȫ��������ʶ"BankManager"�����ʻ�����Ա
    Bank.AccountManager manager = 
	Bank.AccountManagerHelper.bind(
      		orb, "/BankPOA", "BankManager".getBytes());
    String name = args.length > 0 ? args[0] : "SHLSong";
    // �����ʻ�����Ա�ҳ�һ��ָ�����ֵ��ʻ����޴��ʻ����¿�һ��
    Bank.Account account = manager.open(name);
    System.out.println(name + "���ʻ����Ϊ" + 
		account.getBalance() + "Ԫ");
    account.deposit(200);
    System.out.println("���200Ԫ�󣬱������Ϊ"+ 
		account.getBalance() + "Ԫ");
    account = manager.open(name);
    System.out.println("�����" + name + "���ʻ����Ϊ" + 
		account.getBalance() + "Ԫ");
  }
}
