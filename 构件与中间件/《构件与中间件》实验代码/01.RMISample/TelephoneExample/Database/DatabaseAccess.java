package Database;

import java.sql.*;

public class DatabaseAccess
{
	protected final String DATABASE_NAME = "jdbc:odbc:Telephone";
	
	protected Connection connection;	//Ϊ���ݿ⽨��������
	protected Statement statement;		//��ִ�е�SQL���
	protected CallableStatement callable;	//��ִ�е�SQL�洢����
	
	public DatabaseAccess()
	{
		try{
			//������ָ�����ݿ������
			connection = DriverManager.getConnection(DATABASE_NAME);
			//������ӳɹ������Ƿ��о�����Ϣ
			SQLWarning warn = connection.getWarnings();
			while(warn != null){
				System.out.println(warn.getMessage());
				warn = warn.getNextWarning();
			}
			//����һ������ִ��SQL�����
			statement = connection.createStatement();
			callable = null;
		}catch(SQLException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
	}
	
	public synchronized void finalize()
	{
		try{
			connection.close();
		}catch(SQLException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
	}
	
	public synchronized ResultSet callQuery(String procedure)
		throws SQLException
	{
		callable = connection.prepareCall("{call " + procedure + "}");
		ResultSet rs = callable.executeQuery();
		return rs;
	}
}