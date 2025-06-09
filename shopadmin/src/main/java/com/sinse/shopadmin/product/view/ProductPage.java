package com.sinse.shopadmin.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.product.model.TopCategory;

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
	
	JComboBox<TopCategory> cb_topcategory;
	JComboBox cb_subcategory;
	JTextField t_product_name;
	JTextField t_brand;
	JTextField t_price;
	JTextField t_discount;
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
		la_subcategory = new JLabel("하위 카테고리");
		la_product_name = new JLabel("상품명");
		la_brand = new JLabel("브랜드");
		la_price = new JLabel("가격");
		la_discount = new JLabel("할인가");
		la_color = new JLabel("색상");
		la_size = new JLabel("사이즈");
		bt_open = new JButton("상품사진 등록");
		la_introduce=new JLabel("상품 소개");
		la_detail = new JLabel("상세설명");
		
		cb_topcategory = new JComboBox<>();
		cb_subcategory = new JComboBox<>();
		t_product_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		t_discount = new JTextField();
		t_color = new JTextField();
		t_size = new JTextField();
		p_preview = new JPanel(); //추후 익명 내부 클래스로 전환 
		t_introduce = new JTextArea();
		t_detail = new JTextArea();
		bt_regist = new JButton("등록");
		bt_list = new JButton("목록");
		
		//스타일
		Dimension d = new Dimension(400, 30);
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
		t_discount.setPreferredSize(d);
		t_color.setPreferredSize(d);
		t_size.setPreferredSize(d);
		p_preview.setPreferredSize(new Dimension(400, 80)); //이미지 미리보기 도화지..
		t_introduce.setPreferredSize(new Dimension(400, 50)); //GPT를 연동한 소개글 
		t_detail.setPreferredSize(new Dimension(400, 60));
		
		bt_regist.setPreferredSize(new Dimension(130, 30));
		bt_list.setPreferredSize(new Dimension(130, 30));
		
		//조립 
		add(la_topcategory);
		add(cb_topcategory);
		add(la_subcategory);
		add(cb_subcategory);
		add(la_product_name);
		add(t_product_name);
		add(la_brand);
		add(t_brand);
		add(la_price);
		add(t_price);
		add(la_discount);
		add(t_discount);
		add(la_color);
		add(t_color);
		add(la_size);
		add(t_size);
		add(bt_open);
		add(p_preview);
		add(la_introduce);
		add(t_introduce);
		add(la_detail);
		add(t_detail);
		add(bt_regist);
		add(bt_list);
		
		setPreferredSize(new Dimension(880, 750));
		
		//최상위 카테고리에 이벤트 연결 
		cb_topcategory.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("다른 아이템을 선택햇어?");
						
					TopCategory topCategory=(TopCategory)cb_topcategory.getSelectedItem();
					int topcategory_id=topCategory.getTopcategory_id();
					System.out.println(topcategory_id);
					
				}
			}
		});
		
		//최상위 카테고리 불러오기 
		getTopCategory();
		
		
		
	}
	
	public void getTopCategory() {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from topcategory");
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=appMain.con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();//select 문일 경우..
			
			//한칸씩 커서를 이동하면서, 콤포박스에 채워넣기 
			while(rs.next()) {
				//상위 카테고리 레코드 한건은, 이름만을 보유한 데이터가 아니라, 2개의 컬럼을 갖는 복합 데이터이므로
				//자바의 객체로 담아버리자 !! 
				TopCategory topCategory = new TopCategory();
				//은닉화된 객체의 데이터를 넣을때는 setter로 넣어줘야 함 
				topCategory.setTopcategory_id(rs.getInt("topcategory_id"));   //pk
				topCategory.setTop_name(rs.getString("top_name"));  //top_name
				
				cb_topcategory.addItem( topCategory);//String 을 넣지말고, 더 많은 데이터를 안고있는
																		//모델 객체를 넣어버림
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
	}
}













