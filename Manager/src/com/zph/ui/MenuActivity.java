package com.zph.ui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.zph.pojo.User;
import com.zph.service.UserService;
import com.zph.service.imp.UserServiceImp;
import com.zph.util.FileUtil;
import com.zph.util.ListUtils;

public class MenuActivity extends JFrame implements ActionListener {
	private Container con;
	// 设置全屏显示
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private JMenu jm = new JMenu("工作台1");
	private JMenu jm2 = new JMenu("工作台2");
	private JMenuItem t1 = new JMenuItem("添加用户"); // 菜单项
	private JMenuItem t2 = new JMenuItem("查找用户");// 菜单项
	private JTabbedPane jp = new JTabbedPane(JTabbedPane.LEFT);

	// 最小化到系统托盘
	private TrayIcon trayIcon;//  托盘图标  
	private SystemTray systemTray;//  系统托盘

	//列表
	private JTable jLabel;
	private String[] headTitle={"姓名","电话号码","地址","补充说明","录入时间"};
	
	
	
	private UserService userService;
	public MenuActivity(String title) {
		con=this.getContentPane();
		
		t1.addActionListener(this);
		t2.addActionListener(this);
		jm.add(t1); // 将菜单项目添加到菜单
		jm.add(t2); // 将菜单项目添加到菜单
		JMenuBar br = new JMenuBar(); // 创建菜单工具栏
		br.add(jm); // 将菜单增加到菜单工具栏
		br.add(jm2); // 将菜单增加到菜单工具栏
		this.setJMenuBar(br); // 为 窗体设置 菜单工具栏
		userService=new UserServiceImp();
		initDate();
//		initSystemTray();
		this.setSize(d);
		this.setTitle(title);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);// 居中显示
		Image img = Toolkit.getDefaultToolkit().getImage(
				"src/icon/ui_icon/icon_app.png");
		this.setIconImage(img);
	}

	/**
	 * 设置系统托盘最小化
	 * */
	private void initSystemTray() {
		SystemTray.isSupported(); // 判断当前平台是否支持系统托盘
		systemTray = SystemTray.getSystemTray();// 获得系统托盘的实例
		setSize(150, 150);
		PopupMenu popupMenu = new PopupMenu();
		MenuItem restoreItem = new MenuItem("还原");
		MenuItem exitItem = new MenuItem("退出");
		restoreItem.addActionListener(new RestoreItemActionListener());
		exitItem.addActionListener(new ExitItemActionListener());
		popupMenu.add(restoreItem);
		popupMenu.addSeparator();
		popupMenu.add(exitItem);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		try {
			trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(
					"src/icon/ui_icon/icon_app.png"));
			trayIcon.addMouseListener(new TrayIconMouseListener());
			systemTray.add(trayIcon);// 设置托盘的图标，0.gif与该类文件同一目录
		} catch (AWTException e2) {
			e2.printStackTrace();
		}

		this.addWindowListener(new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
				dispose();// 窗口最小化时dispose该窗口
			}
		});

		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1)// 双击托盘窗口再现
					setExtendedState(Frame.NORMAL);
				setVisible(true);
			}
		});
		trayIcon.displayMessage("通知：", "用户管理软件最小化到系统托盘",
				TrayIcon.MessageType.INFO);
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

					UIManager
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel());
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

		if (e.getSource() == t1) {
			// 打开一个添加用户的界面窗口
			new AddUserActivity();
		} else if (e.getSource() == t2) {
			List<User> list=userService.SelectAllUser();
			//展示数据
			showMyUser(list);
			
		}

	}

	private class RestoreItemActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			con.setVisible(true);
		}
	}

	private class ExitItemActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			System.exit(0);
		}
	}

	private class TrayIconMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent me) {
			if (me.getButton() == MouseEvent.BUTTON1) {
				con.setVisible(true);
			}
		}
	}
	
	/**
	 * 以列表的形式展现出我的用户
	 * */
	private void showMyUser(List<User> list){
		if(list.size()==0)
			return;
		Object[][] data =new Object[list.size()][5];
		for(int i=0;i<list.size();++i){
			System.out.println("姓名："+list.get(i).getUsername());
			System.out.println("手机号："+list.get(i).getPhone());
			System.out.println("address："+list.get(i).getAddress());
			System.out.println("其它："+list.get(i).getOther());
			System.out.println("时间："+list.get(i).getTime());
		}
		for(int i=0;i<list.size();++i){
			data[i][0]=list.get(i).getUsername();
			data[i][1]=list.get(i).getPhone();
			data[i][2]=list.get(i).getAddress();
			data[i][3]=list.get(i).getOther();
			data[i][4]=list.get(i).getTime();
		}
//		jLabel=new JTable(data,headTitle);
		DefaultTableModel model = new DefaultTableModel(data, headTitle) {
			public boolean isCellEditable(int row, int column) {
			    return false;
			  }
			};
			jLabel = new JTable(model);
		jLabel.setBounds(100, 100, d.width-100, d.height-100);
		jLabel.setBackground(Color.WHITE);
		
		con.add(jLabel);
	}
	
	

}
