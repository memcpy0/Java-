public class ServerApplication
{
	final static String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	
	public static void main(String args[])
	{
		//ΪRMI���ð�ȫ������
		System.setSecurityManager(new java.rmi.RMISecurityManager());
		//����JDBC����
		try{
			Class.forName(JDBC_DRIVER);
		}catch(ClassNotFoundException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
		//������ע���ŷ�����
		try{
			//�����ŷ�����
			Telephone.CallManager callManager = new Telephone.CallManager();
			//������"CallManagerServant001"ע���ŷ�����
			java.rmi.Naming.rebind("//localhost/CallManagerServant001", callManager);
		}catch(java.rmi.RemoteException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}catch(java.net.MalformedURLException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
		System.out.println("Call manager in the server is ready ...");
	}
}