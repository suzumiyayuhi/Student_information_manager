package frame;
import javax.swing.*;
import db.StudentDao;
import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;

public class Login extends JFrame 
			implements ActionListener {
	private static final long serialVersionUID = 1L;

	JLabel lName = new JLabel("�˺ţ�");
	JLabel lPw = new JLabel("���룺");
	JTextField tName = new JTextField(15);
	JPasswordField tPw = new JPasswordField(15);
	JButton btnOk = new JButton("��  ½");
	JButton btnExit = new JButton("��  ��");

	//��ʼ������
	public Login() {
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 8));

		c.add(lName);
		c.add(tName);
		c.add(lPw);
		c.add(tPw);
		c.add(btnOk);
		c.add(btnExit);

		// 2����ťע�������
		btnOk.addActionListener(this);
		btnExit.addActionListener(this);

		this.setTitle("ѧ����Ϣ����ϵͳ����");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("imgs/cursor1.gif"); 
		Cursor cu = tk.createCustomCursor(img, new Point(10, 10), "stick");
		this.setCursor(cu);
		this.setBounds(800, 500, 480, 130);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		Color bk_color=new Color(255,121,121);
		this.getContentPane().setBackground(Color.pink);
	}
	
	
	//��ť�¼�����
	public void actionPerformed(ActionEvent e) {
		//�˳���ť
		if (e.getSource() == btnExit) {
			System.exit(0);
		}
		//��½��ť
		if (e.getSource() == btnOk) {
			// �Ȼ�ȡ�û�������ʻ���������
			String name = tName.getText();
			char[] pwTmp = tPw.getPassword();
			String pw = new String(pwTmp);
			
			boolean flag = StudentDao.checkUser(name, pw);

			if (flag == true) {				
				this.dispose();
				new MainFrame();	//��֤�ɹ�������������
			} else {
				JOptionPane.showMessageDialog(this, 
						"���ź����˺Ż��������", "������ʾ",
						JOptionPane.ERROR_MESSAGE);
			}
			// ����û�֮ǰ������
			tPw.setText("");
			tName.grabFocus();
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}
