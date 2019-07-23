package employee.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField pfConfirm;
	private JPasswordField pfNew;
	private JPasswordField pfOld;
	private JButton btnCancel;
	private JButton btnChange;
    public static final String USERS_FILE =  "/employee/resources/users.txt";
    public static final Path USERS_FILE2 =  FileSystems.getDefault().getPath(USERS_FILE); 
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
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
	public ChangePassword() {
		
		initComponent();
		createEvent();
		
	}

	private void createEvent() {
		
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String password = pfOld.getText();
				
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
					String username = null;
					String realOldPassword = null;
					
					try {
						username = br.readLine();
						realOldPassword = br.readLine();
						br.close();
						
					if (realOldPassword.equals(password))			        
				    	if(pfNew.getText().equals(pfConfirm.getText()))
				    	{
							Runtime.getRuntime().exec("attrib -H -R nbash");
				    		int choise = JOptionPane.showConfirmDialog(null, "Are you sure you want to change your password?","Confirm changing", JOptionPane.YES_NO_OPTION);

				    		if(choise==JOptionPane.YES_OPTION)
				    		try 
				    		{
				    			KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
				                SecretKey myDesKey = keygenerator.generateKey();

				                Cipher desCipher;
				                desCipher = Cipher.getInstance("AES");


				                byte[] text = pfNew.getText().getBytes("UTF8");


				                desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
				                byte[] textEncrypted = desCipher.doFinal(text);

				                String s = new String(textEncrypted);
				                System.out.println(s);

				                desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
				                byte[] textDecrypted = desCipher.doFinal(textEncrypted);

				                s = new String(textDecrypted);
				                System.out.println(s);
							OutputStream outputStream = new FileOutputStream(file);
							BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
							bw.write(username);
							bw.newLine();
							bw.write(pfNew.getText());
							bw.close();
							JOptionPane.showMessageDialog(null, "Your password has been changed successfully");
				    		SelectTask frame=new SelectTask();
				    		frame.setVisible(true);dispose();
				    		} catch (IOException e1)
				    		{
							e1.printStackTrace();
				    		} catch (NoSuchAlgorithmException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (NoSuchPaddingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvalidKeyException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalBlockSizeException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (BadPaddingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    		else
				    		{
				    			pfOld.setText("");
				    			pfNew.setText("");
				    			pfConfirm.setText("");
				    		}
						
						}
				    	else
						JOptionPane.showMessageDialog(null, "Please make sure that password confirmation is correct");
										
				    else
				    	JOptionPane.showMessageDialog(null, "Please make sure that the old password is correct");
						
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				    try {
						Runtime.getRuntime().exec("attrib +H nbash");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				   

				
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectTask frame=new SelectTask();
				frame.setVisible(true);dispose();
			}
		});
		
	}

	private void initComponent() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditEmployees.class.getResource("/employee/resources/SAS_Logo.png")));
		setTitle("Change password - \u062A\u063A\u064A\u064A\u0631 \u0627\u0644\u0631\u0642\u0645 \u0627\u0644\u0633\u0631\u064A");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		JLabel lblOldPassword = new JLabel("Old Password:");
		
		JLabel lblNewPassword = new JLabel("New Password:");
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		
		btnChange = new JButton("Change - \u062A\u063A\u064A\u064A\u0631");
		
		
		btnCancel = new JButton("Back - \u0627\u0644\u0631\u062C\u0648\u0639");
		
		
		pfConfirm = new JPasswordField();
		
		pfNew = new JPasswordField();
		
		pfOld = new JPasswordField();
		
		label = new JLabel("\u0627\u0644\u0631\u0642\u0645 \u0627\u0644\u0633\u0631\u064A \u0627\u0644\u0642\u062F\u064A\u0645");
		
		label_1 = new JLabel("\u0627\u0644\u0631\u0642\u0645 \u0627\u0644\u0633\u0631\u064A \u0627\u0644\u062C\u062F\u064A\u062F");
		
		label_2 = new JLabel("\u062A\u0623\u0643\u064A\u062F \u0627\u0644\u0631\u0642\u0645 \u0627\u0644\u0633\u0631\u064A \u0627\u0644\u062C\u062F\u064A\u062F");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblConfirmPassword)
								.addComponent(lblNewPassword)
								.addComponent(label_1)
								.addComponent(lblOldPassword)
								.addComponent(label))
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(pfOld, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(0)
									.addComponent(btnChange, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
								.addComponent(pfNew, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
								.addComponent(pfConfirm, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
							.addGap(26))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_2)
							.addContainerGap(299, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(lblOldPassword))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(pfOld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))))
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(pfNew, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblNewPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_1)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pfConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnChange, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblConfirmPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_2)))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
