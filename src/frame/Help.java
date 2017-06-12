package frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Help extends JFrame{
	
	public Help(){
		String date = new String("2017/6/12");
		String version = new String("1.0");
		JOptionPane.showMessageDialog(null, "发布日期:"+date+'\n'+"版本号:"+version);
	}
}
