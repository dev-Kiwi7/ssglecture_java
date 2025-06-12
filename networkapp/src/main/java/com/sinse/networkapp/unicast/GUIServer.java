package com.sinse.networkapp.unicast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIServer extends JFrame implements Runnable{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	Thread thread; //서버 가동시, 메인 쓰레드가 accept()에서 대기 상태에 빠지지 않게 하기 위해
	ServerSocket server;
	
	BufferedReader buffr; //듣기용 스트림
	BufferedWriter buffw;//말하기용 스트림 
	
	public GUIServer() {
		p_north = new JPanel();
		t_port = new JTextField("9999", 8);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		area.setBackground(Color.YELLOW);
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
	
		bt.addActionListener(e->{
			thread = new Thread(GUIServer.this);//runnable 구현자를 대입하면, 
																	//쓰레드 start 시 구현자의 run()을 호출
			thread.start();
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setBounds(2300, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 생성 및 접속자 청취 중...\n");
			
			Socket socket=server.accept();//여기서 대기 상태에 빠지므로, 우리는 쓰레드로 처리했음..
			String ip=socket.getInetAddress().getHostAddress();
			area.append(ip+" 접속\n");
			
			//접속과 동시에 스트림을 얻어놓아야 대화가 가능  
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			//서버는 끝없이, 듣고 말해야 한다..
			while(true) {
				listen();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//듣기 
	public void listen() {
		String msg=null;
		
		try {
			msg=buffr.readLine();
			area.append(msg+"\n");
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//말하기
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush(); //출력스트림 중 버퍼처리된 것만..
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		startServer();
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}
	
}





