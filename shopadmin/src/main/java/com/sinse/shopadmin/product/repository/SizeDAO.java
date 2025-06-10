package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Size;

//Size 테이블에 대한 CRUD 객체 
public class SizeDAO {
	DBManager dbManager=DBManager.getInstance();
	
	//한건 넣기
	public int insert(Size size) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into size(size_name) values(?)");
		
		con=dbManager.getConnetion();
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, size.getSize_name());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		return result;
	}
	
	//모두 가져오기 
	public List selectAll() {
		return null;
	}
}



