package phoneStore.control;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import phoneStore.StoreStart;
import phoneStore.db.DB_Login;
import phoneStore.db.DB_User;
import phoneStore.window.StoreWindow;

public class LoginControl  {

	StoreStart ss;
	
	DB_Login dbl = new DB_Login();
	
	public LoginControl(StoreStart ss) {
		this.ss = ss;
		this.ss.sw.btlogin.addActionListener(btloginAction());
		this.ss.sw.btregi.addActionListener(btregiAction());
	}
	
	
	public ActionListener btloginAction() {
		
		ActionListener btlogin_ac = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("btlogin");
				
				int chklogin =  dbl.login(ss.sw.textid.getText(), ss.sw.textpw.getText());
				int regichk = 0;
				System.out.println("chklogin::" + chklogin);
				
				if(chklogin == 2) {
					
					DB_User dbu = new DB_User();
					
					ss.sw.storespec = dbu.registorespec(ss.sw.textid.getText());
					
					System.out.println(ss.sw.storespec.getStoreid() + ss.sw.storespec.getStorelevel());

					int con_yn = dbl.chkconyn(ss.sw.textid.getText());
					
					
					//매장등록 단계 로그인
					if(con_yn == 1) {
						
						ss.sw.id = ss.sw.textid.getText();
						ss.sw.setJMenuBar(ss.sw.jmb);
						ss.sw.setContentPane(ss.sw.storeregi_panel);
						ss.sw.setVisible(true);
						
					}else if(con_yn == 2) {
						
						JOptionPane.showMessageDialog(null, "승인 대기 중입니다. 본사에 문의하세요.");
						
					//대리점 화면	
					}else if(con_yn == 3) {
						JOptionPane.showMessageDialog(null, "대리점 화면입니다. 구현중입니다.");
						
					//판매점 화면	
					}else if(con_yn == 4) {
						JOptionPane.showMessageDialog(null, "판매점 화면입니다. 구현중입니다.");
						
					//본사 승인화면	
					}else if(con_yn == 5) {
						ss.aw.make_regiapproval_panel(0);
						
					}
					
					
				}else if(chklogin == 0) regichk = JOptionPane.showConfirmDialog(null,"아이디가 없습니다. 회원가입 하시겠습니까?");
				else JOptionPane.showMessageDialog(null, "아이디와 비밀번호가 일치하지 않습니다.");

				System.out.println("regichk:" + regichk);
				
				if(regichk == 0 && chklogin == 0) {
					ss.sw.MODE = 1;
					System.out.println("btregi");
					ss.sw.loginjp.add(ss.sw.btregi, null); 
					ss.sw.loginjp.remove(ss.sw.btlogin);
					ss.sw.setContentPane(ss.sw.loginjp);
				}
				
				ss.sw.textid.setText("");
				ss.sw.textpw.setText("");
			}
		};
		
		return btlogin_ac;
	}
	
	public ActionListener btregiAction() {
		ActionListener al = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int chk = dbl.chkId(ss.sw.textid.getText());
				int chkregi = 0;
				
				if(chk == 1) JOptionPane.showMessageDialog(null, "이미 존재하는 아이디 입니다. 다시 입력하세요.");
				else chkregi =  dbl.regiUser(ss.sw.textid.getText(), ss.sw.textpw.getText());
				
				if(chkregi == 1) {
					ss.sw.MODE = 0;
					JOptionPane.showMessageDialog(null, "회원가입이 되었습니다. 로그인 해주세요.");
					ss.sw.loginjp.add(ss.sw.btlogin, null);
					ss.sw.loginjp.remove(ss.sw.btregi);
					ss.sw.setContentPane(ss.sw.loginjp);
					
					ss.sw.textid.setText("");
					ss.sw.textpw.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "시스템에 오류가 생겼습니다. 잠시 후 다시 시도해주세요.");
				}
			}
		};
		
		return al;
		
	}
	
}
