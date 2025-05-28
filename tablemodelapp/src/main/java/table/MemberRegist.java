package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	JPanel p_south; //남쪽에 정보 패널 추가!!
	JLabel la_id;
	JLabel la_value;
	JLabel la_name;
	JLabel la_tel;
	JTextField t_name2;
	JTextField t_tel2;
	JButton bt_edit;
	JButton bt_del;
	
	
	TableModel model;
	int index=0; //몇번째 층에 회원을 넣을지 결정짓는 인덱스
	
	Connection con; //접속은 윈도우창 생성시 한번 시도되며, 창 닫을때 접속해제
	
	//현재 사용자고 보고있는 회원의 pk값 
	int member4_id;
	
	//현재 유저가 선택한 한 회원의 모든 정보 
	String[] member;
	
	public MemberRegist() {
		//생성
		p_west = new JPanel();
		t_id = new JTextField();
		t_name=new JTextField();
		t_tel = new JTextField();
		
		//애플리케이션의 퀄리티를 높이기위해 이미지 적용해보기 
		//단, 이미지 경로는 플랫폼에 의존적인 경로 말고, 중립적인 클래스패스를 기준으로 가져오자 
		Class myClass=this.getClass();
		URL url=myClass.getClassLoader().getSystemResource("write.png");
		
		bt = new JButton("가입");
		
		
		p_center = new JPanel();
		//생성자에 이차원배열을 대입 방식은 불편...생성하는 시점부터 언제나 데이터가 있어야하는 전제조건이
		//생성자의 인수에 이 테이블에 보여줘야할 데이터 또는 데이터처리 객체
		//JTable은 MVC패턴을 어느 정보 반영한 컴포넌트이다..(완벽하지 않음 모델+컨트롤러)
		table = new JTable(model =new MyModel(this)); //JTable은 껍데기에 지나지 않기 때문에, 실제 보여질 데이터는
														//모델이 결정한다!!!
		scroll = new JScrollPane(table);
		
		//새로 추가된 센터 영역 컴포넌트 
		p_south = new JPanel();
		la_id = new JLabel("ID");
		la_name = new JLabel("Name");
		la_tel = new JLabel("Tel");
		la_value=new JLabel("");
		
		t_name2 = new JTextField();
		t_tel2 = new JTextField();
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		
		
		//style 적용 
		p_west.setBackground(Color.ORANGE);
		p_west.setPreferredSize(new Dimension(150, 500));

		Dimension d = new Dimension(146, 35);
		t_id.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_tel.setPreferredSize(d);
		
		scroll.setPreferredSize(new Dimension(435, 350));
		
		//새로운 컴포넌트에 대한 스타일 
		p_south.setPreferredSize(new Dimension(450, 145));
		p_south.setBackground(Color.YELLOW);
		
		Dimension d2 = new Dimension(200, 30);		
		la_id.setPreferredSize(d2);
		la_name.setPreferredSize(d2);
		la_tel.setPreferredSize(d2);
		
		Dimension d3 = new Dimension(370, 30);
		la_value.setPreferredSize(d3);
		t_name2.setPreferredSize(d3);
		t_tel2.setPreferredSize(d3);
		
		
		//조립 
		p_west.add(t_id);
		p_west.add(t_name);
		p_west.add(t_tel);
		p_west.add(bt);
		add(p_west, BorderLayout.WEST);//프레임의 서쪽에 패널 부착 
	
		p_center.add(scroll);
		add(p_center);
		add(p_south, BorderLayout.SOUTH);
		
		//남쪽 패널에 부착 
		p_south.add(la_id);
		p_south.add(la_value);
		p_south.add(la_name);
		p_south.add(t_name2);
		p_south.add(la_tel);
		p_south.add(t_tel2);
		p_south.add(bt_edit);
		p_south.add(bt_del);
		
		
		bt.addActionListener(this); //버튼과 리스너 연결 
		table.addMouseListener(this); //테이블과 리스너 연결 
		bt_edit.addActionListener(this); //수정 버튼과 리스너 연결 
		bt_del.addActionListener(this); //삭제 버튼과 리스너 연결 
		
		
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
	
	//모든 회원 데이터 가져오기 
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
						rs.getString("id"),rs.getString("name"), rs.getString("tel") , rs.getString("member4_id")		
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
	
	//선택된 회원만 가져오기 
	public void select(int member4_id) {
		//System.out.println("사원 선택했어?");
		String sql="select * from member4 where member4_id="+member4_id;
		System.out.println(sql);
		
		//쿼리문이 검증되었으므로, JDBC 통해 네트웍으로 전송하자 
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			pstmt=con.prepareStatement(sql); //쿼리문 객체 생성 
			rs=pstmt.executeQuery(); //레코드 결과 반환 
			
			//화면에 출력
			if(rs.next()) { //레코드가 있다면..아래의 코드 수행.즉 회원이 있을때만...
				la_value.setText(rs.getString("id"));
				t_name2.setText(rs.getString("name"));
				t_tel2.setText(rs.getString("tel"));
			}
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
	
	//선택된 회원 1명 삭제
	public void delete(int member4_id) {
		String sql="delete from member4 where member4_id="+member4_id;
		
		//System.out.println(sql);
		PreparedStatement pstmt=null;
		
		try {//쿼리객체 생성
			pstmt=con.prepareStatement(sql);
			
			//DML수행시, 이 쿼리 수행에 의해 영향을 받을 레코드 수가 반환된다..개발자는 이 반환값으로 
			//실행 성공 여부를 판단해야 하는데, 만일 반환값이 0인 경우는 실패!!(에러는 아님)
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				JOptionPane.showMessageDialog(this, "삭제 성공");
				//MyModel 이 보유한 예전 이차원 배열을 업데이트 하도록 처리..
				selectAll();
				//table.updateUI(); //차이점: 개발자가 그린 그림을 다시 그릴때 repaint()사용 -> update() -> paint() 
			}else {
				JOptionPane.showMessageDialog(this, "삭제되지 않았습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//현재 회원의 정보 수정 
	//한사람에 대한 정보를 담는 배열을 매개변수로 선언
	//따라서 이 메서드를 호출하는 者는 , 한사람에 대한 정보를 배열로 담아서 전달
	public void edit(String[] record) { 
		String sql="update member4  set id='"+record[0]+"' , name='"+record[1]+"', tel='"+record[2]+"' ";
		sql+=" where  member4_id="+record[3];
		
		//System.out.println(sql);
		
		PreparedStatement pstmt=null;
		try {
			pstmt=con.prepareStatement(sql);
			int result=pstmt.executeUpdate(); //update DML 수행
			
			if(result>0) {
				JOptionPane.showMessageDialog(this, "수정 성공");
				selectAll(); //MyModel의 이차원배열 갱신 및 테이블 updateUI() 포함
			}else {
				JOptionPane.showMessageDialog(this, "변경 사항이 반영되지 않았습니다");
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
	
	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		
		if(obj==bt) {//등록이라면..
			regist();			
		}else if(obj==bt_edit) {//수정이라면..
			int result = JOptionPane.showConfirmDialog(this, "수정하시겠어요?");
			if(result == JOptionPane.OK_OPTION) {
				//edit() 메서드호출 전에, 배열을 우리가 입력한 데이터를 반영하여 조작을 가하자!!
				member[1]=t_name2.getText();
				member[2]=t_tel2.getText();
						
				edit(member);//이미 멤버변수로 선언된 회원한 사람 정보를 담는 배열을 전달하자
			}			
		}else if(obj==bt_del) {//삭제라면..
			int result = JOptionPane.showConfirmDialog(this, "삭제하시겠어요?");
			if(result == JOptionPane.OK_OPTION) {
				delete(member4_id); //pk를 넘겨줘야 함				
			}
		}
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
		
		//데이터베이스 접속 끊기 
		try {
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//프로세스 종료 
		System.exit(0);
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
		//테이블을 마우스로 클릭 시,JTable의 메서드 중 유저가 선택한  row와 col 정보를 반환하는 메서드 
		
		int row = table.getSelectedRow();//유저가 선택한 층(row)
		int col = table.getSelectedColumn();//유저가 선택한 호(col)
		
		String[][] rows=((MyModel)model).rows;
		
		//방법1) 추천함 왜? 숨겨진 데이터도 접근 가능하므로..
		//System.out.println("선택한 레코드의 이름은 "+rows[row][1]);
		System.out.println("선택한 레코드의 pk는 "+rows[row][3]);
		
		member = rows[row];//멤버 변수로 보관...
		
		//방법2) JTable 자체에 자신의 셀의 정보 반환 
		//String value=(String)table.getValueAt(row, col);
		//System.out.println(value);
		
		//아래의 코드는 이미 member정보를 보관한 일차원 배열에 의해 불필요한 코드이지만, 
		//이미 삭제하기를 구현할때 사용했으므로, 그냥 냅둔다..
		//멤버변수인 member4_id에 보관
		member4_id=Integer.parseInt(rows[row][3]);
		select(member4_id);//pk 전달
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





