package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.CreateEventController;
import enterprise.service.exceptions.EventCategoryException;
import enterprise.service.exceptions.EventNameException;
import model.Event;
import uielement.RowTable;
import environment.ContextCreator;

import javax.swing.JTabbedPane;
import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField eventnametf;
	private JTextField categorytf;
	private JTextField datetf;
	private RowTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		InitialContext context = new ContextCreator().getCtx();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home(context);
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
	public Home(InitialContext context) {
		CreateEventController event = new CreateEventController(context);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 661, 265);
		contentPane.add(tabbedPane);
		datetf = new JTextField();
		datetf.setText("7-Jun-2013");
		datetf.setBounds(154, 89, 86, 20);
		datetf.setColumns(10);
		JPanel panel = new JPanel();
		panel.add(datetf);
		tabbedPane.addTab("Event", null, panel, null);
		panel.setLayout(null);
		JButton btnCreate = new JButton("Create ");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				Date date = null;
				try {
					date = formatter.parse(datetf.getText());
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					
					Event evt = event.create(eventnametf.getText(),date,categorytf.getText());
					System.out.println("RegisterBean called");	
					if (event != null){JOptionPane.showMessageDialog(null,evt.getDate());}
					else{JOptionPane.showMessageDialog(null,"Error");}
				} catch (EventNameException | EventCategoryException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnCreate.setBounds(330, 146, 89, 23);
		panel.add(btnCreate);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(26, 26, 76, 14);
		panel.add(lblName);
		
		JLabel lblCategory = new JLabel("Category :");
		lblCategory.setBounds(26, 61, 76, 14);
		panel.add(lblCategory);
		
		eventnametf = new JTextField();
		eventnametf.setBounds(154, 23, 86, 20);
		panel.add(eventnametf);
		eventnametf.setColumns(10);
		
		categorytf = new JTextField();
		categorytf.setBounds(154, 58, 86, 20);
		panel.add(categorytf);
		categorytf.setColumns(10);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(26, 92, 46, 14);
		panel.add(lblDate);
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("All events", null, panel_1, null);
		panel_1.setLayout(null);
		table = new RowTable();
		table.setBackground(Color.WHITE);
		
		

		table.setBounds(10, 11, 473, 80);
		table.setAutoCreateRowSorter(true);
		table.setVisible(true);
		panel_1.add(table);	
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Event >liste =event.allEvents();
		        Vector<String> columnNames = new Vector<String>();
		        columnNames.add("Artist");
		        columnNames.add("Date");
		        columnNames.add("Category");
		        Vector data = new Vector();
		
					for (Event evt  : liste) {
					    Vector<String> vector = new Vector<String>();
					    	vector.add(evt.getArtist());
					    	vector.add(evt.getDate().toString());
					    	vector.add("C"+evt.getId());
					    data.add(vector);
					}
			
		        table.setModel(new DefaultTableModel(data,columnNames)
		        		{
					Class[] columnTypes = new Class[] {
							String.class, String.class, String.class
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
					});
			}
		});
		btnRefresh.setBounds(493, 7, 89, 23);
		panel_1.add(btnRefresh);
	}
}
