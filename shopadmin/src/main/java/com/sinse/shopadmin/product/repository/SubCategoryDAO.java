package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.SubCategory;
import com.sinse.shopadmin.product.model.TopCategory;

public class SubCategoryDAO {
	DBManager dbManager=DBManager.getInstance();
	
	public void selectAll() {	
	}

	//하위 카테고리 중 유저가 선택한 상위 카테고리에 소속된 목록만 가져오기
	public List selectByTop(TopCategory topcategory) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList list=new ArrayList(); //size 0
		
		StringBuffer sql= new StringBuffer();
		sql.append("select * from subcategory where topcategory_id=?");
		
		con=dbManager.getConnetion();
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, topcategory.getTopcategory_id());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				SubCategory subcategory = new SubCategory(); //empty
				subcategory.setSubcategory_id(rs.getInt("subcategory_id"));//pk 옮기기 
				subcategory.setSub_name(rs.getString("sub_name"));	
				subcategory.setTopcategory(topcategory);
				list.add(subcategory);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return list;		
	}
	
}








