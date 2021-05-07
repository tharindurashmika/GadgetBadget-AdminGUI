package com.gadget_badget.fundingbodies.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
public class FundingBodiesServlet {
	
	//Connect to the MySQL DB
		private Connection connect() 
		{ 
			Connection con = null; 
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver");  
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3310/gadgetbadgetadmin?useTimezone=true&serverTimezone=UTC", "root", ""); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();} 
			 	return con; 
			} 

			public String insertFundingBodies(String name, String field, String range) 
			 { 
				 String output = ""; 
				 try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 {
						 return "Error while connecting to the database for inserting."; 
					 } 

					 LocalDate date= LocalDate.now();
					 LocalTime time= LocalTime.now();
				 	 	 // create a prepared statement
					 	 String query = "INSERT INTO funnding_bodies(name,field,ranges,published_date,published_time)" + " VALUES (?,?,?,?,?)"; 
						 PreparedStatement preparedStmt = con.prepareStatement(query); 					 
						 
						 // binding values
						 preparedStmt.setString(1,name);
						 preparedStmt.setString(2,field);
						 preparedStmt.setString(3,range);
						 preparedStmt.setString(4,date.toString());
					     preparedStmt.setString(5,time.toString());

						
						 				 
						 preparedStmt.execute(); 
						 con.close(); 
						 
						 String newFundingBodies = readFundingBodies(); 
						 output = "{\"status\":\"success\", \"data\": \"" + newFundingBodies + "\"}"; 
				 } 
				 catch (Exception e) 
				 { 
					 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Details of Funding Bodies.\"}";
					 System.err.println(e.getMessage());
				 } 
			 	return output; 
			 } 

			//Read FundingBodiess
			 public String readFundingBodies() 
			 { 
				 String output = ""; 

				 try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 {
						 return "Error while connecting to the database for reading."; 
					 } 
					 
					 // Prepare the html table to be displayed
					 output = "<table bFundingBodies='1'><tr><th>Funding Body ID</th>"
					 + "<th>name</th>" +
					 "<th>field</th>" + 
					 "<th>range</th>" + 
					 "<th>Published Date</th>" +
					 "<th>Published Time</th>" +
					 "<th>Update</th><th>Remove</th></tr>"; 
				 
					 
					 String query = "SELECT * FROM funnding_bodies"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query); 
					 
					 // iterate through the rows in the result set
					 while (rs.next()) 
					 { 
						String id= Integer.toString(rs.getInt("id"));
						String name= rs.getString("name");
						String field = rs.getString("field");
						String range = rs.getString("ranges");
						String date = rs.getString("published_date");
						String time = rs.getString("published_time");
						
						 
						 
						 // Add into the html table
						 output += "<tr><td>" + id + "</td>"; 
						 output += "<td>" + name + "</td>"; 
						 output += "<td>" + field + "</td>"; 
						 output += "<td>" + range + "</td>"; 
						 output += "<td>" + date + "</td>"; 
						 output += "<td>" + time + "</td>"; 
						 
						 
						 
						 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" 
						 + id + "'>" + "</td></tr>";
					 } 
					 	 con.close(); 
					 	 // Complete the html table
					 	 output += "</table>"; 
				 } 
				 catch (Exception e) 
				 { 
					 output = "Error while reading the FundingBodies"; 
					 System.err.println(e.getMessage()); 
				 } 
			 	 return output; 
			 } 
					
			//Update FundingBodiess
			public String updateFundingBodies(String ID,String name,String field, String range)
			{ 
				 String output = ""; 
				 try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 {
						 return "Error while connecting to the database for updating."; 
					 } 
					 LocalDate date= LocalDate.now();
					 LocalTime time= LocalTime.now();
					 // create a prepared statement
					 String query = "UPDATE funnding_bodies SET name=?,field=?,ranges=?,published_date=?,published_time=? WHERE id=?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 preparedStmt.setString(1, name); 
					 preparedStmt.setString(2, field);
					 preparedStmt.setString(3, range);
					 preparedStmt.setString(4, date.toString()); 
					 preparedStmt.setString(5, time.toString()); 
					 preparedStmt.setInt(6, Integer.parseInt(ID));  
					 
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 String newFundingBodies = readFundingBodies(); output = "{\"status\":\"success\", \"data\": \"" + newFundingBodies + "\"}"; 
				 } 
				 catch (Exception e) 
				 { 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the FundingBodies.\"}"; 
					 System.err.println(e.getMessage()); 
				 } 
				 	return output; 
				 } 
			
				//Delete FundingBodiess
				 public String deleteFundingBodies(String ID) 
				 { 
					 String output = ""; 
				 try
				 { 
					 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for deleting."; 
				 } 
				 
				 	 // create a prepared statement
					 String query = "DELETE FROM funnding_bodies WHERE id=?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(ID)); 
					 
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 String newFundingBodies = readFundingBodies(); output = "{\"status\":\"success\", \"data\": \"" + newFundingBodies + "\"}";
				 } 
				 catch (Exception e) 
				 { 
					 output = "{\"status\":\"error\", \"data\": \"Error while deleting the FundingBodies.\"}"; 
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
			} 
} 