package com.sinse.shopadmin.common.view;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.config.Config;

//쇼핑몰 관리자 페이지의 최상단 객체 
public class Page extends JPanel{
	
	//모든 페이지는 AppMain 안에 소속된 페이지들이므로, 서로 공유할 데이터가 있다면 
	//AppMain을 통해서 공유하도록 한다!! 
	protected AppMain appMain;
	
	public Page(AppMain appMain) {
		this.appMain=appMain;
		setPreferredSize(new Dimension(Config.ADMINMAIN_WIDTH-Config.SIDE_WIDTH, Config.ADMINMAIN_HEIGHT-Config.UTIL_HEIGHT));
		setVisible(false);
	}
}







