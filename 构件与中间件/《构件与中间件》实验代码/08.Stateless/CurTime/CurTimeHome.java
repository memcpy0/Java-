//The code is used to create the home interface of an enterprise bean
//used by the CurTimeApp application
import javax.ejb.*;
public interface CurTimeHome extends EJBHome
{
	//��״̬��session beanֻ�ܰ����޲�����create
	//public CurTime create(int i) throws java.rmi.RemoteException, javax.ejb.CreateException;//, UserException2;
	public CurTime create() throws java.rmi.RemoteException, javax.ejb.CreateException;//, UserException2;
  //public void remove() throws java.rmi.RemoteException, javax.ejb.RemoveException;
}
	