package com.sinse.shop.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.util.StringUtil;
import com.sinse.shop.home.MainPage;
import com.sinse.shop.product.model.Product;

public class ProductItem extends JPanel{
	Product product;
	Image image;
	MainPage mainPage; //현재 ProductItem 을 생성한 주체 페이지이므로...
	
	public ProductItem(MainPage mainPage, Product product) {
		this.mainPage=mainPage;
		this.product=product;
		
		try {
			image = ImageIO.read(new File("C:/public/"+product.getFilenameList().get(0)));
			image=image.getScaledInstance(210, 150, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//마우스 리스너 연결
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//어떤 상품을 선택했는지 그 정보를 보관
				mainPage.product=product;
				
				//페이지 전환
				mainPage.appMain.showPage(Config.PRODUCT_DETAIL_PAGE);
			}
		});
		
		setPreferredSize(new Dimension(220, 280));
		setBackground(Color.YELLOW);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		g2.drawImage(image, 5, 5, 210, 150, this); 
		
		//상품명 그리기 
		g2.setFont(new Font("Gulim", Font.BOLD,20));
		g2.drawString(product.getProduct_name(), 5, 180); //상품명 
		
		g2.drawString(StringUtil.getPriceString(product.getPrice()), 5, 205);//가격 
	}
	
}

















