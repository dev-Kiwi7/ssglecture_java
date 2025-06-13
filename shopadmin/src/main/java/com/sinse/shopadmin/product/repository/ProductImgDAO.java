package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shopadmin.common.exception.ProductException;
import com.sinse.shopadmin.common.exception.ProductImgException;
import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.ProductImg;

public class ProductImgDAO {
	DBManager dbManager = DBManager.getInstance();
	
	//하나의 제품에 딸려있는 이미지 등록 
	public void insert(ProductImg productImg) throws ProductImgException{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=dbManager.getConnetion();
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into product_img(filename, product_id) values(?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, productImg.getFilename());
			pstmt.setInt(2, productImg.getProduct().getProduct_id());
			int result =pstmt.executeUpdate();
			if(result <1) {
				throw new ProductImgException("상품 이미지가 등록되지 않았습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("상품 이미지가 등록되지 않았습니다", e);
		}finally {
			dbManager.release(pstmt);
		}
	}
}
