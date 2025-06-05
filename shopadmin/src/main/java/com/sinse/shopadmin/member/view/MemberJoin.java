package com.sinse.shopadmin.member.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.util.StringUtil;
import com.sinse.shopadmin.common.view.Page;

//관리자 등록 페이지 
public class MemberJoin extends Page{
	JLabel la_id;
	JLabel la_pwd;
	JLabel la_name;
	JLabel la_email;
	
	JTextField t_id;
	JPasswordField t_pwd;
	JTextField t_name;
	JTextField t_email;
	
	JButton bt_login;
	JButton bt_join;
	
	public MemberJoin(AppMain appMain) {
		super(appMain);
		
		la_id = new JLabel("ID");
		la_pwd = new JLabel("Password");
		la_name = new JLabel("Name");
		la_email = new JLabel("Email");

		t_id = new JTextField();
		t_pwd = new JPasswordField();
		t_name = new JTextField();
		t_email = new JTextField();
		
		bt_login = new JButton("Login");
		bt_join = new JButton("Join");
		
		Dimension d= new Dimension(200, 35);
		
		la_id.setPreferredSize(d);
		la_pwd.setPreferredSize(d);
		la_name.setPreferredSize(d);
		la_email.setPreferredSize(d);
		t_id.setPreferredSize(d);
		t_pwd.setPreferredSize(d);
		la_name.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_email.setPreferredSize(d);
		
		//부모님의 사이즈 무시하고 회원가입 페이지의 사이즈는 별도로 정의..
		this.setPreferredSize(new Dimension(450, 300));
		
		add(la_id);
		add(t_id);
		add(la_pwd);
		add(t_pwd);
		add(la_name);
		add(t_name);
		add(la_email);
		add(t_email);
		add(bt_login);
		add(bt_join);
		
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appMain.showPage(Config.LOGIN_PAGE);
			}
		});
		
		//가입 버튼에 리스너연결 
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
	}
	
	//관리자 정보를 데이터베이스에 넣기
	public void regist() {
		//데이터베이스에 넣기 전에, 폼양식을 제대로 작성했는지 여부를 체크해보자
		//유효성 체크 
		if(t_id.getText().length() <1) {
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
		}else if(t_pwd.getPassword().length<1) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
		}else if(t_name.getText().length()<1) {
			JOptionPane.showMessageDialog(this, "이름을 입력하세요");
		}else if(t_email.getText().length()<1) {
			JOptionPane.showMessageDialog(this, "이메일을 입력하세요");
		}else {
			//mysql에 insert
			StringBuffer sql=new StringBuffer();
			sql.append("insert into admin(id, pwd, name, email)");
			sql.append(" values(?,?,?,?)");
			
			PreparedStatement pstmt=null;
			try {
				pstmt=appMain.con.prepareStatement(sql.toString());
				pstmt.setString(1, t_id.getText()); //사용자가 입력한 아이디값 
				pstmt.setString(2, StringUtil.getSecuredPass(new String(t_pwd.getPassword())));
				pstmt.setString(3, t_name.getText());
				pstmt.setString(4, t_email.getText());
				
				int result=pstmt.executeUpdate(); //DML 실행
				if(result>0) {
					JOptionPane.showMessageDialog(this, "관리자 가입 성공");
				}else {
					JOptionPane.showMessageDialog(this, "등록 실패");
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			
		}
	}
	
}


















