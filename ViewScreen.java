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
	Container c;
	String column[] = {"name","rollno","age","gender"};
	Object[][] data = new Object[][] {
            {"Mayur", 1, 23, "Male" },
            {"Rahul",2, 22, "Male" },
            {"Zorro",3, 60, "Female" },
        };
	JTable valTable;
	JButton backBtn;
	Border empty,white;
	
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
			stu = session.createQuery("from Student").list();
			for(Student s: stu){
				System.out.println(s.getRno()+"  "+s.getName());
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
		setVisible(true);
	}
}