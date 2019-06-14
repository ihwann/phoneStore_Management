package phoneStore.control;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import phoneStore.StoreStart;
import phoneStore.db.DB_User;

public class UserControl {
	
	StoreStart ss;
	
	DB_User dbu = new DB_User();
	
	public UserControl(StoreStart ss) {
		this.ss = ss;
		this.ss.sw.storeregi_bt.addActionListener(btloginAction());
		ss.sw.storespec = dbu.registorespec(this.ss.sw.id);
	}

	public ActionListener btloginAction() {
		
		ActionListener storeregi_ac = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int chk = dbu.updatechk(ss.sw.id);
				
				if(chk == 0) {
					dbu.updateStore(ss.sw.id, ss.sw.addr_tfiled.getText(), ss.sw.storetype_cbbox.getSelectedItem().toString() , ss.sw.saletype_cbbox.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "등록되었습니다.");
				}else if(chk == 1) {
					JOptionPane.showMessageDialog(null, "이미 등록 완료되었습니다.");
				}else if(chk == 2) {
					JOptionPane.showMessageDialog(null, "승인 대기 단계입니다.");
				}
				ss.sw.addr_tfiled.setText("");
			}
		};
		return storeregi_ac;
	}
			
	
}
