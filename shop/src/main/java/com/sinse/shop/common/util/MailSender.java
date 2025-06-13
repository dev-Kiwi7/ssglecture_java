package com.sinse.shop.common.util;

import java.util.Properties;

import com.sinse.shop.common.exception.EmailException;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

//이메일 보내주는 객체
public class MailSender {
	String account_user="devlearncampus@gmail.com"; //구글의 이메일 계정 
	String app_pwd="esxi jald yvbl xyiw"; //내가 받은 앱비밀번호 
	Session session;
	
	public MailSender() {
		//key-value map 의 자식 객체 
		Properties props=new Properties();
		props.put("mail.smtp.auth" , "true");
		//TLS 는 (Transport Layer Security) 데이터를 암호화해서 안전하게 전송하는 통신 프로토콜
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com"); //구글 보내는 메일 서버
		props.put("mail.smtp", "587");
		
		session=Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account_user, app_pwd);
			}
		});
	}
	
	public void send(String targetMail, String title, String content) throws EmailException{
		
		try {
			//메일의 내용 구성하기 
			Message message  = new MimeMessage(session);
			message.setFrom(new InternetAddress(account_user)); //보내는 사람 메일 
			message.setRecipients(Message.RecipientType.TO , InternetAddress.parse(targetMail) ); //받을 사람 
			message.setSubject(title);//메일 제목 
			message.setText(content);//메일 내용 
			
			//메일 전송 
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
			throw new EmailException("메일 발송 실패", e);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new EmailException("메일 발송 실패", e);
		}
		
	}
}





