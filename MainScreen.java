package p1;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

class MainScreen extends JFrame{
	
	Container c;
	JButton addBtn;
	JButton delBtn;
	JButton viewBtn;
	JButton upBtn;
	Border empty,white;
	
	
	 MainScreen(){
		c = getContentPane();
		c.setLayout(new FlowLayout());
		empty = BorderFactory.createEmptyBorder();
		white = BorderFactory.createLineBorder(Color.white);
		
		// button instantiations 
		addBtn = new JButton("Add");
		delBtn = new JButton("Delete");
		viewBtn = new JButton("View");
		upBtn = new JButton("Update");
		
		// button settings
		addBtn.setBackground(new Color(0, 208, 96));
		addBtn.setForeground(Color.WHITE);
		addBtn.setFont(new Font("Arial", Font.PLAIN, 40));
		addBtn.setBorder(white);
		addBtn.setFocusPainted(false);
		addBtn.setPreferredSize(new Dimension(200, 200));
		
		delBtn.setBackground(new Color(234, 73, 36));
		delBtn.setForeground(Color.WHITE);
		delBtn.setFont(new Font("Arial", Font.PLAIN, 40));
		delBtn.setBorder(white);
		delBtn.setFocusPainted(false);
		delBtn.setPreferredSize(new Dimension(200, 200));
		
		viewBtn.setBackground(new Color(57, 88, 174));
		viewBtn.setForeground(Color.WHITE);
		viewBtn.setFont(new Font("Arial", Font.PLAIN, 40));
		viewBtn.setBorder(white);
		viewBtn.setFocusPainted(false);
		viewBtn.setPreferredSize(new Dimension(200, 200));
		
		upBtn.setBackground(new Color(0, 149, 143));
		upBtn.setForeground(Color.WHITE);
		upBtn.setFont(new Font("Arial", Font.PLAIN, 40));
		upBtn.setBorder(white);
		upBtn.setFocusPainted(false);
		upBtn.setPreferredSize(new Dimension(200, 200));
		
		c.add(addBtn);
		c.add(viewBtn);
		c.add(delBtn);
		c.add(upBtn);
		
		
		
		//action listener
		ActionListener a = (ae) -> { 
			AddScreen as = new AddScreen();
			this.dispose();
		};
		addBtn.addActionListener(a);
		
		ActionListener a1 = (ae) -> { 
			UpdateScreen as = new UpdateScreen();
			this.dispose();
		};
		upBtn.addActionListener(a1);
		
		ActionListener a2 = (ae) -> { 
			DeleteScreen as = new DeleteScreen();
			this.dispose();
		};
		delBtn.addActionListener(a2);
		
		ActionListener a3 = (ae) -> { 
			ViewScreen as = new ViewScreen();
			this.dispose();
		};
		viewBtn.addActionListener(a3);
		
		
		// JFrame Basic Settings
		c.setBackground(Color.yellow);
		setTitle("S.M.S");
		setSize(440,458);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String args[]){
		MainScreen as = new MainScreen();
	}
}