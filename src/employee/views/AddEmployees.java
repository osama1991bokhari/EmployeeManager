package employee.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

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

import java.util.Locale;
import java.util.Locale.Category;
import java.awt.font.NumericShaper.Range;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.awt.Toolkit;

public class AddEmployees extends JFrame {

	private JPanel contentPane;
	private JTextField tfFname;
	private JTextField tfLname;
	private JTextField tfEmail;
	private JTextField tfContactLocal;
	private JTextField tfContactHome;
	private JTextField tfSalary;
	private JTextField tfOtherAllowance;
	private JButton btnAdd;
	private JComboBox cbGrade;
	private JComboBox cbNationality;
    private JComboBox cbProfession;
    private JComboBox cbPosition;
    private JComboBox cbSection;
    private JComboBox cbWS;
    private JComboBox cbProjName;
    private JComboBox cbCity;
    private JButton btnClear;
    private JButton btnCancel;
    private static String dbURL = "jdbc:derby://localhost:1527/Employee;create=true;user=root;password=root";
    private static String tableName = "EMPLOYEE";
    private static Connection conn = null;
    private static Statement stmt = null;
    public static final String DATA_TARGET_PATH =  "/employee/resources/targetDatas.txt";
    public static final String ID_FILE = "/employee/resources/idFile.txt";
    public static final String POSITION =  "/employee/resources/Positions.txt";
	public static final String PROF =  "/employee/resources/Professions.txt";
	public static final String PROJ =  "/employee/resources/Projects.txt";
	public static final String SECTION =  "/employee/resources/Sections.txt";
	public static final String CITY =  "/employee/resources/City.txt";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployees frame = new AddEmployees();
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
	  private static void insertEmployees(String fname,String lname,String nationality, String email,String contactLocal,String contactHome,String profession, String position, String section, int grade, String ws, String project, String city, double salary, double otherSalary)
	    {
	        try
	        {
	            stmt = conn.createStatement();
	            if(contactHome.equals("Optional")||contactHome.equals("")||contactHome==null)
	            stmt.execute("insert into " + tableName + " (fname,lname,nationality,email,contactLocal,contactHome,profession,position,section,grade,workingStatus,project,city,salary,otherSalary)"+ " values ('"+fname + "','" + lname + "','" + nationality +"','"+email + "','" + contactLocal + "',NULL,'"+profession+"','"+position+"','"+section+"',"+grade+",'"+ws+"','"+project+"','"+city+"',"+salary+","+otherSalary+")");
	            else
	            	stmt.execute("insert into " + tableName + " (fname,lname,nationality,email,contactLocal,contactHome,profession,position,section,grade,workingStatus,project,city,salary,otherSalary)"+ " values ('"+fname + "','" + lname + "','" + nationality +"','"+email + "','" + contactLocal + "','"+contactHome+"','"+profession+"','"+position+"','"+section+"',"+grade+",'"+ws+"','"+project+"','"+city+"',"+salary+","+otherSalary+")");
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
	    private static int selectEmployees()
	    {	int id = 0;
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
	                id = results.getInt(1);
	                String fName = results.getString(2);
	                String lName = results.getString(3);
	                String grade = results.getString(4);
	                System.out.println(id + "\t\t" + fName + "\t\t" + lName + "\t\t" + grade);
	            }
	            results.close();
	            stmt.close();
	            return id;
	        }
	        catch (SQLException sqlExcept)
	        {
	            sqlExcept.printStackTrace();
	            return id;
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
	  
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Method to add employees to the text file "targetDatas.txt"
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void addE(String data) throws IOException,     NumberFormatException {
        BufferedWriter dataWriter = new BufferedWriter(new FileWriter(DATA_TARGET_PATH,true));
                dataWriter.append(data);
                dataWriter.newLine();
                dataWriter.close();
        }
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////// Method for managing and creating ID numbers for new employees, probably won't use with the DB since ID is generated automatically.
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static int createID()throws IOException,     NumberFormatException
	{
		int newID=0;
		try 
		{

		    BufferedReader br = new BufferedReader(new FileReader(ID_FILE));
		    String id=br.readLine();
		    if(id!=null){
		    newID = Integer.parseInt(id);
		    newID++;}
		    else
		    newID=0;
		    String ffs = ""+newID;
		    BufferedWriter bw = new BufferedWriter(new FileWriter(ID_FILE));
		    
		    bw.write(ffs);
		    System.out.println("****"+ffs+"****");
		   	bw.close();
		    br.close();
		 }
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
			return newID;
	}
	


	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public AddEmployees() throws IOException {

		initComponent();
		createEvent();
		
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
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// Method to reset all the fields
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void clearAll(){
		tfFname.setText("");tfFname.setForeground(Color.BLACK);
		tfLname.setText("");tfLname.setForeground(Color.BLACK);
		tfEmail.setText("");tfEmail.setForeground(Color.BLACK);
		tfContactLocal.setText("");tfContactLocal.setForeground(Color.BLACK);
		tfContactHome.setText("Optional");tfContactHome.setForeground(Color.GRAY);
		tfSalary.setText("");tfSalary.setForeground(Color.BLACK);
		tfOtherAllowance.setText("");tfOtherAllowance.setForeground(Color.BLACK);
		cbNationality.setSelectedIndex(-1);
		cbProfession.setSelectedIndex(-1);
		cbPosition.setSelectedIndex(-1);
		cbSection.setSelectedIndex(-1);
		cbGrade.setSelectedIndex(-1);
		cbWS.setSelectedIndex(-1);
		cbProjName.setSelectedIndex(-1);
		cbCity.setSelectedIndex(-1);
	}
	private void initComponent() throws IOException 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddEmployees.class.getResource("/employee/resources/SAS_Logo.png")));
		setTitle("Add Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		setLocationRelativeTo(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(15, 19, 77, 14);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(326, 19, 83, 14);
		
		JLabel lblNationality = new JLabel("Nationality:");
		lblNationality.setBounds(15, 57, 77, 14);
		
		JLabel lblContactNumberlocal = new JLabel("Contact Number (Local):");
		lblContactNumberlocal.setBounds(15, 95, 140, 14);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(326, 57, 55, 14);
		
		JLabel lblContactNumberhome = new JLabel("Contact Number (Home):");
		lblContactNumberhome.setBounds(326, 95, 149, 14);
		
		JLabel lblProfission = new JLabel("Profession:");
		lblProfission.setBounds(15, 133, 77, 14);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(326, 133, 68, 14);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(15, 171, 55, 14);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(326, 171, 55, 14);
		
		JLabel lblWorkingStatus = new JLabel("Working Status:");
		lblWorkingStatus.setBounds(15, 209, 107, 14);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		lblProjectName.setBounds(326, 209, 83, 14);
		
		JLabel lblProject = new JLabel("City:");
		lblProject.setBounds(15, 247, 55, 14);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(326, 247, 68, 14);
		
		JLabel lblAllowence = new JLabel("Other Allowance:");
		lblAllowence.setBounds(15, 286, 107, 14);
		
		tfFname = new JTextField();
		tfFname.setBounds(89, 16, 219, 20);
		tfFname.setColumns(10);
		
		tfLname = new JTextField();
		tfLname.setBounds(432, 16, 161, 20);
		tfLname.setColumns(10);
		
		cbNationality = new JComboBox(getAllCountries());
		cbNationality.setBounds(89, 54, 219, 20);
		cbNationality.insertItemAt(" ", 0);
		cbNationality.setSelectedIndex(0);
		tfEmail = new JTextField();
		tfEmail.setBounds(432, 54, 161, 20);
		tfEmail.setColumns(10);
		
		tfContactLocal = new JTextField();
		tfContactLocal.setBounds(165, 92, 143, 20);
		tfContactLocal.setColumns(10);
		
		tfContactHome = new JTextField();
		tfContactHome.setBounds(473, 92, 120, 20);
		tfContactHome.setColumns(10);
		tfContactHome.setText("Optional");
		tfContactHome.setForeground(Color.GRAY);
		
		cbProfession = new JComboBox(getAllProfessions());
		cbProfession.setBounds(122, 130, 186, 20);
		cbProfession.insertItemAt(" ", 0);
		cbProfession.setSelectedIndex(0);
		
		cbPosition = new JComboBox(getAllPositions());
		cbPosition.setBounds(423, 130, 170, 20);
		cbPosition.insertItemAt(" ", 0);
		cbPosition.setSelectedIndex(0);
		
		cbSection = new JComboBox(getAllSections());
		cbSection.setBounds(122, 168, 186, 20);
		cbSection.insertItemAt(" ", 0);
		cbSection.setSelectedIndex(0);
		
		cbGrade = new JComboBox();
		cbGrade.setBounds(423, 168, 52, 20);
		cbGrade.setModel(new DefaultComboBoxModel());
		for(int i=10;i<=50;i++)
			cbGrade.addItem(i);
		cbGrade.setSelectedIndex(0);
		
		cbWS = new JComboBox();
		cbWS.setModel(new DefaultComboBoxModel(new String[] {" ", "Business Trip", "Working", "Vacation"}));
		cbWS.setBounds(122, 206, 186, 20);
		
		cbProjName = new JComboBox(getAllProjects());
		cbProjName.setBounds(423, 206, 169, 20);
		cbProjName.insertItemAt(" ", 0);
		cbProjName.setSelectedIndex(0);
		
		cbCity = new JComboBox(getAllCities());
		cbCity.setBounds(122, 244, 186, 20);
		cbCity.insertItemAt(" ", 0);
		cbCity.setSelectedIndex(0);
		
		tfSalary = new JTextField();
		tfSalary.setBounds(423, 244, 169, 20);
		tfSalary.setColumns(10);
		
		tfOtherAllowance = new JTextField();
		tfOtherAllowance.setBounds(122, 283, 186, 20);
		tfOtherAllowance.setColumns(10);
		
		btnAdd = new JButton("Add");
		
		btnAdd.setBounds(354, 282, 67, 23);
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
		contentPane.add(btnAdd);
		
		btnClear = new JButton("Clear");

		btnClear.setBounds(433, 282, 67, 23);
		contentPane.add(btnClear);
		
		btnCancel = new JButton("Cancel");
		
		btnCancel.setBounds(510, 282, 78, 23);
		contentPane.add(btnCancel);
	}
	
	private void createEvent() 
	{		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectTask frame=new SelectTask();
				frame.setVisible(true);dispose();
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			clearAll();
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
		btnAdd.addActionListener(new ActionListener() {
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
				double otherSalary = 0;boolean salaryp=true;
				if(fname.equals("")||lname.equals("")||email.equals("")||conLocal.equals("")||nationality.equals(" ")||grade.equals(" ")||position.equals(" ")||profession.equals(" ")||section.equals(" ")||project.equals(" ")||city.equals(" ")||ws.equals(" ")){
					JOptionPane.showMessageDialog(null, "Please fill in all the required fields!!!");fnamep=lnamep=emailp=conLocalp=nationalityp=gradep=profp=posp=secp=projp=cityp=wsp=false;}
				else{
				try{	
				 salary = Double.parseDouble(tfSalary.getText());
				 otherSalary = Double.parseDouble(tfOtherAllowance.getText());
				 salaryp=true;
				   }
				catch(Exception e){
					JOptionPane.showMessageDialog(null,"Please enter a valid salary and allowance. ");
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
					if ((conHome.charAt(i)>=48&&conHome.charAt(i)<=57)){
						tfContactHome.setForeground(Color.BLACK);conHomep=true;}
					else if(conHome.equals("Optional")){
						tfContactHome.setForeground(Color.GRAY);conHomep=true;}
					else{
					JOptionPane.showMessageDialog(null, "Please enter a valid contact number");tfContactHome.setForeground(Color.RED);conHomep=false;break;
					}}
				if (conHome.length()==0)conHomep=true;
				
				if(tfEmail.getText().indexOf('@')<=0||tfEmail.getText().indexOf('.')<(tfEmail.getText().indexOf('@')+2)){
					JOptionPane.showMessageDialog(null, "Not a valid mail");tfEmail.setForeground(Color.RED);emailp=false;}
				else
					{tfEmail.setForeground(Color.BLACK);emailp = true;}
				if(!nationality.equals("")||!nationality.equals(" "))nationalityp=true;
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
				int id = 0;
				try {
					id = createID();
				} catch (NumberFormatException | IOException e1) {
					
					e1.printStackTrace();
				}
				String formatted = null;
				formatted = String.format("%05d", id);
				
				try {
				
					addE("2018"+formatted+"  "+tfFname.getText()+"|"+tfLname.getText()+"|"+cbNationality.getSelectedItem()+"|"+tfEmail.getText()+"|"+cbGrade.getSelectedItem());
				} catch (NumberFormatException e) {

					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				int gradeInt = (int) cbGrade.getSelectedItem();
				createConnection();
		        insertEmployees(fname, lname,nationality,email,conLocal,conHome,profession,position,section,gradeInt,ws,project,city,salary,otherSalary);
		        int ID =selectEmployees();
		        shutdown();
				JOptionPane.showMessageDialog(null, "Employee: "+fname+" "+lname+" has been successfully added with ID# "+ID);clearAll();
																			}
				}/*End of else for not empty fields*/									   }/*End of Action listener*/
		});
		
	}
}
