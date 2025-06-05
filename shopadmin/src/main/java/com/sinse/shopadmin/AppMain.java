package com.sinse.shopadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.security.model.Admin;

public class AppMain extends JFrame{
	JPanel p_north;
	JPanel p_west; //사이드 메뉴 영역
	JPanel p_container; //페이지가 교체될 영역
	JLabel la_user;//현재 로그인 한 유저
	
	JLabel la_product;
	JLabel la_order;
	JLabel la_member;
	JLabel la_cs;
	JLabel la_config;
	Connection con;
	Admin admin;
	
	//모든 페이지를 담게될 배열 
	Page[] pages;
	
	public AppMain() {	
		p_north = new JPanel();
		p_west = new JPanel();
		p_container = new JPanel();
		
		la_user = new JLabel("scott");
		
		la_product = new JLabel("상품관리");
		la_order = new JLabel("주문관리");
		la_member = new JLabel("회원관리");
		la_cs = new JLabel("고객센터");
		la_config = new JLabel("환경설정");
		
		//스타일
		p_north.setPreferredSize(new Dimension(Config.UTIL_WIDTH, Config.UTIL_HEIGHT));
		p_north.setBackground(Color.CYAN);
		
		p_west.setPreferredSize(new Dimension(Config.SIDE_WIDTH, Config.SIDE_HEIGHT));
		p_west.setBackground(Color.YELLOW);
		
		Dimension d=new Dimension(185, 100);
		la_product.setPreferredSize(d);
		la_order.setPreferredSize(d);
		la_member.setPreferredSize(d);
		la_cs.setPreferredSize(d);
		la_config.setPreferredSize(d);
		
		Font f=new Font(null, Font.BOLD, 25);
		la_product.setFont(f);
		la_order.setFont(f);
		la_member.setFont(f);
		la_cs.setFont(f);
		la_config.setFont(f);
		
		
		
		//조립 
		p_west.add(la_product);
		p_west.add(la_order);
		p_west.add(la_member);
		p_west.add(la_cs);
		p_west.add(la_config);
		
		add(p_north, BorderLayout.NORTH);
		add(p_west, BorderLayout.WEST);
		
		//데이터베이스 접속해놓기
		connect();
		
		//모든 버튼에 이벤트 연결
		//리스너 중 재정의할 메서드가 많은 경우(3개이상 되는 경우)는 어댑터 지원을 의심해보자
		//어댑터란? 우리 대신 리스너의 메서드를 오버라이딩 해놓은 중간 객체, sun사가 개발자 배려..
		la_product.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loginCheck();
			}
		});
		la_order.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loginCheck();
			}
		});
		la_member.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loginCheck();
			}
		});
		la_cs.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loginCheck();
			}
		});
		la_config.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loginCheck();
			}
		});
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); //db연동 후엔 제거 
		setBounds(2000, 50, Config.ADMINMAIN_WIDTH, Config.ADMINMAIN_HEIGHT);
		setVisible(true);
	}
	
	
	public void connect() {
		String url="jdbc:mysql://localhost:3306/shop";
		String user="shop";
		String pass="1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			if(con !=null) {
				this.setTitle("MySQL 접속 완료");
			}else {
				this.setTitle("MySQL 미접속");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//로그인 햇는지 체크하여, 로그인 한 경우만 해당 페이지 보여주기
	public void loginCheck() {
		if(admin==null) {
			JOptionPane.showMessageDialog(this, "로그인이 필요한 서비스입니다");
		}else {
			//원래 보여주려면 페이지 보여주기
		}
	}
	
	//쇼핑몰에 사용할 모든 페이지 생성 및 부착 
	
	
	
    public static void main( String[] args ){
    	new AppMain();
    }
    
}





