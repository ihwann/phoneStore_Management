package phoneStore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import phoneStore.sources.Properties;

public class DB_Login {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DB_Login() {
		
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
	public int chkId(String id) {
		String sql = "select storeid,storepw from phone_store";
		int chk=0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String dbid = "";
			while(rs.next()) {
				dbid = rs.getString("storeid");
				System.out.println("dbid::" + dbid);
				if(id.equals(dbid)) {
					chk=1;
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		System.out.println("chk:" + chk);
		return chk;
	}

	public int chkPw(String id, String pw) {
		String sql = "select storepw from phone_store where storeid = ?";
		int chk=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			String dbpw = "";
			rs.next();
			
			dbpw = rs.getString("storepw");
			
			if(pw.equals(dbpw)) {
				chk=1;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return chk;
	}


	/*
	로그인처리
	*/
	public int chkconyn(String id) {
		
		String sql = "select con_yn, storelevel from phone_store where storeid = ?";
		int chk=0;
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			String con_yn = "";
			String storelevel = "";
			
			rs.next();
			con_yn = rs.getString("con_yn");
			storelevel = rs.getString("storelevel");
			System.out.println("con_yn::" + con_yn);
			
			if("L1".equals(storelevel)) chk=5;
			else if(con_yn == null) chk=1;
			else if("n".equals(con_yn)) chk=2;
			else if("y".equals(con_yn)) {
				
				if("L2".equals(storelevel)) chk=3;
				else if("L3".equals(storelevel)) chk=4;
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		System.out.println("chk:" + chk);
		return chk;
	}

	
	
	//chk3 0 id 1  id pw 2
	public int login(String id, String pw) {
		int chk1 = chkId(id);
		int chk2 = chkPw(id,pw);
		int chk3 = 0;
		
		//id ok pw no
		System.out.println("chk1:" + chk1);
		System.out.println("chk2:" + chk2);
		if(chk1 == 1 && chk2 == 0) {
			chk3 = 1;
		//id ok pw ok
		}else if(chk1 == 1 && chk2 == 1) {
			chk3 = 2;
		//id no pw no
		}else {
			
		}

		return chk3;
	}
	
	public int regiUser(String id, String pw) {
		int rs = 0;
		String sql = "insert into phone_store(storeid,storepw,update_date,regi_date) values(?,?,now(),now())";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
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
