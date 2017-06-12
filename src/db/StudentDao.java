package db;

import java.sql.*;

public class StudentDao {
	
	//��֤��½�ʻ��Ƿ�Ϸ�
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

	// ���ѧ��
	public static int insertStudent(String sql, Student aFriend) {
		int result = 0;
		/*
		 * ȡ����
		 * Ԥ�����������IN������ֵ 
		 * ִ�д����insert��� 
		 * ���ؽ��
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

	// ��ѯ����ѧ��
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

	// ������ģ������ѧ��
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

	// ��������ȷ����ѧ��
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
	
	//��ѧ�ż���ѧ��
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

	// ����ѧ���������޸�ѧ�����Ϻ�ɾ��ѧ����Ϣ
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
