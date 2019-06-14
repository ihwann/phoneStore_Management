package phoneStore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import phoneStore.sources.Properties;
import phoneStore.window.StoreWindow;
import phoneStore.dto.StoreDto;

public class DB_User {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DB_User() {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {

			String userId = "gongbu1";
			String userPw = "2019gong1";
			con = DriverManager.getConnection(Properties.jdbcUrl, userId, userPw);
			
		}catch(SQLException e) {
			
		}
		
	}
	
	/*
	로그인처리
	*/
	
	public StoreDto registorespec(String id) {
		String sql = "select storeid, storeaddr, storelevel, storetype from phone_store where storeid = ?";
		
		StoreDto sdto = new StoreDto();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String storeid = rs.getString("storeid");
				String storeaddr = rs.getString("storeaddr");
				String storelevel = rs.getString("storelevel");
				String storetype = rs.getString("storetype");
				if(storeid == null) {
					break;
				}else {
					sdto.setall(storeid, storeaddr, storelevel, storetype);
					break;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return sdto;
		
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
			if("y".equals(con_yn)) chk = 1 ;
			else if("n".equals(con_yn)) chk = 2;
			
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
		
		if("대리점".equals(storelevel)) storelevelin = "L2";
		else if("판매점".equals(storelevel)) storelevelin = "L3";
		else storelevelin = " ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, storeaddr);
			pstmt.setString(2, storelevelin);
			pstmt.setString(3, storetype);
			pstmt.setString(4, id);
			rs = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	public void insertStore() {
		String sql = "insert into phone_item(phone_model_no,phone_name,phone_lease_price) values(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "lg_v50");
			pstmt.setString(2, "엘지V50");
			pstmt.setString(3, "1300000");
			int rs = pstmt.executeUpdate();
			System.out.println("insert result:" + rs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
}
