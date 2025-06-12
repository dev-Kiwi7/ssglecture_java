package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Product;
import com.sinse.shopadmin.product.model.ProductColor;

public class ProductColorDAO {
	DBManager dbManager=DBManager.getInstance();
	
	//특정 상품에 딸려있는 색상들을 입력 예) 12번 상품이 지원하는 red, blue, yellow
	public int insert(ProductColor productColor) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.getConnetion();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product_color(product_id, color_id) values(?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, productColor.getProduct().getProduct_id());
			pstmt.setInt(2, productColor.getColor().getColor_id());
			result=pstmt.executeUpdate();//DML 실행
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		return result;
	}
}








