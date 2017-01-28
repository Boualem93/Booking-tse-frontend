package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import org.jdatepicker.*;

import controller.BookingController;
import controller.CustomerController;
import enterprise.service.BookingManager;
import controller.EventController;
import enterprise.service.exceptions.EventCategoryException;
import enterprise.service.exceptions.EventNameException;
import model.Booking;
import model.Category;
import model.Event;
import uielement.DateLabelFormatter;
import uielement.RowTable;
import environment.ContextCreator;
import model.Customer;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField eventnametf;
	private JTextField categorytf;
	private JTextField datetf;
	private RowTable table;
	private int cell = -1;
	private int row =-1;
	private int cell1 = -1;
	private int row1 =-1;
	private JTextField updateartisttf;
	private JTextField updatedatetf;
	JComboBox comboBox = new JComboBox();
	private JTable table_1;
	private JTextField updatefirstnametf;
	private JTextField updatelastnametf;
	private JTextField updateemailtf;
	private JTextField updateroletf;
	private JTextField updatepasswordtf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		InitialContext context = new ContextCreator().getCtx();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home(context,Login.result);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param result 
	 */
	public Home(InitialContext context, Customer result) {

		EventController event = new EventController(context);
		CustomerController customer = new CustomerController(context);
		BookingController booking = new BookingController(context);
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
		
		
//		UtilDateModel model = new UtilDateModel();
//		//model.setDate(20,04,2014);
//		// Need this...
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//		// Don't know about the formatter, but there it is...
//		panel.add(datePanel);
//		
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		
		
		
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 473, 181);
		panel_1.add(scrollPane);
		table = new RowTable();
		table.setCellSelectionEnabled(true);
		
		table.setEnabled(false);
		table.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(table);
		table.setBackground(Color.WHITE);
		table.setVisible(true);
		JButton btnRefresh = new JButton("Refresh");
		List<Category> listc = event.getAllCategories();
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Event >liste =event.allEvents();


				Vector<String> columnNames = new Vector<String>();
				columnNames.add("ID");
				columnNames.add("Artist");
				columnNames.add("Date");
				columnNames.add("Category");
				columnNames.add("A Seat");
				columnNames.add("B Seat");
				columnNames.add("C Seat");
				columnNames.add("D Seat");
				Vector data = new Vector();

				for (Event evt  : liste) {
					Vector<String> vector = new Vector<String>();
					HashMap <String,Integer> map = booking.getBookedSeats(evt.getId());
					vector.add(Integer.toString(evt.getId()));
					vector.add(evt.getArtist());
					vector.add(evt.getDate().toString());
					vector.add(evt.getCategoryBean().getName()); 
					for (int i=0;i<map.size();i++){
						vector.add(map.get("A")+"/25");
						vector.add(map.get("B")+"/45");
						vector.add(map.get("C")+"/100");
						vector.add(map.get("D")+"/500");
					}
					
					System.out.println(evt.toString());
					data.add(vector);
				}

				table.setModel(new DefaultTableModel(data,columnNames)
				{
					Class[] columnTypes = new Class[] {
							String.class, String.class, String.class, String.class, String.class, String.class, String.class,String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
			}
		});

		btnRefresh.setBounds(493, 7, 89, 23);
		panel_1.add(btnRefresh);

		JButton btnDelete = new JButton("Delete");
		JButton btnEdit = new JButton("Update");
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				cell = -1;
				System.out.println(cell);
				btnDelete.setEnabled(false);
				btnEdit.setEnabled(false);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				cell = -1;
				cell1 = -1;
				System.out.println(cell);
				btnDelete.setEnabled(false);
				btnEdit.setEnabled(false);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				cell = -1;
				cell1=-1;
				System.out.println(cell);
				btnDelete.setEnabled(false);
				btnEdit.setEnabled(false);
			}
		});
		comboBox.setBounds(560, 145, 86, 20);
		
		for (Category category : listc) {
			comboBox.addItem(category.getName());
		}

		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.setBounds(472, 42, 89, 23);
		btnDelete_1.setEnabled(false);
		
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.setBounds(560, 214, 86, 23);
		btnUpdate_1.setEnabled(false);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				row = table.rowAtPoint(ev.getPoint());
				cell = Integer.parseInt(table.getValueAt(row, 0).toString());
				if (cell != -1){
					btnDelete.setEnabled(true);
					btnEdit.setEnabled(true);
					JLabel lblArtist = new JLabel("Artist :");
					lblArtist.setBounds(493, 124, 46, 14);
					panel_1.add(lblArtist);
					
					panel_1.add(comboBox);
					
					updateartisttf = new JTextField();
					updateartisttf.setBounds(560, 121, 86, 20);
					panel_1.add(updateartisttf);
					updateartisttf.setColumns(10);
					
					JLabel lblCategory_1 = new JLabel("Category :");
					lblCategory_1.setBounds(493, 149, 61, 14);
					panel_1.add(lblCategory_1);
					
					JLabel lblDate_1 = new JLabel("Date :");
					lblDate_1.setBounds(493, 174, 46, 14);
					panel_1.add(lblDate_1);
					
					updatedatetf = new JTextField();
					updatedatetf.setBounds(560, 171, 86, 20);
					panel_1.add(updatedatetf);
					updatedatetf.setColumns(10);
					//evt.setDate(date);
					Event evt = event.getEventByID(cell);
					updatedatetf.setText(evt.getDate().toLocaleString());
					
					updateartisttf.setText(evt.getArtist());
					btnDelete_1.setEnabled(false);
					btnUpdate_1.setEnabled(false);
				}
				System.out.println(row);
			}});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cell!=-1){
				if (event.deleteEvent(cell)){
					JOptionPane.showMessageDialog(null,"Event Deleted, Refresh the list !");
					btnDelete.setEnabled(false);
				}
				}
			}
		});
		btnDelete.setBounds(493, 41, 89, 23);
		panel_1.add(btnDelete);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get the new date 
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				Date date = null;
				try {
					date = formatter.parse(updatedatetf.getText());
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				// Create the new updated event

				System.out.println(comboBox.getSelectedItem());
				Event ev1 = event.getEventByID(cell);
				System.out.println("Id event :"+ev1.getId());
				ev1.setArtist(updateartisttf.getText());
				ev1.setDate(date);
				Category cat = null;
				for (Category category : listc) {
					if  (category.getName().equals(comboBox.getSelectedItem().toString()))
						cat = category;
				}
				ev1.setCategoryBean(cat);
				
				System.out.println(ev1.getCategoryBean().getName());
				
				event.updateEvent(ev1,comboBox.getSelectedItem().toString());
				System.out.println(ev1.getArtist()+"  "+ev1.getId()+"   "+ev1.getCategoryBean());
			}
		});
		
		btnEdit.setBounds(560, 202, 89, 23);
		panel_1.add(btnEdit);
		
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("All customers", null, panel_2, null);
		panel_2.setLayout(null);

		
		panel_2.add(btnDelete_1);
	
		panel_2.add(btnUpdate_1);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 452, 181);
		panel_2.add(scrollPane_1);
		
		table_1 = new RowTable();
		table_1.setEnabled(false);
		table_1.setCellSelectionEnabled(true);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.setBounds(472, 8, 89, 23);
		panel_2.add(btnRefresh_1);
		
		
		
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Customer >liste = customer.getAllCustomer();


				Vector<String> columnNames = new Vector<String>();
				columnNames.add("ID");
				columnNames.add("First name");
				columnNames.add("Last name");
				columnNames.add("Email");
				columnNames.add("Role");
		
				Vector data = new Vector();

				for (Customer cus  : liste) {
					Vector<String> vector = new Vector<String>();
					//HashMap <String,Integer> map = booking.getBookedSeats(evt.getId());
					vector.add(Integer.toString(cus.getId()));
					vector.add(cus.getFirstname());
					vector.add(cus.getLastname());
					vector.add(cus.getEmail());
					vector.add(Integer.toString(cus.getRole()));
					data.add(vector);
				}

				table_1.setModel(new DefaultTableModel(data,columnNames)
				{
					Class[] columnTypes = new Class[] {
							String.class, String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
			}
		});
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				row1 = table_1.rowAtPoint(ev.getPoint());
				cell1 = Integer.parseInt(table_1.getValueAt(row1, 0).toString());
				if (cell1 != -1){
					btnDelete_1.setEnabled(true);
					btnUpdate_1.setEnabled(true);
					JLabel lblFirstName = new JLabel("First name :");
					lblFirstName.setBounds(467, 114, 73, 14);
					panel_2.add(lblFirstName);
					
					JLabel lblLastName = new JLabel("Last name :");
					lblLastName.setBounds(467, 139, 73, 14);
					panel_2.add(lblLastName);
					
					JLabel lblEmail = new JLabel("Email :");
					lblEmail.setBounds(467, 164, 46, 14);
					panel_2.add(lblEmail);
					
					JLabel lblRole = new JLabel("Role :");
					lblRole.setBounds(467, 191, 46, 14);
					panel_2.add(lblRole);
					
					updatefirstnametf = new JTextField();
					updatefirstnametf.setBounds(560, 111, 86, 20);
					panel_2.add(updatefirstnametf);
					updatefirstnametf.setColumns(10);
					
					JLabel lblPassword = new JLabel("Email :");
					lblPassword.setBounds(420, 164, 46, 14);
					panel_2.add(lblPassword);
					
					updatepasswordtf = new JTextField();
					updatepasswordtf.setBounds(500, 111, 86, 20);
					panel_2.add(updatepasswordtf);
					updatepasswordtf.setColumns(10);
					
					
					updatelastnametf = new JTextField();
					updatelastnametf.setBounds(560, 139, 86, 20);
					panel_2.add(updatelastnametf);
					updatelastnametf.setColumns(10);
					
					updateemailtf = new JTextField();
					updateemailtf.setText("");
					updateemailtf.setBounds(560, 164, 86, 20);
					panel_2.add(updateemailtf);
					updateemailtf.setColumns(10);
					
					updateroletf = new JTextField();
					updateroletf.setBounds(560, 188, 86, 20);
					panel_2.add(updateroletf);
					updateroletf.setColumns(10);
					btnDelete.setEnabled(false);
					btnEdit.setEnabled(false);
				}
				System.out.println(cell1);
			}});
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				cell = -1;
				cell1 = -1;
				System.out.println(cell);
				btnDelete.setEnabled(false);
				btnEdit.setEnabled(false);
			}
		});		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				cell = -1;
				cell1 = -1;
				System.out.println(cell);
				btnDelete_1.setEnabled(false);
				btnUpdate_1.setEnabled(false);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				cell = -1;
				cell1=-1;
				System.out.println(cell);
				btnDelete_1.setEnabled(false);
				btnUpdate_1.setEnabled(false);
			}
		});
	}
}
