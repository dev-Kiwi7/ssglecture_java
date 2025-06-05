package com.sinse.threadapp.ani;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//사각형을 이동시켜보자(지우고 그리는 작업을 연속적으로 시도해보자)
public class RectMove extends JFrame{
	JButton bt;
	JPanel p_north;
	JPanel p_center;
	int x;
	int y;
	Thread thread;
	
	public RectMove() {
		bt = new JButton("동작");
		p_north = new JPanel();
		
		p_center = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.setColor(Color.BLUE);
				g.fillRect(x, y, 50, 50);
			}
		};
		
		thread = new Thread() {
			//개발자는 쓰레드로 구현하고 싶은 코드를 run() 메서드 안에 넣어두어야 한다..
			public void run() {
				while(true) {
					move();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}			
		};
		
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread.start();
			}
		});
		
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void move() {
		x++;
		y++;
		//다시 그려라!!!
		p_center.repaint(); //update() 싹 지우고, paint() 호출
	}
	
	public static void main(String[] args) {
		new RectMove();
	}
}








