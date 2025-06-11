package com.sinse.shopadmin.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

//API 자체적으로 지원해주는 진행바는 너무 단순하므로, 우리가 원하는 스타일의 바로 
//커스텀 해보자 
public class MyBar extends JProgressBar{
	public MyBar() {
		setPreferredSize(new Dimension(680, 50));
		setBorder(new TitledBorder("지난해 단합대회.jpg ---> 237489234.jpg"));
		setStringPainted(true);//진행바 중앙에 텍스트 띄우기
		setFont(new Font("Verdana", Font.BOLD, 17));
		setForeground(Color.BLUE);
		this.setValue(30);
	}
}








