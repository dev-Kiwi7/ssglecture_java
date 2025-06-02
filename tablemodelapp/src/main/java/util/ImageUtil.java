package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

//어떤 프로그램이던 이미지와 관련된 유용한 기능을 이 클래스에서 제공해줌 
public class ImageUtil {
	
	//메서드를 정의하여, 어떤 애플리케이션에서건 사용할 수 있도록 처리하자 
	public Icon getIcon(String filename, int width, int height) {
		//ImageIcon 자체는 크기를 지정할 수없으므로, 아이콘 사용에 앞서, 크기 조절이 가능한 
		//이미지를 먼저 얻어야 함..
		//Toolkit 을 통해 Image 객체는 픽셀 정보까지 접근할 수 없기 때문에 단순 이미지를 보여줄때
		//이용 가치가 있음..회사의 이미지로고를 박은 워터마크 처리 등의 보다 섬세하고 기술적인 이미지 제어
		//를 하려면 , BufferedImage 를 사용해야 함
		//아이콘 얻기 
		Class myClass=getClass();
		//패키지 안에 들어있는 자원의 이름을 명시하면, URL을 반환해줌  
		URL url=myClass.getClassLoader().getResource(filename);
		
		ImageIcon icon=null;
		
		try {
			BufferedImage buffrImg=ImageIO.read(url);
			Image image=buffrImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			icon=new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon;
	} 
}








