package table;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTest extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	String[][] data= {
			{"아우디","벤츠","BMW","K9"},
			{"아우디","벤츠","BMW","K9"},
			{"아우디","벤츠","BMW","K9"},
			{"아우디","벤츠","BMW","K9"}
	};
	String[] title= {"브랜드1","브랜드2","브랜드3","브랜드4"};
	
	public TableTest() {
		table = new JTable(data , title) ;
		scroll = new JScrollPane(table);
		add(scroll);
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] 	args) {
		new TableTest();

	}

}
