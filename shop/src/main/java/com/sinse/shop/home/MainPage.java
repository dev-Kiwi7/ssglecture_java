package com.sinse.shop.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.util.ImageUtil;
import com.sinse.shop.common.view.Page;

public class MainPage extends Page{
	JPanel p_visual; //메인 비주얼 영역(메인 배너 영역 - carousel)
	JPanel p_content;//상품이 출력될 영역 
	ImageUtil imageUtil=new ImageUtil();
	Image image;
	
	public MainPage() {
		//생성
		//패널을 이름없는 익명 클래스로 재정의하는 코드를 작성...장점? 별도의 .java파일을 생성할 필요
		//없고, 내부 클래스이다 보니, 외부클래스인 MainPage의 멤버를 공유할 수 있다.
		image = imageUtil.getImage("images/banner.jpg",Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT);
		
		p_visual = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);// update() 에 지워진 배경을 스스로 복구
				
				//Toolkit 은 이미지를 구성하는 바이트 정보에 접근 불가하다..
				//BufferedImage 객체를 이용하여 얻어온 이미지는 훨씬 더 다양한 제어가 가능..
				//우리가 원하는 그림을 그리자..즉 패널의 그림을 뺏어 그리자!!
				g.drawImage(image, 0, 0, Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT, p_visual);
			}
		};
		
		p_content = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		//스타일
		p_visual.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT));
		p_content.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, 410));
		
		//자신의 크기설정 
		this.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.SHOPMAIN_HEIGHT-(Config.UTIL_HEIGHT+Config.NAVI_HEIGHT)));
		
		
		p_visual.setBackground(Color.CYAN);
		p_content.setBackground(Color.RED);
		
		
		//조립 
		add(p_visual);
		add(p_content);
		
		setVisible(true);
	}
}






