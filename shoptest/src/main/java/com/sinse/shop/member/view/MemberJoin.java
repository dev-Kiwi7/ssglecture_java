package com.sinse.shop.member.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sinse.shop.AppMain;
import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.exception.EmailException;
import com.sinse.shop.common.exception.MemberException;
import com.sinse.shop.common.util.MailSender;
import com.sinse.shop.common.view.Page;
import com.sinse.shop.member.model.Member;
import com.sinse.shop.member.repository.MemberDAO;

public class MemberJoin extends Page{
	
	JPanel p_container;
	JTextField t_id;
	JPasswordField t_pwd;
	JTextField t_name;
	JTextField t_email;
	JButton bt;
	MemberDAO memberDAO;
	MailSender mailSender;
	
	public MemberJoin(AppMain appMain) {
		super(appMain);
		
		//생성 
		p_container = new JPanel();
		t_id = new JTextField();
		t_pwd = new JPasswordField();
		t_name = new JTextField();
		t_email = new JTextField();
		bt = new JButton("JOIN");
		memberDAO=new MemberDAO();
		mailSender = new MailSender();
		
		//스타일
		p_container.setPreferredSize(new Dimension(300, 200));
		Dimension d = new Dimension(220,28);
		
		t_id.setPreferredSize(d);
		t_pwd.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_email.setPreferredSize(d);
		bt.setPreferredSize(d);
		
		//조립 
		p_container.add(t_id);
		p_container.add(t_pwd);
		p_container.add(t_name);
		p_container.add(t_email);
		p_container.add(bt);
		
		add(p_container);
		
		bt.addActionListener(e->{
			join();
		});
		
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.SHOPMAIN_HEIGHT- Config.NAVI_HEIGHT-Config.UTIL_HEIGHT));
		setVisible(true);
	}
	public void join() {
		Member member = new Member();
		member.setId(t_id.getText());
		member.setPwd(new String(t_pwd.getPassword()));
		member.setName(t_name.getText());
		member.setEmail(t_email.getText());//실제 사용할 이메일 
		
		try {
			memberDAO.insert(member);
			//이메일 전송
			mailSender.sendHtml(member.getEmail(), "회원가입 축하드립니다", "저희 회원이 되어 주셔서 감사합니다");
			JOptionPane.showMessageDialog(this, "회원가입 완료");	
		} catch (MemberException | EmailException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
}	









