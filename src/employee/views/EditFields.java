package employee.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class EditFields extends JFrame {

	private JPanel contentPane;
	private JButton btnCancel;
	
	public static final String POSITION =  "src/Positions.txt";
	public static final String PROF =  "src/Professions.txt";
	public static final String PROJ =  "src/Projects.txt";
	public static final String SECTION =  "src/Sections.txt";
	public static final String CITY =  "src/City.txt";
	public String [] profA = new String [100];
	public String [] posA = new String [100];
	public String [] secA = new String [100];
	public String [] projA = new String [100];
	public String [] cityA = new String [100];
	private JButton btnSave;
	private JTextArea taProf;
	private JTextArea taPosition;
	private JTextArea taSection;
	private JTextArea taProj;
	private JTextArea taCity;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditFields frame = new EditFields();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	
	public static String [] sortField(String [] fieldA){
		int n = fieldA.length;
	    String temp;
	    for(int i =0; i< n;i++){
	    	for (int j = 1;j< (n-i);j++){
	    		if(fieldA[j]==null)
	    			break;
	    		if(fieldA[j-1].compareTo(fieldA[j])>0){
	    			temp = fieldA[j-1];
	    			fieldA[j-1]=fieldA[j];
	    			fieldA[j] =temp;
	    		}
	    	}
	    }
		return fieldA;
	}
	public EditFields() throws IOException {
		setResizable(false);
		
		initComponent();
		createEvent();
		
		
	}

	private void createEvent() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectTask frame=new SelectTask();
				frame.setVisible(true);dispose();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////// Reading the content of the Text Area before saving them.
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				int j=0;
				for (String line : taProf.getText().split("\\n"))
				{
					profA[j]=line;j++;
				}	
				j=0;
				for (String line : taPosition.getText().split("\\n"))
				{
					posA[j]=line;j++;
				}	
				j=0;
				for (String line : taSection.getText().split("\\n"))
				{
					secA[j]=line;j++;
				}	
				j=0;
				for (String line : taProj.getText().split("\\n"))
				{
					projA[j]=line;j++;
				}	
				j=0;
				for (String line : taCity.getText().split("\\n"))
				{
					cityA[j]=line;j++;
				}	
					
			posA = sortField(posA);
			profA = sortField(profA);
			projA = sortField(projA);
			cityA = sortField(cityA);
			secA = sortField(secA);
			int choise = JOptionPane.showConfirmDialog(null, "Are you sure you want to save your changes: ","Confirm Changes",  0);
			if(choise==0)
			try {
				BufferedWriter bwPos = new BufferedWriter(new FileWriter(POSITION));
				BufferedWriter bwProf = new BufferedWriter(new FileWriter(PROF));
				BufferedWriter bwSec = new BufferedWriter(new FileWriter(SECTION));
				BufferedWriter bwProj = new BufferedWriter(new FileWriter(PROJ));
				BufferedWriter bwCity = new BufferedWriter(new FileWriter(CITY));
				for (int i=0;i<posA.length;i++)
					if(posA[i]!=null)
					{
						bwPos.write(posA[i]);
						bwPos.newLine();
					}
					else
					{
						bwPos.close();
						break;
					}
				for (int i=0;i<profA.length;i++)
					if(profA[i]!=null)
					{
						bwProf.write(profA[i]);
						bwProf.newLine();
					}
					else
					{
						bwProf.close();
						break;
					}
				for (int i=0;i<secA.length;i++)
					if(secA[i]!=null)
					{
						bwSec.write(secA[i]);
						bwSec.newLine();
					}
					else
					{
						bwSec.close();
						break;
					}
				for (int i=0;i<projA.length;i++)
					if(projA[i]!=null)
					{
						bwProj.write(projA[i]);
						bwProj.newLine();
					}
					else
					{
						bwProj.close();
						break;
					}
				for (int i=0;i<cityA.length;i++)
					if(cityA[i]!=null)
					{
						bwCity.write(cityA[i]);
						bwCity.newLine();
					}
					else
					{
						bwCity.close();
						break;
					}
				JOptionPane.showMessageDialog(null, "Changes has been saved for the fields");
				SelectTask frame=new SelectTask();
				frame.setVisible(true);dispose();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		});
	}

	private void initComponent() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditEmployees.class.getResource("/employee/resources/SAS_Logo.png")));
		setTitle("Edit Fields");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JLabel lblProfession = new JLabel("Profession:");
		
		JScrollPane scrProf = new JScrollPane();
		
		JLabel lblPosition = new JLabel("Position:");
		
		JScrollPane scrPosition = new JScrollPane();
		
		JScrollPane scrSection = new JScrollPane();
		
		JLabel lblSection = new JLabel("Section:");
		
		JScrollPane scrProj = new JScrollPane();
		
		JLabel lblProjects = new JLabel("Projects:");
		
		JScrollPane scrCity = new JScrollPane();
		
		JLabel lblCity = new JLabel("City:");
		
		btnSave = new JButton("Save");
		
		
		btnCancel = new JButton("Cancel");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(scrProj, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
									.addComponent(scrProf, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
								.addGap(83))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblProjects)
								.addGap(220)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblProfession)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrCity, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
								.addComponent(lblPosition)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrPosition, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(65)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnCancel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnSave, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(scrSection, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblSection))
							.addGap(69))
						.addComponent(lblCity))
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPosition)
						.addComponent(lblSection)
						.addComponent(lblProfession))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrProf, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
						.addComponent(scrPosition, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
						.addComponent(scrSection, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProjects)
								.addComponent(lblCity))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrProj, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrCity, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(24))))
		);
		
		taCity = new JTextArea();
		scrCity.setViewportView(taCity);
		
		taProj = new JTextArea();
		scrProj.setViewportView(taProj);
		
		taSection = new JTextArea();
		scrSection.setViewportView(taSection);
		
		taPosition = new JTextArea();
		scrPosition.setViewportView(taPosition);
		
		taProf = new JTextArea();
		scrProf.setViewportView(taProf);
		contentPane.setLayout(gl_contentPane);
		
		BufferedReader brProf = new BufferedReader(new FileReader(PROF));
		BufferedReader brSec = new BufferedReader(new FileReader(SECTION));
		BufferedReader brPos = new BufferedReader(new FileReader(POSITION));
		BufferedReader brProj = new BufferedReader(new FileReader(PROJ));
		BufferedReader brCity = new BufferedReader(new FileReader(CITY));
		String line;String full = "";int i = 0;
		while((line = brCity.readLine())!=null)
		{
			full = full + line+"\n";
			cityA [i] = line;i++;
		}
			taCity.setText(full);
			full = "";i=0;
		while((line = brProj.readLine())!=null)
			{
			full = full + line+"\n";
			projA  [i] = line;
			}
			taProj.setText(full);
			full = "";i=0;
		while((line = brSec.readLine())!=null)
			{
			full = full + line+"\n";
			secA [i] = line;
			}
			taSection.setText(full);
			full = "";i=0;
		while((line = brPos.readLine())!=null)
			{
			full = full + line+"\n";
			posA [i] = line;
			}
			taPosition.setText(full);
			full = "";i=0;
		while((line = brProf.readLine())!=null)
			{
			full = full + line+"\n";
			profA [i] = line;
			}
			taProf.setText(full);	
			brCity.close();
			brSec.close();
			brProf.close();
			brPos.close();
			brProj.close();
		//Add here the code to fill the text areas with data from text tiles.
	}
}
