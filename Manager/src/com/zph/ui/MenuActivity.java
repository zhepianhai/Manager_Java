package com.zph.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.zph.contact.Contact;
import com.zph.pojo.User;
import com.zph.service.UserService;
import com.zph.service.imp.UserServiceImp;

public class MenuActivity extends JFrame implements ActionListener {

	// 设置全屏显示
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	// 菜单
	private JMenu jm = new JMenu("工作台1");
	private JMenu jm2 = new JMenu("工作台2");
	private JMenuItem t1 = new JMenuItem("添加用户"); // 菜单项
	private JMenuItem t2 = new JMenuItem("查找用户");// 菜单项
	private JTabbedPane jp = new JTabbedPane(JTabbedPane.LEFT);
	// 列表
	private JTable jLabel;
	private String[] headTitle = { "姓名", "电话号码", "地址", "补充说明", "录入时间" };
	private JScrollPane JSP;
	// 系统界面布局
	private JPanel panel_main = null;
	private JPanel Panel_left = null;
	
	
	private JPanel panel_bottom = null;
	private JPanel panel_bottom_message=null;
	private JLabel Bottom_lbl_payout=null;
	
	
	private JPanel panel_center = null;
	private JPanel panel_userlist = null;
	// 其它
	private UserService userService;
	private String title;

	public MenuActivity() {
		super();
		this.title = Contact.UserName;
		initialize();
	}

	private void initialize() {
		userService = new UserServiceImp();
		this.setSize(d);
		this.setJMenuBar(getJJMenuBar()); // 为 窗体设置 菜单工具栏
		this.setContentPane(getPanel_main());
		this.setTitle(title);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);// 居中显示
		Image img = Toolkit.getDefaultToolkit().getImage(
				"src/icon/ui_icon/icon_app.png");
		this.setIconImage(img);
	}

	/**
	 * 返回主界面
	 * */
	private Container getPanel_main() {
		if (panel_main == null) {
			panel_main = new JPanel();
			panel_main.setLayout(new BorderLayout(0, 0));
			panel_main.setBackground(Color.white);
			panel_main.add(getPanel_left(), BorderLayout.WEST);
			panel_main.add(getPanel_bottom(), BorderLayout.SOUTH);
			panel_main.add(getPanel_center(), BorderLayout.CENTER);
		}
		return panel_main;
	}

	/**
	 * 中间区域
	 * */
	private JPanel getPanel_center() {
		if (panel_center == null) {
			panel_center = new JPanel();
			panel_center.setLayout(null);
			// 添加用户列表
			panel_center.add(getTableUser(), null);

		}
		return panel_center;
	}
	
	/**
	 * 得到用户列表
	 * */
	private JPanel getTableUser() {
		if (panel_userlist == null) {
			panel_userlist=new JPanel();
			panel_userlist.setBounds(0,0, d.width, d.height);
			List<User> list = userService.SelectAllUser();
			// 展示数据
			if (list.size() == 0)
				return panel_userlist;
			Object[][] data = new Object[list.size()][5];
			for (int i = 0; i < list.size(); ++i) {
				data[i][0] = list.get(i).getUsername();
				data[i][1] = list.get(i).getPhone();
				data[i][2] = list.get(i).getAddress();
				data[i][3] = list.get(i).getOther();
				data[i][4] = list.get(i).getTime();
			}
			DefaultTableModel model = new DefaultTableModel(data, headTitle) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			if (jLabel == null)
				jLabel = new JTable(model);
			jLabel.setBackground(Color.WHITE);
			JSP = new JScrollPane(jLabel);
			panel_userlist.add(JSP);
		}
		return panel_userlist;
	}

	/**
	 * 底部
	 * */
	private JPanel getPanel_bottom() {
		if (panel_bottom == null) {
			panel_bottom = new JPanel();
			panel_bottom.setBounds(0, d.height - 10, d.width, d.height);
			panel_bottom.setBackground(Color.BLUE);
			panel_bottom.setLayout(new GridLayout(6, 1));
			panel_bottom.add(getBottomMessage(),null);
		}
		return panel_bottom;
	}
	/**
	 * 底部的信息
	 * */
	private JPanel getBottomMessage() {
		if(null==panel_bottom_message){
			panel_bottom_message=new JPanel();
			panel_bottom_message.setLayout(new FlowLayout(FlowLayout.CENTER));
			if(null==Bottom_lbl_payout){
				Bottom_lbl_payout=new JLabel();
				Bottom_lbl_payout.setText("当前用户是："+Contact.UserName);
				
			}
			panel_bottom_message.add(Bottom_lbl_payout);
			
		}
		return panel_bottom_message;
	}


	/**
	 * 左侧
	 * */
	private JPanel getPanel_left() {
		if (Panel_left == null) {
			Panel_left = new JPanel();
			Panel_left.setBounds(0, 0, 100, d.height);
			Panel_left.setLayout(new FlowLayout(FlowLayout.LEFT));
			Panel_left.setBackground(Color.RED);
		}
		return Panel_left;
	}

	private JMenuBar getJJMenuBar() {
		t1.addActionListener(this);
		t2.addActionListener(this);
		jm.add(t1); // 将菜单项目添加到菜单
		jm.add(t2); // 将菜单项目添加到菜单
		JMenuBar br = new JMenuBar(); // 创建菜单工具栏
		br.add(jm); // 将菜单增加到菜单工具栏
		br.add(jm2); // 将菜单增加到菜单工具栏
		return br;
	}

	/**
	 * 初始化数据
	 * */
	private void initDate() {

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFrame.setDefaultLookAndFeelDecorated(true);
				new MenuActivity();
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
		}

	}

}
