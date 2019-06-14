package phoneStore.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import phoneStore.StoreStart;
import phoneStore.db.DB_Login;
import phoneStore.dto.StoreDto;
import phoneStore.trash.DB_PhoneStore;

public class StoreWindow extends JFrame {

	StoreStart ss;
	public String id;
	DB_Login dba = new DB_Login();

	public StoreDto storespec = new StoreDto();

	public JPanel loginjp;
	public JTextField textid, textpw;
	public JButton btlogin;
	public JButton btregi;
	public JLabel ptlb;
	public JLabel ptid;
	public JLabel ptpw;

	// ---- user page start-----

	public JMenuBar jmb;
	public JMenu m1, m2, m3, m4, m5;
	public JMenuItem m1_1;

	// -----------매장등록패널------------
	public JPanel storeregi_panel;
	// 매장등록 컴포넌트
	public JTextField addr_tfiled;
	public JComboBox<String> storetype_cbbox, saletype_cbbox;
	public JButton storeregi_bt;

	// @@---------관리자화면-------------@@
	// ---------관리자메뉴바--------------
	public JMenuBar jmb_admin;
	public JMenu m1_admin;
	public JMenuItem m1_1_admin, m1_2_admin;

	// -----------등록승인패널------------
	public JPanel regiapproval_panel;

	public JTextArea regiapproval_area;
	public JToggleButton regi_bt1, regi_bt2, regi_bt3, regi_bt4;
	public JButton regi_submit;
	public JButton regi_nextStoreList;
	public JButton regi_previousStoreList;

	// -----------휴대폰등록관리패널------------
	public JPanel phoneregimng_panel;

	public JTextField phoneregi_phoneid;
	public JTextField phoneregi_phonename;
	public JTextField phoneregi_phoneprice;

	public JButton phoneregi_bt; // 등록
	public JButton phoneregirevise_bt; // 수정
	public JButton phoneregidelete_bt; // 삭제

	public JTextField phoneregi_phonesearch;
	public JButton phoneregisearch_bt;

	/*
	 * 
	 * 
	 * public JToggleButton regi_bt1,regi_bt2,regi_bt3,regi_bt4; public JButton
	 * regi_submit;
	 */

	// ---- user page end-----

	// 0 : 로그인 1 : 회원가입
	public int MODE = 0;

	public StoreWindow(StoreStart ss) {
		this.ss = ss;
		makeWindow();
	}

//----------------------- detail logic ---------------------------------------------//

	public void makeWindow() {
		setTitle("휴대폰 매장관리");
		setSize(1200, 700);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);

		//final ImageIcon img1 = new ImageIcon(
		//		"C:/Users/pl10h/eclipse-workspace/phoneStore_04277/src/phoneStore/phoneStore.png");
		
		final ImageIcon img1 = new ImageIcon(
				"src/img/phoneStore.png");

		loginjp = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img1.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		textid = new JTextField();
		textid.setSize(100, 30);
		textid.setLocation(700, 415);
		textpw = new JTextField();
		textpw.setSize(100, 30);
		textpw.setLocation(700, 450);

		btlogin = new JButton("로그인");
		btlogin.setSize(80, 30);
		btlogin.setLocation(820, 450);

		btregi = new JButton("회원가입");
		btregi.setSize(110, 30);
		btregi.setLocation(820, 450);

		ptid = new JLabel("아이디");
		ptid.setSize(80, 30);
		ptid.setLocation(650, 415);

		ptpw = new JLabel("패스워드");
		ptpw.setSize(110, 30);
		ptpw.setLocation(640, 450);

		ptlb = new JLabel(img1);
		ptlb.setSize(1100, 900);
		ptlb.setLocation(10, 10);

		storeregi_panel = new JPanel();
		storeregi_panel.setLayout(null);

		addr_tfiled = new JTextField();
		addr_tfiled.setSize(300, 50);
		addr_tfiled.setLocation(100, 100);

		String[] storetype = { "대리점", "판매점" };
		String[] saletype = { "ALL", "SKT", "KT", "LGT" };
		storetype_cbbox = new JComboBox<String>(storetype);
		storetype_cbbox.setSize(300, 50);
		storetype_cbbox.setLocation(100, 200);
		saletype_cbbox = new JComboBox<String>(saletype);
		saletype_cbbox.setSize(300, 50);
		saletype_cbbox.setLocation(100, 300);

		storeregi_bt = new JButton("매장등록");
		storeregi_bt.setSize(140, 50);
		storeregi_bt.setLocation(450, 300);

		storeregi_panel.add(addr_tfiled, null);
		storeregi_panel.add(storetype_cbbox, null);
		storeregi_panel.add(saletype_cbbox, null);
		storeregi_panel.add(storeregi_bt, null);

		jmb = new JMenuBar();
		m1 = new JMenu("menu1");
		m2 = new JMenu("menu2");
		m3 = new JMenu("menu3");
		m4 = new JMenu("menu4");
		m5 = new JMenu("menu5");
		m1.setFont(new Font("돋움", Font.PLAIN, 40));
		m2.setFont(new Font("돋움", Font.PLAIN, 40));
		m3.setFont(new Font("돋움", Font.PLAIN, 40));
		m4.setFont(new Font("돋움", Font.PLAIN, 40));
		m5.setFont(new Font("돋움", Font.PLAIN, 40));

		m1_1 = new JMenuItem("휴대폰판매");
		m1.add(m1_1);

		jmb.add(m1);
		jmb.add(m2);
		jmb.add(m3);
		jmb.add(m4);
		jmb.add(m5);

		// setJMenuBar(jmb);

		// -------본사승인화면-------
		regiapproval_panel = new JPanel();
		regiapproval_panel.setLayout(null);

		regiapproval_area = new JTextArea();
		regiapproval_area.setSize(780, 500);
		regiapproval_area.setLocation(20, 50);

		// 메뉴바패널
		jmb_admin = new JMenuBar();

		// 대분류메뉴
		m1_admin = new JMenu("본사 관리");
		m1_admin.setFont(new Font("돋움", Font.PLAIN, 25));

		// 소분류메뉴
		m1_1_admin = new JMenuItem("매장 등록 승인");
		m1_2_admin = new JMenuItem("휴대폰 등록 관리");
		m1_1_admin.setFont(new Font("돋움", Font.PLAIN, 25));
		m1_2_admin.setFont(new Font("돋움", Font.PLAIN, 25));

		m1_admin.add(m1_1_admin);
		m1_admin.add(m1_2_admin);

		jmb_admin.add(m1_admin);

		/////////////////////////////////////////////////
		// m1_1_admin.adda
		// setJMenuBar(jmb_admin);

		regiapproval_panel.add(regiapproval_area, null);

		loginjp.setLayout(null);
		// loginjp.add(ptlb, null);
		loginjp.add(textid, null);
		loginjp.add(textpw, null);
		loginjp.add(btlogin, null);
		loginjp.add(ptid, null);
		loginjp.add(ptpw, null);

		setContentPane(loginjp);

		setVisible(true);

	}

}
