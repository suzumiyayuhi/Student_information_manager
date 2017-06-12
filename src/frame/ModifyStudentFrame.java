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
	JLabel l1 = new JLabel("ѧ   �ţ�", JLabel.CENTER);
	JLabel l2 = new JLabel("��������  ", JLabel.RIGHT);
	JLabel l3 = new JLabel("���°༶  ", JLabel.RIGHT);
	JLabel l4 = new JLabel("�����Ա�  ", JLabel.RIGHT);
	JLabel l5 = new JLabel("��������  ", JLabel.RIGHT);
	JLabel l6 = new JLabel("���µ绰  ", JLabel.RIGHT);
	JTextField tf = new JTextField(15);
	JTextField tf_name = new JTextField(15);
	JTextField tf_classs = new JTextField(15);
	JTextField tf_sex = new JTextField(15);
	JTextField tf_birthdate = new JTextField(15);
	JTextField tf_phone = new JTextField(15);
	JScrollPane sp = new JScrollPane();
	JButton b_qry = new JButton("��    ѯ");
	JButton b_upd = new JButton("��    ��");
	JButton b_res = new JButton("��    ��");

	//��ʼ������
	ModifyStudentFrame() {
		super("����ѧ����Ϣ");
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
		
		sp.setBorder(BorderFactory.createTitledBorder("�����µ�ѧ����Ϣ"));
		
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

		// �رղ��������������ڣ������˳�Ӧ�ó���
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ע�������
		b_qry.addActionListener(this);
		b_upd.addActionListener(this);

		// ���ð�ť�¼�����
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
	
	//��ѯ �����°�ť �¼�����
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();

		// ��ѯ
		if (src == b_qry) {
			// �����һ�ָ����ı����е�����
			tf_name.setText("");
			tf_classs.setText("");
			tf_sex.setText("");
			tf_birthdate.setText("");
			tf_phone.setText("");

			// ��ȡ��ѯ��ѧ��ѧ��
			String number = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "ѧ�Ų���Ϊ��", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				number = tf.getText().trim();
			}

			// ��ѯ�����µ�ѧ����Ϣ
			ResultSet rt = StudentDao.getStudentByNumber(number);
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}

		// ����
		if (src == b_upd) {
			String number = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "ѧ�Ų���Ϊ��", "����",
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

			//�������ǿգ����������
			if(!tf_name.getText().equals("")){
				newName=tf_name.getText();
				sql = "update t_stuinfo set Name='" + newName
						+ "' where ID='" + number + "'";
				StudentDao.updateStudent(sql);
			}
			
			//���༶�ǿգ�����°༶
			if(!tf_classs.getText().equals("")){
				newClasss=tf_classs.getText();
				sql = "update t_stuinfo set Class='" + newClasss
						+ "' where ID='" + number + "'";
				StudentDao.updateStudent(sql);
			}
			
			// ���Ա�ǿգ�������Ա�
			if (!tf_sex.getText().equals("")) {
				if (!tf_sex.getText().matches("[��|Ů|M|F]")) {
					JOptionPane.showMessageDialog
					(this, "�Ա�����ǣ��л�Ů��M��F������������",
							"����", JOptionPane.ERROR_MESSAGE);
					tf_sex.setText("");
				} else {
					newSex = tf_sex.getText();
					sql = "update t_stuinfo set sex='" + newSex
							+ "' where ID='" + number + "'";
					StudentDao.updateStudent(sql);
				}
			}

			// ���������ڷǿգ�����³�������
			if (!tf_birthdate.getText().equals("")) {
				newBirthdate = tf_birthdate.getText();
				sql = "update t_stuinfo set Birth_date='" + newBirthdate 
						+ "' where ID='"+ number + "'";
				StudentDao.updateStudent(sql);
			}

			// ���绰�ǿգ�����µ绰
			if (!tf_phone.getText().equals("")) {

				if (!tf_phone.getText().matches("[0-9]{8,11}")) {
					JOptionPane.showMessageDialog
					(this, "�绰���������8~11λ���֣�����������",
							"����", JOptionPane.ERROR_MESSAGE);
					tf_phone.setText("");

				} else {
					newPhone = tf_phone.getText();
					sql = "update t_stuinfo set Phone='" + newPhone
							+ "' where ID='" + number + "'";
					StudentDao.updateStudent(sql);
				}
			}

			// ���½��������¼�����ѧ������ʾ���º����Ϣ
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
