package com.sinse.shop.product.model;

//오직 레코드 1건을 담기 위한 객체를 모델 객체라 한다.
public class SubCategory {
	//데이터베이스의 컬럼명과 일치하는 멤버변수를 보유하고, 은닉화 시켜야 함 
	private int subcategory_id;
	private String sub_name;
	private TopCategory topcategory; //데이터베이스에서 부모를 표현한 모델을 자식이 보유..

	public int getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(int subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public TopCategory getTopcategory() {
		return topcategory;
	}
	public void setTopcategory(TopCategory topcategory) {
		this.topcategory = topcategory;
	}

	@Override
	public String toString() {
		return sub_name;
	}
	
}












