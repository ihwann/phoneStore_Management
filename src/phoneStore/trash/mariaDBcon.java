package phoneStore.trash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import phoneStore.sources.Properties;

public class mariaDBcon {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public mariaDBcon() {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
	
			String userId = "root";
			String userPw = "1313zz";
			con = DriverManager.getConnection(Properties.jdbcUrl, userId, userPw);
			
		}catch(SQLException e) {
			
		}
		
	}
	
	public void selectStore() {
		String sql = "select * from phone_item";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			System.out.println("phone name:" + rs.getString("phone_name"));
		}catch(Exception e) {
			e.printStackTrace();
			
		}
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
	
	public static void main(String[] args) {
		mariaDBcon db = new mariaDBcon();
		db.selectStore();
		db.insertStore();
	}
	
}
