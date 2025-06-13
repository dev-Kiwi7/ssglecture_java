package com.sinse.shopadmin.product.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.sinse.shopadmin.product.model.Product;
import com.sinse.shopadmin.product.repository.ProductDAO;

//JTable은 껍데기이므로, 이 모델만을 바라보고 정보를 가져감 
public class ProductModel extends AbstractTableModel{
	ProductDAO productDAO;
	List<Product> list;
	
	String[] column= {
		"topcategory_id","top_name","subcategory_id","sub_name",
		"product_id","product_name", "brand","price","discount","introduce","detail"
	};
	
	public ProductModel() {
		productDAO= new ProductDAO();
		list = productDAO.selectAll();
	}
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return column.length;
	}

	public String getColumnName(int col) {
		return column[col];
	}
	
	//row는 층수를 결정한다 , col은 0번째 무얼, 1번째 무얼 가져올지 조건문으로 내가 원하는 속성 사용
	public Object getValueAt(int row, int col) {
		Product product=list.get(row);
		String value=null;
		
		switch(col) {
			case 0:value=Integer.toString(product.getSubCategory().getTopcategory().getTopcategory_id());break;
			case 1:value=product.getSubCategory().getTopcategory().getTop_name();break;
			case 2:value=Integer.toString(product.getSubCategory().getSubcategory_id());break;
			case 3:value=product.getSubCategory().getSub_name();break;
			case 4:value=Integer.toString(product.getProduct_id());break;
			case 5:value=product.getProduct_name();break;
			case 6:value=product.getBrand();break;
			case 7:value=Integer.toString(product.getPrice());break;
			case 8:value=Integer.toString(product.getDiscount());break;
			case 9:value=product.getIntroduce();break;
			case 10:value=product.getDetail();break;
		}
		return value;
	}
	
}





