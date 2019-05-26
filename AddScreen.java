package p1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class AddScreen extends JFrame
{
	Container c;
	JLabel nameLabel;
	JLabel nameemptyLabel;
	JLabel rollLabel;
	JLabel rollemptyLabel;
	JLabel ageLabel;
	JLabel ageemptyLabel;
	JLabel genderLabel;
	JLabel genderemptyLabel;
	JTextField nameField;
	JTextField rollField;
	JTextField ageField;
	JRadioButton maleRadio;
	JRadioButton femaleRadio;
	JButton subBtn;
	JButton backBtn;
	Border empty,white;
	
	
	
	
	AddScreen(){
		
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// components instantiations
		nameemptyLabel = new JLabel("                                                                                                                                          ");
		nameemptyLabel.setFont(new Font("Arial", Font.BOLD, 7));
		rollemptyLabel = new JLabel("                                                                                                                                          ");
		rollemptyLabel.setFont(new Font("Arial", Font.BOLD, 7));
		ageemptyLabel = new JLabel("                                                                                                                                          ");
		ageemptyLabel.setFont(new Font("Arial", Font.BOLD, 7));
		genderemptyLabel = new JLabel("                                                                                                                                                                               ");
		genderemptyLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		nameLabel = new JLabel("Enter Name : ");
		nameLabel.setFont(new Font("Arial", Font.BOLD, 19));
		rollLabel = new JLabel("Enter Roll no : ");
		rollLabel.setFont(new Font("Arial", Font.BOLD, 19));
		ageLabel = new JLabel("Enter Age : ");
		ageLabel.setFont(new Font("Arial", Font.BOLD, 19));
		genderLabel = new JLabel("Enter Gender : ");
		genderLabel.setFont(new Font("Arial", Font.BOLD, 19));
		nameField = new JTextField();
		nameField.setPreferredSize( new Dimension( 250, 24 ));
		rollField = new JTextField();
		rollField.setPreferredSize( new Dimension( 200, 24 ));
		ageField = new JTextField();
		ageField.setPreferredSize( new Dimension( 200, 24 ));
		
		maleRadio = new JRadioButton("male");
		maleRadio.setOpaque(false);
		femaleRadio = new JRadioButton("Female");
		femaleRadio.setOpaque(false);
		ButtonGroup group = new ButtonGroup();
		group.add(maleRadio);
        group.add(femaleRadio);
		
		
		subBtn = new JButton("Submit");
		subBtn.setBackground(new Color(0, 208, 96));
		subBtn.setForeground(Color.WHITE);
		subBtn.setFont(new Font("Arial", Font.BOLD, 12));
		subBtn.setBorder(white);
		subBtn.setFocusPainted(false);
		subBtn.setPreferredSize(new Dimension(70, 35));
		
		backBtn = new JButton("Back");
		backBtn.setBackground(new Color(255, 0, 0));
		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("Arial", Font.BOLD, 12));
		backBtn.setBorder(white);
		backBtn.setFocusPainted(false);
		backBtn.setPreferredSize(new Dimension(70, 35));
		
		c.add(nameLabel);
		c.add(nameField);
		c.add(nameemptyLabel);
		c.add(rollLabel);
		c.add(rollField);
		c.add(rollemptyLabel);
		c.add(ageLabel);
		c.add(ageField);
		c.add(ageemptyLabel);
		c.add(genderLabel);
		c.add(maleRadio);
		c.add(femaleRadio);
		c.add(genderemptyLabel);
		c.add(subBtn);
		c.add(backBtn);
		
		//action listener
		ActionListener a = (ae) -> {
			MainScreen ms = new MainScreen();
			this.dispose();
		};
		
		backBtn.addActionListener(a);
		
		ActionListener a1 = (ae) -> {
			String empty = new String();
			String name = nameField.getText();
			String rollNo = rollField.getText();
			String age = ageField.getText();
			//String gender = maleRadio.getText();
			if(name.equals(empty) || rollNo.equals(empty) || age.equals(empty)){
				JOptionPane.showMessageDialog(c,"Please Enter Value in Black Field");
			}else{
					Configuration cfg = new Configuration();
					cfg.configure("hibernate.cfg.xml");
					SessionFactory sfact = cfg.buildSessionFactory();
					Session session = sfact.openSession();
					Transaction t = null;
					try{
						System.out.println("begin");
						t = session.beginTransaction();
						Student stud = new Student();
						stud.setName(name);
						stud.setRno(Integer.parseInt(rollNo));
						stud.setAge(Integer.parseInt(age));
						stud.setGender("male");
						session.save(stud);
						t.commit();
						System.out.println("Record Inserted...");
						System.out.println("end");
						JOptionPane.showMessageDialog(c,"Data Saved Successfully...");
					}catch(Exception e){
						if (t!=null) t.rollback();
					}finally{
						session.flush();
						session.close();
					}
			}
		};
		
		subBtn.addActionListener(a1);
		
		
		// JFrame Basic Settings
		c.setBackground(Color.yellow);
		setTitle("S.M.S");
		setSize(440,260);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String args[]){
		AddScreen as = new AddScreen();
	}
}