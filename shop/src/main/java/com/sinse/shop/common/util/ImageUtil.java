package com.sinse.shop.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sinse.shop.common.config.Config;

//이미지와 관련된 유용한 공통 기능을 제공해주는 유틸 클래스 
public class ImageUtil {

	//클래스패스로 부터 이미지를 반환해주는 메서드 
	public Image getImage(String filename, int width, int height) {
		//패키지경로로 부터 이미지 얻어오기 
		//패키지 경로는 URL 로 이미지를 얻어와야 함 
		URL url=this.getClass().getClassLoader().getResource(filename);
		Image image=null;
		
		try {
			BufferedImage buffrImg=ImageIO.read(url);
			image=buffrImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;		
	}
	
	
	//클래스패스로 부터 아이콘을 반환해주는 메서드 
	
	
}




