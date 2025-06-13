package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shopadmin.common.exception.ProductSizeException;
import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.ProductSize;

public class ProductSizeDAO {
	DBManager dbManager=DBManager.getInstance();
	
	public void insert(ProductSize productSize ) throws ProductSizeException{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=dbManager.getConnetion();
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into product_size(product_id, size_id) values(?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, productSize.getProduct().getProduct_id());
			pstmt.setInt(2, productSize.getSize().getSize_id());
			int result = pstmt.executeUpdate();
			
			if(result<1) {
				throw new ProductSizeException("상품 사이즈 등록 실패");
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductSizeException("상품 사이즈 등록 실패", e);
		}finally {
			dbManager.release(pstmt);
		}
	}
}






