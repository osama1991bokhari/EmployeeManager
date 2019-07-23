package employee.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class SelectTask extends JFrame {

	private JPanel contentPane;
	private JButton btnAddEmployee;
	private JButton btnEditEmployee;
	private JButton btnDeleteEmployee;
	private JButton btnQueryEmployee;
	private JButton btnEditFields;
	private JButton btnChangePassword;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectTask frame = new SelectTask();
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
	public SelectTask() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectTask.class.getResource("/employee/resources/SAS_Logo.png")));
		setTitle("Task Selection  - \u0627\u062E\u062A\u064A\u0627\u0631 \u0627\u0644\u0645\u0647\u0645\u0629");
		initComponents();
		createEvents();
		

	}

	private void createEvents() {
		
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassword changingPasswordFrame = new ChangePassword();
				changingPasswordFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		btnEditFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditFields editingFieldsFrame = null;
				try {
					editingFieldsFrame = new EditFields();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				editingFieldsFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployees addingFrame = null;
				try {
					addingFrame = new AddEmployees();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				addingFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		btnEditEmployee.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
				EditEmployees editingFrame = null;
				try {
					editingFrame = new EditEmployees();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				editingFrame.setVisible(true);
				setVisible(false);
			}
		});
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteEmployees deletingFrame = null;
				try {
					deletingFrame = new DeleteEmployees();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				deletingFrame.setVisible(true);
				setVisible(false);
			}
		});
		btnQueryEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryCriteria quering;
				try {
					quering = new QueryCriteria();
					quering.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				setVisible(false);
			}
		});
	}

	private void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JLabel lblSelectTheRequired = new JLabel("Select the required task:");
		
		btnAddEmployee = new JButton("<html>Add Employees<br /> \u0625\u0636\u0627\u0641\u0629 \u0645\u0648\u0638\u0641\u064A\u0646</html>");
	
		
		btnEditEmployee = new JButton("<html>Edit employees<br /> \u062A\u062D\u0631\u064A\u0631 \u0645\u0648\u0638\u0641\u064A\u0646</html>");

		
		btnDeleteEmployee = new JButton("<html>Delete Emplyees<br /> \u0627\u0632\u0627\u0644\u0629 \u0645\u0648\u0638\u0641\u064A\u0646</html>");
		
		
		btnQueryEmployee = new JButton("<html>Query Employees<br /> \u0627\u0644\u0627\u0633\u062A\u0639\u0644\u0627\u0645 \u0639\u0646 \u0645\u0648\u0638\u0641</html>");
		
		
		btnChangePassword = new JButton("<html>Change Password<br /> \u062A\u063A\u064A\u064A\u0631 \u0627\u0644\u0631\u0642\u0645 \u0627\u0644\u0633\u0631\u064A</html>");

		
		btnEditFields = new JButton("<html>Edit Fields<br /> \u062A\u062D\u0631\u064A\u0631 \u0627\u0644\u062D\u0642\u0648\u0644</html>");
		
		lblNewLabel = new JLabel("\u0627\u0644\u0631\u062C\u0627\u0621 \u0627\u062E\u062A\u064A\u0627\u0631 \u0627\u0644\u0639\u0645\u0644\u064A\u0629 \u0627\u0644\u0645\u0637\u0644\u0648\u0628\u0629");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnChangePassword, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
								.addComponent(btnDeleteEmployee, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
								.addComponent(lblSelectTheRequired)
								.addComponent(btnAddEmployee, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEditFields, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(btnEditEmployee, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(btnQueryEmployee, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblSelectTheRequired)
					.addGap(4)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnEditFields, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChangePassword, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditEmployee, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddEmployee, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDeleteEmployee, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnQueryEmployee, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(66))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
