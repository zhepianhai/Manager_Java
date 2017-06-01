package com.zph.ui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.zph.pojo.User;
import com.zph.service.UserService;
import com.zph.service.imp.UserServiceImp;

/**
 * 添加用户
 * */
public class AddUserActivity extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container con;
	private static final int WIDTH=320;
	private static final int HEIGHT=650;
	private JButton ok = new JButton("确定"); 
	
	private JTextField JName,JName1,JName2,JName3;
	
	private UserService userService;
	public AddUserActivity(){
		initView();
		AddListener();
		userService=new UserServiceImp();
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("添加用户");
		this.setVisible(true);  
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);//居中显示
	}
	private void AddListener() {
		ok.addActionListener(this);
		
	}
	/**
	 * 初始化视图
	 * */
	private void initView() {
		// TODO Auto-generated method stub
		con=this.getContentPane();
		con.setLayout(null);
		JPanel jp = new JPanel();  
		JLabel  name = new JLabel("姓名："); 
		JName= new JTextField(15); //明文账号输入
        name.setHorizontalAlignment(SwingConstants.LEFT);  //设置该组件的对齐方式为向右对齐  
        jp.add(name);
        jp.add(JName);
        jp.setBounds(0, 0, 320, 60);
        
        JPanel jp1 = new JPanel();  
		JLabel  name1 = new JLabel("电话："); 
		JName1 = new JTextField(15); //明文账号输入
        name1.setHorizontalAlignment(SwingConstants.LEFT);  //设置该组件的对齐方式为向右对齐  
        jp1.add(name1);
        jp1.add(JName1);
        jp1.setBounds(0, 60, 320, 60);
        
        JPanel jp2 = new JPanel();  
		JLabel  name2 = new JLabel("住址："); 
		JName2 = new JTextField(15); //明文账号输入
        name2.setHorizontalAlignment(SwingConstants.LEFT);  //设置该组件的对齐方式为向右对齐  
        jp2.add(name2);
        jp2.add(JName2);
        jp2.setBounds(0, 120, 320, 60);
        
        JPanel jp3 = new JPanel();  
		JLabel  name3 = new JLabel("补充："); 
		JName3 = new JTextField(15); //明文账号输入
        name3.setHorizontalAlignment(SwingConstants.LEFT);  //设置该组件的对齐方式为向右对齐  
        jp3.add(name3);
        jp3.add(JName3);
        jp3.setBounds(0, 180, 320, 60);
        
        ok.setBounds(0, 250, 200, 48);
        
        con.add(jp);
        con.add(jp1);
        con.add(jp2);
        con.add(jp3);
        con.add(ok);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					
					/*
					 * 想要修改皮肤的话，只需要更改，下面这个函数的参数，具体改成什么样，可以打开，Referenced Libraries -> 
					 * 点击substance.jar -> 找到org.jvnet.substance.skin这个包  -> 
					 * 将下面的SubstanceDustCoffeeLookAndFeel 
					 * 替换成 刚刚打开的包下的任意一个“Substance....LookAndFeel”即可
					 */
					UIManager
//							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
					//例如按照上面的步骤，可以看见一个叫，"SubstanceOfficeBlue2007LookAndFeel.class"，想要替换成这个皮肤，就可以向下面这样写
					//UIManager
					.setLookAndFeel(new org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel());
					// 运行一下，皮肤就可以换了
					// 想要详细了解的同学，可以去百度这个第三方包：substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFrame.setDefaultLookAndFeelDecorated(true);  
		        new AddUserActivity();  
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ok){
			//写入数据库
			User user=new User();
			user.setAddress(JName2.getText());
			user.setUsername(JName.getText());
			user.setOther(JName3.getText());
			user.setPhone(JName1.getText());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			user.setTime(sdf.format(new Date()));
			userService.AddOneUser(user);
			this.dispose();
			
		}
		
	}

}
