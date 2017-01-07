import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Register extends JFrame {

	private JPanel registerbtn;
	private JTextField firstnametf;
	private JTextField lastnametf;
	private JTextField usernametf;
	private JTextField passwordtf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		registerbtn = new JPanel();
		registerbtn.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(registerbtn);
		registerbtn.setLayout(null);
		
		JLabel usernamelbl = new JLabel("Username");
		usernamelbl.setBounds(91, 96, 60, 14);
		registerbtn.add(usernamelbl);
		
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
		
		usernametf = new JTextField();
		usernametf.setBounds(199, 93, 86, 20);
		registerbtn.add(usernametf);
		usernametf.setColumns(10);
		
		passwordtf = new JTextField();
		passwordtf.setBounds(199, 121, 86, 20);
		registerbtn.add(passwordtf);
		passwordtf.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(196, 166, 89, 23);
		registerbtn.add(btnRegister);
	}

}
