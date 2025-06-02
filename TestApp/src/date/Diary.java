package date;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Diary extends JFrame{
	JPanel p_north;
	JPanel p_days;
	JPanel p_center; //wrapper
	JPanel p_content;
	
	public Diary() {
		p_north = new JPanel();
		p_days = new JPanel();
		p_center = new JPanel();
		p_content = new JPanel(new GridLayout(6,7));

		//스타일
		p_north.setPreferredSize(new Dimension(700, 100));
		p_days.setPreferredSize(new Dimension(700, 50));
		p_content.setPreferredSize(new Dimension(700, 600));
		
		p_north.setBackground(Color.ORANGE);
		p_days.setBackground(Color.pink);
		
		
		//조립
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		createCell();//셀 생성
		p_center.add(p_content);
		
		setSize(714,750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void createCell() {
		for(int i=0;i<6;i++) {
			for(int a=0;a<7;a++) {
				Cell cell = new Cell();
				p_content.add(cell);
			}
		}
	}
	
	public static void main(String[] args) {
		new Diary();
	}

}
