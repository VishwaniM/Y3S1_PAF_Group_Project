package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pay?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayDetails(String payID, String cusID, String pmethod, String date, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment(`payID`,`cusID`,`pmethod`,`date`,`amount`)"
					+ " values (?, ?, ?, ?,? )";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, payID);
			preparedStmt.setString(2, cusID);
			preparedStmt.setString(3, pmethod);
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, amount);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Payment Inserted successfully";
		} catch (Exception e) {
			output = "Error Payment NOT Inserted.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayDetails() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error not connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Pay ID</th><th>Customer ID</th><th>Payment method</th><th>Date</th><th>Total Amount</th></tr>";
			String query = "select * from payment";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				
				String payID = rs.getString("payID");
				String cusID = rs.getString("cusID");
				String pmethod = rs.getString("pmethod");
				String date = rs.getString("date");
				String amount = rs.getString("amount");
				

				// Add into the html table
				output += "<tr><td>" + payID + "</td>";
				output += "<td>" + cusID + "</td>";
				output += "<td>" + pmethod + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + amount + "</td>";
				
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayDetails(String payID, String cusID, String pmethod, String date, String amount) {
		String output = "Payment Updated Successfully";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error Payment NOT Updated.";
			}

			// create a prepared statement
			String query = "UPDATE payment SET cusID=?,pmethod=?,date=?,amount=?" + "WHERE payID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			
			preparedStmt.setString(1, cusID);
			preparedStmt.setString(2, pmethod);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, amount);
			preparedStmt.setString(5, payID);
			
			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletePayDetails(String payID) {
		String output = "Payment Deleted successfully ";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error Payment NOT Deleted.";
			}

			// create a prepared statement
			String query = "delete from payment where payID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, (payID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the payment Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
