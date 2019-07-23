package employee.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import employee.common.*;

public class QueryCriteria extends JFrame {

	private JPanel contentPane;
	private JComboBox cbNationality;
    private JComboBox cbProfession;
    private JComboBox cbPosition;
    private JComboBox cbSection;
    private JComboBox cbWS;
    private JComboBox cbProjName;
    private JComboBox cbCity;
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
    private JButton btnQuery;
    private JLabel lblGrade;
    private JComboBox cbGrade;
    private JButton btnQueryExport;
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JLabel label_5;
    private JLabel label_6;
    private JLabel label_7;
    private JLabel label_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryCriteria frame = new QueryCriteria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	//////////////////////// Viewing the employees in the Database.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    private static Employees [] queryEmployees(String []criteria)
	    {
	    	Employees result[] = new Employees[100];
	        try
	        {	String statement = "SELECT * from " + tableName;
	        		int i =0;
	            if(criteria[0]!=null)
	            {
	            	if(criteria[0].equals("all"))
	            	statement = "SELECT * from " + tableName;
	            	else
	            	{
	            	int id = Integer.parseInt(criteria[0]);
	            	statement="SELECT * FROM " + tableName + " WHERE id = " + id;
	            	}
	            }
	            if(!criteria[1].equals(" ")) 
	            {	
	            	statement = "SELECT a.* FROM ("+statement+") a WHERE nationality = '"+criteria[1]+"'";
	            	//System.out.println(statement);
	            	 
	            }
	           
	            if(!criteria[2].equals(" ")) 
	            	statement = "SELECT b.* FROM ("+statement+") b WHERE profession = '"+criteria[2]+"'";
	            if(!criteria[3].equals(" ")) 
	            	statement = "SELECT c.* FROM ("+statement+") c WHERE position = '"+criteria[3]+"'";
	            if(!criteria[4].equals(" ")) 
	            	statement = "SELECT d.* FROM ("+statement+") d WHERE section = '"+criteria[4]+"'";
	            if(!criteria[5].equals("0")) 
	            	statement = "SELECT e.* FROM ("+statement+") e WHERE grade = "+Integer.parseInt(criteria[5]);
	            if(!criteria[6].equals(" ")) 
	            	statement = "SELECT f.* FROM ("+statement+") f WHERE workingStatus = '"+criteria[6]+"'";
	            if(!criteria[7].equals(" ")) 
	            	statement = "SELECT g.* FROM ("+statement+") g WHERE project = '"+criteria[7]+"'";
	            if(!criteria[8].equals(" ")) 
	            	statement = "SELECT h.* FROM ("+statement+") h WHERE city = '"+criteria[8]+"'";
	            stmt = conn.createStatement();
	            ResultSet results = stmt.executeQuery(statement);
	            ResultSetMetaData rsmd = results.getMetaData();
	            int numberCols = rsmd.getColumnCount();
	            for (int j=1; j<=numberCols; j++)
	            {
	                //print Column Names
	                System.out.print(rsmd.getColumnLabel(j)+"\t\t\t");  
	            }
	            
	            System.out.println("\n-------------------------------------------------");
	            while(results.next())
	            {
	            	result[i]=new Employees(results.getInt(1),results.getString(2),results.getString(3),results.getString(4)
	            			,results.getString(5),results.getString(6),results.getString(7),results.getString(8)
	            			,results.getString(9),results.getString(10),results.getInt(11),results.getString(12)
	            			,results.getString(13),results.getString(14),results.getDouble(15),results.getDouble(16));
	          
	                System.out.println(result[i].getID() + "\t\t" + result[i].getFname() + "\t\t\t" + result[i].getLname() + "\t\t\t" + result[i].getNationality()+
	                		"\t\t\t" + result[i].getEmail() + "\t\t" + result[i].getConLocal() + "\t\t\t" + result[i].getConHome() + "\t\t\t" + result[i].getProf()+
	                		"\t\t\t" + result[i].getPos() + "\t\t" + result[i].getSec() + "\t\t\t" + result[i].getGrade() + "\t\t\t" + result[i].getWS()+
	                		"\t\t\t" + result[i].getProj() + "\t\t" + result[i].getCity() + "\t\t\t" + result[i].getSalary() + "\t\t\t" + result[i].getOtherSalary());
	                
	                      i++;
	            }
	            if(i==0)
	            JOptionPane.showMessageDialog(null, "No result was found with the given criteria!");
	            results.close();
	            stmt.close();
	            System.out.println("\n");
	            return result;
	        }
	        catch (SQLException sqlExcept)
	        {

	            sqlExcept.printStackTrace();
	            return null;
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
	
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public QueryCriteria() throws IOException {
		setResizable(false);
		initComponent();
		createEvent();
		
		
	}

	private void createEvent() {

		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ID = null,nationality = null,prof = null,pos = null,sec = null,ws = null,proj = null,city = null;
				int grade=0;
				//Checking the ID should take high presidency 
				if(tfID.getText().length()==0&&cbNationality.getSelectedItem().equals(" ")&&cbProfession.getSelectedItem().equals(" ")&&cbPosition.getSelectedItem().equals(" ")&&cbSection.getSelectedItem().equals(" ")&&cbGrade.getSelectedItem().equals(" ")&&cbWS.getSelectedItem().equals(" ")&&cbProjName.getSelectedItem().equals(" ")&&cbCity.getSelectedItem().equals(" "))
				JOptionPane.showMessageDialog(null, "Please enter at least one criteria to complete the query!");
				else
				{		if(!tfID.getText().equals("all"))
						for(int i=0;i<tfID.getText().length();i++)
							if(tfID.getText().charAt(i)<48||tfID.getText().charAt(i)>57){
								JOptionPane.showMessageDialog(null,	"Please enter a valid employee ID");ID=null;break;}
							else ID = tfID.getText();
					nationality = cbNationality.getSelectedItem()+"";
					prof = cbProfession.getSelectedItem()+"";
					pos = cbPosition.getSelectedItem()+"";
					sec = cbSection.getSelectedItem()+"";
					if(!cbGrade.getSelectedItem().equals(" "))
					grade = (int) cbGrade.getSelectedItem();
					ws = cbWS.getSelectedItem()+"";
					proj = cbProjName.getSelectedItem()+"";
					city = cbCity.getSelectedItem()+"";
				String []criteria = new String []{ID,nationality,prof,pos,sec,grade+"",ws,proj,city};
				createConnection();
				Employees queryResult[] =queryEmployees(criteria);
				shutdown();
				if(queryResult[0]!=null){
				QueryTableDaialog table = new QueryTableDaialog(queryResult);
				table.setVisible(true);}
				}
				
				
			}
		});
		
		
		btnQueryExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("ResultExcelSheet");
				XSSFRow row = sheet.createRow(0);
				row.createCell(0).setCellValue("#");
				row.createCell(1).setCellValue("ID");
				row.createCell(2).setCellValue("First Name");
				row.createCell(3).setCellValue("Last Name");
				row.createCell(4).setCellValue("Nationality");
				row.createCell(5).setCellValue("Email");
				row.createCell(6).setCellValue("Contact Local");
				row.createCell(7).setCellValue("Contact Home");
				row.createCell(8).setCellValue("Profession");
				row.createCell(9).setCellValue("Position");
				row.createCell(10).setCellValue("Section");
				row.createCell(11).setCellValue("Grade");
				row.createCell(12).setCellValue("Working Status");
				row.createCell(13).setCellValue("Project");
				row.createCell(14).setCellValue("City");
				row.createCell(15).setCellValue("Salary");
				row.createCell(16).setCellValue("Other Allowance");
				for(int i =0;i<17;i++)
				sheet.autoSizeColumn(i);
				
				XSSFRow row2 = sheet.createRow(1);
				
				
				String ID = null,nationality = null,prof = null,pos = null,sec = null,ws = null,proj = null,city = null;
				double salarySum = 0,otherSalarySum = 0;
				int grade=0;
				//Checking the ID should take high presidency 
				if(tfID.getText().length()==0&&cbNationality.getSelectedItem().equals(" ")&&cbProfession.getSelectedItem().equals(" ")&&cbPosition.getSelectedItem().equals(" ")&&cbSection.getSelectedItem().equals(" ")&&cbGrade.getSelectedItem().equals(" ")&&cbWS.getSelectedItem().equals(" ")&&cbProjName.getSelectedItem().equals(" ")&&cbCity.getSelectedItem().equals(" "))
				JOptionPane.showMessageDialog(null, "Please enter at least one criteria to complete the query!");
				else
				{		if(!tfID.getText().equals("all"))
						for(int i=0;i<tfID.getText().length();i++)
							if(tfID.getText().charAt(i)<48||tfID.getText().charAt(i)>57){
								JOptionPane.showMessageDialog(null,	"Please enter a valid employee ID");ID=null;break;}
							else ID = tfID.getText();
					nationality = cbNationality.getSelectedItem()+"";
					prof = cbProfession.getSelectedItem()+"";
					pos = cbPosition.getSelectedItem()+"";
					sec = cbSection.getSelectedItem()+"";
					if(!cbGrade.getSelectedItem().equals(" "))
					grade = (int) cbGrade.getSelectedItem();
					ws = cbWS.getSelectedItem()+"";
					proj = cbProjName.getSelectedItem()+"";
					city = cbCity.getSelectedItem()+"";
				String []criteria = new String []{ID,nationality,prof,pos,sec,grade+"",ws,proj,city};
				createConnection();
				Employees queryResult[] =queryEmployees(criteria);
				shutdown();
				if(queryResult[0]!=null){
				for(int i=0;i<100;i++){
					row2 = sheet.createRow(i+1);
					if(queryResult[i]==null){
						row2 =sheet.createRow(i+2);
						row2.createCell(14).setCellValue("Total:");
						row2.createCell(15).setCellValue(salarySum);
						row2.createCell(16).setCellValue(otherSalarySum);
						sheet.autoSizeColumn(14);sheet.autoSizeColumn(15);sheet.autoSizeColumn(16);
						break;}
					
				row2.createCell(0).setCellValue(i+1);
				row2.createCell(1).setCellValue(queryResult[i].getID());
				row2.createCell(2).setCellValue(queryResult[i].getFname());
				row2.createCell(3).setCellValue(queryResult[i].getLname());
				row2.createCell(4).setCellValue(queryResult[i].getNationality());
				row2.createCell(5).setCellValue(queryResult[i].getEmail());
				row2.createCell(6).setCellValue(queryResult[i].getConLocal());
				row2.createCell(7).setCellValue(queryResult[i].getConHome());
				row2.createCell(8).setCellValue(queryResult[i].getProf());
				row2.createCell(9).setCellValue(queryResult[i].getPos());
				row2.createCell(10).setCellValue(queryResult[i].getSec());
				row2.createCell(11).setCellValue(queryResult[i].getGrade());
				row2.createCell(12).setCellValue(queryResult[i].getWS());
				row2.createCell(13).setCellValue(queryResult[i].getProj());
				row2.createCell(14).setCellValue(queryResult[i].getCity());
				row2.createCell(15).setCellValue(queryResult[i].getSalary());
				row2.createCell(16).setCellValue(queryResult[i].getOtherSalary());
				salarySum =salarySum+queryResult[i].getSalary();
				otherSalarySum = otherSalarySum +queryResult[i].getOtherSalary();
				for(int j =0;j<17;j++)
					sheet.autoSizeColumn(j);}
				}
				}
				/*
				cell = row.createCell(1);
				DataFormat format = workbook.createDataFormat();
				CellStyle dataStyle = workbook.createCellStyle();
				dataStyle.setDataFormat(format.getFormat("yyyy.mm.dd"));
				cell.setCellStyle(dataStyle);
				cell.setCellValue(new Date());
				sheet.autoSizeColumn(1);
				row.createCell(2).setCellValue("3. Cell");
				*/
				try {
					workbook.write(new FileOutputStream("Results.xlsx"));
					workbook.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectTask frame=new SelectTask();
				frame.setVisible(true);dispose();
			}
		});
	}

	private void initComponent() throws IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditEmployees.class.getResource("/employee/resources/SAS_Logo.png")));
		setTitle("Query Employees  -  \u0627\u0644\u0627\u0633\u062A\u0639\u0644\u0627\u0645 \u0639\u0646 \u0645\u0648\u0638\u0641");
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JLabel lblNationality = new JLabel("Nationality:");
		lblNationality.setBounds(15, 54, 77, 14);
		
		JLabel lblProfission = new JLabel("Profession:");
		lblProfission.setBounds(15, 97, 77, 14);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(15, 138, 68, 14);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(15, 180, 55, 14);
		
		JLabel lblWorkingStatus = new JLabel("Working Status:");
		lblWorkingStatus.setBounds(15, 275, 107, 14);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		lblProjectName.setBounds(15, 316, 83, 14);
		
		JLabel lblProject = new JLabel("City:");
		lblProject.setBounds(15, 361, 55, 14);
		
		cbNationality = new JComboBox(getAllCountries());
		cbNationality.setBounds(147, 51, 319, 20);
		cbNationality.insertItemAt(" ", 0);
		cbNationality.setSelectedIndex(0);
		
		cbProfession = new JComboBox(getAllProfessions());
		cbProfession.setBounds(147, 94, 319, 20);
		cbProfession.insertItemAt(" ", 0);
		cbProfession.setSelectedIndex(0);
		
		cbPosition = new JComboBox(getAllPositions());
		cbPosition.setBounds(147, 135, 319, 20);
		cbPosition.insertItemAt(" ", 0);
		cbPosition.setSelectedIndex(0);
		
		cbSection = new JComboBox(getAllSections());
		cbSection.setBounds(147, 177, 319, 20);
		cbSection.insertItemAt(" ", 0);
		cbSection.setSelectedIndex(0);
		
		
		cbWS = new JComboBox();
		cbWS.setModel(new DefaultComboBoxModel(new String[] {" ", "Business Trip", "Working", "Vacation"}));
		cbWS.setBounds(148, 272, 318, 20);
		
		cbProjName = new JComboBox(getAllProjects());
		cbProjName.setBounds(148, 313, 318, 20);
		cbProjName.insertItemAt(" ", 0);
		cbProjName.setSelectedIndex(0);
		
		cbCity = new JComboBox(getAllCities());
		cbCity.setBounds(147, 358, 319, 20);
		cbCity.insertItemAt(" ", 0);
		cbCity.setSelectedIndex(0);
		contentPane.setLayout(null);
		contentPane.add(lblNationality);
		contentPane.add(cbNationality);
		contentPane.add(lblProfission);
		contentPane.add(lblSection);
		contentPane.add(lblProject);
		contentPane.add(cbCity);
		contentPane.add(cbProfession);
		contentPane.add(cbSection);
		contentPane.add(lblWorkingStatus);
		contentPane.add(cbWS);
		contentPane.add(lblProjectName);
		contentPane.add(lblPosition);
		contentPane.add(cbProjName);
		contentPane.add(cbPosition);
		
		btnCancel = new JButton("Back  -  \u0627\u0644\u0631\u062C\u0648\u0639");
		
		
		btnCancel.setBounds(362, 409, 114, 23);
		contentPane.add(btnCancel);
		
		JLabel lblEmployeeIdTo = new JLabel("Employee ID to query:");
		lblEmployeeIdTo.setBounds(15, 5, 122, 14);
		contentPane.add(lblEmployeeIdTo);
		
		tfID = new JTextField();
		tfID.setBounds(147, 16, 319, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		btnQuery = new JButton("Query -  \u0627\u0633\u062A\u0639\u0644\u0627\u0645");
		btnQuery.setBounds(15, 409, 125, 23);
		
		contentPane.add(btnQuery);
		
		lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(15, 233, 55, 14);
		
		cbGrade = new JComboBox();
		cbGrade.setBounds(147, 230, 319, 20);
		for(int i=10;i<=50;i++)
			cbGrade.addItem(i);
		cbGrade.insertItemAt(" ", 0);
		cbGrade.setSelectedIndex(0);
		
		btnQueryExport = new JButton("Query & Export - \u0627\u0633\u062A\u0639\u0644\u0627\u0645 \u0648\u0627\u0633\u062A\u062E\u0631\u0627\u062C");
		btnQueryExport.setBounds(147, 409, 205, 23);
		
		label = new JLabel("\u0631\u0642\u0645 \u0627\u0644\u0645\u0648\u0638\u0641");
		label.setBounds(15, 19, 55, 14);
		
		label_1 = new JLabel("\u0627\u0644\u062C\u0646\u0633\u064A\u0629");
		label_1.setBounds(15, 66, 39, 14);
		
		label_2 = new JLabel("\u0627\u0644\u0645\u0647\u0646\u0629");
		label_2.setBounds(15, 110, 29, 14);
		contentPane.setLayout(null);
		contentPane.add(lblNationality);
		contentPane.add(cbNationality);
		contentPane.add(lblProfission);
		contentPane.add(lblSection);
		contentPane.add(lblProject);
		contentPane.add(cbCity);
		contentPane.add(cbProfession);
		contentPane.add(cbSection);
		contentPane.add(lblWorkingStatus);
		contentPane.add(cbWS);
		contentPane.add(lblProjectName);
		contentPane.add(lblPosition);
		contentPane.add(cbProjName);
		contentPane.add(cbPosition);
		contentPane.add(btnCancel);
		contentPane.add(lblEmployeeIdTo);
		contentPane.add(tfID);
		contentPane.add(btnQuery);
		contentPane.add(label_2);
		contentPane.add(label);
		contentPane.add(lblGrade);
		contentPane.add(label_1);
		contentPane.add(cbGrade);
		contentPane.add(btnQueryExport);
		
		label_3 = new JLabel("\u0627\u0644\u0645\u0646\u0635\u0628");
		label_3.setBounds(15, 150, 46, 14);
		contentPane.add(label_3);
		
		label_4 = new JLabel("\u0627\u0644\u0642\u0633\u0645");
		label_4.setBounds(15, 193, 46, 14);
		contentPane.add(label_4);
		
		label_5 = new JLabel("\u0627\u0644\u0645\u0631\u062A\u0628\u0629");
		label_5.setBounds(15, 241, 46, 14);
		contentPane.add(label_5);
		
		label_6 = new JLabel("\u062D\u0627\u0644\u0629 \u0627\u0644\u0639\u0645\u0644");
		label_6.setBounds(15, 286, 55, 14);
		contentPane.add(label_6);
		
		label_7 = new JLabel("\u0627\u0633\u0645 \u0627\u0644\u0645\u0634\u0631\u0648\u0639");
		label_7.setBounds(15, 328, 68, 14);
		contentPane.add(label_7);
		
		label_8 = new JLabel("\u0627\u0644\u0645\u062F\u064A\u0646\u0629");
		label_8.setBounds(15, 371, 46, 14);
		contentPane.add(label_8);
	}
}
