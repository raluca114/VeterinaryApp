package cabinet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

class RegistrationTable extends JFrame implements ActionListener 
{
		   JTable table ;
		   JLabel searchLbl; 
		   JTextField jtf;

		  JMenuBar menuBar;
	      JMenu menu, submenu;
	      JMenuItem inregistrareNoua, programareNoua, consultatieNoua;
	      JMenuItem inregistrari, programari, consultatii;
	      JRadioButtonMenuItem rbMenuItem;
	      JCheckBoxMenuItem cbMenuItem;
	      
	      JButton deleteButton = new JButton("Sterge inregistrare");
	      JButton updateButton = new JButton("Actualizeaza inregistrare");
	      
	      DefaultTableModel model = new DefaultTableModel();
	      
	        String jdbcURL="jdbc:postgresql://localhost:5432/cabinetVeterinar";
			String username="postgres";
			String password="1234";
	 	 
		RegistrationTable()
		{
			menuBar=new JMenuBar();
	        menu= new JMenu("Optiuni");
	        menuBar.add(menu);
	        
	        menu.addSeparator();
	        submenu=new JMenu("Inregistrari");
	        menu.add(submenu);
	        
	        inregistrareNoua = new JMenuItem("Adauga inregistrare noua");
	        inregistrareNoua.addActionListener((ActionListener) this);
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
		
	        deleteButton.addActionListener(this);
	        deleteButton.setBackground(new Color(102, 204, 204));
	        deleteButton.setBounds(400,700,200,50);
	        
	        
	        updateButton.addActionListener(this);
	        updateButton.setBackground(new Color(102, 204, 204));
	        updateButton.setBounds(850,700,200,50);
	        
	        
			setBounds(0,0,1536,828);
			setBackground(new Color(255, 204, 100));
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setJMenuBar(menuBar);
			add(deleteButton);
			add(updateButton);
		    setLayout(null);
			setVisible(true);
			
			jtf = new JTextField(15);
		    searchLbl = new JLabel("Search");
		    
			
			
	        model.addColumn("Nume proprietar");
	        model.addColumn("Adresa");
	        model.addColumn("Numar de telefon");
	        model.addColumn("Nume pacient");
	        model.addColumn("Varsta pacient");
	        model.addColumn("Data ultimei deparazitari");
	        model.addColumn("Data ultimei vaccinari");
	        model.addColumn("Specie");
	        model.addColumn("Rasa");
	       
			  try {
					Connection connection = DriverManager.getConnection(jdbcURL, username, password);
					System.out.println("Connected to PostgreSQL server");

					String sql= "SELECT * FROM inregistrare";
					Statement statement = connection.createStatement();
					ResultSet result = statement.executeQuery(sql);
					while(result.next())
					{
						String nume_proprietar = result.getString("nume_proprietar");
						String adresa = result.getString("adresa");
						String telefon = result.getString("telefon");
						String nume_pacient = result.getString("nume_pacient");
						Integer varsta = result.getInt("varsta");
						String data_deparazitare = result.getString("data_deparazitare");
						String data_vaccinare = result.getString("data_vaccinare");
						String specie = result.getString("specie");
						String rasa = result.getString("rasa");
						
				        model.addRow(new Object[]{nume_proprietar,adresa,telefon, nume_pacient, varsta, data_deparazitare, data_vaccinare, specie, rasa});
			
					}
					
					connection.close();
					
				} catch (SQLException ex) {
					System.out.println("Error in connecting to PostgreSQL server");
					ex.printStackTrace();
				}
			  
			  table=new JTable(model);
			
		    add(searchLbl);
		    add(jtf);
	
		    
		    JScrollPane sp=new JScrollPane(table);
		    this.setLayout(null);
		    sp.setBounds(0,0,1523,1100);
		    sp.getViewport().setBackground(new Color(255, 204, 100));
		    add(sp);
			validate();
		    
		    
		    
	       
	 
	}		
		
		void deleteInDataBase(int id)
		{
			try {
				Connection connection = DriverManager.getConnection(jdbcURL, username, password);
				System.out.println("Connected to PostgreSQL server");
				CallableStatement statement = null;
				statement = connection.prepareCall("CALL z_delete_inregistrare(?)");
				
				statement.setInt(1, id);
				
				statement.execute();
				System.out.println("A fost stearsa o inregistrare");
				connection.close();
				JOptionPane.showMessageDialog(null, "Stergere cu succes", "Delete", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (SQLException ex) {
				System.out.println("Error in connecting to PostgreSQL server");
				ex.printStackTrace();
			}
		}
		
		void update(Inregistrare inregistrare )
		{
			try {
				Connection connection = DriverManager.getConnection(jdbcURL, username, password);
				System.out.println("Connected to PostgreSQL server");
				CallableStatement statement = null;
				statement = connection.prepareCall("CALL z_update_inregistrare(?,?,?,?,?,?,?,?,?)");
				
				statement.setString(1, inregistrare.getNumeProprietar());
				statement.setString(2, inregistrare.getAdresa());
				statement.setString(3, inregistrare.getTelefon());
				statement.setString(4, inregistrare.getNumePacient());
				statement.setInt(5, inregistrare.getVarsta());
				statement.setString(6, inregistrare.getDataDeparazitare());
				statement.setString(7, inregistrare.getDataVaccinare());
				statement.setString(8, inregistrare.getSpecie());
				statement.setString(9, inregistrare.getRasa());
				
				statement.execute();
				JOptionPane.showMessageDialog(null, "Actualizare cu succes", "Update", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("A fost editata o inregistrare");
				connection.close();
				
			} catch (SQLException ex) {
				System.out.println("Error in connecting to PostgreSQL server");
				ex.printStackTrace();
			}
      }
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==inregistrareNoua)
			 {
				 setVisible(false); 
				 RegistrationPage page = new RegistrationPage();
			 }

			 if (e.getSource() == inregistrari)
			 {
				 setVisible(false); 
				 RegistrationTable table= new RegistrationTable();
			 }
			 
			 if (e.getSource() == programareNoua)
			 {
				 setVisible(false); 
				 AppointmentPage page = new AppointmentPage();
			 }
			 
			 if (e.getSource() == programari)
			 {
				 setVisible(false); 
				 AppointmentTable table = new AppointmentTable();
			 }
			 
			 if (e.getSource() == consultatieNoua)
			 {
				 setVisible(false); 
				 ConsultationPage page = new ConsultationPage();
			 }
			 
			 if (e.getSource() == consultatii)
			 {
				 setVisible(false); 
				 ConsultationTable table = new ConsultationTable();
			 }
			 if(table.getSelectedRow() != -1) 
			 {System.out.println(table.getSelectedRow());
	               // remove selected row from the model
				 if (e.getSource() == deleteButton)
				 {
					 deleteInDataBase(table.getSelectedRow());
					 model.removeRow(table.getSelectedRow());
					 	 
				 } 
				 
				 if (e.getSource() == updateButton)
				 {
						 String nume_proprietar = (String) table.getValueAt(table.getSelectedRow(),0).toString();
						 String adresa= (String) table.getValueAt(table.getSelectedRow(),1).toString();
						 String telefon= (String) table.getValueAt(table.getSelectedRow(),2).toString();
					     String nume_pacient= (String) table.getValueAt(table.getSelectedRow(),3).toString();
					     int varsta= Integer.parseInt(table.getValueAt(table.getSelectedRow(),4).toString()) ;
					     String data_deparazitare=(String) table.getValueAt(table.getSelectedRow(),5).toString();
					     String data_vaccinare=(String) table.getValueAt(table.getSelectedRow(),6).toString();
					     String specie=(String) table.getValueAt(table.getSelectedRow(),7).toString();
					     String rasa=(String) table.getValueAt(table.getSelectedRow(),8).toString();
						 System.out.println(rasa+","+specie);
						 Inregistrare inregistrare = new Inregistrare(nume_proprietar, adresa,telefon, nume_pacient,varsta,data_deparazitare,data_vaccinare,specie,rasa);
					     update(inregistrare);
					
				 }
					
				     
				
			 }
			 
			
		}
}