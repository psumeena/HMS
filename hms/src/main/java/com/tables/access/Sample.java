package com.tables.access;
import com.tables.beans.*;
public class Sample {
	
	public static void main(String[] args)
	{
		Disease obj2=new Disease();
		obj2.setDiseaseid("D2");
		Patient obj1= new Patient();
		obj1.setPatientname("hii");
		obj1.setAge(90);
		obj1.setAddress("hgjkl");
		obj1.setPhonenumber(5467890);
		obj1.setDisease(obj2);
		obj1.setDoctorid(102);
DataAccess obj= new DataAccess();
int id=obj.insertPatient(obj1);
System.out.println(id);
	}
	
}