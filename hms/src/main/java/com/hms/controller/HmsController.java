package com.hms.controller;
import java.util.*;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tables.access.DataAccess;
import com.tables.beans.*;

@RestController
public class HmsController {
	
	@RequestMapping(path="/hms/patient",method=RequestMethod.POST)
		public Patient getPatient(@RequestBody Patient patient) throws InterruptedException
		{
		    
			DataAccess obj=new DataAccess();
			int id=obj.insertPatient(patient);
			patient.setPatientid(id);
			Appointment obj1= new Appointment();
			obj1.setPatient(patient);
			obj1.setStatus("Pending");
			obj1.setTiming("12:00:00");
			obj.insertAppointment(obj1);
			return patient;
		}
	@RequestMapping(path="/hms/appointment",method=RequestMethod.PUT)
	
		public void setAppointment(@RequestBody Appointment appointment) throws InterruptedException
		{
		
		DataAccess obj=new DataAccess();
		obj.updateAppointmentStatus(appointment);
		
	}

	@RequestMapping(path="/hms/patients",method=RequestMethod.GET)
	public List<Patient> viewPatients() throws InterruptedException
	{
		
		DataAccess obj=new DataAccess();
		return obj.getPatients();	
	}
	@RequestMapping(path="/hms/timings",method=RequestMethod.GET)
	public List<Appointment> displayTimings()throws InterruptedException
	{
		DataAccess obj=new DataAccess();
		return obj.getTimings();
	}
	@RequestMapping(path="/hms/doctors",method=RequestMethod.GET)
	public HashMap<Integer,List<Doctor>> displayDoctors() throws InterruptedException
	{
		
		DataAccess obj=new DataAccess();
		return obj.getDoctor();	
	}

}
