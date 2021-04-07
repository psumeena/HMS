
 package com.example.hms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class Myclass {
    public static void main(String[] args) {
    	System.out.println("hi");
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hms", "postgres", "sumi2717")) {
 
            System.out.println("Java JDBC PostgreSQL Example");

 
            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM diseases");
            while (resultSet.next()) {
                System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("disease_id"), resultSet.getString("disease_name"));
            }
         
        }
    catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}

