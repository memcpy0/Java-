import java.util.*;
import org.omg.PortableServer.*;

public class AccountManagerImpl
	extends Bank.AccountManagerPOA
{
	protected Hashtable accountList;
	
	public AccountManagerImpl()
	{
		accountList = new Hashtable();
	}
	
	public synchronized Bank.Account open(String name)
	{
		Bank.Account account = (Bank.Account)accountList.get(name);
		
		if(account == null){
			//�漴��ʼ��һ��0��1000֮��Ľ��
			
			float balance = 0;
			AccountImpl accountServant = new AccountImpl(balance);
			try{
				//��ȱʡ��POA�����������ȱʡ��POA���Ǹ�POA
				org.omg.CORBA.Object obj = 
					_default_POA().servant_to_reference(accountServant);
				//����������խ��ΪAccount����
				account = Bank.AccountHelper.narrow(obj);
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			accountList.put(name, account);
			System.out.println("�¿��˻���" + name);
		}
		
		return account;
	}
}			