package com.sinse.threadapp.ani;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

//스윙의 UI 컴포넌트 중 , 진행율을 표현하는 컴포넌트인 JProgressBar 를 사용해보자  
public class ProgressTest extends JFrame{
	JProgressBar bar1;
	JProgressBar bar2;
	JProgressBar bar3;
	
	JButton bt;
	
	//독립적으로 실행할 단위인 쓰레드 3개 준비
	ProgressThread t1;
	ProgressThread t2;
	ProgressThread t3;
	
	public ProgressTest() {
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		bt = new JButton("진행");
		
		//스타일 
		Dimension d = new Dimension(750, 45);
		bar1.setPreferredSize(d);
		bar2.setPreferredSize(d);
		bar3.setPreferredSize(d);
		
		setLayout(new FlowLayout());
		
		add(bar1);
		add(bar2);
		add(bar3);
		add(bt);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1=new ProgressThread(bar1,1);
				t2=new ProgressThread(bar2,5);
				t3=new ProgressThread(bar3,8);
				
				t1.start();
				t2.start();
				t3.start();
			}
		});
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		new ProgressTest();
	}
	
}




