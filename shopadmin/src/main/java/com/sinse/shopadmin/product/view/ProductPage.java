package com.sinse.shopadmin.product.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.view.Page;

//상품 등록 페이지 
public class ProductPage extends Page{
	JLabel la_topcategory;
	JLabel la_subcategory;
	JLabel la_product_name;
	JLabel la_brand;
	JLabel la_price;
	JLabel la_discount;
	JLabel la_color;
	JLabel la_size;
	JLabel la_photo;
	JLabel la_introduce;
	JLabel la_detail;
	
	JComboBox cb_topcategory;
	JComboBox cb_subcategory;
	JTextField t_product_name;
	JTextField t_brand;
	JTextField t_price;
	JTextField t_color;
	JTextField t_size;
	JButton bt_photo; //상품 선태창 띄우기 버튼
	JTextArea t_introduce; //상품 소개 
	JTextArea t_detail;
	
	public ProductPage(AppMain appMain) {
		super(appMain);
		setBackground(Color.CYAN);
	}
}




