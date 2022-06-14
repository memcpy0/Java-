package Telephone;

public class CallManager
	extends java.rmi.server.UnicastRemoteObject
	implements CallManagerInterface
{
	protected Database.DatabaseAccess database;
	
	//ȱʡ���췽���������׳�RemoteException
	public CallManager()
		throws java.rmi.RemoteException
	{
		database = new Database.DatabaseAccess();
	}
	
	//Ϊ�˷�ֹ����û������ĵ������ݿ��ѯ����������Ϊͬ��������
	public synchronized Database.DatabaseTableModel getCallHistory(String subscriber)
		throws java.rmi.RemoteException
	{
		String sql = "";
		Database.DatabaseTableModel table = null;
		System.out.println("Respond to client request: " + subscriber);
		try{
			sql = "QueryCallHistoryWithSubscriber('" + subscriber + "')";
			java.sql.ResultSet rs = database.callQuery(sql);
			table = new Database.DatabaseTableModel(rs);
			rs.close();
		}catch(java.sql.SQLException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
		return table;
	}
}