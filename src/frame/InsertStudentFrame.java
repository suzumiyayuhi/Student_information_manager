package frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import db.Student;
import db.StudentDao;

public class InsertStudentFrame extends JFrame 
		implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("ID  *");
	JLabel l2 = new JLabel("��    ��");
	JLabel l3 = new JLabel("��    ��");
	JLabel l4 = new JLabel("��    ��");
	JLabel l5 = new JLabel("������");
	JLabel l6 = new JLabel("��    ��");
	JTextField tf1 = new JTextField(15);
	JTextField tf2 = new JTextField(15);
	JTextField tf3 = new JTextField(15);
	JTextField tf4 = new JTextField(15);
	JTextField tf5 = new JTextField(15);
	JTextField tf6 = new JTextField(15);
	JLabel msg1 = new JLabel("  ע�⣺��*�ı��");
	JLabel msg2 = new JLabel();
	JButton btnIns = new JButton("�� ��");
	JButton btnRes = new JButton("�� ��");
	
	// ��ʼ������
	public InsertStudentFrame() {
		super("���ѧ����Ϣ");
		Container c = this.getContentPane();
		c.setLayout(null);

		JPanel p1 = new JPanel(new GridLayout(1, 2));
		JPanel p2 = new JPanel
			(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JPanel p3 = new JPanel
			(new FlowLayout(FlowLayout.CENTER, 5, 15));

		p1.add(msg1);
		p1.add(msg2);

		p2.add(l1);
		p2.add(tf1);
		p2.add(l2);
		p2.add(tf2);
		p2.add(l3);
		p2.add(tf3);
		p2.add(l4);
		p2.add(tf4);
		p2.add(l5);
		p2.add(tf5);
		p2.add(l6);
		p2.add(tf6);

		p3.add(btnIns);
		p3.add(btnRes);
		
		c.add(p1);
		c.add(p2);
		c.add(p3);

		p1.setBounds(0, 0, 360, 30);
		p2.setBounds(0, 30, 240, 204);
		p3.setBounds(240, 30, 100, 150);

		msg2.setForeground(Color.BLUE);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("imgs/cursor1.gif"); 
		Cursor cu = tk.createCustomCursor(img, new Point(10, 10), "stick");
		this.setCursor(cu);
		this.setBounds(350, 200, 360, 250);		
		this.setResizable(false);
		this.setVisible(true);		

		//�޸�Ĭ�Ϲرղ���Ϊ DISPOSE_ON_CLOSE
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// ��ťע�������
		btnIns.addActionListener(this);
		btnRes.addActionListener(this);
	}

	//��ť�¼�����
	public void actionPerformed(ActionEvent arg0) {

		JButton src = (JButton) arg0.getSource();
		
		// ���
		if (src == btnIns) {
			msg2.setText(""); // �����һ����ӵķ�����Ϣ
			/*
			 * ȡѧ����Ϣ����װ��Student���� 
			 * ��Student������뵽���ݿ�
			 */
			Student aFriend = new Student();

			// ID����Ϊ��			
			String id = null;			
			if (tf1.getText().trim().equals("")) {
				JOptionPane.showMessageDialog
				(this, "�� * �Ĳ���Ϊ�գ�", "����",
						JOptionPane.ERROR_MESSAGE);
				return;			
			}else{				
				id =  tf1.getText().trim();
			}
			
			//��ȡ����
			String name = "";
			if (tf2.getText().trim().equals("")){
				JOptionPane.showMessageDialog
				(this, "��������Ϊ��!","����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				name = tf2.getText().trim();
			}
			
			//��ȡ�༶
			String classs = "";
			classs = tf3.getText().trim();

			// �Ա�ֻ���� �С�Ů��M��F ��ʾ
			String sex = "";
			if (!tf4.getText().equals("")
					&& !tf4.getText().matches("[��|Ů|M|F]")) {
				JOptionPane.showMessageDialog(this, "�Ա�����ǣ��л�Ů��M��F������������",
						"����", JOptionPane.ERROR_MESSAGE);
				tf4.setText("");
				return;
			} else {
				sex = tf4.getText();
			}

			// ��ȡ��������
			String birthdate = "";
			birthdate = tf5.getText();

			// �绰������������֣��ҹ� 8~11λ
			String phone = "";
			if (!tf6.getText().equals("")
					&& !tf6.getText().matches("[0-9]{8,11}")) {
				JOptionPane.showMessageDialog(this, "�绰���������8~11λ���֣�����������",
						"����", JOptionPane.ERROR_MESSAGE);
				tf6.setText("");
				return;
			} else {
				phone = tf6.getText();
			}


			// �����û������ѧ����Ϣ���õ�1�������ѧ��ʵ��
			aFriend.setID(id);
			aFriend.setName(name);
			aFriend.setClass(classs);
			aFriend.setSex(sex);
			aFriend.setBirth_date(birthdate);
			aFriend.setPhone(phone);

			// ׼�� insert ���
			//����StudentDao��insertStudent()����ʵ��¼��ѧ����Ϣ
			String sql = "insert into t_stuinfo (ID,Name,Class,Sex,Birth_date,Phone) values(?,?,?,?,?,?)";
			int result = StudentDao.insertStudent(sql, aFriend);
			
			// ����insertStudent()������ִ�н�����ڽ�������ʾ��Ӧ�Ľ����Ϣ
			if (result == 1) {
				msg2.setText("���ѧ����Ϣ�ɹ���");
				tf1.setText("�������ѧ����Ϣ��");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
			} else {
				msg2.setText("���ѧ����Ϣʧ�ܣ�����");
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
			}
		}

		// ����
		if (src == btnRes) {
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");
			tf4.setText("");
			tf5.setText("");
			tf6.setText("");
			msg2.setText("");
		}	
	}
}
