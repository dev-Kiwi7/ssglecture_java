package date;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Cell extends JPanel{

	public Cell() {
		setPreferredSize(new Dimension(50, 50));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
	}
	
}

