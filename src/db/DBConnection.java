package db;
import java.sql.*;

public class DBConnection {
	
	//获取数据库连接
	public static Connection getConn() {
		Connection conn = null;		
		String DRIVERNAME = "com.mysql.jdbc.Driver";
//		String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
//		DRIVERNAME的注释版本用于mysql 8.0
		String DBURL = "jdbc:mysql://localhost:3306/student_information_manager?useUnicode=true&characterEncoding=utf8";
//		String DBURL = "jdbc:mysql://localhost:3306/student_information_manager?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
//		DBURL的注释版本用于mysql 8.0
		// 建立连接
		try {
			// 加载JDBC驱动
			Class.forName(DRIVERNAME);
			// 获取连接
			conn = DriverManager.getConnection(DBURL, "root", "123456");
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
		return conn;
	}
}
