package com.sinse.dbproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*JTable은 껍데기에 불과하므로, 연동할 테이블이 수백개라 할지라도
 * TableModel은 1개만 충분하다..결국 바뀌는 건 쿼리문만...

 WindowListener를 포함하여, 이벤트 리스너 중 재정의할 메서드의 수가 너무 많은경우
 사용하지도 않는 부모의 메서드를 클래스 코드 안에 남겨놔야 하는 상황이 발생하게 됨...
 
 * */


public class EmpLoad extends JFrame implements WindowListener{
	JPanel p_north;
	JButton bt_emp;
	JButton bt_dept;
	
	JTable table;
	JScrollPane scroll;
	
	//윈도우 프레임이 뜰때 한번 접속하고, 윈도우 닫을때 데이터베이스 닫자
	String url="jdbc:mysql://localhost:3306/dev";
	String user="java";
	String pwd="1234";
	Connection con; //윈도우 창 닫으면 접속을 끊어야 하므로, 멤버변수로 빼두어야 함
							//Connection은 접속 정보를 가진 객체이므로, 접속을 끊을 수도 있다
	
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
		//이벤트 구현 시 정의되는 리스너 클래스는 재사용성이 없으므로, 굳이 
		//.java 파일까지 정의해가면서 개발할 필요가 있는가? 
		//내부클래스 중, 이름 없는 클래스를 가리켜 익명내부 클래스라 한다.
		//주로, 일회성 객체 사용시(이벤트)
		//익명 내부클래스는, 자신을 감싸고 있는 바깥쪽 외부 클래스의 멤버들을 
		//같이 사용할 수 있다..즉 접근할 수 있다는 점이 장점
		bt_emp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//System.out.println("나 눌렀어?");
				loadEmp();
			}
		});
		
		connect();//db접속 
		
		setSize(800, 630);
		setVisible(true);
	}
	
	//데이터베이스 접속 
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pwd);
			if(con!=null) {
				this.setTitle("접속 성공");
			}else {
				this.setTitle("접속 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//사원 테이블 가져오기 
	public void loadEmp() {

		
		
	}
	
	public static void main(String[] args) {
		new EmpLoad();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}












