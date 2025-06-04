package com.sinse.shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.view.Page;
import com.sinse.shop.home.MainPage;

public class AppMain extends JFrame{
	JPanel p_north; //p_util, p_navi 공존시켜야 하므로..
	JPanel p_util;//최상단 유틸리티 네비 영역 (서브 메뉴) 
	JPanel p_navi;//메인 네비게이션
	JPanel p_container;//모든 페이지가 전환될 컨테이너 영역 
	
	//유틸리티 네비관련 
	JLabel la_login;
	JLabel la_join;
	JLabel la_cart;
	JLabel la_wishlist;
	
	//메인 네비게이션 관련
	JLabel la_home;
	JLabel la_category;
	JLabel la_new;
	JLabel la_best;
	JLabel la_cs;
	
	Page[] pages;
	
	public AppMain() {
		//생성 
		p_north = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		p_util = new JPanel(new FlowLayout(FlowLayout.RIGHT));//패널 내에서의 우측정렬
		p_navi = new JPanel();
		p_container = new JPanel();
		
		la_login = new JLabel("Login");
		la_join = new JLabel("Join");
		la_cart = new JLabel("Cart");
		la_wishlist = new JLabel("WishList");
		
		la_home = new JLabel("Home");
		la_category = new JLabel("Category");
		la_new = new JLabel("New Arrivals");
		la_best = new JLabel("Best");
		la_cs = new JLabel("CS");
		
		
		//스타일
		p_util.setBackground(Color.YELLOW);
		p_navi.setBackground(Color.PINK);
		p_container.setBackground(Color.GREEN);
		p_north.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.UTIL_HEIGHT+Config.NAVI_HEIGHT));
		p_util.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.UTIL_HEIGHT));
		p_navi.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.NAVI_HEIGHT));
		p_container.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH , 810));
		
		//조립
		p_util.add(la_login);
		p_util.add(la_join);
		p_util.add(la_cart);
		p_util.add(la_wishlist);

		p_navi.add(la_home);
		p_navi.add(la_category);
		p_navi.add(la_new);
		p_navi.add(la_best);
		p_navi.add(la_cs);
		
		p_north.add(p_util);
		p_north.add(p_navi);
		add(p_north, BorderLayout.NORTH);
		add(p_container);
		
		createPage(); //앱이 가동될때 모든 페이지 생성 및 부착 
		
		//그 부착된 페이지 들 중, 보고싶은 페이지의 index를 넘기자
		showPage(Config.MAIN_PAGE);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); //db연동 후엔 지우기
		setBounds(2000, 50, 1400, 900);
		setVisible(true);
	}
	
	//쇼핑몰의 모든 페이지를 생성하여 부착!!
	public void createPage() {
		//배열 생성
		pages=new Page[1]; //본인이 만든 페이지 수로 추후 대체...
		
		//페이지 생성 
		pages[Config.MAIN_PAGE]=new MainPage();
		
		//모든 페이지를 대상으로 p_container에 부착!!
		for(int i=0;i<pages.length;i++) {
			p_container.add(pages[i]);
		}
	}
	
	//원하는 페이지를 보여주는 메서드 정의 
	public void showPage(int target) {
		//반복문의 대상이 되려면 모든 페이지는 같은 자료형의 배열로 존재해야 함...
		//따라서 Page 라는 최상위 객체를 만들엇음
		for(int i=0;i<pages.length;i++) {
			pages[i].setVisible( (i==target)? true:false);
		}
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
}









