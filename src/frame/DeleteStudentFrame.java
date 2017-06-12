package frame;
import java.awt.*;
import javax.swing.*;
import db.StudentDao;
import java.awt.event.*;
import java.sql.*;

public class DeleteStudentFrame extends JFrame 
	implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("��  ����", JLabel.CENTER);
	JLabel l2 = new JLabel("ѧ  �ţ�", JLabel.CENTER);
	JTextField tf = new JTextField(15);
	JTextField tf1 = new JTextField(15);
	JScrollPane sp = new JScrollPane();
	JLabel msg = new JLabel("", JLabel.CENTER);
	
	JButton b_qry = new JButton("��    ѯ");
	JButton b_del = new JButton("ɾ    ��");
	
	//��ʼ������
	public DeleteStudentFrame() {
		super("ɾ��ѧ����Ϣ");
		Container c = this.getContentPane();
		c.setLayout(null);

		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 45, 5));
		p.add(l1);
		p.add(tf);
		p.add(b_qry);
		p.add(l2);
		p.add(tf1);
		p.add(b_del);

		sp.setBorder(BorderFactory.
				createTitledBorder("��ɾ����ѧ����Ϣ"));

		msg.setForeground(Color.RED);

		c.add(p);
		c.add(sp);
		c.add(msg);

		p.setBounds(0, 0, 500, 70);
		sp.setBounds(5, 90, 480, 60);
		msg.setBounds(5, 110, 480, 50);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("imgs/cursor1.gif"); 
		Cursor cu = tk.createCustomCursor(img, new Point(10, 10), "stick");
		this.setCursor(cu);
		this.setBounds(350, 200, 480, 380);
		this.setResizable(false);
		this.setVisible(true);

		// �رղ��������������ڣ������˳�Ӧ�ó���
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ע�������
		b_qry.addActionListener(this);
		b_del.addActionListener(this);
	}

	//��ť �¼�����
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();

		// ��ѯ
		if (src == b_qry) {
			//�����һ�ֵķ�����Ϣ
			msg.setText("");
			// ��ȡ��ѯ��ѧ������
			String name = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "��������Ϊ��", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				name = tf.getText().trim();
			}
			// ��ѯ�����µ�ѧ����Ϣ
			ResultSet rt = StudentDao.getStudentByName(name);
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}

		// ɾ��
		if (src == b_del) {
			//��ȡ��ɾ��ѧ����ѧ��
			String number = "";
			if (tf1.getText().trim().equals("")) {
				JOptionPane.showMessageDialog
				(this, "ѧ�Ų���Ϊ��", "����",
				JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				number = tf1.getText().trim();
			}

			//ȷ�϶Ի���
			int option = JOptionPane.
			showConfirmDialog(null, "ȷ��Ҫɾ������ѧ����Ϣ��",
			"ȷ��ɾ��",JOptionPane.OK_CANCEL_OPTION);

			//��ȷ�϶Ի���ѡ���ˡ��ǡ�����ִ��ɾ��������ִ��ɾ��
			if (option == JOptionPane.OK_OPTION) { // ȷ��ɾ��
				String sql = "delete from t_stuinfo " +
						" where ID='" + number + "'";
				int result = StudentDao.updateStudent(sql);
				if (result == 1) {
					msg.setText("ɾ���ɹ���");
					sp.setViewportView(null);					
				} else {
					msg.setText("ɾ��ʧ��");
				}
			} else { // ȡ��ɾ��
				return;
			}
		}
	}
}
