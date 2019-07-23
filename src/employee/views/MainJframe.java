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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
				
				File file = new File("nbash");
			    InputStream inputStream = null;
			    try {
					inputStream = new FileInputStream("nbash");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			    BufferedReader br ;
			    if(file.exists())
				 br = new BufferedReader(new InputStreamReader(inputStream));
			    else
			     br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(USERS_FILE)));
				try {
			
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
		setTitle("Welcome  -  \u0645\u0631\u062D\u0628\u0627\u064B");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJframe.class.getResource("/employee/resources/SAS_Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 290, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JLabel lblUsername = new JLabel("Username:");
		
		usernameTF = new JTextField();
		usernameTF.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		
		btnLogin = new JButton("Login  -  \u0627\u0644\u062F\u062E\u0648\u0644");
		
		passwordPF = new JPasswordField();
		
		JLabel label = new JLabel("\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062A\u062E\u062F\u0645");
		
		JLabel label_1 = new JLabel("\u0627\u0644\u0631\u0642\u0645 \u0627\u0644\u0633\u0631\u064A");

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsername)
										.addComponent(label))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(usernameTF, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPassword)
										.addComponent(label_1))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(passwordPF, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(91)
							.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblUsername)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(usernameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(passwordPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(btnLogin)
					.addContainerGap(78, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
