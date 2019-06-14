package phoneStore.control;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import phoneStore.StoreStart;
import phoneStore.db.DB_Admin;
import phoneStore.dto.PhoneItemDto;
import phoneStore.dto.StoreDto;

public class AdminControl {

	StoreStart ss;
	DB_Admin dbl = new DB_Admin();

	ArrayList<StoreDto> sdto;
	ArrayList<StoreDto> updto = new ArrayList<StoreDto>();

	Font font = new Font(null, Font.BOLD, 24);

	int start_registore_idx = 0;

	public AdminControl(StoreStart ss) {
		this.ss = ss;
	}

	public void make_regiapproval_panel(int chk) {

		sdto = dbl.registore_search(start_registore_idx);
		// viewSdto(sdto);

		ss.sw.regi_submit = new JButton("전송");
		ss.sw.regi_submit.setSize(70, 30);
		ss.sw.regi_submit.setLocation(950, 80);

		ss.sw.regi_nextStoreList = new JButton("다음목록");
		ss.sw.regi_nextStoreList.setSize(140, 30);
		ss.sw.regi_nextStoreList.setLocation(850, 480);

		ss.sw.regi_previousStoreList = new JButton("이전목록");
		ss.sw.regi_previousStoreList.setSize(140, 30);
		ss.sw.regi_previousStoreList.setLocation(850, 520);

		ss.sw.regi_bt1 = new JToggleButton("승인1");
		ss.sw.regi_bt1.setSize(70, 30);
		ss.sw.regi_bt1.setLocation(850, 80);

		ss.sw.regi_bt2 = new JToggleButton("승인2");
		ss.sw.regi_bt2.setSize(70, 30);
		ss.sw.regi_bt2.setLocation(850, 130);

		ss.sw.regi_bt3 = new JToggleButton("승인3");
		ss.sw.regi_bt3.setSize(70, 30);
		ss.sw.regi_bt3.setLocation(850, 180);

		ss.sw.regi_bt4 = new JToggleButton("승인4");
		ss.sw.regi_bt4.setSize(70, 30);
		ss.sw.regi_bt4.setLocation(850, 230);

		System.out.println(sdto.size());

		if (sdto.size() == 0) {

		} else if (sdto.size() == 1) {
			ss.sw.regiapproval_panel.add(ss.sw.regi_bt1, null);
		} else if (sdto.size() == 2) {
			ss.sw.regiapproval_panel.add(ss.sw.regi_bt1, null);
			ss.sw.regiapproval_panel.add(ss.sw.regi_bt2, null);
		} else if (sdto.size() == 3) {
			ss.sw.regiapproval_panel.add(ss.sw.regi_bt1, null);
			ss.sw.regiapproval_panel.add(ss.sw.regi_bt2, null);
			ss.sw.regiapproval_panel.add(ss.sw.regi_bt3, null);
		} else if (sdto.size() == 4) {
			ss.sw.regiapproval_panel.add(ss.sw.regi_bt1, null);
			ss.sw.regiapproval_panel.add(ss.sw.regi_bt2, null);
			ss.sw.regiapproval_panel.add(ss.sw.regi_bt3, null);
			ss.sw.regiapproval_panel.add(ss.sw.regi_bt4, null);
		}

		ss.sw.regiapproval_panel.add(ss.sw.regi_submit, null);
		ss.sw.regiapproval_panel.add(ss.sw.regi_nextStoreList, null);
		ss.sw.regiapproval_panel.add(ss.sw.regi_previousStoreList, null);
		ss.sw.regi_submit.addActionListener(btsubmit());
		ss.sw.regi_nextStoreList.addActionListener(btNextStoreList());
		ss.sw.regi_previousStoreList.addActionListener(btPreviousStoreList());

		/*
		 * String regiarea = ""; int cnt = 1; for (StoreDto item : sdto) { regiarea =
		 * regiarea + "승인번호 : " + cnt + " id : " + item.getStoreid() + " addr : " +
		 * item.getStoreadd() + " level : " + item.getStorelevel() + " type : " +
		 * item.getStoretype() + "\n"; cnt++; }
		 */

		ss.sw.setJMenuBar(ss.sw.jmb_admin);
		ss.sw.m1_1_admin.addActionListener(menuchange1());
		ss.sw.m1_2_admin.addActionListener(menuchange2());

		ss.sw.regiapproval_area.setText(viewSdto(sdto));
		ss.sw.regiapproval_area.setFont(font);
		ss.sw.regiapproval_area.setEditable(false);

		ss.sw.setContentPane(ss.sw.regiapproval_panel);
		ss.sw.setVisible(true);

		if (chk == 0)
			JOptionPane.showMessageDialog(null, "본사 관리자 모드 입니다.");
	}

	public void makePhoneWindow() {
		// -------휴대폰 등록 관리-------
		ss.sw.phoneregimng_panel = new JPanel();
		ss.sw.phoneregimng_panel.setLayout(null);

		ss.sw.phoneregi_phoneid = new JTextField();
		ss.sw.phoneregi_phoneid.setSize(300, 50);
		ss.sw.phoneregi_phoneid.setLocation(100, 200);
		ss.sw.phoneregi_phoneid.setBorder(new TitledBorder("phoneid"));

		ss.sw.phoneregi_phonename = new JTextField();
		ss.sw.phoneregi_phonename.setSize(300, 50);
		ss.sw.phoneregi_phonename.setLocation(100, 300);
		ss.sw.phoneregi_phonename.setBorder(new TitledBorder("phonename"));

		ss.sw.phoneregi_phoneprice = new JTextField();
		ss.sw.phoneregi_phoneprice.setSize(300, 50);
		ss.sw.phoneregi_phoneprice.setLocation(100, 400);
		ss.sw.phoneregi_phoneprice.setBorder(new TitledBorder("phoneprice"));

		ss.sw.phoneregi_bt = new JButton("휴대폰등록");
		ss.sw.phoneregi_bt.setSize(100, 50);
		ss.sw.phoneregi_bt.setLocation(450, 400);
		ss.sw.phoneregi_bt.addActionListener(btRegiPhone());

		ss.sw.phoneregirevise_bt = new JButton("수정");
		ss.sw.phoneregirevise_bt.setSize(70, 50);
		ss.sw.phoneregirevise_bt.setLocation(580, 400);
		ss.sw.phoneregirevise_bt.setEnabled(false);
		ss.sw.phoneregirevise_bt.addActionListener(btRevisePhone());

		ss.sw.phoneregidelete_bt = new JButton("휴대폰삭제");
		ss.sw.phoneregidelete_bt.setSize(100, 50);
		ss.sw.phoneregidelete_bt.setLocation(450, 460);
		ss.sw.phoneregidelete_bt.addActionListener(btDeletePhone());
		ss.sw.phoneregidelete_bt.setEnabled(false);

		ss.sw.phoneregi_phonesearch = new JTextField();
		ss.sw.phoneregi_phonesearch.setSize(300, 50);
		ss.sw.phoneregi_phonesearch.setLocation(100, 100);
		ss.sw.phoneregi_phonesearch.setBorder(new TitledBorder("phonesearch_id"));

		ss.sw.phoneregisearch_bt = new JButton("휴대폰조회");
		ss.sw.phoneregisearch_bt.setSize(100, 50);
		ss.sw.phoneregisearch_bt.setLocation(450, 100);
		ss.sw.phoneregisearch_bt.addActionListener(btSearchPhone());

		ss.sw.phoneregimng_panel.add(ss.sw.phoneregi_phoneid);
		ss.sw.phoneregimng_panel.add(ss.sw.phoneregi_phonename);
		ss.sw.phoneregimng_panel.add(ss.sw.phoneregi_phoneprice);
		ss.sw.phoneregimng_panel.add(ss.sw.phoneregi_bt);
		ss.sw.phoneregimng_panel.add(ss.sw.phoneregirevise_bt);
		ss.sw.phoneregimng_panel.add(ss.sw.phoneregi_phonesearch);
		ss.sw.phoneregimng_panel.add(ss.sw.phoneregisearch_bt);
		ss.sw.phoneregimng_panel.add(ss.sw.phoneregidelete_bt);

		ss.sw.setContentPane(ss.sw.phoneregimng_panel);
		ss.sw.setVisible(true);
	}

	public ActionListener btsubmit() {
		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (ss.sw.regi_bt1.isSelected())
					updto.add(sdto.get(0));
				if (ss.sw.regi_bt2.isSelected())
					updto.add(sdto.get(1));
				if (ss.sw.regi_bt3.isSelected())
					updto.add(sdto.get(2));
				if (ss.sw.regi_bt4.isSelected())
					updto.add(sdto.get(3));

				int rnt = dbl.registore_update(updto);

				System.out.println("rnt:" + rnt);
				if (rnt > 0)
					JOptionPane.showMessageDialog(null, "승인이 완료 되었습니다.");

				make_regiapproval_panel(1);
			}
		};

		return ac;

	}

	public ActionListener menuchange1() {
		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ss.sw.setContentPane(ss.sw.regiapproval_panel);
				ss.sw.setVisible(true);
			}
		};

		return ac;
	}

	public ActionListener menuchange2() {
		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				makePhoneWindow();
			}
		};

		return ac;
	}

	public ActionListener btRegiPhone() {
		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (ss.sw.phoneregi_phoneid.getText().isEmpty() || ss.sw.phoneregi_phonename.getText().isEmpty()
						|| ss.sw.phoneregi_phoneprice.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "필수 요소를 입력해주세요.");
				} else {
					if (checkNumber(ss.sw.phoneregi_phoneprice.getText())) {
						int rnt = dbl.insertPhoneItem(
								new PhoneItemDto(ss.sw.phoneregi_phoneid.getText(), ss.sw.phoneregi_phonename.getText(),
										Integer.parseInt(ss.sw.phoneregi_phoneprice.getText())));
						if (rnt != 0) {
							JOptionPane.showMessageDialog(null, "등록이 완료되었습니다.");
						} else {
							JOptionPane.showMessageDialog(null, "등록을 실패했습니다.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "입력 형식이 올바르지 않습니다.");
					}

				}
			}
		};
		return ac;
	}

	public ActionListener btSearchPhone() {
		ActionListener ac = new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				ss.sw.phoneregi_phoneid.setText("");
				ss.sw.phoneregi_phonename.setText("");
				ss.sw.phoneregi_phoneprice.setText("");
				PhoneItemDto pdto = dbl.getPhoneItem(ss.sw.phoneregi_phonesearch.getText());

				if (pdto.getPhone_model_id() != null) {
					JOptionPane.showMessageDialog(null, "조회에 성공하였습니다.");
					ss.sw.phoneregi_phoneid.setText(pdto.getPhone_model_id());
					ss.sw.phoneregi_phonename.setText(pdto.getPhone_name());
					ss.sw.phoneregi_phoneprice.setText(String.valueOf(pdto.getPhone_lease_price()));
					ss.sw.phoneregi_phoneid.disable();
					ss.sw.phoneregi_bt.setEnabled(false);
					ss.sw.phoneregirevise_bt.setEnabled(true);
					ss.sw.phoneregidelete_bt.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "조회에 실패하였습니다.");
				}
			}
		};
		return ac;
	}

	public ActionListener btRevisePhone() {
		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PhoneItemDto pdto = dbl.getPhoneItem(ss.sw.phoneregi_phonesearch.getText());
				if (pdto != null) {
					int result = dbl.revisePhoneItem(pdto, ss.sw.phoneregi_phonename.getText(),
							ss.sw.phoneregi_phoneprice.getText());
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "조회에 실패하였습니다.");
				}
			}

		};

		return ac;
	}

	public ActionListener btDeletePhone() {
		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PhoneItemDto pdto = dbl.getPhoneItem(ss.sw.phoneregi_phonesearch.getText());
				if (pdto != null) {
					int result = dbl.deletePhoneItem(pdto);
					if (result != 0) {
						JOptionPane.showMessageDialog(null, "휴대폰 정보를 삭제했습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "휴대폰 정보를 삭제하지 못했습니다.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "조회에 실패하였습니다.");
				}

			}
		};
		return ac;
	}

	public ActionListener btNextStoreList() {
		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				start_registore_idx += 4;
				sdto = dbl.registore_search(start_registore_idx);
				if (sdto.isEmpty()) {
					JOptionPane.showMessageDialog(null, "마지막 페이지 입니다.");
					start_registore_idx -= 4;
				} else {
					ss.sw.regiapproval_area.setText(viewSdto(sdto));
					System.out.println("start_registore_idx " + start_registore_idx);
				}
			}
		};
		return ac;
	}

	public ActionListener btPreviousStoreList() {
		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				start_registore_idx -= 4;
				if (start_registore_idx < 0) {
					JOptionPane.showMessageDialog(null, "처음 페이지 입니다.");
					start_registore_idx = 0;
				} else {
					sdto = dbl.registore_search(start_registore_idx);
					ss.sw.regiapproval_area.setText(viewSdto(sdto));
					System.out.println("start_registore_idx " + start_registore_idx);
				}
			}
		};
		return ac;
	}

	public boolean checkNumber(String s) {
		boolean result = false;
		try {
			Double.parseDouble(s);
			result = true;
		} catch (Exception e) {

		}
		return result;
	}

	public String viewSdto(ArrayList<StoreDto> sdtoItem) {

		String regiarea = "";
		int cnt = 1;
		for (StoreDto item : sdto) {
			regiarea = regiarea + "승인번호 : " + cnt + " id : " + item.getStoreid() + " addr : " + item.getStoreadd()
					+ " level : " + item.getStorelevel() + " type : " + item.getStoretype() + "\n";
			cnt++;
		}
		return regiarea;
	}

}
