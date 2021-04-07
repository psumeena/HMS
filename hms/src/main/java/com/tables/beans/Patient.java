package com.tables.beans;
public class Patient {
	private int patientid;
	private String patientname;
	private int age;
	private String address;
	private long  phonenumber;
	private int doctorid;
	private Disease disease;
	
	

	public Disease getDisease() {
		return disease;
	}
	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public int getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	public String toString()
	{
		return patientid +" "+patientname +" "+age+" "+phonenumber+" "+address+" "+doctorid+" "+disease.getDiseaseid()+" "+disease.getDiseasename();
	}
	

}
