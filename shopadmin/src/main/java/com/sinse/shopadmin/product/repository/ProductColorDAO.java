package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shopadmin.common.exception.ProductColorException;
import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Product;
import com.sinse.shopadmin.product.model.ProductColor;

public class ProductColorDAO {
	DBManager dbManager=DBManager.getInstance();
	
	//특정 상품에 딸려있는 색상들을 입력 예) 12번 상품이 지원하는 red, blue, yellow
	public void insert(ProductColor productColor) throws ProductColorException{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=dbManager.getConnetion();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product_color(product_id, color_id) values(?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, productColor.getProduct().getProduct_id());
			pstmt.setInt(2, productColor.getColor().getColor_id());
			
			int result=pstmt.executeUpdate();//DML 실행
			
			if(result<1) {
				throw new ProductColorException("상품의 색상이 등록되지 않았어요");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductColorException("상품의 색상 등록시 문제가 발생하였습니다", e);
		}finally {
			dbManager.release(pstmt);
		}
	}
}








