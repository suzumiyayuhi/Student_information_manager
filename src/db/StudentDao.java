package db;

import java.sql.*;

public class StudentDao {
	
	//验证登陆帐户是否合法
	public static boolean checkUser(String name, String pw) {
		boolean flag = false;
		String sql = "select * from t_administrator where ID=? and Password=?";
		Connection conn = DBConnection.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	// 添加学生
	public static int insertStudent(String sql, Student aFriend) {
		int result = 0;
		/*
		 * 取连接
		 * 预处理语句对象各IN参数赋值 
		 * 执行传入的insert语句 
		 * 返回结果
		 */
		Connection conn = DBConnection.getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aFriend.getID());
			pstmt.setString(2, aFriend.getName());
			pstmt.setString(3, aFriend.getClasss());
			pstmt.setString(4, aFriend.getSex());
			pstmt.setString(5, aFriend.getBirth_date());
			pstmt.setString(6, aFriend.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 查询所有学生
	public static ResultSet getAllStudents() {
		ResultSet rt = null;
		Connection conn = DBConnection.getConn();
		Statement stmt = null;
		String sql = "select * from t_stuinfo";
		try {
			stmt = conn.createStatement
			(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			rt = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rt;
	}

	// 按姓名模糊检索学生
	public static ResultSet getStudent(String name) {
		ResultSet rt = null;
		Connection conn = DBConnection.getConn();
		Statement stmt = null;
		String sql = "select * from t_stuinfo " +
				"where Name LIKE '%" + name + "%'";
		try {
			stmt = conn.createStatement
			(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			rt = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rt;
	}

	// 按姓名精确检索学生
	public static ResultSet getStudentByName(String name) {
		ResultSet rt = null;
		Connection conn = DBConnection.getConn();
		Statement stmt = null;
		String sql = "select * from t_stuinfo " +
				"where Name ='" + name + "'";
		try {
			stmt = conn.createStatement
			(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			rt = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rt;
	}
	
	//按学号检索学生
	public static ResultSet getStudentByNumber (String number) {
		ResultSet rt = null;
		Connection conn = DBConnection.getConn();
		Statement stmt = null;
		String sql = "select * from t_stuinfo " +
				"where ID ='" + number + "'";
		try {
			stmt = conn.createStatement
			(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			rt = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rt;
	}

	// 更新学生：包括修改学生资料和删除学生信息
	public static int updateStudent(String sql) {
		int result = 0;
		Connection conn = DBConnection.getConn();
		Statement stmt = null;
		try {
			stmt = conn.createStatement
			(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
