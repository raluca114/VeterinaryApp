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

class ConsultationTable extends JFrame implements ActionListener 
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
		 
		 ConsultationTable()
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
		   
	        model.addColumn("Nume Medic");
	        model.addColumn("Diagnostic");
	        model.addColumn("Interventii");
	        model.addColumn("Tratament");
	        model.addColumn("Costul consultatiei");
	       
			  try {
					Connection connection = DriverManager.getConnection(jdbcURL, username, password);
					System.out.println("Connected to PostgreSQL server");

					String sql= "SELECT * FROM consultatie";
					Statement statement = connection.createStatement();
					ResultSet result = statement.executeQuery(sql);
					while(result.next())
					{
						String medic = result.getString("medic");
						String diagnostic = result.getString("diagnostic");
						String interventii = result.getString("interventii");
						String tratament = result.getString("tratament");
						String cost_consultatie = result.getString("cost_consultatie");
						
				        model.addRow(new Object[]{medic ,diagnostic,interventii, tratament,cost_consultatie} );
			
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
					statement = connection.prepareCall("CALL z_delete_consultatie(?)");
					
					statement.setInt(1, id);
					
					statement.execute();
					JOptionPane.showMessageDialog(null, "Stergere cu succes", "Delete", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("A fost stearsa o consultatie");
					connection.close();
					
				} catch (SQLException ex) {
					System.out.println("Error in connecting to PostgreSQL server");
					ex.printStackTrace();
				}
			}
			
			void update(Consultatie consultatie )
			{
				try {
					Connection connection = DriverManager.getConnection(jdbcURL, username, password);
					System.out.println("Connected to PostgreSQL server");
					CallableStatement statement = null;
					statement = connection.prepareCall("CALL z_update_consultatie(?,?,?,?,?)");
					
					statement.setString(1, consultatie.getMedic());
					statement.setString(2, consultatie.getDiagnostic());
					statement.setString(3, consultatie.getInterventii());
					statement.setString(4, consultatie.getTratament());
					statement.setInt(5, consultatie.getCostConsultatie());
					
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
						 System.out.println(table.getSelectedRow());
						 deleteInDataBase(table.getSelectedRow());
						 model.removeRow(table.getSelectedRow());
						 
						 System.out.println(table.getSelectedRow());		 
					 } 
					 
					 if (e.getSource() == updateButton)
					 {
						 String medic = (String) table.getValueAt(table.getSelectedRow(),0).toString();
						 String diagnostic= (String) table.getValueAt(table.getSelectedRow(),1).toString();
						 String interventii= (String) table.getValueAt(table.getSelectedRow(),2).toString();
					     String tratament= (String) table.getValueAt(table.getSelectedRow(),3).toString();
					     int cost_consultatie= Integer.parseInt(table.getValueAt(table.getSelectedRow(),4).toString()) ;
				
						 Consultatie consultatie = new Consultatie(medic, diagnostic,interventii, tratament,cost_consultatie);
					     update(consultatie);
					 }

				 }

			}
	}