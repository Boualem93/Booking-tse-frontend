package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.loginController;
import environment.ContextCreator;

import javax.swing.JButton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	private JTextField usernametf;
	private JPasswordField passwordtf;

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
	 * @throws NamingException 
	 */
	public Login() throws NamingException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws NamingException 
	 */
	private void initialize() throws NamingException {
		InitialContext context = new ContextCreator().getCtx();
		loginController logincontroller = new loginController(context);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel email = new JLabel("E-mail");
		email.setBounds(72, 79, 78, 14);
		frame.getContentPane().add(email);
		
		JLabel password = new JLabel("Password");
		password.setBounds(72, 133, 78, 14);
		frame.getContentPane().add(password);
		
		usernametf = new JTextField();
		usernametf.setBounds(209, 76, 86, 23);
		frame.getContentPane().add(usernametf);
		usernametf.setColumns(10);
		
		JButton registerbtn = new JButton("Register");
		
		registerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("hello");
				Register reg = new Register(context);
				reg.setVisible(true);
			}
		});
		registerbtn.setBounds(209, 195, 86, 23);
		frame.getContentPane().add(registerbtn);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = logincontroller.login(usernametf.getText(),passwordtf.getText());
				if(result==null){
					JOptionPane.showMessageDialog(null,"Unable to log in");
				}
				else{
					Home home = new Home(context);
					home.setVisible(true);
					frame.setVisible(false);
				}
			}
		});
		loginbtn.setBounds(209, 161, 86, 23);
		frame.getContentPane().add(loginbtn);
		
		passwordtf = new JPasswordField();
		passwordtf.setBounds(209, 127, 86, 23);
		frame.getContentPane().add(passwordtf);
	}
	
}
