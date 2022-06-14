//The code is used to create the enterprise bean class
//used by the HelloApp application
import javax.ejb.*;
public class HelloBean implements SessionBean
{
	//int ii;
	//�Զ����constructor û��ʲô����
	public void HelloBean(int i){
		System.out.println("HelloBean constructor");
	}
	//��״̬��session bean������finalize������
	/*
	public void finalize() throws Throwable{
		System.out.println("HelloBean deconstructor");
	}
	*/
	SessionContext Context;
	//��״̬��session beanֻ�ܰ����޲�����create
	public void ejbCreate(){
		//ii = i;
		System.out.println("HelloBean ejbCreate");
	}// throws CreateException, UserException2{}
	public String sayHello(String MyName)
	{
		//System.out.println("ii = " + ii);
		return "Hello," + MyName;
	}
	public void ejbRemove(){
		System.out.println("HelloBean ejbRemove");
	}
	public void ejbPassivate(){}
	public void ejbActivate(){}
	public void setSessionContext(SessionContext Context)
	{
		this.Context = Context;
	}
}