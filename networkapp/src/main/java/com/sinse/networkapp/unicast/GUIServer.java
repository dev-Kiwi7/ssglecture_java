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
			
			while(true) {
				Socket socket=server.accept();//여기서 대기 상태에 빠지므로, 우리는 쓰레드로 처리했음..
				String ip=socket.getInetAddress().getHostAddress();
				area.append(ip+" 접속\n");
				
				//접속자 1명당, 대화용 쓰레드인 ServerThread 인스턴스 만들면서 Socket을 선물로 주자!!
				ServerThread st = new ServerThread(this, socket);
				st.start();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
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





