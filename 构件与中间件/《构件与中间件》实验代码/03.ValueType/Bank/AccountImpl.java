package Bank;
public class AccountImpl
    extends Account
{
    // ���Զ���
//    protected float balance;?
    // ���췽������ָ�������µ��ʻ�
    public AccountImpl(float bal){
        balance = bal;
    }
    // ���ʻ��д��
    public void deposit(float amount){
        balance += amount;
    }
    // ���ʻ���ȡ���������򷵻�false
    public boolean withdraw(float amount){
        if (balance < amount)  return false;
else {
            balance -= amount;
            return true;
}
}
// ��ѯ�ʻ����
public float getBalance(){
        return balance;
}
}    
