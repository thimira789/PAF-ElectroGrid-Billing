package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
	
	private Connection connect() {
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// this sample 1
	
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electriproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			
			//For testing
			System.out.print("Successfully connected");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//Read function
	public String readbill()
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
			 output = "<table border='1'><tr><th>billCName</th>" +"<th>billAccNO</th><th>billDate</th>"+ "<th>billUnits</th><th>billAmount</th>" + "<th>Update</th><th>Remove</th></tr>"; 
			 String query = "select * from billingm"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 	
				 // Retrieve from database using column names
				 String billID  = Integer.toString(rs.getInt("billID")); 
				 String billCName = rs.getString("billCName"); 
				 String billAccNO = rs.getString("billAccNO"); 
				 String billDate = rs.getString("billDate"); 
				 String billUnits = rs.getString("billUnits");
				 String billAmount = Double.toString(rs.getDouble("billAmount")); 
				 
				 // Add a row into the html table
				 output += "<tr><td>"+ billCName + "</td>";
				 output += "<td>" + billAccNO + "</td>"; 
				 output += "<td>" + billDate + "</td>";
				 output += "<td>" + billUnits + "</td>";
				 output += "<td>" + billAmount + "</td>";
				 
				 // Buttons
				 output += 
				   "<td><input name='btnUpdate' type='button' value='Update' " + "class='btnUpdate btn btn-secondary' data-billid='" + billID + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-billid='" + billID + "'>"+"</td>"
				 + "</form></td></tr>";			 
			 } 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
			 } 
		catch (Exception e) 
		 { 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}	
	//Insert function
	public String insertbill (String CName, String AccNO, String Date, String Units, String Amount)
	{ 
		String output = "";  
		try
		 { 
			Connection con = connect(); 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database"; 
			 } 
			 // create a prepared statement
			 String query = " insert into billingm (`billID`,`billCName`,`billAccNO`,`billDate`,`billUnits`,`billAmount`)"+" values (?, ?, ?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, CName); 
			 preparedStmt.setString(3, AccNO); 
			 preparedStmt.setString(4, Date); 
			 preparedStmt.setString(5, Units);
			 preparedStmt.setDouble(6, Double.parseDouble(Amount)); 
			 
			 //execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 String newBills = readbill();
			 output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
		 } 
		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the bill.\"}";
			 System.err.println(e.getMessage()); 
		 } 
		return output; 
	}	
	//Delete function
	public String deleteBill(String billID)
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
			String query = "delete from billingm where billID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(billID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newBills = readbill();
			output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the bill.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	//Update function
	public String updateBill(String billID, String CName, String AccNO, String Date, String Units, String Amount)
	{
		String output = "";
		try 
		{
			Connection con = connect();
	
			if (con == null) 
			{
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE billingm SET billCName=?,billAccNO=?,billDate=?,billUnits=?,billAmount=?" + "WHERE billID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, CName);
			preparedStmt.setString(2, AccNO);
			preparedStmt.setString(3, Date);
			preparedStmt.setString(4, Units);
			preparedStmt.setString(5, Amount);
			preparedStmt.setInt(6, Integer.parseInt(billID));

			// execute the statement
					preparedStmt.execute();
					con.close();

					String newBills = readbill();
					output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the bill.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}
