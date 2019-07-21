package employee.views;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class DeleteEmployees extends JFrame {
	private JPanel contentPane;
	private JTextField tfFname;
	private JTextField tfLname;
	private JTextField tfEmail;
	private JTextField tfContactLocal;
	private JTextField tfContactHome;
	private JTextField tfSalary;
	private JTextField tfOtherAllowance;
	private JButton btnDelete;
	private JComboBox cbGrade;
	private JComboBox cbNationality;
    private JComboBox cbProfession;
    private JComboBox cbPosition;
    private JComboBox cbSection;
    private JComboBox cbWS;
    private JComboBox cbProjName;
    private JComboBox cbCity;
    private JButton btnCancel;
    public static final String DATA_TARGET_PATH =  "src/targetDatas.txt";
    public static final Path DATA_TARGET_PATH2 =  FileSystems.getDefault().getPath(DATA_TARGET_PATH);
	private JTextField textField;
	private static JTextField tfID;
    private JButton btnGet;
    private static String dbURL = "jdbc:derby://localhost:1527/Employee;create=true;user=root;password=root";
    private static String tableName = "EMPLOYEE";
    public static final String POSITION =  "/employee/resources/Positions.txt";
	public static final String PROF =  "/employee/resources/Professions.txt";
	public static final String PROJ =  "/employee/resources/Projects.txt";
	public static final String SECTION =  "/employee/resources";
	public static final String CITY =  "/employee/resources/City.txt";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static JLabel lblEmployeeSelected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEmployees frame = new DeleteEmployees();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////Method for deleting the employee using his ID
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void deleteE(String data) throws IOException
	{
		List<String> fileContent = new ArrayList<>(Files.readAllLines(DATA_TARGET_PATH2, StandardCharsets.UTF_8));

		for (int i = 0; i < fileContent.size(); i++) {
		    if (fileContent.get(i).substring(0, 9).equals(data.substring(0,9))) {
		        fileContent.remove(i);
		        break;
		    }
		}

		Files.write(DATA_TARGET_PATH2, fileContent, StandardCharsets.UTF_8);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Establishing the connection with the Database.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  private static void createConnection()
	    {
	        try
	        {
	            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
	            //Get a connection
	            conn = DriverManager.getConnection(dbURL); 
	        }
	        catch (Exception except)
	        {
	            JOptionPane.showMessageDialog(null, "Error connecting to the Database!", "Connection Error",JOptionPane.WARNING_MESSAGE);
	            except.printStackTrace();
	        }
	    }
	  
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Adding employees to the Database.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  private static void deleteEmployee()
	    {
	        try
	        {
	            stmt = conn.createStatement();
	            
		            stmt.execute("DELETE FROM "+tableName+" WHERE id = "+lblEmployeeSelected.getText().substring(22));
	            stmt.close();
	        }
	        catch (SQLException sqlExcept)
	        {
	            sqlExcept.printStackTrace();
	        }
	    }
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Viewing the employees in the Database.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    private static void selectEmployees()
	    {
	        try
	        {
	            stmt = conn.createStatement();
	            ResultSet results = stmt.executeQuery("select * from " + tableName);
	            ResultSetMetaData rsmd = results.getMetaData();
	            int numberCols = rsmd.getColumnCount();
	            for (int i=1; i<=numberCols; i++)
	            {
	                //print Column Names
	                System.out.print(rsmd.getColumnLabel(i)+"\t\t\t");  
	            }

	            System.out.println("\n-------------------------------------------------");

	            while(results.next())
	            {
	                int id = results.getInt(1);
	                String fName = results.getString(2);
	                String lName = results.getString(3);
	                String grade = results.getString(4);
	                System.out.println(id + "\t\t" + fName + "\t\t" + lName + "\t\t" + grade);
	            }
	            results.close();
	            stmt.close();
	        }
	        catch (SQLException sqlExcept)
	        {
	            sqlExcept.printStackTrace();
	        }
	    }
	    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Closing the connection between the Database and the Application.
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
	    private static void shutdown()
	    {
	        try
	        {
	            if (stmt != null)
	            {
	                stmt.close();
	            }
	            if (conn != null)
	            {
	                DriverManager.getConnection(dbURL + ";shutdown=true");
	                conn.close();
	            }           
	        }
	        catch (SQLException sqlExcept)
	        {
	            
	        }

	    }
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////Method for finding the employee using his ID
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String findEmployee(String id) throws IOException,     NumberFormatException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(DATA_TARGET_PATH))) {
			String line;
			while ((line = br.readLine()) != null) {
				if(id.equals(line.substring(0, 9))){
					System.out.println("Employee found");
					return line;
				}
			}
		}

		return null;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Method to acquire all countries and sort them.
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String[] getAllCountries() {
	    String[] countries = new String[Locale.getISOCountries().length];
	    String[] countryCodes = Locale.getISOCountries();
	    for (int i = 0; i < countryCodes.length; i++) {
	        Locale obj = new Locale("", countryCodes[i]);
	        countries[i] = obj.getDisplayCountry();
	    }
	    int n = countries.length;
	    String temp;
	    for(int i =0; i< n;i++){
	    	for (int j = 1;j< (n-i);j++){
	    		if(countries[j-1].compareTo(countries[j])>0){
	    			temp = countries[j-1];
	    			countries[j-1]=countries[j];
	    			countries[j] =temp;
	    		}
	    	}
	    }
	    return countries;
	 }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////Method for finding the employee using his ID
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String [] getEmployee(String id) 
	{
		String[] result = new String [15];
		if(id=="")
			return null;
		else
		 try
	        {
	            stmt = conn.createStatement();
	            ResultSet results = stmt.executeQuery("SELECT * FROM " + tableName+" WHERE id = "+id);
	            
	            while(results.next())
	            {
	                result[0] = results.getString(2);
	                result[1] = results.getString(3);
	                result[2] = results.getString(4);
	                result[3] = results.getString(5);
	                result[4] = results.getString(6);
	                result[5] = results.getString(7);
	                result[6] = results.getString(8);
	                result[7] = results.getString(9);
	                result[8] = results.getString(10);
	                result[9] = results.getString(11);
	                result[10] = results.getString(12);
	                result[11] = results.getString(13);
	                result[12] = results.getString(14);
	                result[13] = results.getString(15);
	                result[14] = results.getString(16);
	                
	                System.out.print(id);
	                for (int i =0;i<result.length;i++)
	                System.out.print( "\t" + result[i]);
	            }
	            System.out.println();
	            results.close();
	            stmt.close();
				if(result[0]!=null)
	            lblEmployeeSelected.setText(lblEmployeeSelected.getText().substring(0, 22)+tfID.getText());
				else
				{
		        lblEmployeeSelected.setText(lblEmployeeSelected.getText().substring(0, 22));
	        	JOptionPane.showMessageDialog(null, "Employee not found!");
				}

	            return result;
	        }
	        catch (SQLException sqlExcept)
	        {	if(id.matches(".*\\d+.*"))
	        	JOptionPane.showMessageDialog(null, "Please enter a valid employee number!");
	        	else
	        	JOptionPane.showMessageDialog(null, "Employee not found!");
	        	return null;
	        }
		

	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Method to acquire all Professions from file.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String[] getAllProfessions() throws IOException {
		BufferedReader brProf = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(PROF)));
		String[] profession = new String[100];
		String line;int i=0;
		while((line = brProf.readLine())!=null)
		{
		profession [i] = line;i++;
		}
		String [] noNull = new String [i];
		for (int j =0;j<noNull.length;j++)
			noNull[j]=profession[j];
		brProf.close();
		return noNull;
	 }
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Method to acquire all Positions from file.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String[] getAllPositions() throws IOException {
		BufferedReader brPos = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(POSITION)));
		String[] position = new String[100];
		String line;int i=0;
		while((line = brPos.readLine())!=null)
		{
		position [i] = line;i++;
		}
		String [] noNull = new String [i];
		for (int j =0;j<noNull.length;j++)
			noNull[j]=position[j];
		brPos.close();
		return noNull;
	 }
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Method to acquire all Sections from file.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String[] getAllSections() throws IOException {
		BufferedReader brSec = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(SECTION)));
		String[] section = new String[100];
		String line;int i=0;
		while((line = brSec.readLine())!=null)
		{
		section [i] = line;i++;
		}
		String [] noNull = new String [i];
		for (int j =0;j<noNull.length;j++)
			noNull[j]=section[j];
		brSec.close();
		return noNull;
	 }
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Method to acquire all Projects from file.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String[] getAllProjects() throws IOException {
		BufferedReader brProj = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(PROJ)));
		String[] project = new String[100];
		String line;int i=0;
		while((line = brProj.readLine())!=null)
		{
		project [i] = line;i++;
		}
		String [] noNull = new String [i];
		for (int j =0;j<noNull.length;j++)
			noNull[j]=project[j];
		brProj.close();
		return noNull;
	 }
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Method to acquire all Cities from file.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String[] getAllCities() throws IOException {
		BufferedReader brCity = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(CITY)));
		String[] city = new String[100];
		String line;int i=0;
		while((line = brCity.readLine())!=null)
		{
		city [i] = line;i++;
		}
		String [] noNull = new String [i];
		for (int j =0;j<noNull.length;j++)
			noNull[j]=city[j];
		brCity.close();
		return noNull;
	 }
	

	
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public DeleteEmployees() throws IOException {
		
		initComponent();
		createEvent();
		
		
	}

	private void createEvent() {
		tfContactHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tfContactHome.getText().equals("Optional")){
				tfContactHome.setText("");
				tfContactHome.setForeground(Color.black);}
			}
		});
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*String searchResult="notFound";
				try {
					 searchResult = findEmployee(tfID.getText());
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(searchResult!=null)
				{
				String fname=searchResult.substring(11);
				tfFname.setText(fname.substring(0, fname.indexOf('|')));
				String lname=fname.substring(fname.indexOf('|')+1);
				tfLname.setText(lname.substring(0, lname.indexOf('|')));
				String nationality=lname.substring(lname.indexOf('|')+1);
				cbNationality.setSelectedItem((nationality.substring(0, nationality.indexOf('|'))));
				String email=nationality.substring(nationality.indexOf('|')+1);
				tfEmail.setText(email.substring(0, email.indexOf('|')));
				String grade=email.substring(email.indexOf('|')+1);
				cbGrade.setSelectedItem(grade);
				}
				else
				JOptionPane.showMessageDialog(null,	"Employee not found");*/
				boolean go=true;
				if(tfID.getText().length()==0){
					JOptionPane.showMessageDialog(null,	"Please enter a valid employee ID");go=false;}
				else
					for(int i=0;i<tfID.getText().length();i++)
					if(tfID.getText().charAt(i)<48||tfID.getText().charAt(i)>57){
						JOptionPane.showMessageDialog(null,	"Please enter a valid employee ID");go=false;break;}
				if(go){
				String [] fields= new String [15];
				createConnection();
		        fields = getEmployee(tfID.getText());
		        if(fields == null)
		        	JOptionPane.showMessageDialog(null,	"Employee not found");
		        else{
		        shutdown();
		        tfFname.setText(fields[0]);
		        tfLname.setText(fields[1]);
		        cbNationality.setSelectedItem(fields[2]);
		        tfEmail.setText(fields[3]);
		        tfContactLocal.setText(fields[4]);
		        tfContactHome.setText(fields[5]);
		        cbProfession.setSelectedItem(fields[6]);
		        cbPosition.setSelectedItem(fields[7]);
		        cbSection.setSelectedItem(fields[8]);
		        cbGrade.setSelectedItem(Integer.parseInt(fields[9]));
		        cbWS.setSelectedItem(fields[10]);
		        cbProjName.setSelectedItem(fields[11]);
		        cbCity.setSelectedItem(fields[12]);
		        tfSalary.setText(fields[13]);
		        tfOtherAllowance.setText(fields[14]);}
					}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectTask frame=new SelectTask();
				frame.setVisible(true);dispose();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {/*
				if(tfFname.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Please specify the employee you want to delete");
				else{
					 int choise = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete Employee: "+tfFname.getText()+" "+tfLname.getText(),"Deletion confirmation",  0);
					 if(choise == 0)
						try {
							deleteE(findEmployee(tfID.getText()));
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 
					}*/
				if(lblEmployeeSelected.getText().substring(22).length()==0)
					JOptionPane.showMessageDialog(null, "Please specify the employee you want to delete first");
				else{
					 int choise = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete Employee: "+tfFname.getText()+" "+tfLname.getText(),"Deletion confirmation",  0);
					 if(choise == 0)
						try {
							createConnection();
							deleteEmployee();
							selectEmployees();
							shutdown();
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
					}
			}});
	}
	
	private void initComponent() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditEmployees.class.getResource("/employee/resources/SAS_Logo.png")));
		setTitle("Delete Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		setLocationRelativeTo(null);//centralize the frame
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 75, 77, 14);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(321, 75, 83, 14);
		
		JLabel lblNationality = new JLabel("Nationality:");
		lblNationality.setBounds(10, 113, 77, 14);
		
		JLabel lblContactNumberlocal = new JLabel("Contact Number (Local):");
		lblContactNumberlocal.setBounds(10, 151, 140, 14);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(321, 113, 55, 14);
		
		JLabel lblContactNumberhome = new JLabel("Contact Number (Home):");
		lblContactNumberhome.setBounds(321, 151, 149, 14);
		
		JLabel lblProfission = new JLabel("Profession:");
		lblProfission.setBounds(10, 189, 77, 14);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(321, 189, 68, 14);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(10, 227, 55, 14);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(321, 227, 55, 14);
		
		JLabel lblWorkingStatus = new JLabel("Working Status:");
		lblWorkingStatus.setBounds(10, 265, 107, 14);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		lblProjectName.setBounds(321, 265, 83, 14);
		
		JLabel lblProject = new JLabel("City:");
		lblProject.setBounds(10, 303, 55, 14);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(321, 303, 68, 14);
		
		JLabel lblAllowence = new JLabel("Other Allowance:");
		lblAllowence.setBounds(10, 342, 107, 14);
		
		tfFname = new JTextField();
		tfFname.setEditable(false);
		tfFname.setBounds(84, 72, 219, 20);
		tfFname.setColumns(10);
		
		tfLname = new JTextField();
		tfLname.setEditable(false);
		tfLname.setBounds(427, 72, 161, 20);
		tfLname.setColumns(10);
		
		cbNationality = new JComboBox(getAllCountries());
		cbNationality.setEnabled(false);
		cbNationality.setBounds(84, 110, 219, 20);
		cbNationality.insertItemAt(" ", 0);
		cbNationality.setSelectedIndex(0);
		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		tfEmail.setBounds(427, 110, 161, 20);
		tfEmail.setColumns(10);
		
		tfContactLocal = new JTextField();
		tfContactLocal.setEditable(false);
		tfContactLocal.setBounds(160, 148, 143, 20);
		tfContactLocal.setColumns(10);
		
		tfContactHome = new JTextField();
		tfContactHome.setEditable(false);
		tfContactHome.setBounds(468, 148, 120, 20);
		tfContactHome.setColumns(10);
		tfContactHome.setText("Optional");
		tfContactHome.setForeground(Color.GRAY);
		
		cbProfession = new JComboBox(getAllProfessions());
		cbProfession.setEnabled(false);
		cbProfession.setBounds(117, 186, 186, 20);
		cbProfession.insertItemAt(" ", 0);
		cbProfession.setSelectedIndex(0);
		
		cbPosition = new JComboBox(getAllPositions());
		cbPosition.setEnabled(false);
		cbPosition.setBounds(418, 186, 170, 20);
		cbPosition.insertItemAt(" ", 0);
		cbPosition.setSelectedIndex(0);
		cbPosition.setEditable(false);
		
		cbSection = new JComboBox(getAllSections());
		cbSection.setEnabled(false);
		cbSection.setBounds(117, 224, 186, 20);
		cbSection.insertItemAt(" ", 0);
		cbSection.setSelectedIndex(0);
		cbSection.setEditable(false);
		
		cbGrade = new JComboBox();
		cbGrade.setEnabled(false);
		cbGrade.setBounds(418, 224, 52, 20);
		cbGrade.setModel(new DefaultComboBoxModel());
		for(int i=10;i<=50;i++)
			cbGrade.addItem(i);
		cbGrade.insertItemAt(" ", 0);
		cbGrade.setSelectedIndex(0);
		cbGrade.setEditable(false);
		
		cbWS = new JComboBox();
		cbWS.setEnabled(false);
		cbWS.setModel(new DefaultComboBoxModel(new String[] {" ", "Business Trip", "Working", "Vacation"}));
		cbWS.setBounds(117, 262, 186, 20);
		cbWS.setEditable(false);
		
		cbProjName = new JComboBox(getAllProjects());
		cbProjName.setEnabled(false);
		cbProjName.setBounds(418, 262, 169, 20);
		cbProjName.insertItemAt(" ", 0);
		cbProjName.setSelectedIndex(0);
		cbProjName.setEditable(false);
		
		cbCity = new JComboBox(getAllCities());
		cbCity.setEnabled(false);
		cbCity.setBounds(117, 300, 186, 20);
		cbCity.insertItemAt(" ", 0);
		cbCity.setSelectedIndex(0);
		cbCity.setEditable(false);
		
		tfSalary = new JTextField();
		tfSalary.setBounds(418, 300, 169, 20);
		tfSalary.setColumns(10);
		tfSalary.setEditable(false);
		
		tfOtherAllowance = new JTextField();
		tfOtherAllowance.setBounds(117, 339, 186, 20);
		tfOtherAllowance.setColumns(10);
		tfOtherAllowance.setEditable(false);
		
		btnDelete = new JButton("Delete");
		
		
		btnDelete.setBounds(418, 338, 76, 23);
		contentPane.setLayout(null);
		contentPane.add(lblFirstName);
		contentPane.add(tfFname);
		contentPane.add(lblNationality);
		contentPane.add(cbNationality);
		contentPane.add(lblContactNumberlocal);
		contentPane.add(tfContactLocal);
		contentPane.add(lblProfission);
		contentPane.add(lblSection);
		contentPane.add(lblProject);
		contentPane.add(lblAllowence);
		contentPane.add(tfOtherAllowance);
		contentPane.add(cbCity);
		contentPane.add(cbProfession);
		contentPane.add(cbSection);
		contentPane.add(lblWorkingStatus);
		contentPane.add(cbWS);
		contentPane.add(lblContactNumberhome);
		contentPane.add(tfContactHome);
		contentPane.add(lblLastName);
		contentPane.add(lblEmail);
		contentPane.add(tfEmail);
		contentPane.add(tfLname);
		contentPane.add(lblProjectName);
		contentPane.add(lblSalary);
		contentPane.add(lblPosition);
		contentPane.add(lblGrade);
		contentPane.add(cbGrade);
		contentPane.add(tfSalary);
		contentPane.add(cbProjName);
		contentPane.add(cbPosition);
		contentPane.add(btnDelete);
		
		btnCancel = new JButton("Cancel");
		
		btnCancel.setBounds(505, 338, 78, 23);
		contentPane.add(btnCancel);
		
		JLabel lblEmployeeIdTo = new JLabel("Employee ID to edit:");
		lblEmployeeIdTo.setBounds(10, 11, 140, 14);
		contentPane.add(lblEmployeeIdTo);
		
		tfID = new JTextField();
		tfID.setBounds(130, 8, 173, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		btnGet = new JButton("Find Employee");
		
		
		btnGet.setBounds(422, 7, 140, 23);
		contentPane.add(btnGet);
		
		lblEmployeeSelected = new JLabel("Selected Employee ID: ");
		lblEmployeeSelected.setForeground(Color.BLUE);
		lblEmployeeSelected.setBounds(10, 36, 293, 14);
		contentPane.add(lblEmployeeSelected);
	
	}
}
