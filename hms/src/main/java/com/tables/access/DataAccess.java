package com.tables.access;
import com.tables.beans.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class DataAccess {
   public List<Patient> getPatients()
   {
	   List<Patient> list =new ArrayList<Patient>();
	   try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hms", "postgres", "sumi2717")) {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT * from patient inner join diseases on(patient.disease_id=diseases.disease_id)");
          
           
           while (resultSet.next()) {
        	   Patient obj=new Patient();
               Disease obj1=new Disease();
        	   obj.setPatientid(resultSet.getInt("patient_id"));
        	   obj.setPatientname(resultSet.getString("patient_name"));
        	   obj.setAge(resultSet.getInt("age"));
        	   obj.setPhonenumber(resultSet.getLong("phone_number"));
        	   obj.setAddress(resultSet.getString("address"));
        	   obj.setDoctorid(resultSet.getInt("doctor_id"));
        	   obj1.setDiseaseid(resultSet.getString("disease_id"));
        	   obj1.setDiseasename(resultSet.getString("disease_name"));
        	   obj.setDisease(obj1);
        	   list.add(obj);
        	   
           }
           
          
        }
   catch (SQLException e) {
           System.out.println("Connection failure.");
           e.printStackTrace();
       }
	return list;
	   
   }
   public List<Appointment> getTimings()
   {
	   List<Appointment> list =new ArrayList<Appointment>();
	   HashMap<Integer,List<Doctor>> hashmap=new HashMap<Integer,List<Doctor>>();
	   DataAccess o=new DataAccess();
	   try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hms", "postgres", "sumi2717")) {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT * from appointment inner join patient on(patient.patient_id=appointment.patient_id) inner join diseases on (appointment.disease_id=diseases.disease_id)");;
           hashmap=o.getDoctor();
           while (resultSet.next()) {
        	   Patient obj=new Patient();
               Appointment obj1=new Appointment();
               Disease obj3=new Disease();
        	   obj.setPatientid(resultSet.getInt("patient_id"));
        	   obj.setPatientname(resultSet.getString("patient_name"));
        	   obj.setAge(resultSet.getInt("age"));
        	   obj.setPhonenumber(resultSet.getLong("phone_number"));
        	   obj.setAddress(resultSet.getString("address"));
        	   obj.setDoctorid(resultSet.getInt("doctor_id"));
        	   obj3.setDiseaseid(resultSet.getString("disease_id"));
        	   obj3.setDiseasename(resultSet.getString("disease_name"));
        	   obj.setDisease(obj3);
        	   List<Doctor> nlist=hashmap.get(resultSet.getInt("doctor_id"));
        	   obj1.setDoctor(nlist.get(0));
               obj1.setTiming(resultSet.getString("timing"));
               obj1.setStatus(resultSet.getString("appointment_status"));
        	   obj1.setPatient(obj);
        	   list.add(obj1); 
          
        }
	   }
   catch (SQLException e) {
           System.out.println("Connection failure.");
           e.printStackTrace();
       }
	return list;
	   
	   
   }
  
public HashMap<Integer,List<Doctor>> getDoctor()
{
	HashMap<Integer,List<Doctor>> hashmap=new HashMap<Integer,List<Doctor>>();
	
	 try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hms", "postgres", "sumi2717")) {
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * from doctor");
         while(resultSet.next())
         {
        	 Doctor obj=new Doctor();
        	 obj.setDoctorid(resultSet.getInt("doctor_id"));
        	 obj.setDoctorname(resultSet.getString("doctor_name"));
        	 obj.setSpecialization(resultSet.getString("specialization"));
        	 obj.setAge(resultSet.getInt("age"));
        	 obj.setEmergencyno(resultSet.getLong("emergency_number"));
        	 if(hashmap.containsKey(obj.getDoctorid()))		//if doctor id already present
        	 {
        		 List<Doctor> olist = hashmap.get(obj.getDoctorid());
        		 olist.add(obj);
        		 hashmap.put(obj.getDoctorid(), olist);
        	
        		 
        	 }
        	 else	//if doctor id not present
        	 {
        		 List<Doctor>list=new ArrayList<Doctor>();
        		 list.add(obj);
        		 hashmap.put(obj.getDoctorid(),list);
        	 }
         }
   
     }
catch (SQLException e) {
        System.out.println("Connection failure.");
        e.printStackTrace();
    }
	 return hashmap;

   
}
public Connection connect() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/hms", "postgres", "sumi2717");
}

public int insertPatient(Patient patient) {
        String SQL = "INSERT INTO patient(patient_name,age,address,disease_id,phone_number,doctor_id) "
                + "VALUES(?,?,?,?,?,?) RETURNING patient_id";
 
        int id=0;
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL))  {
        	
            
            pstmt.setString(1,patient.getPatientname());
            pstmt.setInt(2,patient.getAge());
            pstmt.setString(3,patient.getAddress());
            pstmt.setString(4,patient.getDisease().getDiseaseid());
            pstmt.setLong(5, patient.getPhonenumber());
            pstmt.setInt(6,patient.getDoctorid());
            ResultSet rs= pstmt.executeQuery();
            if(rs.next())
            {
            	id=rs.getInt(1);
            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
public long insertAppointment(Appointment appointment) {
    String SQL = "INSERT INTO appointment(patient_id,doctor_id,timing,appointment_status,disease_id)"
            + "VALUES(?,?,?,?,?)";
long id=0;
    try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL))  {
    	
        pstmt.setInt(1,appointment.getPatient().getPatientid());
        pstmt.setInt(2,appointment.getPatient().getDoctorid());
        pstmt.setString(3,appointment.getTiming());
        pstmt.setString(4,appointment.getStatus());
        pstmt.setString(5,appointment.getPatient().getDisease().getDiseaseid());
        int affectedRows = pstmt.executeUpdate();
        
        if (affectedRows > 0) {
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getLong(1);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return id;
}
public int updateAppointmentStatus(Appointment appointment) {
    String SQL = "UPDATE appointment "
            + "SET appointment_status = ? "
            + "WHERE patient_id = ?";

    int affectedrows = 0;

    try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {

        pstmt.setString(1, appointment.getStatus());
        pstmt.setInt(2,appointment.getPatient().getPatientid());

        affectedrows = pstmt.executeUpdate();

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } 
    return affectedrows;
}

}

