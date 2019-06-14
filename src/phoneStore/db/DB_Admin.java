package phoneStore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import phoneStore.dto.PhoneItemDto;
import phoneStore.dto.StoreDto;
import phoneStore.sources.Properties;
import phoneStore.window.StoreWindow;

public class DB_Admin {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DB_Admin() {

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			String userId = "gongbu1";
			String userPw = "2019gong1";
			con = DriverManager.getConnection(Properties.jdbcUrl, userId, userPw);

		} catch (SQLException e) {

		}

	}

	/*
	 * 스토워본사승인조회
	 */

	public ArrayList<StoreDto> registore_search(int startNum) {
		String sql = "select * from phone_store where storelevel != 'L1' and con_yn = 'n' limit ?,4";
		ArrayList<StoreDto> sdto = new ArrayList<StoreDto>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String storeid = rs.getString("storeid");
				String storeaddr = rs.getString("storeaddr");
				String storelevel = rs.getString("storelevel");
				String storetype = rs.getString("storetype");

				sdto.add(new StoreDto(storeid, storeaddr, storelevel, storetype));
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return sdto;
	}

	/*
	 * 스토워본사승인조회
	 */

	public int registore_update(ArrayList<StoreDto> uplist) {
		String sql = "update phone_store set con_yn = 'y', update_date=now() where storeid in(''";
		int rnt = 0;

		try {
			pstmt = con.prepareStatement(sql);

			for (StoreDto item : uplist) {
				sql += ",'" + item.getStoreid() + "'";
			}
			sql += ")";

			rnt = pstmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return rnt;
	}

	public int updatechk(String id) {

		int chk = 0;

		String sql = "select * from phone_store where storeid = ?";

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			rs.next();
			String con_yn = rs.getString("con_yn");
			if ("y".equals(con_yn))
				chk = 1;
			else if ("n".equals(con_yn))
				chk = 2;

			System.out.println("chk:" + chk);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return chk;
	}

	public int updateStore(String id, String storeaddr, String storelevel, String storetype) {

		int rs = 0;
		String sql = "update phone_store set  storeaddr=?, storelevel=?, storetype=?, con_yn='n', update_date=now() where storeid =?";

		String storelevelin = "";

		System.out.println(storelevel);

		if ("대리점".equals(storelevel))
			storelevelin = "L2";
		else if ("판매점".equals(storelevel))
			storelevelin = "L3";
		else
			storelevelin = " ";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, storeaddr);
			pstmt.setString(2, storelevelin);
			pstmt.setString(3, storetype);
			pstmt.setString(4, id);
			rs = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	/*
	 * 휴대폰 단말 등록
	 */

	public int insertPhoneItem(PhoneItemDto pdto) {
		int rnt = 0;

		String sql = "insert into phone_item (phone_model_id, phone_name, phone_lease_price) values(?, ?, ?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdto.getPhone_model_id());
			pstmt.setString(2, pdto.getPhone_name());
			pstmt.setInt(3, pdto.getPhone_lease_price());

			rnt = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return rnt;
	}

	public PhoneItemDto getPhoneItem(String searchPhoneItem) {

		String sql = "select phone_model_id, phone_name, phone_lease_price from phone_item where phone_model_id = ? ";
		PhoneItemDto pdto = new PhoneItemDto();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, searchPhoneItem);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pdto.setPhone_model_id(rs.getString("phone_model_id"));
				pdto.setPhone_name(rs.getString("phone_name"));
				pdto.setPhone_lease_price(rs.getInt("phone_lease_price"));
				return pdto;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pdto;
	}

	public int revisePhoneItem(PhoneItemDto phoneItemDto, String phoneregi_phonename, String phoneregi_phoneprice) {
		int rnt = 0;

		String sql = "update phone_item set phone_name=?, phone_lease_price=? where phone_model_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phoneregi_phonename);
			pstmt.setInt(2, Integer.parseInt(phoneregi_phoneprice));
			pstmt.setString(3, phoneItemDto.getPhone_model_id());
			rnt = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rnt;
	}

	public int deletePhoneItem(PhoneItemDto pdto) {
		// TODO Auto-generated method stub
		int rnt = 0;
		String sql = "delete from phone_item where phone_model_id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdto.getPhone_model_id());
			rnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rnt;
	}

}
