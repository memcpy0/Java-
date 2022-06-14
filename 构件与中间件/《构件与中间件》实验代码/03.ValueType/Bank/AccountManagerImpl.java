package Bank;
import java.util.*;
public class AccountManagerImpl
    extends Bank.AccountManagerPOA
{
  protected Hashtable accountList; // ���ʻ�����Ա��������ʻ��嵥
  public AccountManagerImpl(){
        accountList = new Hashtable();
  }
  public synchronized Bank.Account open(String name){
    Bank.AccountImpl account=
		(Bank.AccountImpl)accountList.get(name);
    if (account == null) {
      Random random = new Random();
      float balance = Math.abs(random.nextInt())%100000/100f;
      AccountImpl accountServant = new AccountImpl(balance);
//      try {
//	org.omg.CORBA.Object obj = 
//         _default_POA().servant_to_reference(accountServant);
//	account = Bank.AccountHelper.narrow(obj);
//      } catch(Exception exc) {
//         exc.printStackTrace();
//      }
      accountList.put(name, accountServant);
      System.out.println("�¿��ʻ���" + name);
      return accountServant;
    }
    return account;
  }
}
