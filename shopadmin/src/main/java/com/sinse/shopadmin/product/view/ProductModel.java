package com.sinse.shopadmin.product.view;

import javax.swing.table.AbstractTableModel;

//JTable은 껍데기이므로, 이 모델만을 바라보고 정보를 가져감 
public class ProductModel extends AbstractTableModel{

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public int getColumnCount() {
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
	
}
