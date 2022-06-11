package cabinet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultationPage implements ActionListener{
	
	 
	   JTextField medic;
	   JTextField diagnostic;
	   JTextField interventii;
	   JTextField tratament;
	   JTextField cost_consultatie;
	   JTextField fullInfo;
	        
	   
	  JPanel inFieldPane = new JPanel();
	  
	  JButton submitButton = new JButton("Înregistrează");

	  
	  String jdbcURL="jdbc:postgresql://localhost:5432/cabinetVeterinar";
	  String username="postgres";
	  String password="1234";	
	  
	  JMenuBar menuBar;
      JMenu menu, submenu;
      JMenuItem inregistrareNoua, programareNoua, consultatieNoua;
      JMenuItem inregistrari, programari, consultatii;
      JRadioButtonMenuItem rbMenuItem;
      JCheckBoxMenuItem cbMenuItem;
	  
      JFrame f= new JFrame("Consultatie nouă");  
      
	public ConsultationPage()
	{
     
		
		 menuBar=new JMenuBar();
	        menu= new JMenu("Optiuni");
	        menuBar.add(menu);
	        
	        menu.addSeparator();
	        submenu=new JMenu("Inregistrari");
	        menu.add(submenu);
	        
	        inregistrareNoua = new JMenuItem("Adauga inregistrare noua");
	        inregistrareNoua.addActionListener(this);
	        inregistrari= new JMenuItem("Toate inregistrarile");
	        inregistrari.addActionListener(this);
	        submenu.add(inregistrareNoua);
	        submenu.addSeparator();
	        submenu.add(inregistrari);
	 
	        
	        menu.addSeparator();
	        submenu=new JMenu("Programari");
	        menu.add(submenu);
	        programareNoua = new JMenuItem("Adauga programare noua");
	        programareNoua.addActionListener(this);
	        programari= new JMenuItem("Toate programarile");
	        programari.addActionListener(this);
	        submenu.add(programareNoua);
	        submenu.addSeparator();
	        submenu.add(programari);
	        
	        menu.addSeparator();
	        submenu=new JMenu("Consultatii");
	        menu.add(submenu);
	        consultatieNoua = new JMenuItem("Adauga consultatie noua");
	        consultatieNoua.addActionListener(this);
	        consultatii= new JMenuItem("Toate consultatiile");
	        consultatii.addActionListener(this);
	        submenu.add(consultatieNoua);
	        submenu.addSeparator();
	        submenu.add(consultatii);
	        
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       JPanel panel=new JPanel();  
       panel.setBounds(0,0,1540,830);
       panel.setBackground(new Color(255, 204, 100));
       f.setJMenuBar(menuBar);
       f.setLayout(null);
       
       

       Icon imgIcon;
       JLabel label;
       
       try {
	        imgIcon = new ImageIcon(this.getClass().getResource("pisicadoctor.gif"));
	         label = new JLabel(imgIcon);
	         label.setBounds(440, -130, 1250, 970); 
		        f.getContentPane().add(label);
       }catch(Exception e) {};
	       
       Icon img;
       JLabel logo;
       try {
    	     img = new ImageIcon(this.getClass().getResource("logo.PNG"));
    	     logo = new JLabel(img);
    	     logo.setBounds(0,0, 50, 50); 
    	       f.getContentPane().add(logo);
      }catch(Exception e) {};
       
       medic = new JTextField(10);
       diagnostic= new JTextField(20);
	   interventii=new JTextField(20);
	   tratament=new JTextField(20);
	   cost_consultatie= new JTextField(20);
	   
	   
	   fullInfo = new JTextField(20);
       fullInfo.setEditable(false);
	  
       inFieldPane.setBounds(70, 30, 500, 600);
       inFieldPane.setBackground(new Color(255, 204, 100));
       inFieldPane.setLayout(new GridLayout(22,10));
        
       inFieldPane.add(new JLabel("   Nume Medic:"));
       inFieldPane.add(medic);
       medic.addActionListener(this);
        
       inFieldPane.add(new JLabel("   Diagnostic:"));
       inFieldPane.add(diagnostic);
       diagnostic.addActionListener(this);
        
       inFieldPane.add(new JLabel("   Interventii:"));
       inFieldPane.add(interventii);
       interventii.addActionListener(this);
       
       inFieldPane.add(new JLabel("   Tratament:"));
       inFieldPane.add(tratament);
       tratament.addActionListener(this);
       
       inFieldPane.add(new JLabel("   Costul consultatiei:"));
       inFieldPane.add( cost_consultatie);
       cost_consultatie.addActionListener(this);
       
       f.add(inFieldPane,BorderLayout.NORTH);
 
       
       JPanel outFieldPane = new JPanel();
       outFieldPane.setBounds(70, 660, 400, 600);
       outFieldPane.setBackground(new Color(255, 204, 100));
       outFieldPane.add(new JLabel(" Ați introdus:"));
       outFieldPane.setLayout(new GridLayout(23,20));
       outFieldPane.add(fullInfo);
       f.add(outFieldPane,BorderLayout.SOUTH);
       
       
       JPanel submitPane = new JPanel();
       submitPane.setBounds(40,625,400,400);
       submitPane.setBackground(new Color(255, 204, 100));
       submitPane.setLayout(new FlowLayout());
       submitPane.add(new JLabel(" Doriți să înregistrați datele introduse?    "));
       submitButton.addActionListener(this);
       submitButton.setBackground(new Color(102, 204, 204));
       submitPane.add(submitButton);
       f.add(submitPane,BorderLayout.CENTER);
       
       f.pack();
       f.setVisible(true);

       f.add(panel);  
       f.setSize(1540,830); 
       f.setLayout(null);    
       f.setVisible(true); 
	}

	void insertInDataBase()
	{
		String fullString = medic.getText().trim() + ", "
       		 +diagnostic.getText().trim()+", "
       		 +interventii.getText().trim()+", "
       		 +tratament.getText().trim()+", "
       		 +cost_consultatie.getText().trim()+", ";
        
        fullInfo.setText(fullString);
		     
        try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connected to PostgreSQL server");
			CallableStatement statement =null;
			statement = connection.prepareCall("CALL z_insert_consultatie(?,?,?,?,?)");
			
			statement.setString(1, medic.getText().trim());  
			statement.setString(2, diagnostic.getText().trim());
			statement.setString(3, interventii.getText().trim());
			statement.setString(4, tratament.getText().trim());
			statement.setInt(5, Integer.parseInt (cost_consultatie.getText().trim()));
			
			statement.execute();
			JOptionPane.showMessageDialog(null, "Inserare cu succes", "Insert", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("A fost inserata o noua consultatie");
			connection.close();
			
		} catch (SQLException ex) {
			System.out.println("Error in connecting to PostgreSQL server");
			ex.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == submitButton)
	      {
	         insertInDataBase();
	      }
		 
		 if(e.getSource()==inregistrareNoua)
		 {
			 f.setVisible(false);
			 RegistrationPage page = new RegistrationPage();
		 }
		 if (e.getSource() == inregistrari)
		 {
			 f.setVisible(false);
			 RegistrationTable table= new RegistrationTable();
		 }
		 
		 if (e.getSource() == programareNoua)
		 {
			 f.setVisible(false);
			 AppointmentPage page = new AppointmentPage();
		 }
		 
		 if (e.getSource() == programari)
		 {
			 f.setVisible(false);
			 AppointmentTable table = new AppointmentTable();
		 }
		 
		 if (e.getSource() == consultatieNoua)
		 {
			 f.setVisible(false);
			 ConsultationPage page = new ConsultationPage();
		 }
		 
		 if (e.getSource() == consultatii)
		 {
			 f.setVisible(false);
			 ConsultationTable table = new ConsultationTable();
		 }
		
		 
		
	}
}