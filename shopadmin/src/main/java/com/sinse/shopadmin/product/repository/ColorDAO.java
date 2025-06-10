package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Color;

//다른 로직은 포함하면 안되며, 오직 	Color 테이블에 CRUD만을 수행하는 쿼리수행 객체를 DAO라 한다.
//Data Access Object (쿼리 전담 객체) 
public class ColorDAO {
	DBManager dbManager=DBManager.getInstance();
	
	//Create = insert 
	public int insert(Color color) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into color(color_name) values(?)");
		
		con=dbManager.getConnetion();
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, color.getColor_name()); //바인드 변수값 결정
			result=pstmt.executeUpdate(); //DML 이 수행되면, 이 쿼리에 의해 영향을 받은 레코드 수 반환

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		return result;
	}
}










