package com.sinse.shop.product.model;

import java.util.List;

//멤버변수가 db 컬럼명과 일치하도록 한다
public class Product {
	private int product_id;
	private String product_name;
	private String brand;
	private int price;
	private int discount;
	private List<Size> sizeList; //이 상품이 지원하는 사이즈 정보
	private List<Color> colorList;//이 상품이 지원하는 색상 
	private List<String> filenameList;//이 상품에 등록된 이미지명  
	private String introduce;
	private String detail;
	private SubCategory subCategory; //ERD상에서는 자식이 부모의 pk를 숫자로 보유하지만
														//자바 코드에서는 부모를 객체로 보유해야 한다..	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public List<Size> getSizeList() {
		return sizeList;
	}
	public void setSizeList(List<Size> sizeList) {
		this.sizeList = sizeList;
	}
	public List<Color> getColorList() {
		return colorList;
	}
	public void setColorList(List<Color> colorList) {
		this.colorList = colorList;
	}
	public List<String> getFilenameList() {
		return filenameList;
	}
	public void setFilenameList(List<String> filenameList) {
		this.filenameList = filenameList;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	
	
}






