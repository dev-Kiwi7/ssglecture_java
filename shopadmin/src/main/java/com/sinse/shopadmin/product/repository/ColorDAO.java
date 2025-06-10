package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	//모든 색상 가져오기
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList list=new ArrayList();
		
		con=dbManager.getConnetion();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from color");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();//표 반환...
			
			//rs죽이기 전에 rs가 보유한 데이터를 모델 객체로 옮기자!!!
			// 모델 인스턴스 1건은 레코드 1건을 담는다..
			while(rs.next()) {
				Color color = new Color(); //레코드 1건을 담는 모델 인스턴스
				color.setColor_id(rs.getInt("color_id"));
				color.setColor_name(rs.getString("color_name"));
				list.add(color);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbManager.release(pstmt, rs);
		}
		return list;
	}
	
}




















