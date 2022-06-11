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

public class RegistrationPage implements ActionListener{
	
	   JTextField pName;
	   JTextField pAdress;
	   JTextField phoneNumber;
	   JTextField petName;
	   JTextField dataUltimeiDeparazitari;
	   JTextField dataUltimuluiVaccin;
	   JTextField fullInfo;
	   JTextField age;
	   String[] animale = {"-", "câine", "pasăre", "pisică"};
	   JComboBox species = new JComboBox(animale);
	  
	   String[] rase = {"-"};
	   JComboBox race= new JComboBox(rase);
	     
	  JPanel inFieldPane = new JPanel();
	  
	  JButton pacientButton = new JButton("Înregistrează un pacient");
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
      
      JFrame f= new JFrame("Fisa inregistrare"); 
	public RegistrationPage()
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
    
        
       
       
       pName = new JTextField(10);
       petName = new JTextField(10);
       age=new JTextField(10); 
	   pAdress= new JTextField(20);
	   phoneNumber=new JTextField(20);
	  
	   dataUltimeiDeparazitari=new JTextField(10);
       dataUltimuluiVaccin=new JTextField(10);
	  
       fullInfo = new JTextField(20);
       fullInfo.setEditable(false);
            
       inFieldPane.setBounds(70, 30, 500, 600);
       inFieldPane.setBackground(new Color(255, 204, 100));
       inFieldPane.setLayout(new GridLayout(22,10));
        
       inFieldPane.add(new JLabel("   Nume Proprietar:"));
       inFieldPane.add(pName);
       pName.addActionListener(this);
        
       inFieldPane.add(new JLabel("   Adresă:"));
       inFieldPane.add(pAdress);
       pAdress.addActionListener(this);
        
       inFieldPane.add(new JLabel("   Număr de telefon:"));
       inFieldPane.add(phoneNumber);
       phoneNumber.addActionListener(this);
        
       inFieldPane.add(new JLabel("   Nume Pacient:"));
       inFieldPane.add(petName);
       petName.addActionListener(this);
    
       inFieldPane.add(new JLabel("   Varstă Pacient:"));
       inFieldPane.add(age);
       age.addActionListener(this);
       
       inFieldPane.add(new JLabel("   Data ultimei deparazitări:"));
       inFieldPane.add(dataUltimeiDeparazitari);
       dataUltimeiDeparazitari.addActionListener(this);
       
       inFieldPane.add(new JLabel("   Data ultimei vaccinări:"));
       inFieldPane.add(dataUltimuluiVaccin);
       dataUltimuluiVaccin.addActionListener(this);
       
       inFieldPane.add(new JLabel("   Specie:"));
       inFieldPane.add(species);
       species.addActionListener(this);   
        
       inFieldPane.add(new JLabel("   Rasă:"));
       inFieldPane.add(race);
       race.addActionListener(this);
        
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
		String fullString = pName.getText().trim() + ", "
       		 +pAdress.getText().trim()+", "
       		 +phoneNumber.getText().trim()+", "
                + petName.getText().trim()+", "
                +age.getText().trim()+", "
                +dataUltimeiDeparazitari.getText().trim()+", "
                +dataUltimuluiVaccin.getText().trim()
                +species.getSelectedItem() +", "
                +race.getSelectedItem()+", ";
        
        fullInfo.setText(fullString);
		     
		     try {
				Connection connection = DriverManager.getConnection(jdbcURL, username, password);
				System.out.println("Connected to PostgreSQL server");
				CallableStatement statement =null;
				statement = connection.prepareCall("CALL s_insert_inregistrare(?,?,?,?,?,?,?,?,?)");
				
				statement.setString(1, pName.getText().trim());  
				statement.setString(2, pAdress.getText().trim());
				statement.setString(3, phoneNumber.getText().trim());
				statement.setString(4, petName.getText().trim());
				statement.setInt(5, Integer.parseInt (age.getText().trim()));
				statement.setString(6, dataUltimeiDeparazitari.getText().trim());
				statement.setString(7, dataUltimuluiVaccin.getText().trim());
				statement.setString(8, species.getSelectedItem().toString());
				statement.setString(9, race.getSelectedItem().toString());
				
				statement.execute();
				JOptionPane.showMessageDialog(null, "Inserare cu succes", "Insert", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("A fost inserata o noua inregistrare");
				connection.close();
				
			} catch (SQLException ex) {
				System.out.println("Error in connecting to PostgreSQL server");
				ex.printStackTrace();
			}
	}
	
	void addRaceForEachSpecies()
	{
		race.removeAllItems();
		  if(species.getSelectedItem().equals("câine"))
	      {
	        	String [] rase = { "Akita Inu", "American Eskimo", "Azawakh", "Beagle", "Bulldog englez", "Bulldog francez", "Cane Corso",
	        			"Chihuahua", "Chow chow","Ciobănesc de Anatolia", "Ciobănesc de Berna", "Ciobănesc caucazian","Ciobănesc german",
	        			"Ciobănesc românesc de Bucovina", "Dalmațian" , "Dobermann", "Dog argentinian", "Fox terrier", "Golden Retriever",
	        			"Husky Siberian", "Jack Russell Terrier", "Kuvasz", "Labrador Retriever", "Malamut de Alaska", "Pechinez", "Pitbull",
	        			"Rottweiler", "West Highland terrier", "Yorkshire Terrier"};
	        	 for(String s: rase)
		          {
		        	race.addItem(s);
		          }
	      }
		  else if(species.getSelectedItem().equals("pasăre"))
	      {
	        	String [] rase = {"Bibilică cu mărgele roșii", "Curcan",  "Găină" , "Gâsc‎ă" , "Papagal", "Porumbel" , "Rață"};	
	        	 for(String s: rase)
		          {
		        	race.addItem(s);
		          }
	      }
	      else
	      {
	        	String [] rase = { "Abisiniană","American curl", "Angora Turcească" , "Birmaneză", "British Shorthair", "Burmilla", "Chartreux", "Devon Rex",
	        			"Korat" ,"Maine Coon", "Norvegiana de pădure", "Ocicat", "Pisică europeană", "Pisică orientală", "Pisică persană",
	        			"Pisică siberiană", "Siameză"};
	        	 for(String s: rase)
		          {
		        	race.addItem(s);
		          }
	      }
        race.setVisible(true);
		  inFieldPane.add(race);
	}
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if(e.getSource() == species)
		  {
			 addRaceForEachSpecies();
		  }
		 
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
