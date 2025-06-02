package com.sinse.dbproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//프레임에서 귀달지 않고 별도의 클래스에서 리스너 구현해보자 
public class MyActionListener implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("나 눌렀어?");
	}
}
