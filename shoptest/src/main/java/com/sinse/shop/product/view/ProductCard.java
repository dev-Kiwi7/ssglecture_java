package com.sinse.shop.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sinse.shop.product.model.Product;

public class ProductCard extends JPanel{
	Product product;
	
	public ProductCard(Product product) {
		this.product=product;
		
		setPreferredSize(new Dimension(150, 250));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();

        // 안티앨리어싱 적용 (부드럽게)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 배경 색
        g2.setColor(Color.YELLOW);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        //상품 이미지 
        //ImageIO.read(new File("C://public/"+product.get));
        //g2.drawImage(null, ALLBITS, ABORT, WIDTH, HEIGHT, getFocusCycleRootAncestor())
        
        g2.dispose();		
	}
}
