package com.tables.beans;
import com.tables.beans.*;
import com.tables.beans.Doctor;
import java.sql.Time;
public class Appointment {
	private Patient patient;
	private Doctor doctor;
	private String timing;
	private String status;
	private Disease disease;
	
	public Disease getDisease() {
		return disease;
	}
	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public String toString() {
		return patient.getPatientid()+" "+patient.getPatientname()+" "+patient.getDisease().getDiseaseid()+" "+patient.getDisease().getDiseasename()+" "+doctor.getDoctorid()+" "+doctor.getDoctorname()+" "+timing+" "+status;
	}

}

