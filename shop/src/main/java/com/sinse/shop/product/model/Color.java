package com.sinse.shop.product.model;

//모델 객체는, 로직이 전혀없고, 그냥 테이블에 1:1 매핑되어 데이터를 담거나 전달하는 역할
public class Color {
	//따라서 멤버변수명도 아무거나 부여하는게 아니라, 테이블의 컬럼명과 일치해야 한다..
	private int color_id;
	private String color_name;
	
	public int getColor_id() {
		return color_id;
	}
	public void setColor_id(int color_id) {
		this.color_id = color_id;
	}
	public String getColor_name() {
		return color_name;
	}
	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}
	
	@Override
	public String toString() {
		return color_name;
	}
}
