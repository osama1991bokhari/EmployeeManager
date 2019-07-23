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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
	private static JTextField tfFname;
	private static JTextField tfLname;
	private static JTextField tfEmail;
	private static JTextField tfContactLocal;
	private static JTextField tfContactHome;
	private static JTextField tfSalary;
	private static JTextField tfOtherAllowance;
	private JButton btnDelete;
	private static JComboBox cbGrade;
	private static JComboBox cbNationality;
    private static JComboBox cbProfession;
    private static JComboBox cbPosition;
    private static JComboBox cbSection;
    private static JComboBox cbWS;
    private static JComboBox cbProjName;
    private static JComboBox cbCity;
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
		setTitle("Delete Employee  -  \u0627\u0632\u0627\u0644\u0629 \u0645\u0648\u0638\u0641\u064A\u0646");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		setLocationRelativeTo(null);//centralize the frame
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 95, 77, 14);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(320, 95, 83, 14);
		
		JLabel lblNationality = new JLabel("Nationality:");
		lblNationality.setBounds(10, 133, 77, 14);
		
		JLabel lblContactNumberlocal = new JLabel("Contact Number (Local):");
		lblContactNumberlocal.setBounds(10, 169, 140, 14);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(320, 133, 55, 14);
		
		JLabel lblContactNumberhome = new JLabel("Contact Number (Home):");
		lblContactNumberhome.setBounds(320, 169, 149, 14);
		
		JLabel lblProfission = new JLabel("Profession:");
		lblProfission.setBounds(10, 217, 77, 14);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(313, 217, 68, 14);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(10, 264, 55, 14);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(320, 264, 55, 14);
		
		JLabel lblWorkingStatus = new JLabel("Working Status:");
		lblWorkingStatus.setBounds(10, 309, 107, 14);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		lblProjectName.setBounds(320, 312, 83, 14);
		
		JLabel lblProject = new JLabel("City:");
		lblProject.setBounds(10, 350, 55, 14);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(320, 350, 68, 14);
		
		JLabel lblAllowence = new JLabel("Other Allowance:");
		lblAllowence.setBounds(15, 388, 107, 14);
		
		tfFname = new JTextField();
		tfFname.setEditable(false);
		tfFname.setBounds(84, 91, 219, 20);
		tfFname.setColumns(10);
		
		tfLname = new JTextField();
		tfLname.setEditable(false);
		tfLname.setBounds(427, 91, 224, 20);
		tfLname.setColumns(10);
		
		cbNationality = new JComboBox(getAllCountries());
		cbNationality.setEnabled(false);
		cbNationality.setBounds(84, 130, 219, 20);
		cbNationality.insertItemAt(" ", 0);
		cbNationality.setSelectedIndex(0);
		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		tfEmail.setBounds(427, 130, 224, 20);
		tfEmail.setColumns(10);
		
		tfContactLocal = new JTextField();
		tfContactLocal.setEditable(false);
		tfContactLocal.setBounds(160, 169, 143, 20);
		tfContactLocal.setColumns(10);
		
		tfContactHome = new JTextField();
		tfContactHome.setEditable(false);
		tfContactHome.setBounds(467, 172, 184, 20);
		tfContactHome.setColumns(10);
		tfContactHome.setText("Optional");
		tfContactHome.setForeground(Color.GRAY);
		
		cbProfession = new JComboBox(getAllProfessions());
		cbProfession.setEnabled(false);
		cbProfession.setBounds(117, 217, 186, 20);
		cbProfession.insertItemAt(" ", 0);
		cbProfession.setSelectedIndex(0);
		
		cbPosition = new JComboBox(getAllPositions());
		cbPosition.setEnabled(false);
		cbPosition.setBounds(418, 217, 233, 20);
		cbPosition.insertItemAt(" ", 0);
		cbPosition.setSelectedIndex(0);
		cbPosition.setEditable(false);
		
		cbSection = new JComboBox(getAllSections());
		cbSection.setEnabled(false);
		cbSection.setBounds(117, 261, 186, 20);
		cbSection.insertItemAt(" ", 0);
		cbSection.setSelectedIndex(0);
		cbSection.setEditable(false);
		
		cbGrade = new JComboBox();
		cbGrade.setEnabled(false);
		cbGrade.setBounds(419, 261, 52, 20);
		cbGrade.setModel(new DefaultComboBoxModel());
		for(int i=10;i<=50;i++)
			cbGrade.addItem(i);
		cbGrade.insertItemAt(" ", 0);
		cbGrade.setSelectedIndex(0);
		cbGrade.setEditable(false);
		
		cbWS = new JComboBox();
		cbWS.setEnabled(false);
		cbWS.setModel(new DefaultComboBoxModel(new String[] {" ", "Business Trip", "Working", "Vacation"}));
		cbWS.setBounds(117, 309, 186, 20);
		cbWS.setEditable(false);
		
		cbProjName = new JComboBox(getAllProjects());
		cbProjName.setEnabled(false);
		cbProjName.setBounds(419, 309, 232, 20);
		cbProjName.insertItemAt(" ", 0);
		cbProjName.setSelectedIndex(0);
		cbProjName.setEditable(false);
		
		cbCity = new JComboBox(getAllCities());
		cbCity.setEnabled(false);
		cbCity.setBounds(117, 347, 186, 20);
		cbCity.insertItemAt(" ", 0);
		cbCity.setSelectedIndex(0);
		cbCity.setEditable(false);
		
		tfSalary = new JTextField();
		tfSalary.setBounds(419, 347, 232, 20);
		tfSalary.setColumns(10);
		tfSalary.setEditable(false);
		
		tfOtherAllowance = new JTextField();
		tfOtherAllowance.setBounds(117, 385, 186, 20);
		tfOtherAllowance.setColumns(10);
		tfOtherAllowance.setEditable(false);
		
		btnDelete = new JButton("Delete - \u062D\u0630\u0641");
		
		
		btnDelete.setBounds(419, 401, 100, 23);
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
		
		btnCancel = new JButton("Back  -  \u0627\u0644\u0631\u062C\u0648\u0639");
		
		btnCancel.setBounds(528, 401, 123, 23);
		contentPane.add(btnCancel);
		
		JLabel lblEmployeeIdTo = new JLabel("Employee ID to edit:");
		lblEmployeeIdTo.setBounds(10, 11, 140, 14);
		contentPane.add(lblEmployeeIdTo);
		
		tfID = new JTextField();
		tfID.setBounds(131, 21, 173, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		btnGet = new JButton("Find Employee - \u0627\u0644\u0628\u062D\u062B \u0639\u0646 \u0645\u0648\u0638\u0641");
		
		
		btnGet.setBounds(422, 20, 229, 23);
		contentPane.add(btnGet);
		
		lblEmployeeSelected = new JLabel("Selected Employee ID: ");
		lblEmployeeSelected.setForeground(Color.BLUE);
		lblEmployeeSelected.setBounds(10, 52, 293, 14);
		contentPane.add(lblEmployeeSelected);
		
		label = new JLabel("\u0627\u0644\u0625\u0633\u0645 \u0627\u0644\u0623\u0648\u0644");
		label.setBounds(10, 108, 64, 14);
		contentPane.add(label);
		
		label_1 = new JLabel("\u0627\u0644\u062C\u0646\u0633\u064A\u0629");
		label_1.setBounds(10, 144, 46, 14);
		contentPane.add(label_1);
		
		label_2 = new JLabel("\u0631\u0642\u0645 \u0627\u0644\u062A\u0648\u0627\u0635\u0644 \u0627\u0644\u0645\u062D\u0644\u064A");
		label_2.setBounds(10, 182, 102, 14);
		contentPane.add(label_2);
		
		label_3 = new JLabel("\u0627\u0644\u0645\u0647\u0646\u0629");
		label_3.setBounds(10, 232, 46, 14);
		contentPane.add(label_3);
		
		label_4 = new JLabel("\u0627\u0644\u0642\u0633\u0645");
		label_4.setBounds(10, 274, 46, 14);
		contentPane.add(label_4);
		
		label_5 = new JLabel("\u062D\u0627\u0644\u0629 \u0627\u0644\u0639\u0645\u0644");
		label_5.setBounds(10, 322, 77, 14);
		contentPane.add(label_5);
		
		label_6 = new JLabel("\u0627\u0644\u0645\u062F\u064A\u0646\u0629");
		label_6.setBounds(10, 360, 46, 14);
		contentPane.add(label_6);
		
		label_7 = new JLabel("\u0627\u0644\u0639\u0644\u0627\u0648\u0627\u062A \u0627\u0644\u0623\u062E\u0631\u0649");
		label_7.setBounds(10, 401, 82, 14);
		contentPane.add(label_7);
		
		label_8 = new JLabel("\u0627\u0644\u0627\u0633\u0645 \u0627\u0644\u0623\u062E\u064A\u0631");
		label_8.setBounds(317, 108, 58, 14);
		contentPane.add(label_8);
		
		label_9 = new JLabel("\u0627\u0644\u0625\u064A\u0645\u064A\u0644");
		label_9.setBounds(316, 144, 46, 14);
		contentPane.add(label_9);
		
		label_10 = new JLabel("\u0631\u0642\u0645 \u0627\u0644\u062A\u0648\u0627\u0635\u0644 \u0627\u0644\u062F\u0648\u0644\u064A");
		label_10.setBounds(319, 182, 113, 14);
		contentPane.add(label_10);
		
		label_11 = new JLabel("\u0627\u0644\u0645\u0646\u0635\u0628");
		label_11.setBounds(316, 232, 46, 14);
		contentPane.add(label_11);
		
		label_12 = new JLabel("\u0627\u0644\u0645\u0631\u062A\u0628\u0629");
		label_12.setBounds(320, 274, 46, 14);
		contentPane.add(label_12);
		
		label_13 = new JLabel("\u0627\u0633\u0645 \u0627\u0644\u0645\u0634\u0631\u0648\u0639");
		label_13.setBounds(320, 322, 68, 14);
		contentPane.add(label_13);
		
		label_14 = new JLabel("\u0627\u0644\u0631\u0627\u062A\u0628");
		label_14.setBounds(320, 360, 46, 14);
		contentPane.add(label_14);
		
		label_15 = new JLabel("\u0631\u0642\u0645 \u0627\u0644\u0645\u0648\u0638\u0641");
		label_15.setBounds(10, 23, 77, 14);
		contentPane.add(label_15);
		
		label_16 = new JLabel("\u0627\u0644\u0645\u0648\u0638\u0641 \u0627\u0644\u0645\u062E\u062A\u0627\u0631");
		label_16.setForeground(Color.BLUE);
		label_16.setBounds(211, 52, 77, 14);
		contentPane.add(label_16);
	
	}
}
