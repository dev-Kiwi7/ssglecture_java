package com.sinse.networkapp.multicast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sinse.networkapp.unicast.ServerThread;

public class GUIServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	Thread thread; //서버 가동용 쓰레드 ( 메인쓰레드가 대기에 빠지지 않기 위해 필요)
	
	//ArrayList도 가능은 하지만, 다중 쓰레드 환경에서 쓰레들간의 동기화를 지원하지 않으므로, 
	//운이 없다면, ArrayList  []인덱스에 동시에 쓰레드가 접근하게 되는 상황이 발생할 수도 있다.. 
	//이 경우 개발자가 syncronized{} 블럭으로 코드를 감싸면, 특정 쓰레드가 해당 블럭을 실행하는 동안 
	//따른 쓰레드는 대기상태로 기다려서 , 동기로 안전되게 실행할 수 있다..
	//Vector 는 이미 동기화 처리가 되어 있다. 
	Vector<ServerChatThread> vec=new Vector<>(); //현재는 존재는 하되, 사이즈는 0
	
	public GUIServer() {
		p_north = new JPanel();
		t_port = new JTextField(8);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
			
		area.setBackground(Color.YELLOW);
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		bt.addActionListener(e->{
			thread = new Thread(()->{
				startServer();
			}); 
			thread.start();
		});
		
		setBounds(2300, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void startServer() {
		int port = Integer.parseInt(t_port.getText());
		try {
			ServerSocket server = new ServerSocket(port);
			area.append("서버 생성 및 접속자 감지 시작\n");
			
			while(true) {
				Socket socket=server.accept(); //접속자가 감지될때까지 무한대기에 있다가, 접속자가 발견되면
															//대화용 소켓이 반환되면서 대기상태는 풀린다..
				String ip=socket.getInetAddress().getHostAddress();
				area.append(ip+"님 접속 감지\n");
				
				ServerChatThread chatThread = new ServerChatThread(this, socket);
				chatThread.start();//쓰레드 동작 시작!!
				
				//현재 서버에 접속한 클라이언트 정보인 ServerChatThread 를 Vector 에 넣는다
				vec.add(chatThread);
				area.append("현재  "+vec.size()+"명 접속\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}
	
}





