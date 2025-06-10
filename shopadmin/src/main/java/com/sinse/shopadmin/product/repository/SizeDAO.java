package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList list = new ArrayList(); 
		
		con=dbManager.getConnetion();
		StringBuffer sql= new StringBuffer();
		sql.append("select * from size");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			//rs는 곧 닫히므로, 꺼내서 list에 옮겨심자 
			
			while(rs.next()) {
				Size size = new Size(); //empty
				size.setSize_id(rs.getInt("size_id"));
				size.setSize_name(rs.getString("size_name"));
				list.add(size);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}
}










