package bank;

import javax.ejb.*;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import java.util.Random;

public class BankerBean implements SessionBean
{
	DataSource ds;
	Connection conn;
	public void deposit(String accountName, int amount)throws BankerFailureException{
		try{
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM accounts WHERE accountname = '" + accountName + "'");
			if(res.next()){
				int newBalance;
				newBalance = res.getInt("balance") + amount;
				stmt.execute("UPDATE accounts SET balance = " + newBalance + " WHERE accountname = '" + accountName + "'");
				//res.updateInt("balance", newBalance);
			}
			else{
				throw new BankerFailureException("invalid accountName");
			}
			conn.close();
		}catch(Exception e){
				throw new BankerFailureException("invalid accountName");
		}
	}
	public void withdraw(String accountName, int amount) throws BankerFailureException{
		try{
			System.out.println("Entry withdraw");
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM accounts WHERE accountname = '" + accountName + "'");
			//���˻��ϼ�ȥ��Ӧ�Ľ��
			int newBalance;
			if(res.next()){
				if(amount > res.getInt("balance")){
					throw new BankerFailureException("no enough balance");
				}
				newBalance = res.getInt("balance") - amount;
				stmt.execute("UPDATE accounts SET balance = " + newBalance + " WHERE accountname = '" + accountName + "'");
				//res.updateInt("balance", newBalance);
			}
			else{
				throw new BankerFailureException("invalid accountName");
			}
			conn.close();
			System.out.println(accountName + "'s balance changed to " + newBalance);
			System.out.println("pushing cash...");
			//����ȡ���Ϊ�û��³��ֽ�
			pushCash(amount);
			System.out.println("withdraw finished successfully");
		}catch(SQLException e){
			throw new BankerFailureException("operation failed");
		}
	}
	private void pushCash(int amount){
		Random rand = new Random();
		int i =Math.abs(rand.nextInt()); 
		if(i > 1000000000){
			System.out.println("pushCash failed(" + i +")");
			throw new RuntimeException();
		}
	}

	public int getBalance(String accountName) throws BankerFailureException{
		try{
	//		System.out.println("entry getBalance");
		
			conn = ds.getConnection();
	//		System.out.println("after getConnection");
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM accounts WHERE accountname = '" + accountName + "'");
	//		System.out.println("after executeQuery");
			//���˻��ϼ�ȥ��Ӧ�Ľ��
			int curBalance;
			if(res.next()){
				curBalance = res.getInt("balance");
				conn.close();
				return curBalance;
			}
			else{
				throw new BankerFailureException("invalid accountName");
			}
		}catch(Exception e){
			throw new BankerFailureException("operation failed");
		}
	}		
	
	public void ejbCreate() throws CreateException{
		try{
		//	System.out.println("Entry ejbCreate");
			InitialContext initialCtx = new InitialContext();
		//	System.out.println("new InitialContext");
			ds = (DataSource)initialCtx.lookup("java:comp/env/jdbc/BankDB");
		//	System.out.println("lookup");
		}catch(NamingException ex){
			throw new CreateException("lookup datasource failed");
		}catch(Exception e){
			throw new CreateException("operation failed");
		}

	}
	public void ejbRemove() {}
	public void ejbPassivate() {}
	public void ejbActivate() {}
	public void setSessionContext(SessionContext Context) {}
}