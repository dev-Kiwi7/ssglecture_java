package com.sinse.shop.product.view;

import java.awt.Color;
import java.awt.Dimension;

import com.sinse.shop.AppMain;
import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.view.Page;

public class ProductDetailPage extends Page{
	
	public ProductDetailPage(AppMain appMain) {
		super(appMain);
		
		setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.SHOPMAIN_HEIGHT-Config.UTIL_HEIGHT-Config.NAVI_HEIGHT));
		setBackground(Color.ORANGE);
		setVisible(true);
	}
}





