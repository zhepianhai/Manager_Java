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

import com.zph.service.LoginService;
import com.zph.service.imp.LoginServiceImp;

public class RegiseActivity extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static final int WIDTH=450;
	private static final int HEIGHT=300;
	JButton login = new JButton("注册");  
    JButton exit = new JButton("退出");  
    JLabel  name = new JLabel("账号：");  
    JLabel password = new JLabel("密码：");   
    JLabel password1 = new JLabel("密码：");   
    JTextField JName = new JTextField(10); //明文账号输入  
    JPasswordField JPassword = new JPasswordField(10); // 非明文密码输入； 
    JPasswordField JPassword1 = new JPasswordField(10); // 非明文密码输入； 
    
    LoginService loginService;
	
	public RegiseActivity(){
		this.setBackground(Color.RED);
    	loginService=new LoginServiceImp();
    	
    	Container con=this.getContentPane();//生成一个容器	
    	con.setLayout(null);
    	JPanel jp = new JPanel();  
        name.setHorizontalAlignment(SwingConstants.LEFT);  //设置该组件的对齐方式为向右对齐  
        password.setHorizontalAlignment(SwingConstants.LEFT);  
        jp.add(name);   //将内容加到面板jp上  
        jp.add(JName);   
        jp.setBounds(0, 20, 450, 60);
        jp.setBackground(null);
        jp.setOpaque(false);
        
        JPanel jp1 = new JPanel();  
        jp1.add(password);  
        jp1.add(JPassword);
        jp1.setBounds(0, 90, 450, 60);
        jp1.setBackground(null);
        jp1.setOpaque(false);
        
        
        JPanel jp3 = new JPanel();  
        jp3.add(password1);  
        jp3.add(JPassword1);
        jp3.setBounds(0, 160, 450, 60);
        jp3.setBackground(null);
        jp3.setOpaque(false);
        
        JPanel jp2 = new JPanel();  
        jp2.add(login);  
        jp2.add(exit);  
        jp2.setBounds(0, 220, 450, 60);
        jp2.setBackground(null);
        jp2.setOpaque(false);
        
     
        JLabel jlpic = new JLabel();
        ImageIcon icon = new ImageIcon("src/icon/ui_icon/icon_logidn.jpg");  
        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),  
                icon.getIconHeight(), Image.SCALE_DEFAULT));  
        jlpic.setBounds(0, 0,450, 300);  
        jlpic.setHorizontalAlignment(0);  
        jlpic.setIcon(icon);
        
        
        login.addActionListener(this); //登录增加事件监听  
        exit.addActionListener(this);   //退出增加事件监听  
          
        con.add(jp);   //将整块面板定义在中间  
        con.add(jp1);   //将整块面板定义在中间  
        con.add(jp3);   //将整块面板定义在中间  
        con.add(jp2);   //将整块面板定义在中间  
        con.add(jlpic);
        this.setSize(WIDTH, HEIGHT);
        Image img = Toolkit.getDefaultToolkit ().getImage("src/icon/ui_icon/icon_app.png");
        this.setIconImage(img);
        
        
        this.setTitle("用户注册");  
//        this.setLocation(500,300);  //设置初始位置  
//        this.pack();        //表示随着面板自动调整大小  
        this.setVisible(true);  
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//居中显示
    	
		
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					UIManager
					.setLookAndFeel(new org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel());
				} catch (Exception e) {
					e.printStackTrace();
				}
				JFrame.setDefaultLookAndFeelDecorated(true);  
		        new RegiseActivity();  
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login){
			if("".equals(JName.getText())){
				JOptionPane.showConfirmDialog(null,"用户名不能为空", "确认", JOptionPane.YES_NO_OPTION);
				return;
			}
			if("".equals(JPassword.getText())){
				JOptionPane.showConfirmDialog(null,"密码不能为空", "确认", JOptionPane.YES_NO_OPTION);
				return;
			}
			if("".equals(JPassword1.getText())){
				JOptionPane.showConfirmDialog(null,"重复密码不能为空", "确认", JOptionPane.YES_NO_OPTION);
				return;
			}
			if(!JPassword.getText().equals(JPassword1.getText())){
				JOptionPane.showConfirmDialog(null,"两次密码不一致", "确认", JOptionPane.YES_NO_OPTION);
				return;
			}
			String message=
			loginService.Reigise(JName.getText(), JPassword.getText());
			
			if("OK".equals(message)){
				 JOptionPane.showMessageDialog(null, "注册成功，请进行登录！");  
	                //显示信息提示框  
	                this.dispose();
	                //write log to localhostDataBase
	                loginService.writeLoginLog("admin",new Date());
	                new LoginActivity();
			}
			else{
				JOptionPane.showConfirmDialog(null,"注册失败！", "确认", JOptionPane.YES_NO_OPTION);
			}
			
			
		}
		else if(e.getSource()==exit){
			 int i = JOptionPane.showConfirmDialog(null,"确认要退出吗？", "确认", JOptionPane.YES_NO_OPTION);  
	            // 显示选择对话框  
	            if(i == JOptionPane.YES_OPTION);  
	            System.exit(0); 
		}
		
	}
}
