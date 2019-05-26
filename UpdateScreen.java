package p1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

class UpdateScreen extends JFrame{
	Container c;
	String column[] = {"    ","name","rollno","age","gender"};
	JLabel choiceLabel;
	JLabel newvalueLabel;
	JLabel oldvalueLabel;
	JTextField oldvalueField;
	JTextField newvalueField;
	JLabel emptyLabel;
	JLabel emptyOneLabel;
	JLabel emptyTwoLabel;
	JLabel emptyThreeLabel;
	JComboBox cb;
	JButton upBtn,backBtn;
	Border empty,white;
	
	
	
	UpdateScreen(){
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		choiceLabel = new JLabel("Select Colunm : ");
		choiceLabel.setFont(new Font("Arial", Font.BOLD, 19));
		cb = new JComboBox(column);
		cb.setBounds(50, 50,90,20);
		
		oldvalueLabel = new JLabel("Old Value : ");
		oldvalueLabel.setFont(new Font("Arial", Font.BOLD, 19));
		
		oldvalueField = new JTextField();
		oldvalueField.setPreferredSize( new Dimension( 250, 24 ));
		
		newvalueLabel = new JLabel("New Value : ");
		newvalueLabel.setFont(new Font("Arial", Font.BOLD, 19));
		
		newvalueField = new JTextField();
		newvalueField.setPreferredSize( new Dimension( 250, 24 ));
		
		emptyLabel = new JLabel("                                                                                                                                          ");
		emptyLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		emptyOneLabel = new JLabel("                                                                                                                                                                                ");
		emptyOneLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		emptyTwoLabel = new JLabel("                                                                                                                                                                                ");
		emptyTwoLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		emptyThreeLabel = new JLabel("                                                                                                                                                                                ");
		emptyThreeLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		upBtn = new JButton("Update");
		upBtn.setBackground(new Color(0, 208, 96));
		upBtn.setForeground(Color.WHITE);
		upBtn.setFont(new Font("Arial", Font.BOLD, 12));
		upBtn.setBorder(white);
		upBtn.setFocusPainted(false);
		upBtn.setPreferredSize(new Dimension(70, 35));
		
		backBtn = new JButton("Back");
		backBtn.setBackground(new Color(255, 0, 0));
		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("Arial", Font.BOLD, 12));
		backBtn.setBorder(white);
		backBtn.setFocusPainted(false);
		backBtn.setPreferredSize(new Dimension(70, 35));
		

		c.add(emptyThreeLabel);
		c.add(choiceLabel);
		c.add(cb);
		c.add(emptyLabel);
		c.add(oldvalueLabel);
		c.add(oldvalueField);
		c.add(emptyTwoLabel);
		c.add(newvalueLabel);
		c.add(newvalueField);
		c.add(emptyOneLabel);
		c.add(upBtn);
		c.add(backBtn);
		
		//action listener
		ActionListener a = (ae) -> {
			MainScreen ms = new MainScreen();
			this.dispose();
		};
		
		backBtn.addActionListener(a);
		
		// JFrame Basic Settings
		c.setBackground(Color.yellow);
		setTitle("S.M.S");
		setSize(440,260);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}