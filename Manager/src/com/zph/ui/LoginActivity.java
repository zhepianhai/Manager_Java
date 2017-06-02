package com.zph.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.zph.service.LoginService;
import com.zph.service.imp.LoginServiceImp;

public class LoginActivity extends JFrame implements ActionListener,
		HyperlinkListener {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 290;
	JButton login = new JButton("登录");
	JButton exit = new JButton("退出");
	JButton regise = new JButton("注册");
	JLabel name = new JLabel("账号：");
	JLabel password = new JLabel("密码：");
	JTextField JName = new JTextField(10); // 明文账号输入
	JPasswordField JPassword = new JPasswordField(10); // 非明文密码输入；

	LoginService loginService;
	private Container con;
	public LoginActivity() {
		this.setBackground(Color.RED);
		loginService = new LoginServiceImp();
		
		con= this.getContentPane();// 生成一个容器
		con.setLayout(null);
		JPanel jp = new JPanel();
		name.setHorizontalAlignment(SwingConstants.LEFT); // 设置该组件的对齐方式为向右对齐
		password.setHorizontalAlignment(SwingConstants.LEFT);
		jp.add(name); // 将内容加到面板jp上
		jp.add(JName);
		jp.setBounds(0, 20, 400, 60);
		jp.setBackground(null);
		jp.setOpaque(false);

		JPanel jp1 = new JPanel();
		jp1.add(password);
		jp1.add(JPassword);
		jp1.setBounds(0, 90, 400, 60);
		jp1.setBackground(null);
		jp1.setOpaque(false);

		JPanel jp2 = new JPanel();
		jp2.add(login);
		jp2.add(exit);
		jp2.setBounds(0, 160, 400, 60);
		jp2.setBackground(null);
		jp2.setOpaque(false);

		JPanel jp3 = new JPanel();
		jp3.add(regise);
		jp3.setBounds(0, 220, 400, 60);
		jp3.setBackground(null);
		jp3.setOpaque(false);

		JLabel jlpic = new JLabel();
		ImageIcon icon = new ImageIcon("src/icon/ui_icon/icon_logidn.jpg");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));
		jlpic.setBounds(0, 0, 400, 290);
		jlpic.setHorizontalAlignment(0);
		jlpic.setIcon(icon);

		login.addActionListener(this); // 登录增加事件监听
		exit.addActionListener(this); // 退出增加事件监听
		regise.addActionListener(this); // 退出增加事件监听
			
		con.add(jp); // 将整块面板定义在中间
		con.add(jp1); // 将整块面板定义在中间
		con.add(jp2); // 将整块面板定义在中间
		con.add(jp3);
		con.add(jlpic);
		
		this.setSize(WIDTH, HEIGHT);
		Image img = Toolkit.getDefaultToolkit().getImage(
				"src/icon/ui_icon/icon_app.png");
		this.setIconImage(img);

		this.setTitle("用户登录");
		// this.setLocation(500,300); //设置初始位置
		// this.pack(); //表示随着面板自动调整大小
		this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);// 居中显示

	}

	/**
	 * 按钮的监听事件
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			int i = JOptionPane.showConfirmDialog(null, "确认要退出吗？", "确认",
					JOptionPane.YES_NO_OPTION);
			// 显示选择对话框
			if (i == JOptionPane.YES_OPTION)
				;
			System.exit(0);
		} else if (e.getSource() == login) {

			if ("".equals(JName.getText())) {
				JOptionPane.showConfirmDialog(null, "用户名不能为空", "确认",
						JOptionPane.YES_NO_OPTION);
				return;
			}
			//w3TxnlUyxm25UKsNCH+6ifhdklcSwtStRbbxxjkRqnonstcHgV7oOeq/Y31CzObt
			if ("".equals(JPassword.getText())) {
				JOptionPane.showConfirmDialog(null, "密码不能为空", "确认",
						JOptionPane.YES_NO_OPTION);
				return;
			}
			String message = loginService.checkLogin(JName.getText(),
					JPassword.getText());
			if ("OK".equals(message)) {
				JOptionPane.showMessageDialog(null, "登录成功，欢迎到来！");
				// 显示信息提示框
				this.dispose();
				// write log to localhostDataBase
				loginService.writeLoginLog(JName.getText(), new Date());
				new MenuActivity(JName.getText());
			} else {
				JOptionPane.showMessageDialog(null, "用户或密码错误！请从新登录！");
				// 显示信息提示框
				JName.setText("");
				JPassword.setText("");
			}
		} else if (e.getSource() == regise) {
			this.dispose();
			new RegiseActivity();
		}

	}

	public static void main(String[] args) {

		/*
		 * 使用默认皮肤开启浏览器
		 */
		/*
		 * 下面几句，你们可能看不太懂，这里，其实暂时不懂也没关系，这其实是因为swing不是线程安全的！为了线程安全而采取了以下的措施，去掉的话，可能会报错
		 * 。
		 */

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {

					/*
					 * 想要修改皮肤的话，只需要更改，下面这个函数的参数，具体改成什么样，可以打开，Referenced
					 * Libraries -> 点击substance.jar ->
					 * 找到org.jvnet.substance.skin这个包 ->
					 * 将下面的SubstanceDustCoffeeLookAndFeel 替换成
					 * 刚刚打开的包下的任意一个“Substance....LookAndFeel”即可
					 */
					UIManager
					// .setLookAndFeel(new
					// org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel());
					// 例如按照上面的步骤，可以看见一个叫，"SubstanceOfficeBlue2007LookAndFeel.class"，想要替换成这个皮肤，就可以向下面这样写
					// UIManager
					// .setLookAndFeel(new
					// org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// 运行一下，皮肤就可以换了
					// 想要详细了解的同学，可以去百度这个第三方包：substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFrame.setDefaultLookAndFeelDecorated(true);
				new LoginActivity();
			}
		});

	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO Auto-generated method stub
		System.out.println("TAG");
	}

}
