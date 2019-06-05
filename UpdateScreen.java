package p1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class UpdateScreen extends JFrame{
	private Container c;
	private String column[] = {"    ","name","age","gender"};
	private JLabel choiceLabel;
	private JLabel choiceLabelOne;
	private JLabel newvalueLabel;
	private JLabel oldvalueLabel;
	private static JTextField oldvalueField;
	private static JTextField newvalueField;
	private JLabel emptyLabel;
	private JLabel emptyFourLabel;
	private JLabel emptyOneLabel;
	private JLabel emptyTwoLabel;
	private JLabel emptyThreeLabel;
	private static JComboBox cbOne;
	private JButton upBtn,backBtn;
	private Border empty,white;
	private int myage;
	
	
	
	UpdateScreen(){
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		choiceLabelOne = new JLabel("Select column for update: ");
		choiceLabelOne.setFont(new Font("Arial", Font.BOLD, 19));
		
		cbOne = new JComboBox(column);
		cbOne.setBounds(50, 50,90,20);
		
		oldvalueLabel = new JLabel("Enter Roll No : ");
		oldvalueLabel.setFont(new Font("Arial", Font.BOLD, 19));
		
		oldvalueField = new JTextField();
		oldvalueField.setPreferredSize( new Dimension( 250, 24 ));
		
		newvalueLabel = new JLabel("Enter New value : ");
		newvalueLabel.setFont(new Font("Arial", Font.BOLD, 19));
		
		newvalueField = new JTextField();
		newvalueField.setPreferredSize( new Dimension( 230, 24 ));
		
		emptyLabel = new JLabel("                                                                                                                                          ");
		emptyLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		emptyFourLabel = new JLabel("                                                                                                                                          ");
		emptyFourLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		emptyOneLabel = new JLabel("                                                                                                                                                                                ");
		emptyOneLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		emptyTwoLabel = new JLabel("                                                                                                                                                                                ");
		emptyTwoLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		
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

		c.add(emptyLabel);
		c.add(oldvalueLabel);
		c.add(oldvalueField);
		c.add(emptyFourLabel);
		c.add(choiceLabelOne);
		c.add(cbOne);
		c.add(emptyOneLabel);
		c.add(newvalueLabel);
		c.add(newvalueField);
		


		c.add(emptyTwoLabel);

		c.add(upBtn);
		c.add(backBtn);
		
		//action listener
		ActionListener a = (ae) -> {
			MainScreen ms = new MainScreen();
			this.dispose();
		};
		
		backBtn.addActionListener(a);
		
		
		ActionListener a1 = (ae) -> {

			String colOne = "";
			
			if(cbOne.getSelectedItem().toString() == column[0]){
				JOptionPane.showMessageDialog(c,"Please select column field");
			}else if(cbOne.getSelectedItem().toString() == column[1] 
			|| cbOne.getSelectedItem().toString() == column[2] 
			|| cbOne.getSelectedItem().toString() == column[3]){
				colOne = cbOne.getSelectedItem().toString();
				
				if(oldvalueField.getText().isEmpty() || newvalueField.getText().isEmpty()){
					JOptionPane.showMessageDialog(c,"Please Enter Value in Blank Field");
				}else if(oldvalueField.getText().matches("[0-9]+") == false){
					JOptionPane.showMessageDialog(c,"Please enter numerical value in roll no field.");
				}else{
					if(colOne.equals(column[1])){
						if(newvalueField.getText().matches("[0-9]+") == true){
							JOptionPane.showMessageDialog(c,"Please enter String value in new value field.");
						}else{
							if(newvalueField.getText().length() > 2){
								dataUpdate(Integer.parseInt(oldvalueField.getText()),newvalueField.getText(),colOne);
							}else{
								JOptionPane.showMessageDialog(c,"Entered name length should be greater than Two");
							}
							
						}
					}else if(colOne.equals(column[2])){
						if(newvalueField.getText().matches("[0-9]+") == false){
							JOptionPane.showMessageDialog(c,"Please enter numerical value in new value field.");
						}else{
							dataUpdate(Integer.parseInt(oldvalueField.getText()),Integer.parseInt(newvalueField.getText()),colOne);
						}
					}else if(colOne.equals(column[3])){
						if(newvalueField.getText().matches("[0-9]+") == true){
							JOptionPane.showMessageDialog(c,"Please enter String value in new value field.");
						}else{
							if(newvalueField.getText().equals("male") || newvalueField.getText().equals("female")){
								dataUpdate(Integer.parseInt(oldvalueField.getText()),newvalueField.getText(),colOne);
							}else{
								JOptionPane.showMessageDialog(c,"enter male or female in new value field");
							}
							
						}
					}
				}
				
				
			}
			
		};
		
		upBtn.addActionListener(a1);
		
		// JFrame Basic Settings
		c.setBackground(Color.yellow);
		setTitle("S.M.S");
		setSize(440,260);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void dataUpdate(int oldValue,Object newvalue,Object colName){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact = cfg.buildSessionFactory();
		Session session = sfact.openSession();
		
		try{
			System.out.println("begin");
			Query qry = session.createQuery("update Student set "+colName+"=:newVal where id =:java4s");
			qry.setParameter("newVal",newvalue);
			qry.setParameter("java4s",oldValue);
			int res = qry.executeUpdate();
			System.out.println("end");
			
			if(res>0){
				JOptionPane.showMessageDialog(new JFrame(),"Numer of records effected due to delete query "+res);
			}else{
				JOptionPane.showMessageDialog(new JFrame(),"No Matching Record Found.");
			}
			
			cbOne.setSelectedIndex(0);
			oldvalueField.setText("");
			newvalueField.setText("");
			
			
		}catch(NullPointerException e){
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			session.close();
			sfact.close();
		}
	}
	
	
}