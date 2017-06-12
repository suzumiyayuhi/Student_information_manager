package frame;
import javax.swing.table.*;
import java.sql.*;

public class ResultSetTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	public ResultSetTableModel(ResultSet rs) {
		this.rs = rs;
		try {
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getRowCount() {
		int rowCount = 0;
		try {
			rs.last();
			rowCount = rs.getRow();
			return rowCount;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	public int getColumnCount() {
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}

	}

	public Object getValueAt(int row, int column) {
		try {
			rs.absolute(++row);
			return rs.getObject(++column);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public String getColumnName(int column) {
		try {
			return rsmd.getColumnName(++column);
		} catch (SQLException e) {
			return String.valueOf(++column);
		}
	}
}