//The code is used to create the home interface of an enterprise bean
//used by the HelloApp application
import javax.ejb.*;
public interface HelloHome extends EJBHome
{
	//��״̬��session beanֻ�ܰ����޲�����create
	//public Hello create(int i) throws java.rmi.RemoteException, javax.ejb.CreateException;//, UserException2;
	public Hello create() throws java.rmi.RemoteException, javax.ejb.CreateException;//, UserException2;
}
	