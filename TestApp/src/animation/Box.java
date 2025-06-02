package animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class Box extends JPanel{
	char ch;
	int x;
	int y;
	JPanel p_center;
	
	public Box(char ch, JPanel p_center) {
		this.ch=ch;
		this.p_center=p_center;
		
		this.x=(int)Math.floor( (Math.random()*650)) ;
		this.y=(int)Math.floor( (Math.random()*650));
		
		p_center.add(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		

        // 회전 중심 설정 (예: 패널 중심)
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // 현재 그래픽 상태 저장
        AffineTransform old = g2d.getTransform();

        // 회전 각도 설정 (45도 회전 = Math.PI / 4)
        g2d.rotate(Math.PI / 4, centerX, centerY);

        // 회전된 도형 그리기 (예: 사각형)
        g2d.setColor(Color.RED);
        //g2d.fillRect(centerX - 50, centerY - 50, 100, 100);
        g2d.drawString(Character.toString(ch) ,x, y);

        // 그래픽 상태 복원
        g2d.setTransform(old);		
	}
	
}


