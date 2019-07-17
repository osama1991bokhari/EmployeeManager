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
    public static final String DATA_TARGET_PATH =  "targetDatas.txt";
    public static final String ID_FILE = "idFile.txt";
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
	public static void addE(String data) throws IOException,     NumberFormatException {
        BufferedWriter dataWriter = new BufferedWriter(new FileWriter(DATA_TARGET_PATH,true));
                dataWriter.append(data);
                dataWriter.newLine();
                dataWriter.close();
        }
	public static int createID()throws IOException,     NumberFormatException{
		int newID=0;
		try {

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
		    }catch (IOException e) {
		    e.printStackTrace();
		}
return newID;
		} 
	


	/**
	 * Create the frame.
	 */
	public AddEmployees() {
		setTitle("Add Employee");
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
	private void initComponent() 
	{

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
		
		cbProfession = new JComboBox();
		cbProfession.setBounds(122, 130, 186, 20);
		
		cbPosition = new JComboBox();
		cbPosition.setBounds(423, 130, 170, 20);
		
		cbSection = new JComboBox();
		cbSection.setBounds(122, 168, 186, 20);
		
		cbGrade = new JComboBox();
		cbGrade.setBounds(423, 168, 52, 20);
		cbGrade.setModel(new DefaultComboBoxModel());
		for(int i=10;i<=50;i++)
			cbGrade.addItem(i);
		cbGrade.setSelectedIndex(0);
		
		cbWS = new JComboBox();
		cbWS.setModel(new DefaultComboBoxModel(new String[] {" ", "Business Trip", "Working", "Vacation"}));
		cbWS.setBounds(122, 206, 186, 20);
		
		cbProjName = new JComboBox();
		cbProjName.setBounds(423, 206, 169, 20);
		
		cbCity = new JComboBox();
		cbCity.setBounds(122, 244, 186, 20);
		
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
				if(fname.equals("")||lname.equals("")||email.equals("")||conLocal.equals("")||nationality.equals(" ")){
					JOptionPane.showMessageDialog(null, "Please fill in all fields!!!");fnamep=lnamep=emailp=conLocalp=conHomep=nationalityp=false;}
				else{
				try{	
				double salary = Double.parseDouble(tfSalary.getText());
				double otherAllowance = Double.parseDouble(tfOtherAllowance.getText());
				boolean salaryp=true;
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
					if ((conHome.charAt(i)>=48&&conHome.charAt(i)<=57)||conHome.equals("")){
						tfContactHome.setForeground(Color.BLACK);conHomep=true;}
					else if(conHome.equals("Optional")){
						tfContactHome.setForeground(Color.GRAY);conHomep=true;}
					else{
					JOptionPane.showMessageDialog(null, "Please enter a valid contact number");tfContactHome.setForeground(Color.RED);conHomep=false;break;
					}}
				
				if(tfEmail.getText().indexOf('@')<=0||tfEmail.getText().indexOf('.')<(tfEmail.getText().indexOf('@')+2)){
					JOptionPane.showMessageDialog(null, "Not a valid mail");tfEmail.setForeground(Color.RED);emailp=false;}
				else
					{tfEmail.setForeground(Color.BLACK);emailp = true;}
				if(!nationality.equals(""))nationalityp=true;
				
				
				if(fnamep&&lnamep&&emailp&&conLocalp&&conHomep&&nationalityp){
				System.out.println(tfFname.getText()+" "+tfLname.getText()+" "+tfEmail.getText()+" "+cbGrade.getSelectedItem()+" "+cbNationality.getSelectedItem());
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////access file
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				int id = 0;
				try {
					id = createID();
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String formatted = null;
				formatted = String.format("%05d", id);
				
				try {
				
					addE("2018"+formatted+"  "+tfFname.getText()+" "+tfLname.getText()+" "+tfEmail.getText()+" "+cbGrade.getSelectedItem()+" "+cbNationality.getSelectedItem());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Employee: "+fname+" "+lname+" has been successfully added with ID#2018"+formatted);clearAll();
																			}
				}/*End of else for not empty fields*/									   }/*End of Action listener*/
		});
		
	}
}
