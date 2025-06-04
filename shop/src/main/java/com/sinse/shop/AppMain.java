package com.sinse.shop;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppMain extends JFrame{
	JPanel p_util;//최상단 유틸리티 네비 영역 (서브 메뉴) 
	JPanel p_navi;//메인 네비게이션
	JPanel p_container;//모든 페이지가 전환될 컨테이너 영역 
	
	public AppMain() {
		//생성 
		p_util = new JPanel();
		p_navi = new JPanel();
		p_container = new JPanel();
		
		//스타일
		p_util.setBackground(Color.YELLOW);
		p_navi.setBackground(Color.PINK);
		p_container.setBackground(Color.GREEN);
		p_util.setPreferredSize(new Dimension(1400, 40));
		p_navi.setPreferredSize(new Dimension(1400, 50));
		p_container.setPreferredSize(new Dimension(1400, 810));
		
		
		//조립 
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); //db연동 후엔 지우기
		setBounds(2000, 50, 1400, 900);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
}









