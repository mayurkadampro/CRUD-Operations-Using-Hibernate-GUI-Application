package p1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import javax.swing.*;
import java.util.List;

class ViewScreen extends JFrame{
	private Container c;
	private String column[] = {"name","rollno","age","gender"};
	private Object[][] data;
	private JTable valTable;
	private JButton backBtn;
	private Border empty,white;
	
	ViewScreen(){
		c = getContentPane();
		c.setLayout(new FlowLayout());
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact = cfg.buildSessionFactory();
		Session session = sfact.openSession();
		
		try{
			System.out.println("begin");
			List<Student> stu = new ArrayList<>();
			stu = session.createQuery("from Student order by id").list();
			data = new Object[stu.size()][];
			int i = 0;
			for(Student s: stu){
				data[i] = new Object[]{ s.getName(), s.getRno(), s.getAge(), s.getGender()};
				i++;
				//System.out.println(s.getRno()+"  "+s.getName());
			}
			/*
			for(int i=0; i<stu.size(); i++){
				for(int j=0; j<stu.size(); j++){
					data[i][j] = stu.get(i+1);
				}
			}*/
			System.out.println("end");
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			session.close();
			sfact.close();
		}
		
		valTable = new JTable(data,column);
        valTable.setPreferredScrollableViewportSize(new Dimension(430,150));
		valTable.setFillsViewportHeight(true);
		valTable.setEnabled(false); // for make table uneditable
		valTable.getTableHeader().setReorderingAllowed(false); // for make table unmovable
		
		
		
		
		backBtn = new JButton("Back");
		backBtn.setBackground(new Color(255, 0, 0));
		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("Arial", Font.BOLD, 12));
		backBtn.setBorder(white);
		backBtn.setFocusPainted(false);
		backBtn.setPreferredSize(new Dimension(70, 35));
		
		c.add(new JScrollPane(valTable));
		c.add(backBtn);
		
		//action listener
		ActionListener a = (ae) -> {
			MainScreen ms = new MainScreen();
			this.dispose();
		};
		
		backBtn.addActionListener(a);
		
		// JFrame Basic Settings
		setTitle("S.M.S");
		setSize(469,260);
		//setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(data.length <= 0){
			JOptionPane.showMessageDialog(c,"No Record Found. ");
			MainScreen ms = new MainScreen();
		}else{
			setVisible(true);
		}
		
		
		
		
	}
}