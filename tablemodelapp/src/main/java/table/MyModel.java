package table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/*
 * JTable 개발분야에서 전해내려오는 많이 알려진 개발 방법(=패턴) 중 하나인 MVC 패턴을 구현한 
 * 컴포넌트이다..하지만 완벽하진 않음 
 * 왜??   MC의 역할을 동시에 수행하므로... 데이터를 보유할뿐만 아니라, 그 데이터를 디자인 영역에
 * 반영하는 코드도 포함되어 있기 때문에...
 * 결론) 중요한 것은  JTable과 데이터를 분리시켜놓은 기술임 
 * 
 * TableModelListener 란?  모델이 보유한 데이터를 유저가 수정할때 발생되는 이벤트를 감지하는 리스너.. 
 * */
public class MyModel extends AbstractTableModel implements TableModelListener{
	
	//회원정보 (층,호 를 표현하기 위한 이차원 배열 형태의 데이터가 필요)
	String[][] rows=new String[0][4];
	String[] columns= {"ID","Name","Tel"};
	
	MemberRegist memberRegist; //제어하기 때문에 주소값을 보유하기 위한 has a관계 선언
	
	public MyModel(MemberRegist memberRegist) {
		this.memberRegist=memberRegist; //생성자 주입을 이용한 멤버변수 대입 
		
		//모델과 리스너 연결 
		this.addTableModelListener(this);//나의 레코드가 변경될때 그것을 감지하겟다!!!
	}
	
	//테이블에 보여질 총 레코드 수 
	public int getRowCount() {
		return rows.length;
	}

	//테이블을 구성하는 컬럼 수 
	public int getColumnCount() {
		return columns.length;
	}
		
	//컬럼의 이름 반환해주는 메서드 
	//아래의 메서드는 컬럼 수만큼 반복하면서 호출되는데, 이때 매개변수로 넘겨받는 col의 값은 자동 증가하면서
	//전달되어 진다... 
	public String getColumnName(int col) {
		return columns[col];
	}
	
	//getValueAt() 메서드는 getRowCount() X getColumnCount() 수만큼 호출하면서
	//표를 이루는 각 셀(행,열)의 좌표마다 어떠한 값을 넣을지 return 이 결정한다
	public Object getValueAt(int row, int col) {
		//System.out.println("row="+row+",  col="+col);
		return rows[row][col];
	}
	
	//유저가 테이블 셀에서 아무리 데이터를 편집한다 하더라도, 현재 모델이 보유한 이차원 배열을수정하지 않는한 
	//값 수정 반영이 되지 않는다..따라서 값 변경을 위한 setter가 필요함 
	//셀에서 원하는 데이터 K를 입력하고, 엔터를 치는 순간, 해당 셀의 row, col, k 값이 전달됨..
	@Override
	public void setValueAt(Object value, int row, int col) {
		System.out.println("당신은 "+row+", "+col+"의  데이터를 "+value+"로 바꾸길 원하죠?");

		//모델이 이차원 배열에 반영하기
		rows[row][col]=(String)value;
		
		memberRegist.edit(rows[row]);//데이터베이스도 수정되어야 하니깐..
	}
	
	
	@Override
	public boolean isCellEditable(int row, int col) {
		System.out.println(row+"행 ,"+col+"열은 수정가능합니다");
		//boolean flag=;
		return (col==0)? false : true;
		/*
		if(col==0) {
			return false;
		}else {
			return true;
		} 
		*/
	}
	
	//테이블의 한셀을 수정후 엔터치는 순간 이메서드가 호출됨 
	public void tableChanged(TableModelEvent e) {
		System.out.println("편집 했어?");		
	}
}




