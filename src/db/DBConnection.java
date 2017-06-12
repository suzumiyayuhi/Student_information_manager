package db;
import java.sql.*;

public class DBConnection {
	
	//��ȡ���ݿ�����
	public static Connection getConn() {
		Connection conn = null;		
		String DRIVERNAME = "com.mysql.jdbc.Driver";		
		String DBURL = "jdbc:mysql://localhost:3306/student_information_manager" +
				"?useUnicode=true&characterEncoding=utf8";
		// ��������
		try {
			// ����JDBC����
			Class.forName(DRIVERNAME);
			// ��ȡ����
			conn = DriverManager.getConnection(DBURL, "root", "1996");			
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
		return conn;
	}
}
