package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingPlay extends JFrame{
	JButton bt_rec;
	JButton bt_play;
	JPanel p_north;
	JPanel p_center;
	int x;
	int y;
	
	public DrawingPlay() {
		bt_rec=new JButton("Rec");
		bt_play=new JButton("Play");
		p_north = new JPanel();
		
		
		p_center = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.RED);
				g.drawOval(x, y, 3, 3);
			}
		};
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				x=e.getPoint().x;
				y=e.getPoint().y;
				System.out.println(x);
				p_center.repaint();
			}

		});
		
		p_north.add(bt_rec);
		p_north.add(bt_play);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new DrawingPlay();

	}

}
