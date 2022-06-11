package cabinet;


	import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  
public class MyPanel implements ActionListener
{  
	  
	     
	  JButton pacientButton = new JButton("Înregistrează un pacient"); 
      JFrame frame1= new JFrame("Bine ati venit!"); 
	  public  MyPanel()  
	  { 
		  
	        
		    
		    frame1.setBounds(160,100,1200,630);
		    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame1.getContentPane().setLayout(null);
		    
		    ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("welcome.png"));
	        JLabel label = new JLabel(imgIcon);
	        label.setBounds(-340,-200, 1870, 1030); 
	        frame1.getContentPane().add(label);
	        
	        
	        
		    JPanel welcomePane = new JPanel();
		    welcomePane.setBounds(500,530,200,200);
		    welcomePane.setLayout(new FlowLayout());
		    pacientButton.setBounds(800,800,300,300);
		    pacientButton.addActionListener(this);
		    pacientButton.setBackground(new Color(250, 156, 14));
	        welcomePane.add( pacientButton);
	        frame1.add( welcomePane, BorderLayout.CENTER);	
	        frame1.setVisible(true);
	        
	        
	        }
	 
	public void actionPerformed (ActionEvent e)
	  { 	  
		  if (e.getSource() == pacientButton)
		  { 
			 frame1.setVisible(false); 
			 RegistrationPage register = new RegistrationPage();
		    
          }   
	  }
		public static void main(String args[])  
		{  
			new MyPanel();  
		}  
		   
	        
	     
}
