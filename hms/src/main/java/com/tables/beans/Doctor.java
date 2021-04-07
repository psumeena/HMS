package com.tables.beans;

public class Doctor {
	private int doctorid;
	private String doctorname;
	private String specialization;
	private int age;
	private long emergencyno;
	public int getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	public String getDoctorname() {
		return doctorname;
	}
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getEmergencyno() {
		return emergencyno;
	}
	public void setEmergencyno(long emergencyno) {
		this.emergencyno = emergencyno;
	}
	public String toString()
	{
		return doctorid+" " +doctorname+" "+specialization+" "+age+" "+emergencyno;
	}

}
