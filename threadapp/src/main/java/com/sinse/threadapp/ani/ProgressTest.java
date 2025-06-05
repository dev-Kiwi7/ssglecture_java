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
	int n;
	Thread thread;
	boolean flag=true;
	
	public ProgressTest() {
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		bt = new JButton("진행");
		
		thread = new Thread() {
			public void run() {
				while(flag) {
					try {
						Thread.sleep(100);
						move();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
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
				thread.start(); //jvm에게 맡기기 runnable 상태로 들어가도록..
			}
		});
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void move() {
		//bar1 증가 
		n+=2;
		bar1.setValue(n);
	}
	
	public static void main(String[] args) {
		new ProgressTest();
	}
	
}




