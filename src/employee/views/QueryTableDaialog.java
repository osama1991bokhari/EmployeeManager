package employee.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import employee.common.Employees;

public class QueryTableDaialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			QueryTableDaialog dialog = new QueryTableDaialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public QueryTableDaialog() {
		initComponent();
		 createEvent();
	}

	private void createEvent() {
		
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Query Result");
		setBounds(100, 100, 1456, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "First Name", "Last Name", "Nationality", "E-mail", "Contact Local", "Contact Home", "Profession", "Position", "Section", "Grade", "Working Status", "Project", "City", "Salary", "Other Allowance"},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);		
	}
	public QueryTableDaialog(Employees[]queryResult) {
		setTitle("Query Result");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1456, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "First Name", "Last Name", "Nationality", "E-mail", "Contact Local", "Contact Home", "Profession", "Position", "Section", "Grade", "Working Status", "Project", "City", "Salary", "Other Allowance"},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column","New column" , "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		for(int i = 0;i<queryResult.length;i++)
		{if(queryResult[i]==null)
			break;
				table.setValueAt(queryResult[i].getID(), i+1, 0);
				table.setValueAt(queryResult[i].getFname(), i+1, 1);
				table.setValueAt(queryResult[i].getLname(), i+1, 2);
				table.setValueAt(queryResult[i].getNationality(), i+1, 3);
				table.setValueAt(queryResult[i].getEmail(), i+1, 4);
				table.setValueAt(queryResult[i].getConLocal(), i+1, 5);
				table.setValueAt(queryResult[i].getConHome(), i+1, 6);
				table.setValueAt(queryResult[i].getProf(), i+1, 7);
				table.setValueAt(queryResult[i].getPos(), i+1, 8);
				table.setValueAt(queryResult[i].getSec(), i+1, 9);
				table.setValueAt(queryResult[i].getGrade(), i+1, 10);
				table.setValueAt(queryResult[i].getWS(), i+1, 11);
				table.setValueAt(queryResult[i].getProj(), i+1, 12);
				table.setValueAt(queryResult[i].getCity(), i+1, 13);
				table.setValueAt(queryResult[i].getSalary(), i+1, 14);
				table.setValueAt(queryResult[i].getOtherSalary(), i+1, 15);
				
		}
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
