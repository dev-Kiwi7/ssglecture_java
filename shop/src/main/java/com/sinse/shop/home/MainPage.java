package com.sinse.shop.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.view.Page;

public class MainPage extends Page{
	JPanel p_visual; //메인 비주얼 영역(메인 배너 영역 - carousel)
	JPanel p_content;//상품이 출력될 영역 
	
	public MainPage() {
		//생성 
		p_visual = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
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






