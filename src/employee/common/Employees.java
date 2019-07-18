package employee.common;

public class Employees {
private int id;
private String fname;
private String lname;
private String nationality;
private String email;
private String conLocal;
private String conHome;
private String pos;
private String prof;
private String sec;
private String proj;
private String city;
private String ws;
private int grade;
private double salary;
private double otherSalary;

public  Employees(){
	  id = 0;
	  fname=null;
	  lname=null;
	  nationality=null;
	  email=null;
	  conLocal=null;
	  conHome=null;
	  prof=null;
	  sec=null;
	  proj=null;
	  city=null;
	  ws = null;
	  grade=0;
	  salary=0;
	  otherSalary=0;
}
public  Employees(int id,
					 String fname,
					 String lname,
					 String nationality,
					 String email,
					 String conLocal,
					 String conHome,
					 String prof,				 
					 String pos,
					 String sec,
					 int grade,
					 String ws,					 
					 String proj,
					 String city,
					 double salary,
					 double otherSalary){
	  this.id = id;
	  this.fname=fname;
	  this.lname=lname;
	  this.nationality=nationality;
	  this.email=email;
	  this.conLocal=conLocal;
	  this.conHome=conHome;
	  this.prof=prof;
	  this.pos=pos;
	  this.sec=sec;
	  this.proj=proj;
	  this.city=city;
	  this.ws=ws;
	  this.grade=grade;
	  this.salary=salary;
	  this.otherSalary=otherSalary;
}
public String toString(){
	return this.id+" "+
			this.fname+" "+
			this.lname+" "+
			this.nationality+" "+
			this.email+" "+
			this.conLocal+" "+
			this.conHome+" "+
			this. prof+" "+
			this.sec+" "+
			this.proj+" "+
			this.city+" "+
			this.ws+" "+
			this.grade+" "+
			this.salary+" "+
			this.otherSalary;
}

public int getID()
{
return this.id;
}
public String getFname()
{
return this.fname;
}
public String getLname()
{
return this.lname;
}
public String getEmail()
{
return this.email;
}
public String getNationality()
{
return this.nationality;
}
public String getPos()
{
return this.pos;
}
public String getProf()
{
return this.prof;
}
public String getSec()
{
return this.sec;
}
public String getProj()
{
return this.proj;
}
public String getCity()
{
return this.city;
}
public String getWS()
{
return this.ws;
}
public double getSalary()
{
return this.salary;
}
public double getOtherSalary()
{
return this.otherSalary;
}
public int getGrade()
{
return this.grade;
}
public String getConLocal()
{
return this.conLocal;
}
public String getConHome()
{
return this.conHome;
}

public void setID(int id)
{
 this.id = id;
}
public void setFname(String fname)
{
 this.fname = fname;
}
public void setLname(String lname)
{
 this.lname = lname;
}
public void setEmail(String email)
{
 this.email = email;
}
public void setNationality(String nationality)
{
 this.nationality = nationality;
}
public void setPos(String pos)
{
 this.pos = pos;
}
public void setProf(String prof)
{
 this.prof = prof;
}
public void setSec(String sec)
{
 this.sec = sec;
}
public void setProj(String proj)
{
 this.proj = proj;
}
public void setCity(String city)
{
 this.city = city;
}
public void setWS(String ws)
{
 this.ws = ws;
}
public void setSalary(double salary)
{
 this.salary = salary;
}
public void setOtherSalary(double otherSalary)
{
 this.otherSalary = otherSalary;
}
public void setGrade(int grade)
{
 this.grade = grade;
}
public void setConLocal(String conLocal)
{
 this.conLocal = conLocal;
}
public void setConHome(String conHome)
{
 this.conHome = conHome;
}


}
