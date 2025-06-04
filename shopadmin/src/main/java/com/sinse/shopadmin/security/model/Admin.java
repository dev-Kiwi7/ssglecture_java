package com.sinse.shopadmin.security.model;

//이 클래스의 정의 목적은 로직을 작성하기 위함이 아니라, 데이터베이스의 한 사람의 
//정보를 담기 위한 객체일 뿐이다..이러한 목적의 클래스를 가리켜 모델 객체...
//멤버변수는 데이터베이스의 테이블의 컬럼명과 매칭되어야 하고, 이 클래스는 은닉화 되어야 한다..
public class Admin {
	private int admin_id;
	private String id;
	private String pwd;
	private String name;
	
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
