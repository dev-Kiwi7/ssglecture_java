package com.sinse.shopadmin.product.view;

import java.awt.FlowLayout;

import javax.swing.JDialog;

public class UploadDialog extends JDialog{
	ProductPage productPage;
	
	public UploadDialog(ProductPage productPage) {
		this.productPage=productPage;
		
		JDialog dialog=new JDialog(this, "업로드 정보", true);
		dialog.setLocationRelativeTo(productPage);
		dialog.setSize(700, 600);
		dialog.setLayout(new FlowLayout());
		
		//커스텀 된 바를 임시로 6개 화면에 부착해보자
		for(int i=0;i<productPage.files.length;i++) {
			MyBar bar = new MyBar();
			dialog.add(bar);
		}
		
		dialog.setVisible(true);
	}
}





