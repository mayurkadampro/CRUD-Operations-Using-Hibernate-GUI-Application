package p1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DeleteScreen extends JFrame{
	Container c;
	String column[] = {"    ","name","rollno","age","gender"};
	JLabel choiceLabel;
	JLabel oldvalueLabel;
	static JTextField oldvalueField;
	JLabel emptyLabel;
	JLabel emptyTwoLabel;
	JLabel emptyThreeLabel;
	static JComboBox cb;
	JButton delBtn,backBtn;
	Border empty,white;
	
	
	
	DeleteScreen(){

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
		
		emptyLabel = new JLabel("                                                                                                                                          ");
		emptyLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		
		emptyTwoLabel = new JLabel("                                                                                                                                                                                ");
		emptyTwoLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		emptyThreeLabel = new JLabel("                                                                                                                                                                                ");
		emptyThreeLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		delBtn = new JButton("Delete");
		delBtn.setBackground(new Color(0, 208, 96));
		delBtn.setForeground(Color.WHITE);
		delBtn.setFont(new Font("Arial", Font.BOLD, 12));
		delBtn.setBorder(white);
		delBtn.setFocusPainted(false);
		delBtn.setPreferredSize(new Dimension(70, 35));
		
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
		c.add(delBtn);
		c.add(backBtn);
		
		//action listener
		ActionListener a = (ae) -> {
			MainScreen ms = new MainScreen();
			this.dispose();
		};
		
		backBtn.addActionListener(a);
		
		ActionListener a1 = (ae) -> {
			
			String col = "";
			
			if(cb.getSelectedItem().toString() == column[0]){
				JOptionPane.showMessageDialog(c,"Please select column ");
			}else if(cb.getSelectedItem().toString() == column[1] 
			|| cb.getSelectedItem().toString() == column[2] 
			|| cb.getSelectedItem().toString() == column[3]
			|| cb.getSelectedItem().toString() == column[4])
			{
				col = cb.getSelectedItem().toString();
				if(oldvalueField.getText().isEmpty()){
					JOptionPane.showMessageDialog(c,"Please Enter Value in Black Field");
				}else if(col.equals(column[1])){
					if(oldvalueField.getText().matches("[0-9]+") == false){
							dataDelete(oldvalueField.getText(),col);
					}else{
						JOptionPane.showMessageDialog(c,"Please enter string value");
					}
				}else if(col.equals(column[2])){
					if(oldvalueField.getText().matches("[0-9]+") == true){
						dataDelete(Integer.parseInt(oldvalueField.getText()),col);
					}else{
						JOptionPane.showMessageDialog(c,"Please enter numerical value");
					}
				}else if(col.equals(column[3])){
					if(oldvalueField.getText().matches("[0-9]+") == true){
						dataDelete(Integer.parseInt(oldvalueField.getText()),col);
					}else{
						JOptionPane.showMessageDialog(c,"Please enter numerical value");
					}
				}else if(col.equals(column[4])){
					if(oldvalueField.getText().matches("[0-9]+") == false){
						dataDelete(oldvalueField.getText(),col);
					}else{
						JOptionPane.showMessageDialog(c,"Please enter string value");
					}
				}
				
				
			}
			
		};
		
		delBtn.addActionListener(a1);
		
		// JFrame Basic Settings
		c.setBackground(Color.yellow);
		setTitle("S.M.S");
		setSize(440,260);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void dataDelete(Object oldValue,String colName){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact = cfg.buildSessionFactory();
		Session session = sfact.openSession();
		
		try{
			System.out.println("begin");
			Query qry = session.createQuery("delete from Student where "+colName+"=:java4s");
			qry.setParameter("java4s",oldValue);
			int res = qry.executeUpdate();
			System.out.println("end");
			
			JOptionPane.showMessageDialog(new JFrame(),"Numer of records effected due to delete query "+res);
			cb.setSelectedIndex(0);
			oldvalueField.setText("");
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			session.close();
			sfact.close();
		}
	}
	
	
}