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
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class MainJframe extends JFrame{

	private JPanel contentPane;
	private JTextField usernameTF;
	private JButton btnLogin;
	private JPasswordField passwordPF;

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

	/**
	 * Create the frame.
	 */
	public MainJframe() {
		
		
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
				if(!(usernameTF.getText().equals("root")&&passwordPF.getText().equals("root")))
				{
				JOptionPane.showMessageDialog(null,"Wrong Password / Username");
				usernameTF.setText("");
				passwordPF.setText("");
				usernameTF.requestFocus();
				}
				else
				{
				JOptionPane.showMessageDialog(null,"Welcome back "+ usernameTF.getText());
				SelectTask newTask = new SelectTask();
				newTask.setVisible(true);
				dispose();
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJframe.class.getResource("/employee/rescources/SAS_Logo.png")));
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
