package com.zph.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class MenuActivity extends JFrame implements ActionListener{
	//设置全屏显示
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private JMenu jm=new JMenu("工作台1") ;
	private JMenu jm2=new JMenu("工作台2") ;
	private JMenuItem t1=new JMenuItem("添加用户") ;  //菜单项
	private JMenuItem t2=new JMenuItem("查找用户") ;//菜单项
	private JTabbedPane  jp=new JTabbedPane(JTabbedPane.LEFT) ; 
	public MenuActivity(String title){
		
		t1.addActionListener(this);
		t2.addActionListener(this);
		jm.add(t1) ;   //将菜单项目添加到菜单
		jm.add(t2) ;    //将菜单项目添加到菜单
		JMenuBar  br=new  JMenuBar() ;  //创建菜单工具栏
		br.add(jm) ;      //将菜单增加到菜单工具栏
		br.add(jm2) ;      //将菜单增加到菜单工具栏
		this.setJMenuBar(br) ;  //为 窗体设置  菜单工具栏
		
		initDate();
		
		this.setSize(d);
		this.setTitle(title);  
		this.setVisible(true);  
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);//居中显示
	}
	
	/**
	 * 初始化数据
	 * */
	private void initDate() {
		
		
	}


	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					
					/*
					 * 想要修改皮肤的话，只需要更改，下面这个函数的参数，具体改成什么样，可以打开，Referenced Libraries -> 点击substance.jar -> 找到org.jvnet.substance.skin这个包  -> 将下面的SubstanceDustCoffeeLookAndFeel 替换成 刚刚打开的包下的任意一个“Substance....LookAndFeel”即可
					 */
					UIManager
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
					//例如按照上面的步骤，可以看见一个叫，"SubstanceOfficeBlue2007LookAndFeel.class"，想要替换成这个皮肤，就可以向下面这样写
					//UIManager
//					.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// 运行一下，皮肤就可以换了
					// 想要详细了解的同学，可以去百度这个第三方包：substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFrame.setDefaultLookAndFeelDecorated(true);  
				new MenuActivity(args[0]);
			}
		});
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==t1){
			//打开一个添加用户的界面窗口
			new AddUserActivity();
		}
		else if(e.getSource()==t2){
			
		}
		
	}

}
