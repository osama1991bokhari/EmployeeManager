package employee.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Category;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.font.NumericShaper.Range;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

import java.awt.Toolkit;


public class EditEmployees extends JFrame {

	private JPanel contentPane;
	private static JTextField tfFname;
	private static JTextField tfLname;
	private static JTextField tfEmail;
	private static JTextField tfContactLocal;
	private static JTextField tfContactHome;
	private static JTextField tfSalary;
	private static JTextField tfOtherAllowance;
	private JButton btnEdit;
	private static JComboBox cbGrade;
	private static JComboBox cbNationality;
    private static JComboBox cbProfession;
    private static JComboBox cbPosition;
    private static JComboBox cbSection;
    private static JComboBox cbWS;
    private static JComboBox cbProjName;
    private static JComboBox cbCity;
    private JButton btnCancel;
    public static final String DATA_TARGET_PATH =  "/employee/resources/targetDatas.txt";
    public static final Path DATA_TARGET_PATH2 =  FileSystems.getDefault().getPath(DATA_TARGET_PATH); 
    public static final String POSITION =  "/employee/resources/Positions.txt";
	public static final String PROF =  "/employee/resources/Professions.txt";
	public static final String PROJ =  "/employee/resources/Projects.txt";
	public static final String SECTION =  "/employee/resources/Sections.txt";
	public static final String CITY =  "/employee/resources/City.txt";
    private static String dbURL = "jdbc:derby://localhost:1527/Employee;create=true;user=root;password=root";
    private static String tableName = "EMPLOYEE";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static JTextField tfID;
    private JButton btnGet;
    private static JLabel lblEmployeeSelected;
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JLabel label_5;
    private JLabel label_6;
    private JLabel label_7;
    private JLabel label_8;
    private JLabel label_9;
    private JLabel label_10;
    private JLabel label_11;
    private JLabel label_12;
    private JLabel label_13;
    private JLabel label_14;
    private JLabel label_15;
    private JLabel label_16;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditEmployees frame = new EditEmployees();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	  private static void editEmployee(String fname,String lname,String nationality, String email,String contactLocal,String contactHome,String profession, String position, String section, int grade, String ws, String project, String city, double salary, double otherSalary)
	    {
	        try
	        {
	            stmt = conn.createStatement();
	            if(contactHome.equals("Optional")||contactHome.equals("")||contactHome==null)
	            stmt.execute("UPDATE "+tableName+" SET fname =  '" + fname + "', lname = '" + lname + "', nationality = '" + nationality +"', email = '"+email + "',contactLocal = '" + contactLocal + "',contactHome = NULL,profession = '"+profession+"',position = '"+position+"', section = '"+section+"',grade = "+grade+",workingStatus = '"+ws+"',project = '"+project+"',city = '"+city+"',salary = "+salary+", otherSalary = "+otherSalary+ " WHERE id = "+lblEmployeeSelected.getText().substring(22));
	            else
		            stmt.execute("UPDATE "+tableName+" SET fname =  '" + fname + "', lname = '" + lname + "', nationality = '" + nationality +"', email = '"+email + "',contactLocal = '" + contactLocal + "',contactHome = "+contactHome+",profession = '"+profession+"',position = '"+position+"', section = '"+section+"',grade = "+grade+",workingStatus = '"+ws+"',project = '"+project+"',city = '"+city+"',salary = "+salary+", otherSalary = "+otherSalary+ " WHERE id = "+lblEmployeeSelected.getText().substring(22));
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
/////////////////////////////////////////////Method for editing the selected employee
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void editE(String data) throws IOException,     NumberFormatException 
		{
		List<String> fileContent = new ArrayList<>(Files.readAllLines(DATA_TARGET_PATH2, StandardCharsets.UTF_8));

		for (int i = 0; i < fileContent.size(); i++) {
		    if (fileContent.get(i).substring(0, 9).equals(data.substring(0,9))) {
		        fileContent.set(i, data);
		        break;
		    }
		}

		Files.write(DATA_TARGET_PATH2, fileContent, StandardCharsets.UTF_8);
        }
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	        	tfFname.setText("");
		        tfLname.setText("");
		        cbNationality.setSelectedItem("");
		        tfEmail.setText("");
		        tfContactLocal.setText("");
		        tfContactHome.setText("");
		        cbProfession.setSelectedItem("");
		        cbPosition.setSelectedItem("");
		        cbSection.setSelectedItem("");
		        cbGrade.setSelectedIndex(0);
		        cbWS.setSelectedIndex(0);
		        cbProjName.setSelectedIndex(0);
		        cbCity.setSelectedIndex(0);
		        tfSalary.setText("");
		        tfOtherAllowance.setText("");
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
	 }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////// Method to acquire all Professions from file.
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public String[] getAllProfessions() throws IOException {
File file = new File("Professions.txt");
InputStream inputStream = null;
try{
inputStream =  new FileInputStream("Professions.txt");
}	 catch (FileNotFoundException e) {
e.printStackTrace();
}
BufferedReader brProf;
if(file.exists())
brProf = new BufferedReader(new InputStreamReader(inputStream));
else
brProf = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(PROF)));

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

File file = new File("Positions.txt");
InputStream inputStream = null;
try{
inputStream =  new FileInputStream("Positions.txt");
}catch (FileNotFoundException e) {
e.printStackTrace();
}
BufferedReader brPos;
if(file.exists())
brPos = new BufferedReader(new InputStreamReader(inputStream));
else
brPos = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(POSITION)));

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
File file = new File("Sections.txt");
InputStream inputStream = null;
try{
inputStream =  new FileInputStream("Sections.txt");
}
catch (FileNotFoundException e) {
e.printStackTrace();
}
BufferedReader brSec;
if(file.exists())
brSec = new BufferedReader(new InputStreamReader(inputStream));
else
brSec = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(SECTION)));

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
File file = new File("Projects.txt");
InputStream inputStream = null;
try{
inputStream =  new FileInputStream("Projects.txt");
}catch (FileNotFoundException e) {
e.printStackTrace();
}
BufferedReader brProj;
if(file.exists())
brProj = new BufferedReader(new InputStreamReader(inputStream));
else
brProj = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(PROJ)));

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
File file = new File("City.txt");
InputStream inputStream = null;
try{
inputStream =  new FileInputStream("City.txt");
}catch (FileNotFoundException e) {
e.printStackTrace();
}
BufferedReader brCity;
if(file.exists())
brCity = new BufferedReader(new InputStreamReader(inputStream));
else
brCity = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(CITY)));

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
	public EditEmployees() throws IOException {
		
		initComponent();
		createEvent();
		
	}
	
	private void initComponent() throws IOException 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditEmployees.class.getResource("/employee/resources/SAS_Logo.png")));
		setTitle("Edit Employees  -  \u062A\u062D\u0631\u064A\u0631 \u0645\u0648\u0638\u0641\u064A\u0646");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		setLocationRelativeTo(null);//centralize the frame
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 98, 77, 14);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(321, 98, 83, 14);
		
		JLabel lblNationality = new JLabel("Nationality:");
		lblNationality.setBounds(10, 135, 77, 14);
		
		JLabel lblContactNumberlocal = new JLabel("Contact Number (Local):");
		lblContactNumberlocal.setBounds(10, 174, 140, 14);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(321, 136, 55, 14);
		
		JLabel lblContactNumberhome = new JLabel("Contact Number (Home):");
		lblContactNumberhome.setBounds(321, 174, 149, 14);
		
		JLabel lblProfission = new JLabel("Profession:");
		lblProfission.setBounds(10, 214, 77, 14);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(321, 214, 68, 14);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(10, 262, 55, 14);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(321, 262, 55, 14);
		
		JLabel lblWorkingStatus = new JLabel("Working Status:");
		lblWorkingStatus.setBounds(10, 300, 107, 14);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		lblProjectName.setBounds(321, 303, 83, 14);
		
		JLabel lblProject = new JLabel("City:");
		lblProject.setBounds(10, 342, 55, 14);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(321, 342, 68, 14);
		
		JLabel lblAllowence = new JLabel("Other Allowance:");
		lblAllowence.setBounds(10, 390, 107, 14);
		
		tfFname = new JTextField();
		tfFname.setBounds(84, 82, 219, 20);
		tfFname.setColumns(10);
		
		tfLname = new JTextField();
		tfLname.setBounds(418, 95, 230, 20);
		tfLname.setColumns(10);
		
		cbNationality = new JComboBox(getAllCountries());
		cbNationality.setBounds(84, 132, 219, 20);
		cbNationality.insertItemAt(" ", 0);
		cbNationality.setSelectedIndex(0);
		tfEmail = new JTextField();
		tfEmail.setBounds(418, 132, 230, 20);
		tfEmail.setColumns(10);
		
		tfContactLocal = new JTextField();
		tfContactLocal.setBounds(160, 186, 143, 20);
		tfContactLocal.setColumns(10);
		
		tfContactHome = new JTextField();
		tfContactHome.setBounds(467, 171, 181, 20);
		tfContactHome.setColumns(10);
		tfContactHome.setText("Optional");
		tfContactHome.setForeground(Color.GRAY);
		
		cbProfession = new JComboBox(getAllProfessions());
		cbProfession.setBounds(117, 224, 186, 20);
		cbProfession.insertItemAt(" ", 0);
		cbProfession.setSelectedIndex(0);
		
		cbPosition = new JComboBox(getAllPositions());
		cbPosition.setBounds(418, 211, 230, 20);
		cbPosition.insertItemAt(" ", 0);
		cbPosition.setSelectedIndex(0);
		
		cbSection = new JComboBox(getAllSections());
		cbSection.setBounds(117, 262, 186, 20);
		cbSection.insertItemAt(" ", 0);
		cbSection.setSelectedIndex(0);
		
		cbGrade = new JComboBox();
		cbGrade.setBounds(418, 259, 52, 20);
		cbGrade.setModel(new DefaultComboBoxModel());
		for(int i=10;i<=50;i++)
			cbGrade.addItem(i);
		cbGrade.insertItemAt(" ", 0);
		cbGrade.setSelectedIndex(0);
		
		cbWS = new JComboBox();
		cbWS.setModel(new DefaultComboBoxModel(new String[] {" ", "Business Trip", "Working", "Vacation"}));
		cbWS.setBounds(117, 297, 186, 20);
		
		cbProjName = new JComboBox(getAllProjects());
		cbProjName.setBounds(418, 300, 230, 20);
		cbProjName.insertItemAt(" ", 0);
		cbProjName.setSelectedIndex(0);
		
		cbCity = new JComboBox(getAllCities());
		cbCity.setBounds(117, 339, 186, 20);
		cbCity.insertItemAt(" ", 0);
		cbCity.setSelectedIndex(0);
		
		tfSalary = new JTextField();
		tfSalary.setBounds(418, 339, 230, 20);
		tfSalary.setColumns(10);
		
		tfOtherAllowance = new JTextField();
		tfOtherAllowance.setBounds(117, 387, 186, 20);
		tfOtherAllowance.setColumns(10);
		
		btnEdit = new JButton("Edit  -  \u062A\u062D\u0631\u064A\u0631");
		
		btnEdit.setBounds(418, 407, 107, 23);
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
		contentPane.add(btnEdit);
		
		btnCancel = new JButton("Back  -  \u0627\u0644\u0631\u062C\u0648\u0639");
		
		btnCancel.setBounds(535, 407, 113, 23);
		contentPane.add(btnCancel);
		
		JLabel lblEmployeeIdTo = new JLabel("Employee ID to edit:");
		lblEmployeeIdTo.setBounds(10, 11, 140, 14);
		contentPane.add(lblEmployeeIdTo);
		
		tfID = new JTextField();
		tfID.setBounds(129, 22, 173, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		btnGet = new JButton("Find Employee - \u0627\u0644\u0628\u062D\u062B \u0639\u0646 \u0645\u0648\u0638\u0641");
		
		
		btnGet.setBounds(418, 21, 230, 23);
		contentPane.add(btnGet);
		
		lblEmployeeSelected = new JLabel("Selected Employee ID: ");
		lblEmployeeSelected.setForeground(Color.BLUE);
		lblEmployeeSelected.setBounds(10, 53, 293, 14);
		contentPane.add(lblEmployeeSelected);
		
		label = new JLabel("\u0631\u0642\u0645 \u0627\u0644\u0645\u0648\u0638\u0641");
		label.setBounds(10, 25, 77, 14);
		contentPane.add(label);
		
		label_1 = new JLabel("\u0627\u0644\u0627\u0633\u0645 \u0627\u0644\u0623\u062E\u064A\u0631");
		label_1.setBounds(318, 111, 58, 14);
		contentPane.add(label_1);
		
		label_2 = new JLabel("\u0627\u0644\u0625\u064A\u0645\u064A\u0644");
		label_2.setBounds(318, 149, 46, 14);
		contentPane.add(label_2);
		
		label_3 = new JLabel("\u0631\u0642\u0645 \u0627\u0644\u062A\u0648\u0627\u0635\u0644 \u0627\u0644\u062F\u0648\u0644\u064A");
		label_3.setBounds(321, 189, 107, 14);
		contentPane.add(label_3);
		
		label_4 = new JLabel("\u0627\u0644\u0645\u0646\u0635\u0628");
		label_4.setBounds(318, 227, 46, 14);
		contentPane.add(label_4);
		
		label_5 = new JLabel("\u0627\u0644\u0645\u0631\u062A\u0628\u0629");
		label_5.setBounds(318, 275, 46, 14);
		contentPane.add(label_5);
		
		label_6 = new JLabel("\u0627\u0633\u0645 \u0627\u0644\u0645\u0634\u0631\u0648\u0639");
		label_6.setBounds(321, 317, 68, 14);
		contentPane.add(label_6);
		
		label_7 = new JLabel("\u0627\u0644\u0631\u0627\u062A\u0628");
		label_7.setBounds(321, 355, 46, 14);
		contentPane.add(label_7);
		
		label_8 = new JLabel("\u0627\u0644\u0625\u0633\u0645 \u0627\u0644\u0623\u0648\u0644");
		label_8.setBounds(10, 110, 64, 14);
		contentPane.add(label_8);
		
		label_9 = new JLabel("\u0627\u0644\u062C\u0646\u0633\u064A\u0629");
		label_9.setBounds(10, 149, 46, 14);
		contentPane.add(label_9);
		
		label_10 = new JLabel("\u0631\u0642\u0645 \u0627\u0644\u062A\u0648\u0627\u0635\u0644 \u0627\u0644\u0645\u062D\u0644\u064A");
		label_10.setBounds(10, 189, 102, 14);
		contentPane.add(label_10);
		
		label_11 = new JLabel("\u0627\u0644\u0645\u0647\u0646\u0629");
		label_11.setBounds(10, 227, 46, 14);
		contentPane.add(label_11);
		
		label_12 = new JLabel("\u0627\u0644\u0642\u0633\u0645");
		label_12.setBounds(10, 275, 46, 14);
		contentPane.add(label_12);
		
		label_13 = new JLabel("\u062D\u0627\u0644\u0629 \u0627\u0644\u0639\u0645\u0644");
		label_13.setBounds(10, 317, 77, 14);
		contentPane.add(label_13);
		
		label_14 = new JLabel("\u0627\u0644\u0645\u062F\u064A\u0646\u0629");
		label_14.setBounds(10, 355, 46, 14);
		contentPane.add(label_14);
		
		label_15 = new JLabel("\u0627\u0644\u0639\u0644\u0627\u0648\u0627\u062A \u0627\u0644\u0623\u062E\u0631\u0649");
		label_15.setBounds(10, 407, 82, 14);
		contentPane.add(label_15);
		
		label_16 = new JLabel("\u0627\u0644\u0645\u0648\u0638\u0641 \u0627\u0644\u0645\u062E\u062A\u0627\u0631");
		label_16.setForeground(Color.BLUE);
		label_16.setBounds(203, 53, 77, 14);
		contentPane.add(label_16);
	}
	
	private void createEvent() 
	{
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				String searchResult="notFound";
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
				JOptionPane.showMessageDialog(null,	"Employee not found");
				*/
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
		        if(fields == null){
		        	JOptionPane.showMessageDialog(null,	"Employee not found");
		        	System.out.println("nononoonooonono");
		        	
		        
		        }
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
		
		tfContactHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tfContactHome.getText().equals("Optional")){
				tfContactHome.setText("");
				tfContactHome.setForeground(Color.black);}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fname = tfFname.getText();boolean fnamep = false;
				String lname = tfLname.getText();boolean lnamep = false;
				String email = tfEmail.getText();boolean emailp;
				String conLocal = tfContactLocal.getText();boolean conLocalp = false;
				String conHome = tfContactHome.getText();boolean conHomep = false;
				String nationality = (String) cbNationality.getSelectedItem();boolean nationalityp = false;
				String grade =  cbGrade.getSelectedItem()+"";boolean gradep=false;
				String ws = cbWS.getSelectedItem()+"";boolean wsp=false;
				String profession = cbProfession.getSelectedItem()+"";boolean profp=false;
				String position = cbPosition.getSelectedItem()+"";boolean posp=false;
				String section = cbSection.getSelectedItem()+"";boolean secp=false;
				String project = cbProjName.getSelectedItem()+"";boolean projp=false;
				String city = cbCity.getSelectedItem()+"";boolean cityp=false;
				double salary = 0;
				double otherSalary = 0;boolean salaryp=false;
				
				if(lblEmployeeSelected.getText().substring(22).length()==0)
					JOptionPane.showMessageDialog(null, "Please make sure to select an Employee first!!!");
				else if(fname.equals("")||lname.equals("")||email.equals("")||conLocal.equals("")||nationality.equals(" ")){
					JOptionPane.showMessageDialog(null, "Please fill in all the required fields!!!");fnamep=lnamep=emailp=conLocalp=conHomep=nationalityp=false;}
				else{
					try{	
						 salary = Double.parseDouble(tfSalary.getText());
						 otherSalary = Double.parseDouble(tfOtherAllowance.getText());
						 salaryp=true;
						   }
						catch(Exception e){
							JOptionPane.showMessageDialog(null,"Please enter a valid salary and allowance. ");salaryp=false;
						}
				for(int i =0;i<fname.length();i++){
				if ((fname.charAt(i)>=65&&fname.charAt(i)<=90)||(fname.charAt(i)>=97&&fname.charAt(i)<=122)||fname.charAt(i)==' '){
					tfFname.setForeground(Color.BLACK);fnamep=true;}
				else{
				JOptionPane.showMessageDialog(null, "Please enter a valid first name.");tfFname.setForeground(Color.RED);fnamep = false;break;
					}}
				for(int i =0;i<lname.length();i++){
				if ((lname.charAt(i)>=65&&lname.charAt(i)<=90)||(lname.charAt(i)>=97&&lname.charAt(i)<=122)){
					tfLname.setForeground(Color.BLACK);lnamep=true;}
				else{
				JOptionPane.showMessageDialog(null, "Please enter a valid last name.");tfLname.setForeground(Color.RED);lnamep=false;break;
					}}
				for(int i =0;i<conLocal.length();i++){
					if ((conLocal.charAt(i)>=48&&conLocal.charAt(i)<=57)&&conLocal.length()==10&&conLocal.charAt(0)=='0'&&conLocal.charAt(1)=='5')
						{tfContactLocal.setForeground(Color.BLACK);conLocalp=true;}
				else{
				JOptionPane.showMessageDialog(null, "Please enter a valid contact number");tfContactLocal.setForeground(Color.RED);conLocalp=false;break;
					}}
				
				for(int i =0;i<conHome.length();i++){
					if ((conHome.charAt(i)>=48&&conHome.charAt(i)<=57)||conHome.equals("")){
						tfContactHome.setForeground(Color.BLACK);conHomep=true;}
					else if(conHome.equals("Optional")){
						tfContactHome.setForeground(Color.GRAY);conHomep=true;}
					else{
					JOptionPane.showMessageDialog(null, "Please enter a valid contact number");tfContactHome.setForeground(Color.RED);conHomep=false;break;
					}}
				if(conHome.equals(""))conHomep=true;
				
				if(tfEmail.getText().indexOf('@')<=0||tfEmail.getText().indexOf('.')<(tfEmail.getText().indexOf('@')+2)){
					JOptionPane.showMessageDialog(null, "Not a valid mail");tfEmail.setForeground(Color.RED);emailp=false;}
				else
					{tfEmail.setForeground(Color.BLACK);emailp = true;}
				if(!nationality.equals(""))nationalityp=true;
				if(!grade.equals("")||!grade.equals(" "))gradep=true;
				if(!position.equals("")||!position.equals(" "))posp=true;
				if(!profession.equals("")||!profession.equals(" "))profp=true;
				if(!section.equals("")||!section.equals(" "))secp=true;
				if(!project.equals("")||!project.equals(" "))projp=true;
				if(!city.equals("")||!city.equals(" "))cityp=true;
				if(!ws.equals("")||!ws.equals(" "))wsp=true;
				
				if(fnamep&&lnamep&&emailp&&conLocalp&&conHomep&&nationalityp&&gradep&&profp&&posp&&secp&&projp&&cityp&&wsp&&salaryp){
				System.out.println(tfFname.getText()+" "+tfLname.getText()+" "+tfEmail.getText()+" "+cbGrade.getSelectedItem()+" "+cbNationality.getSelectedItem());
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////access file
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/*try {
				
					editE(tfID.getText()+"  "+tfFname.getText()+"|"+tfLname.getText()+"|"+cbNationality.getSelectedItem()+"|"+tfEmail.getText()+"|"+cbGrade.getSelectedItem());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				JOptionPane.showMessageDialog(null, "Employee: "+fname+" "+lname+" has been successfully edited.");
				int gradeInt = (int) cbGrade.getSelectedItem();
				createConnection();
		        editEmployee(fname, lname,nationality,email,conLocal,conHome,profession,position,section,gradeInt,ws,project,city,salary,otherSalary);
		        selectEmployees();
		        shutdown();
																			}
				}/*End of else for not empty fields*/									   }/*End of Action listener*/
		});
		
	}
}
