package p1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DeleteScreen extends JFrame{
	private Container c;
	private JLabel choiceLabel;
	private JLabel oldvalueLabel;
	private static JTextField oldvalueField;
	private JLabel emptyLabel;
	private JLabel emptyTwoLabel;
	private JButton delBtn,backBtn;
	private Border empty,white;
	
	
	
	DeleteScreen(){

		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		oldvalueLabel = new JLabel("Enter Roll No : ");
		oldvalueLabel.setFont(new Font("Arial", Font.BOLD, 19));
		
		oldvalueField = new JTextField();
		oldvalueField.setPreferredSize( new Dimension( 250, 24 ));
		
		emptyLabel = new JLabel("                                                                                                                                          ");
		emptyLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
		
		emptyTwoLabel = new JLabel("                                                                                                                                                                                ");
		emptyTwoLabel.setFont(new Font("Arial", Font.BOLD, 7));
		
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
			
				if(oldvalueField.getText().isEmpty()){
					JOptionPane.showMessageDialog(c,"Please Enter Value in Black Field");
				}else if(oldvalueField.getText().matches("[0-9]+") == false){
					JOptionPane.showMessageDialog(c,"Please enter numerical value");
				}else {
					dataDelete(Integer.parseInt(oldvalueField.getText()));
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
	
	public static void dataDelete(int oldValue){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
		
		try{
			System.out.println("begin");
			Query qry = session.createQuery("delete from Student where id =:java4s");
			qry.setParameter("java4s",oldValue);
			int res = qry.executeUpdate();
			System.out.println("end");
			if(res>0){
				JOptionPane.showMessageDialog(new JFrame(),"Numer of records effected due to delete query "+res);
			}else{
				JOptionPane.showMessageDialog(new JFrame(),"No Matching Record Found");
			}

			oldvalueField.setText("");
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			session.close();
			factory.close();
		}
	}
	
	
}