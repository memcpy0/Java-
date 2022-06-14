import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StuDao { // DAO���Ǵ������ݿ���ز�������
	private static String sqlStr =  "SELECT * FROM STUDENT WHERE Sno = ?;"; // Sno��ѧ��
	public static Student query(Connection conn, Student stu) throws SQLException {
		// ׼��SQL���
		PreparedStatement stmt = conn.prepareStatement(sqlStr);
		stmt.setString(1, stu.getSno()); // ���?����
		// ִ�в���ȡ�����
		ResultSet rs = stmt.executeQuery();	// ����һ����ά�Ľ����
		Student info = null;
		if (rs.next()) { // ��ѯ����
			info = new Student(rs.getString("Sno"), 
				rs.getString("Sname"),
				rs.getString("Ssex"),
				Integer.valueOf(rs.getString("Sage"))
			);
		}
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		return info;
	}
}
