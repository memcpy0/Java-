import Bank.*;

public class Client
{
	public static void main(String[] args)
	{
		//��ʼ��ORB
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		String iorString = null;
		try{
			java.io.LineNumberReader reader = new java.io.LineNumberReader(
				new java.io.FileReader("BankIOR.dat"));
			iorString = reader.readLine();
			reader.close();
//			System.out.println("IOR: " + iorString);
		}catch(java.io.FileNotFoundException exc){
			System.out.println("���ļ�ʧ��");
			return;
		}catch(java.io.IOException exc){
			System.out.println("���ļ��ж�ȡIORʱ����" + exc.getMessage());
			return;
		}
		Bank.AccountManager manager = null;
		try{
			manager = Bank.AccountManagerHelper.narrow(
				orb.string_to_object(iorString));
		}catch(Exception exc){
			System.out.println("narrow failed");
			System.exit(1);
		}
		Account card_cxy = manager.open("David Zeng");
		System.out.println("David Zeng�����Ϊ:" + card_cxy.getBalance() + "Ԫ��");
	}
}
			