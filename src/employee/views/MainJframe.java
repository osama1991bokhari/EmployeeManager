package employee.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;




public class MainJframe extends JFrame{

	private JPanel contentPane;
	private JTextField usernameTF;
	private JButton btnLogin;
	private JPasswordField passwordPF;
	private static final int PORT = 9999;
	private static ServerSocket socket;   
    public static final String USERS_FILE =  "/employee/resources/users.txt";
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
					{
					MainJframe frame = new MainJframe();
					frame.setVisible(true);
					}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void checkIfRunning() {
		  try {
		    //Bind to localhost adapter with a zero connection queue 
		    socket = new ServerSocket(PORT,0,InetAddress.getByAddress(new byte[] {127,0,0,1}));
		  }
		  catch (BindException e) {
		    System.err.println("Already running.");
		    System.exit(1);
		  }
		  catch (IOException e) {
		    System.err.println("Unexpected error.");
		    e.printStackTrace();
		    System.exit(2);
		  }
		}
	/**
	 * Create the frame.
	 */
	public MainJframe() {
		
		checkIfRunning();
		initComponents();
		createEvents();
		

	}

	///////////////////////////////////////////////////////////////////////
	/////This method contains all the code for creating events
	//////////////////////////////////////////////////////////////////////
	
	private void createEvents() 
	{
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = null;
				String password = null;
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(USERS_FILE)));
			
					 username = br.readLine();
					 password = br.readLine();
					 if(!(usernameTF.getText().equals(username)&&passwordPF.getText().equals(password)))
						{
						JOptionPane.showMessageDialog(null,"Wrong Password / Username");
						usernameTF.setText("");
						passwordPF.setText("");
						usernameTF.requestFocus();
						}
						else
						{
						JOptionPane.showMessageDialog(null,"Welcome back Mr. "+ usernameTF.getText().substring(0, 1).toUpperCase()+usernameTF.getText().substring(1));
						SelectTask newTask = new SelectTask();
						newTask.setVisible(true);
						dispose();
						}	
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

			}
		});
		
	}
	
	///////////////////////////////////////////////////////////////////////
	/////This method contains all the code for creating and initializing components.
	//////////////////////////////////////////////////////////////////////
	
	private void initComponents() 
	{
		setTitle("Welcome");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJframe.class.getResource("/employee/resources/SAS_Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 298, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JLabel lblUsername = new JLabel("Username:");
		
		usernameTF = new JTextField();
		usernameTF.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		
		btnLogin = new JButton("Login");
		
		passwordPF = new JPasswordField();

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUsername)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(usernameTF, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLogin)
								.addComponent(passwordPF, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(usernameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnLogin)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
