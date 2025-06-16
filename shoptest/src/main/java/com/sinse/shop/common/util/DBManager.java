package com.sinse.shop.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.sinse.shop.common.config.Config;

//AppMain 에서 데이터베이스를 핸들링 하지 말고, 보다 중립적인 객체에서 Connection을 얻는것
//뿐 아니라, 닫는 것마져도 대행해주는 기능을 보유한 객체를 선언..
public class DBManager {
	
	private static DBManager instance;
	
	private Connection con;
	
	//아무도 직접 인스턴스를 생성하지 못하게 생성자의 접근제한을 막아버린다.. 
	private DBManager() {
		try {
			
			//1) 드라이버 로드 
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2) 접속 
			con=DriverManager.getConnection(Config.url, Config.user, Config.pass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBManager getInstance() {
		//만일 인스턴스가 존재하지 않으면 이 메서드에서 인스턴스 생성해줌 
		if(instance==null) {
			instance = new DBManager();
		}
		return instance;
	}

	public Connection getConnetion() {
		return con;
	}
	
	//데이터베이스 관련된 자원을 해제하는 메서드
	public void release(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void release(PreparedStatement pstmt) { //DML (insert ,update, delete)
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	public void release(PreparedStatement pstmt, ResultSet rs) { //select
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}	
	public void release(Connection con,  PreparedStatement pstmt, ResultSet rs) { 
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}	
}









