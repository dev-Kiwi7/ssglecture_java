package com.sinse.shopadmin.common.config;

//유저용 쇼핑몰에서 사용되는 모든 상수를 관리하는 클래스  
public class Config {
	
	public static final String url="jdbc:mysql://localhost:3306/shop";
	public static final String user="shop";
	public static final String pass="1234";	
	
	public static final String PRODUCT_IMAGE_PATH="C:\\public";
	
	/*---------------------------------------------
	페이지 정의 
	---------------------------------------------*/
	public static final int LOGIN_PAGE=0;//로그인 페이지 
	public static final int MAIN_PAGE=1; //페이지(통계)
	public static final int PRODUCT_PAGE=2; //상품 등록 페이지
	public static final int ORDER_PAGE=3; //주문 관리 페이지
	public static final int MEMBER_PAGE=4; //회원 관리 페이지
	public static final int CUSTOMER_PAGE=5; //고객센터 관리 페이지
	public static final int CONFIG_PAGE=6;
	public static final int JOIN_PAGE=7; //관리자 가입 페이지
	public static final int PRODUCT_LIST_PAGE=8; //상품 목록 페이지
	
	
	/*---------------------------------------------
	관리자 앱 메인 설정 
	---------------------------------------------*/
	public static final int ADMINMAIN_WIDTH=1300;
	public static final int ADMINMAIN_HEIGHT=900;
	public static final int UTIL_WIDTH=ADMINMAIN_WIDTH;
	public static final int UTIL_HEIGHT=50;
	public static final int SIDE_WIDTH=200;
	public static final int SIDE_HEIGHT=ADMINMAIN_HEIGHT-UTIL_HEIGHT;

}





