package animation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextParticle extends JFrame implements ActionListener{

	JPanel p_north;
	JTextField t_input;
	JButton bt;
	
	JPanel p_center;
	Thread thread;
	char[] ch;
	Box[] box;
	
	
	public TextParticle() {
		p_north = new JPanel();
		
		p_center = new JPanel();
		
		t_input = new JTextField(15);
		bt = new JButton("실행");
		thread = new Thread() {
			public void run() {
				while(true) {
					
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		p_north.add(t_input);
		p_north.add(bt);
		
		p_center.setBackground(Color.YELLOW);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt.addActionListener(this);
		
		setBounds(3100,100, 700,750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		thread.start();
	}
	
	public void devideText() {
		int len=t_input.getText().length();
		
		ch = new char[len];
		box = new Box[len];
		
		//글씨 파편화 시키기
		for(int i=0;i<ch.length;i++) {
			ch[i]=t_input.getText().charAt(i);
			box[i]=new Box(ch[i], p_center);
		}		
	}
	
	public void actionPerformed(ActionEvent e) {
		devideText();
		p_center.updateUI();
	}
	
	public static void main(String[] args) {
		new TextParticle();
	}

}
