package com.sinse.shopadmin.product.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	JButton bt_open;//파일 탐색기 띄우기 버튼
	JLabel la_introduce;
	JLabel la_detail;
	
	JComboBox cb_topcategory;
	JComboBox cb_subcategory;
	JTextField t_product_name;
	JTextField t_brand;
	JTextField t_price;
	JTextField t_color;
	JTextField t_size;
	JPanel p_preview; //관리자가 선택한 상품이미지를 미리보기 한다
	JTextArea t_introduce; //상품 소개 
	JTextArea t_detail;
	JButton bt_regist; //상품 등록 
	JButton bt_list; //상품 목록 
	
	public ProductPage(AppMain appMain) {
		super(appMain);
		setBackground(Color.CYAN);
		
		//생성 
		la_topcategory = new JLabel("최상위 카테고리");
		la_subcategory = new JLabel("최상위 카테고리");
		la_product_name = new JLabel("최상위 카테고리");
		la_brand = new JLabel("최상위 카테고리");
		la_price = new JLabel("최상위 카테고리");
		la_discount = new JLabel("최상위 카테고리");
		la_color = new JLabel("최상위 카테고리");
		la_size = new JLabel("최상위 카테고리");
		bt_open = new JButton("상품사진 등록");
		la_introduce=new JLabel("상품 소개");
		la_detail = new JLabel("상세설명");
		
		cb_topcategory = new JComboBox<>();
		cb_subcategory = new JComboBox<>();
		t_product_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		t_color = new JTextField();
		t_size = new JTextField();
		p_preview = new JPanel(); //추후 익명 내부 클래스로 전환 
		t_introduce = new JTextArea();
		t_detail = new JTextArea();
		bt_regist = new JButton("등록");
		bt_list = new JButton("목록");
		
		//스타일
		Dimension d = new Dimension(200, 30);
		la_topcategory.setPreferredSize(d);
		la_subcategory.setPreferredSize(d);
		la_product_name.setPreferredSize(d);
		la_brand.setPreferredSize(d);
		la_price.setPreferredSize(d);
		la_discount.setPreferredSize(d);
		la_color.setPreferredSize(d);
		la_size.setPreferredSize(d);
		bt_open.setPreferredSize(d);
		la_introduce.setPreferredSize(d);
		la_detail.setPreferredSize(d);
		
		cb_topcategory.setPreferredSize(d);
		cb_subcategory.setPreferredSize(d);
		t_product_name.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_color.setPreferredSize(d);
		t_size.setPreferredSize(d);
		p_preview.setPreferredSize(new Dimension(200, 80)); //이미지 미리보기 도화지..
		t_introduce.setPreferredSize(new Dimension(200, 50)); //GPT를 연동한 소개글 
		t_detail.setPreferredSize(new Dimension(200, 60));
		
		
		//조립 
		
	}
}









