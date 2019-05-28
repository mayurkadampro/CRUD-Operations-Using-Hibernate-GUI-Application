package p1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class UpdateScreen extends JFrame{
	Container c;
	String column[] = {"    ","name","rollno","age","gender"};
	JLabel choiceLabel;
	JLabel choiceLabelOne;
	JLabel newvalueLabel;
	JLabel oldvalueLabel;
	static JTextField oldvalueField;
	static JTextField newvalueField;
	JLabel emptyLabel;
	JLabel emptyFourLabel;
	JLabel emptyOneLabel;
	JLabel emptyTwoLabel;
	JLabel emptyThreeLabel;
	static JComboBox cb;
	static JComboBox cbOne;
	JButton upBtn,backBtn;
	Border empty,white;
	int myage;
	
	
	
	UpdateScreen(){
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		choiceLabel = new JLabel("update Student SET : ");
		choiceLabel.setFont(new Font("Arial", Font.BOLD, 19));
		
		cb = new JComboBox(column);
		cb.setBounds(50, 50,90,20);
		
		choiceLabelOne = new JLabel("Where Colunm : ");
		choiceLabelOne.setFont(new Font("Arial", Font.BOLD, 19));
		
		cbOne = new JComboBox(column);
		cbOne.setBounds(50, 50,90,20);
		
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

		c.add(choiceLabel);
		c.add(cb);
		c.add(emptyLabel);
		c.add(newvalueLabel);
		c.add(newvalueField);
		c.add(emptyOneLabel);
		c.add(choiceLabelOne);
		c.add(cbOne);
		c.add(emptyFourLabel);
		c.add(oldvalueLabel);
		c.add(oldvalueField);
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
			
			String col = "";
			String colOne = "";
			
			if(cb.getSelectedItem().toString() == column[0] || cbOne.getSelectedItem().toString() == column[0] ){
				JOptionPane.showMessageDialog(c,"Please select column ");
			}else if(cb.getSelectedItem().toString() == column[1] 
			|| cb.getSelectedItem().toString() == column[2] 
			|| cb.getSelectedItem().toString() == column[3]
			|| cb.getSelectedItem().toString() == column[4]
			|| cbOne.getSelectedItem().toString() == column[1] 
			|| cbOne.getSelectedItem().toString() == column[2] 
			|| cbOne.getSelectedItem().toString() == column[3]
			|| cbOne.getSelectedItem().toString() == column[4])
			{
				col = cb.getSelectedItem().toString();
				colOne = cbOne.getSelectedItem().toString();
				
				
				if(oldvalueField.getText().isEmpty() || newvalueField.getText().isEmpty()){
					JOptionPane.showMessageDialog(c,"Please Enter Value in Black Field");
				}else if(col.equals(column[1])){
					if(newvalueField.getText().matches("[0-9]+") == false){
						if(newvalueField.getText().length() > 2){
							if(colOne.equals(column[1])){
								if(oldvalueField.getText().matches("[0-9]+") == false){
									dataUpdate(oldvalueField.getText(),newvalueField.getText(),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter string value In Old Value Field");
								}
							}else if(colOne.equals(column[2])){
								if(oldvalueField.getText().matches("[0-9]+") == true){
									dataUpdate(Integer.parseInt(oldvalueField.getText()),newvalueField.getText(),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter numerical value In Old Value Field");
								}
							}else if(colOne.equals(column[3])){
								if(oldvalueField.getText().matches("[0-9]+") == true){
									dataUpdate(Integer.parseInt(oldvalueField.getText()),newvalueField.getText(),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter numerical value In Old Value Field");
								}
							}else if(colOne.equals(column[4])){
								if(oldvalueField.getText().matches("[0-9]+") == false){
									dataUpdate(oldvalueField.getText(),newvalueField.getText(),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter string value In Old Value Field");
								}
							}
						}else{
							JOptionPane.showMessageDialog(c,"Name should be greater than 2 letter");
						}							
					}else{
						JOptionPane.showMessageDialog(c,"Please enter string value In New Value Field");
					}
				}else if(col.equals(column[2])){
					
					if(newvalueField.getText().matches("[0-9]+") == true){
						if(colOne.equals(column[1])){
								if(oldvalueField.getText().matches("[0-9]+") == false){
									dataUpdate(oldvalueField.getText(),Integer.parseInt(newvalueField.getText()),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter string value In Old Value Field");
								}
							}else if(colOne.equals(column[2])){
								if(oldvalueField.getText().matches("[0-9]+") == true){
									dataUpdate(Integer.parseInt(oldvalueField.getText()),Integer.parseInt(newvalueField.getText()),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter numerical value In Old Value Field");
								}
							}else if(colOne.equals(column[3])){
								if(oldvalueField.getText().matches("[0-9]+") == true){
									dataUpdate(Integer.parseInt(oldvalueField.getText()),Integer.parseInt(newvalueField.getText()),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter numerical value In Old Value Field");
								}
							}else if(colOne.equals(column[4])){
								if(oldvalueField.getText().matches("[0-9]+") == false){
									dataUpdate(oldvalueField.getText(),Integer.parseInt(newvalueField.getText()),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter string value In Old Value Field");
								}
							}
					}else{
						JOptionPane.showMessageDialog(c,"Please enter numerical value In New Value Field");
					}
				}else if(col.equals(column[3])){
					if(newvalueField.getText().matches("[0-9]+") == true){
						if((myage = Integer.parseInt(newvalueField.getText())) <= 45){
						if(colOne.equals(column[1])){
								if(oldvalueField.getText().matches("[0-9]+") == false){
									dataUpdate(oldvalueField.getText(),Integer.parseInt(newvalueField.getText()),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter string value In Old Value Field");
								}
						}else if(colOne.equals(column[2])){
								if(oldvalueField.getText().matches("[0-9]+") == true){
									dataUpdate(Integer.parseInt(oldvalueField.getText()),Integer.parseInt(newvalueField.getText()),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter numerical value In Old Value Field");
								}
						}else if(colOne.equals(column[3])){
								if(oldvalueField.getText().matches("[0-9]+") == true){
									dataUpdate(Integer.parseInt(oldvalueField.getText()),Integer.parseInt(newvalueField.getText()),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter numerical value In Old Value Field");
								}
						}else if(colOne.equals(column[4])){
								if(oldvalueField.getText().matches("[0-9]+") == false){
									dataUpdate(oldvalueField.getText(),Integer.parseInt(newvalueField.getText()),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter string value In Old Value Field");
								}
						}
						}else{
							JOptionPane.showMessageDialog(c,"Student age must be less than 46");
						}
					}else{
						JOptionPane.showMessageDialog(c,"Please enter numerical value In New Value Field");
					}
				}else if(col.equals(column[4])){
					if(newvalueField.getText().matches("[0-9]+") == false){
						if(newvalueField.getText().equals("male") || newvalueField.getText().equals("female")){
						if(colOne.equals(column[1])){
								if(oldvalueField.getText().matches("[0-9]+") == false){
									dataUpdate(oldvalueField.getText(),newvalueField.getText(),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter string value In Old Value Field");
								}
							}else if(colOne.equals(column[2])){
								if(oldvalueField.getText().matches("[0-9]+") == true){
									dataUpdate(Integer.parseInt(oldvalueField.getText()),newvalueField.getText(),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter numerical value In Old Value Field");
								}
							}else if(colOne.equals(column[3])){
								if(oldvalueField.getText().matches("[0-9]+") == true){
									dataUpdate(Integer.parseInt(oldvalueField.getText()),newvalueField.getText(),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter numerical value In Old Value Field");
								}
							}else if(colOne.equals(column[4])){
								if(oldvalueField.getText().matches("[0-9]+") == false){
									dataUpdate(oldvalueField.getText(),newvalueField.getText(),col,colOne);
								}else{
									JOptionPane.showMessageDialog(c,"Please enter string value In Old Value Field");
								}
							}
						}else{
							JOptionPane.showMessageDialog(c,"enter male or female in new value field");
						}
					}else{
						JOptionPane.showMessageDialog(c,"Please enter string value In New Value Field");
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
	
	public static void dataUpdate(Object oldValue,Object newvalue, String colName,String colOneName){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact = cfg.buildSessionFactory();
		Session session = sfact.openSession();
		
		try{
			System.out.println("begin");
			Query qry = session.createQuery("update Student set "+colName+"=:newVal WHERE "+colOneName+" =:oldVal");
			qry.setParameter("newVal",newvalue);
			qry.setParameter("oldVal",oldValue);
			int res = qry.executeUpdate();
			System.out.println("end");
			
			if(res>0){
				JOptionPane.showMessageDialog(new JFrame(),"Numer of records effected due to delete query "+res);
			}else{
				JOptionPane.showMessageDialog(new JFrame(),"No Matching Record Found.");
			}
			
			
			oldvalueField.setText("");
			cb.setSelectedIndex(0);
			cbOne.setSelectedIndex(0);
			newvalueField.setText("");
		}catch(Exception e){
			System.out.println(e);
		}finally{
			session.close();
			sfact.close();
		}
	}
	
	
}