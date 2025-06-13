package com.sinse.shopadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.config.view.ConfigPage;
import com.sinse.shopadmin.cs.view.CustomerPage;
import com.sinse.shopadmin.main.view.MainPage;
import com.sinse.shopadmin.member.view.MemberJoin;
import com.sinse.shopadmin.member.view.MemberPage;
import com.sinse.shopadmin.order.view.OrderPage;
import com.sinse.shopadmin.product.view.ProductListPage;
import com.sinse.shopadmin.product.view.ProductPage;
import com.sinse.shopadmin.security.LoginForm;
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
	
	
	DBManager dbManager=DBManager.getInstance();
	public Connection con;
	
	public Admin admin=new Admin(); //추후 제거될 예정
	
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
		la_config = new JLabel("쇼핑몰관리");
		
		//스타일
		p_north.setPreferredSize(new Dimension(Config.UTIL_WIDTH, Config.UTIL_HEIGHT));
		p_north.setBackground(Color.CYAN);
		
		p_west.setPreferredSize(new Dimension(Config.SIDE_WIDTH, Config.SIDE_HEIGHT));
		p_west.setBackground(Color.YELLOW);
		
		p_container.setPreferredSize(new Dimension(Config.ADMINMAIN_WIDTH-Config.SIDE_WIDTH, Config.ADMINMAIN_HEIGHT-Config.UTIL_HEIGHT));
		p_container.setBackground(Color.PINK);
		
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
		add(p_container);
		
		//데이터베이스 접속해놓기
		connect();
		
		//모든 버튼에 이벤트 연결
		//리스너 중 재정의할 메서드가 많은 경우(3개이상 되는 경우)는 어댑터 지원을 의심해보자
		//어댑터란? 우리 대신 리스너의 메서드를 오버라이딩 해놓은 중간 객체, sun사가 개발자 배려..
		la_product.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPage(Config.PRODUCT_PAGE);
			}
		});
		la_order.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPage(Config.ORDER_PAGE);
			}
		});
		la_member.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPage(Config.MEMBER_PAGE);
			}
		});
		la_cs.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPage(Config.CUSTOMER_PAGE);
			}
		});
		la_config.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPage(Config.CONFIG_PAGE);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//데이터베이스 접속 끊기
				dbManager.release(con);
				System.exit(0);//프로세스 종료
			}
		});
		
		createPage();
		showPage(-1); //최초에는 로그인폼은 기본으로 떠있게 처리 
		
		setBounds(2000, 50, Config.ADMINMAIN_WIDTH, Config.ADMINMAIN_HEIGHT);
		setVisible(true);
	}
	
	
	public void connect() {
		con=dbManager.getConnetion();		
	}
	
	//쇼핑몰에 사용할 모든 페이지 생성 및 부착 
	public void createPage() {
		pages = new Page[9];
		
		pages[0]=new LoginForm(this);
		pages[1]=new MainPage(this);
		pages[2]=new ProductPage(this);
		pages[3]=new OrderPage(this);
		pages[4]=new MemberPage(this);
		pages[5]=new CustomerPage(this);
		pages[6]=new ConfigPage(this);
		pages[7]=new MemberJoin(this);
		pages[8]=new ProductListPage(this);
		
		for(int i=0;i<pages.length;i++) {
			p_container.add(pages[i]);
		}
	}
	
	//부착된 페이지들을 대상으로, 어떤 페이지를 보여줄지를 결정하는 메서드 
	public void showPage(int target) {
		//로그인 체크 
		if(admin==null && target !=-1 && target!=Config.JOIN_PAGE
				&& target!=Config.LOGIN_PAGE) {
			JOptionPane.showMessageDialog(this, "로그인이 필요한 서비스입니다");
			pages[Config.LOGIN_PAGE].setVisible(true);
			return;
		}
		for(int i=0;i<pages.length;i++) {
			pages[i].setVisible((i==target)? true:false);
		}
	}
	
    public static void main( String[] args ){
    	new AppMain();
    }
    
}





