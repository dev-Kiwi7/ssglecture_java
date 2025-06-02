package graphic;

import java.awt.Graphics;

import javax.swing.JPanel;

//그림을 뺏으려면, 메서드를 오버라이딩 해야 하므로,.java 로 별도 정의해야 함
public class MyPanel extends JPanel{
	AniTest aniTest;
	
	//나를 생성하는 자는 AniTest의 주소값을 넘겨달라~~
	public MyPanel(AniTest aniTest) {
		this.aniTest=aniTest;
	}
	
	public void paint(Graphics g) {
		super.paint(g); //지우지 말기...
		g.drawRect(aniTest.x, aniTest.y, 200, 200);
	}
	
}








