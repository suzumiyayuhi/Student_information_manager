package frame;
import javax.swing.*;
import db.StudentDao;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

class ModifyStudentFrame extends JFrame 
		implements ActionListener {	

	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("学   号：", JLabel.CENTER);
	JLabel l2 = new JLabel("更新姓名  ", JLabel.RIGHT);
	JLabel l3 = new JLabel("更新班级  ", JLabel.RIGHT);
	JLabel l4 = new JLabel("更新性别  ", JLabel.RIGHT);
	JLabel l5 = new JLabel("更新生日  ", JLabel.RIGHT);
	JLabel l6 = new JLabel("更新电话  ", JLabel.RIGHT);
	JTextField tf = new JTextField(15);
	JTextField tf_name = new JTextField(15);
	JTextField tf_classs = new JTextField(15);
	JTextField tf_sex = new JTextField(15);
	JTextField tf_birthdate = new JTextField(15);
	JTextField tf_phone = new JTextField(15);
	JScrollPane sp = new JScrollPane();
	JButton b_qry = new JButton("查    询");
	JButton b_upd = new JButton("更    新");
	JButton b_res = new JButton("重    置");

	//初始化界面
	ModifyStudentFrame() {
		super("更新学生信息");
		Container c = this.getContentPane();
		c.setLayout(null);
		
		JPanel p1 = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 6));
		p1.add(l1);
		p1.add(tf);
		p1.add(b_qry);

		JPanel p2 = new JPanel(new GridLayout(5, 2, 10, 3));
		p2.add(l2);
		p2.add(tf_name);
		p2.add(l3);
		p2.add(tf_classs);
		p2.add(l4);
		p2.add(tf_sex);
		p2.add(l5);
		p2.add(tf_birthdate);
		p2.add(l6);
		p2.add(tf_phone);

		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
		p3.add(b_upd);
		p3.add(b_res);
		
		sp.setBorder(BorderFactory.createTitledBorder("欲更新的学生信息"));
		
		c.add(p1);
		c.add(p2);
		c.add(sp);
		c.add(p3);

		p1.setBounds(0, 0, 500, 42);
		sp.setBounds(5, 40, 480, 72);
		p2.setBounds(50, 110, 300, 130);
		p3.setBounds(150, 230, 200, 48);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("imgs/cursor1.gif"); 
		Cursor cu = tk.createCustomCursor(img, new Point(10, 10), "stick");
		this.setCursor(cu);
		this.setBounds(800, 500, 500, 470);
		this.setResizable(false);
		this.setVisible(true);

		// 关闭操作：撤销本窗口，但不退出应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 注册监听器
		b_qry.addActionListener(this);
		b_upd.addActionListener(this);

		// 重置按钮事件处理
		b_res.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.setText("");
				tf_name.setText("");
				tf_classs.setText("");
				tf_sex.setText("");
				tf_birthdate.setText("");
				tf_phone.setText("");
				sp.setViewportView(null);
			}
		});

	}
	
	//查询 、更新按钮 事件处理
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();

		// 查询
		if (src == b_qry) {
			// 清空上一轮更新文本框中的内容
			tf_name.setText("");
			tf_classs.setText("");
			tf_sex.setText("");
			tf_birthdate.setText("");
			tf_phone.setText("");

			// 获取查询的学生学号
			String number = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "学号不可为空", "警告",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				number = tf.getText().trim();
			}

			// 查询欲更新的学生信息
			ResultSet rt = StudentDao.getStudentByNumber(number);
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}

		// 更新
		if (src == b_upd) {
			String number = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "学号不可为空", "警告",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				number = tf.getText().trim();
			}
			String sql = "";
			String newName = "";
			String newClasss = "";
			String newSex = "";
			String newBirthdate = "";
			String newPhone = "";

			//若姓名非空，则更新姓名
			if(!tf_name.getText().equals("")){
				newName=tf_name.getText();
				sql = "update t_stuinfo set Name='" + newName
						+ "' where ID='" + number + "'";
				StudentDao.updateStudent(sql);
			}
			
			//若班级非空，则更新班级
			if(!tf_classs.getText().equals("")){
				newClasss=tf_classs.getText();
				sql = "update t_stuinfo set Class='" + newClasss
						+ "' where ID='" + number + "'";
				StudentDao.updateStudent(sql);
			}
			
			// 若性别非空，则更新性别
			if (!tf_sex.getText().equals("")) {
				if (!tf_sex.getText().matches("[男|女|M|F]")) {
					JOptionPane.showMessageDialog
					(this, "性别必须是：男或女，M或F，请重新输入",
							"警告", JOptionPane.ERROR_MESSAGE);
					tf_sex.setText("");
				} else {
					newSex = tf_sex.getText();
					sql = "update t_stuinfo set sex='" + newSex
							+ "' where ID='" + number + "'";
					StudentDao.updateStudent(sql);
				}
			}

			// 若出生日期非空，则更新出生日期
			if (!tf_birthdate.getText().equals("")) {
				newBirthdate = tf_birthdate.getText();
				sql = "update t_stuinfo set Birth_date='" + newBirthdate 
						+ "' where ID='"+ number + "'";
				StudentDao.updateStudent(sql);
			}

			// 若电话非空，则更新电话
			if (!tf_phone.getText().equals("")) {

				if (!tf_phone.getText().matches("[0-9]{8,11}")) {
					JOptionPane.showMessageDialog
					(this, "电话号码必须是8~11位数字，请重新输入",
							"警告", JOptionPane.ERROR_MESSAGE);
					tf_phone.setText("");

				} else {
					newPhone = tf_phone.getText();
					sql = "update t_stuinfo set Phone='" + newPhone
							+ "' where ID='" + number + "'";
					StudentDao.updateStudent(sql);
				}
			}

			// 更新结束后，重新检索该学生，显示更新后的信息
			ResultSet rt = StudentDao.getStudentByNumber(number);
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
			
			tf_name.setText("");
			tf_classs.setText("");
			tf_sex.setText("");
			tf_birthdate.setText("");
			tf_phone.setText("");
		}
	}
}
