package com.zph.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
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

public class MenuActivity extends JFrame implements ActionListener {
	// 设置全屏显示
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private JMenu jm = new JMenu("工作台1");
	private JMenu jm2 = new JMenu("工作台2");
	private JMenuItem t1 = new JMenuItem("添加用户"); // 菜单项
	private JMenuItem t2 = new JMenuItem("查找用户");// 菜单项
	private JTabbedPane jp = new JTabbedPane(JTabbedPane.LEFT);

	public MenuActivity(String title) {

		t1.addActionListener(this);
		t2.addActionListener(this);
		jm.add(t1); // 将菜单项目添加到菜单
		jm.add(t2); // 将菜单项目添加到菜单
		JMenuBar br = new JMenuBar(); // 创建菜单工具栏
		br.add(jm); // 将菜单增加到菜单工具栏
		br.add(jm2); // 将菜单增加到菜单工具栏
		this.setJMenuBar(br); // 为 窗体设置 菜单工具栏

		initDate();

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

		}

	}

}
