package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;

import controller.registerController;
import enterprise.service.exceptions.CustomerEmailException;
import enterprise.service.exceptions.CustomerEntityExistsException;
import enterprise.service.exceptions.CustomerPasswordException;
import environment.ContextCreator;
import model.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
public class Register extends JFrame {

	private JPanel registerbtn;
	private JTextField firstnametf;
	private JTextField lastnametf;
	private JTextField emailtf;
	private	registerController client;
	private JPasswordField passwordtf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			InitialContext context = new ContextCreator().getCtx();
			//registerController logincontroller = new registerController(context);
			public void run() {
				
				try {
					Register frame = new Register(context);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public Register(InitialContext context) {
		
		registerController client = new registerController(context);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		registerbtn = new JPanel();
		registerbtn.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(registerbtn);
		registerbtn.setLayout(null);
		
		JLabel emaillbl = new JLabel("E-mail");
		emaillbl.setBounds(91, 96, 60, 14);
		registerbtn.add(emaillbl);
		
		JLabel passwordlbl = new JLabel("Password");
		passwordlbl.setBounds(91, 124, 60, 14);
		registerbtn.add(passwordlbl);
		
		JLabel firstnamelbl = new JLabel("First name");
		firstnamelbl.setBounds(91, 46, 60, 14);
		registerbtn.add(firstnamelbl);
		
		JLabel lastnamelbl = new JLabel("Last name");
		lastnamelbl.setBounds(91, 71, 60, 14);
		registerbtn.add(lastnamelbl);
		
		firstnametf = new JTextField();
		firstnametf.setBounds(199, 43, 86, 20);
		registerbtn.add(firstnametf);
		firstnametf.setColumns(10);
		
		lastnametf = new JTextField();
		lastnametf.setBounds(199, 68, 86, 20);
		registerbtn.add(lastnametf);
		lastnametf.setColumns(10);
		
		emailtf = new JTextField();
		emailtf.setBounds(199, 93, 86, 20);
		registerbtn.add(emailtf);
		emailtf.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Enter Try");
					System.out.println("Email: " + emailtf.getText());
					System.out.println("Pass : " + passwordtf.getText());
					System.out.println("Lastname : " + lastnametf.getText());
					System.out.println("Firstname : " + firstnametf.getText());
					Customer clt = client.register(emailtf.getText(),passwordtf.getText(),lastnametf.getText(),firstnametf.getText());
					System.out.println("RegisterBean called");
					if (clt != null){JOptionPane.showMessageDialog(null,clt.toString());}
					else{JOptionPane.showMessageDialog(null,"Error");}
				} catch (CustomerEntityExistsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CustomerPasswordException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CustomerEmailException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		btnRegister.setBounds(196, 166, 89, 23);
		registerbtn.add(btnRegister);
		
		passwordtf = new JPasswordField();
		passwordtf.setBounds(199, 121, 86, 20);
		registerbtn.add(passwordtf);
	}

}
