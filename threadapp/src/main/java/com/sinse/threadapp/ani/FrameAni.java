package com.sinse.threadapp.ani;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameAni extends JFrame{
	JPanel p_center;
	
	Image[] imgArray=new Image[18];
	int n;
	Thread thread;
	
	public FrameAni() {
		createImage();//패널이 그림을 그리기 전에 배열을 완성해야 하므로..
		
		p_center = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(imgArray[n] , 100, 100, 200, 213, p_center);
			}
		};
		
		thread = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
						n++;
						if(n>=imgArray.length)n=1;
						p_center.repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		thread.start(); //jvm 에게 runnable 상태로의 진입을 부탁!! -->  jvm 는 os에게 native
								//쓰레드 생성을 요청
		
		add(p_center);
		
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void createImage() {
		for(int i=0;i<18;i++) {
			URL url=this.getClass().getClassLoader().getResource("hero/image"+(i+1)+".png");
			
			try {
				BufferedImage buffrImg=ImageIO.read(url);
				Image img=buffrImg.getScaledInstance(213, 200, Image.SCALE_SMOOTH);
				imgArray[i]=img; //배열의 i번째 요소로, 이미지 넣기				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new FrameAni();
	}
}






