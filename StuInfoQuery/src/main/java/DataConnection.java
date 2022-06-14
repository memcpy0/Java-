import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DataConnection {
	// JDBC������
	private String driver;
	// ���ݿ�·��
	private String url;
	// ���ݿ��û���
	private String username;
	// ���ݿ�����
	private String password;

	// ���캯��
	public DataConnection(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	// ��ȡ���ݿ������
	public Connection getConnection() {
		Connection conn = null;
		try {
			// �������JDBC��Driver��
			Class.forName(driver);
			// ͨ��DriverManager��ȡConnection����
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.print("ClassNotFoundException: " + e);
		} catch (SQLException e) {
			System.out.print("SQLException: " + e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// �ر����ݿ������
	public void closeConnection(Connection conn) throws SQLException {
		if (conn != null) conn.close();
	}
	// ��������
	public static void main(String[] args) {
		DataConnection dc = new DataConnection("com.mysql.cj.jdbc.Driver", 
				"jdbc:mysql://localhost:3306/stu_info?useSSL=false&useUnicode=true&characterEncoding=utf-8",
				"root", 
				"wdmysqlmm123");
		try {
			if (dc.getConnection() != null) 
				System.out.println("���ݿ����ӳɹ�!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��!");
		}
	}
}
