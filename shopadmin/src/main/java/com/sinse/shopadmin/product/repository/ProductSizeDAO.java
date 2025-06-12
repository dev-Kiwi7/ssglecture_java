package com.sinse.shopadmin.product.repository;

import com.sinse.shopadmin.common.util.DBManager;

public class ProductSizeDAO {
	DBManager dbManager=DBManager.getInstance();
	
	public int insert() {
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into product_size(product_id, size_id) values(?,?)");
		
		
		return 0;
	}
}
