package frame;
import javax.swing.*;
import db.StudentDao;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class QueryStudentFrame extends JFrame
			implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("姓  名");
	JTextField tf = new JTextField(15);
	JButton b_qry = new JButton("检索");
	JButton b_all = new JButton("所有人");
	JScrollPane sp = new JScrollPane();

	// 初始化界面
	public QueryStudentFrame() {
		super("检索学生");
		Container c = this.getContentPane();
		c.setLayout(null);

		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p.add(l1);
		p.add(tf);
		p.add(b_qry);
		p.add(b_all);

		c.add(p);
		c.add(sp);

		p.setBounds(0, 0, 600, 35);
		sp.setBounds(5, 40, 580, 320);
		sp.setBorder(BorderFactory.createTitledBorder("学生列表"));

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("imgs/cursor1.gif"); 
		Cursor cu = tk.createCustomCursor(img, new Point(10, 10), "stick");
		this.setCursor(cu);
		this.setBounds(800, 500, 600, 400);
		this.setResizable(false);
		this.setVisible(true);

		// 关闭操作：撤销本窗口，但不退出应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 注册监听器
		b_all.addActionListener(this);
		b_qry.addActionListener(this);
	}

	// 按钮事件处理
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();

		// 所有学生
		if (src == b_all) {
			tf.setText(""); // 清空上一轮的检索关键字
			ResultSet rt = StudentDao.getAllStudents();
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}

		// 按姓名检索
		if (src == b_qry) {
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog
				(this, "查询关键字不可为空", "警告",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			ResultSet rt = StudentDao.getStudent(tf.getText().trim());
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}
	}
}
