package table2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class MemberRegist extends JFrame implements ActionListener, WindowListener, MouseListener{
	//서쪽 영역 
	JPanel p_west;
	JTextField t_id;
	JTextField t_name;
	JTextField t_tel;
	JButton bt;
	
	//센터영역
	JPanel p_center;
	JTable table;
	JScrollPane scroll;
	
	TableModel model;
	int index=0; //몇번째 층에 회원을 넣을지 결정짓는 인덱스
	
	Connection con; //접속은 윈도우창 생성시 한번 시도되며, 창 닫을때 접속해제
	
	public MemberRegist() {
		//생성
		p_west = new JPanel();
		t_id = new JTextField();
		t_name=new JTextField();
		t_tel = new JTextField();
		bt = new JButton("가입");
		
		p_center = new JPanel();
		//생성자에 이차원배열을 대입 방식은 불편...생성하는 시점부터 언제나 데이터가 있어야하는 전제조건이
		//생성자의 인수에 이 테이블에 보여줘야할 데이터 또는 데이터처리 객체
		//JTable은 MVC패턴을 어느 정보 반영한 컴포넌트이다..(완벽하지 않음 모델+컨트롤러)
		table = new JTable(model =new MyModel()); //JTable은 껍데기에 지나지 않기 때문에, 실제 보여질 데이터는
														//모델이 결정한다!!!
		scroll = new JScrollPane(table);
		
		//style 적용 
		p_west.setBackground(Color.ORANGE);
		p_west.setPreferredSize(new Dimension(150, 500));

		Dimension d = new Dimension(146, 35);
		t_id.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_tel.setPreferredSize(d);
		
		scroll.setPreferredSize(new Dimension(435, 490));
		
		//조립 
		p_west.add(t_id);
		p_west.add(t_name);
		p_west.add(t_tel);
		p_west.add(bt);
		add(p_west, BorderLayout.WEST);//프레임의 서쪽에 패널 부착 
	
		p_center.add(scroll);
		add(p_center);
		
		bt.addActionListener(this); //버튼과 리스너 연결 
		
		table.addMouseListener(this);
		
		//윈도우창과 리스너와의 연결 
		this.addWindowListener(this);
		
		setBounds(2000,100, 600, 500);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		 
		connect();//데이터베이스 접속
		selectAll(); //최초 보여질 레코드를 보기 위해 
		
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/dev";
			String id="java";
			String pwd="1234";
			
			con=DriverManager.getConnection(url, id, pwd);
			if(con!=null) {
				this.setTitle("접속 성공");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//회원 한명 등록 (model이 보유한 이차원 배열의 한 공간을 들어가게 할 예정, 즉 원하는 층에 들어갈예정)
	public void regist() {
		//회원1 명을 일차원 배열에 담아서, 그 일차원 배열을 model이 모델이 2차원배열에  
		//가장 최근의 위치에 밀어넣기!!
		//String[]  member = { t_id.getText(), t_name.getText(), t_tel.getText()};
		//((MyModel)model).rows[index++]=member;
		//table.updateUI();//테이블 새로 고침 
		String sql="insert into member4(id, name, tel)";
		sql+="values('"+t_id.getText()+"','"+t_name.getText()+"','"+t_tel.getText()+"')";
		
		System.out.println(sql);//콘솔에 출력된 SQL문을 실제 DB에서 실행했을때 성공되어야 함
		
		PreparedStatement pstmt=null;
		
		try {
			pstmt=con.prepareStatement(sql);//쿼리문이 준비 
			
			int result = pstmt.executeUpdate(); //DML (insert ,update, delete)

			if(result >0) {
				JOptionPane.showMessageDialog(this, "등록성공");
				selectAll();//조회
			}else {
				JOptionPane.showMessageDialog(this, "등록실패");
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
	
	//데이터 가져오기 
	public void selectAll() {
		String sql="select * from member4";
		PreparedStatement pstmt=null; //finally에서 닫으려고 한줄로 처리 안함..
		ResultSet rs=null;
		
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //쿼리문 준비 
			
			//select문이므로 executeQuery()호출 
			rs=pstmt.executeQuery(); //select문일 경우 쿼리수행 시,표가 반환되므로 해당 표를 제어할 
												//ResultSet 객체가 반환됨
			rs.last();//레코드의 마지막으로 포인터를 보낸다... 
			int total = rs.getRow(); //현재 위치 어디인지 묻는다 
			System.out.println("현재 까지 가입한 총 수는 "+total);
			
			//rs 자체는 MyModel이 보유하고 있는 2차원 배열자체가 아니므로, rs의 데이터를 2차원 배열로 
			//변환하여 MyModel이 보유한 배열대신 사용해야 함..즉 대체 
			((MyModel)model).rows = new String[total][4];
			
			//마지막 위치로 보냈던, rs의 커서를 다시 처음으로 복귀시킨다..레코드를 처음부터 차례대로 접근하기 위함
			rs.beforeFirst();//이 커서의 자유로움,  pstmt 생성시, 부여한 상수 옵션때문이다..
			
			//한건의 레코드 조제
			int index=0;
			
			while(rs.next()){
				String[] record = {
						rs.getString("id"),rs.getString("name"), rs.getString("tel"), rs.getString("member4_id") 		
				};
				((MyModel)model).rows[index++]=record; 
			}
			table.updateUI(); //테이블 갱신
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void update(String[] record){
		
		System.out.println("member4_id="+record[3]);
		
		String sql="update member4 set id=? , name=?, tel=? where member4_id=?";
		PreparedStatement pstmt;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		regist();
	}
	
	public static void main(String[] args) {
		new MemberRegist();

	}
	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened()");		
	}
	
	//윈도우 창을 닫는 순간 호출되는 메서드이므로, 무언가 연결되어있던 자원을 
	//해제하는 용도로써 적합하다!!
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing()");
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed()");		
	}
	
	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("windowIconified()");		
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("windowDeiconified()");	
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("windowActivated()");		
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("windowDeactivated()");		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String member4_id=((MyModel)model).rows[table.getSelectedRow()][3];
		System.out.println("선택한 레코드의 pk는 "+member4_id);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}





