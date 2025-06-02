package com.sinse.dbproject;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*JTable은 껍데기에 불과하므로, 연동할 테이블이 수백개라 할지라도
 * TableModel은 1개만 충분하다..결국 바뀌는 건 쿼리문만...
 * */
public class EmpLoad extends JFrame{
	JPanel p_north;
	JButton bt_emp;
	JButton bt_dept;
	
	JTable table;
	JScrollPane scroll;
	
	public EmpLoad() {
		p_north = new JPanel();
		bt_emp = new JButton("사원테이블 로드");
		bt_dept = new JButton("부서테이블 로드");
		
		table = new JTable();
		scroll = new JScrollPane(table);
		
		//style 
		p_north.setPreferredSize(new Dimension(800, 30));
		
		//assemble
		p_north.add(bt_emp);
		p_north.add(bt_dept);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		setSize(800, 630);
		setVisible(true);
	}
	public static void main(String[] args) {
		new EmpLoad();
	}
}










