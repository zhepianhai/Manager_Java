package com.zph.ui;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *  
 * 
 */
public class Demo extends JFrame implements ActionListener, HyperlinkListener {
	/*
	 * ����miniIE���õĿؼ�
	 */
	JMenuBar mentuBar;
    JButton Enter;
	JButton ScreenCapture;
	JTextField TextField;
	JPanel ButtonPanel;
	JEditorPane pane;
	JMenuItem skin1;
	JMenuItem skin2;
	JMenuItem skin3;
	JMenuItem skin4;
	JMenuItem skin5;
	JMenuItem skin6;
	// ����ѡ��Ƥ��ʱ���ݲ���
	Object ob;
    //���ڽ�ͼʱ��Ǳ��ر����� 
	int b=0;
	public Demo() {
		MenuBar();
		/*
		 * ������ʾ��ҳ�Ĳ���
		 */
		pane = new JEditorPane();
		pane.setEditable(false); // Editable
		pane.setContentType("text/html");
		// ����pane�ĳ������Ӽ���
		pane.addHyperlinkListener(this);
		/*
		 * �����Ͽؼ��ŵ���ܼ���
		 */
		// ���ô����С.
		setSize(640, 480);

		this.add(new JScrollPane(pane), "Center");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * ����miniIE������
	 */
	public void MenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("�ļ�");
		JMenu skin = new JMenu("��Ƥ��");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem fileExitMenuItem = new JMenuItem("�˳�", KeyEvent.VK_X);
		skin1 = new JMenuItem("���кܶ�Ƥ������Ҫ��ģ���Դ�����main�����ע��");
		skin.add(skin1);
		menuBar.add(fileMenu);
		menuBar.add(skin);
		setJMenuBar(menuBar);
		fileMenu.add(fileExitMenuItem);
		// ���õ���˳�������˳�
		fileExitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	

	public static void main(String[] args) {
		/*
		 * ʹ��Ĭ��Ƥ�����������
		 */
		/*
		 *  ���漸�䣬���ǿ��ܿ���̫���������ʵ��ʱ����Ҳû��ϵ������ʵ����Ϊswing�����̰߳�ȫ�ģ�Ϊ���̰߳�ȫ���ȡ�����µĴ�ʩ��ȥ���Ļ������ܻᱨ�?
		 */
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					/*
					 * ��Ҫ�޸�Ƥ���Ļ���ֻ��Ҫ��ģ������������Ĳ������ĳ�ʲô����Դ򿪣�Referenced Libraries -> ���substance.jar -> �ҵ�org.jvnet.substance.skin�����  -> �������SubstanceDustCoffeeLookAndFeel �滻�� �ոմ򿪵İ��µ�����һ����Substance....LookAndFeel������
					 */
					UIManager
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
					//���簴������Ĳ��裬���Կ���һ���У�"SubstanceOfficeBlue2007LookAndFeel.class"����Ҫ�滻�����Ƥ�����Ϳ�������������д
					//UIManager
					//.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// ����һ�£�Ƥ���Ϳ��Ի���
					// ��Ҫ��ϸ�˽��ͬѧ������ȥ�ٶ���������substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Demo browser = new Demo();
				browser.setVisible(true);
			}
		});

	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}