package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class Login {

	private JFrame frame;
	private JTextField usernametf;
	private JTextField passwordtf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel username = new JLabel("Username ");
		username.setBounds(72, 79, 78, 14);
		frame.getContentPane().add(username);
		
		JLabel password = new JLabel("Password");
		password.setBounds(72, 133, 78, 14);
		frame.getContentPane().add(password);
		
		usernametf = new JTextField();
		usernametf.setBounds(209, 76, 86, 20);
		frame.getContentPane().add(usernametf);
		usernametf.setColumns(10);
		
		passwordtf = new JTextField();
		passwordtf.setBounds(209, 130, 86, 20);
		frame.getContentPane().add(passwordtf);
		passwordtf.setColumns(10);
		
		JButton registerbtn = new JButton("Register");
		registerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("hello");
				Register reg = new Register();
				reg.setVisible(true);
			}
		});
		registerbtn.setBounds(209, 195, 86, 23);
		frame.getContentPane().add(registerbtn);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.setBounds(209, 161, 86, 23);
		frame.getContentPane().add(loginbtn);
	}
	
}
