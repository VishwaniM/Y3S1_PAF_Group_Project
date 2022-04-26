package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class bill {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bill?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertBillDetails(String billID, String cusID, String unit, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into ebill(`billID`,`cusID`,`unit`,`amount`)"
					+ " values (?, ?, ?, ? )";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, billID);
			preparedStmt.setString(2, cusID);
			preparedStmt.setString(3, unit);
			preparedStmt.setString(4, amount);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the User Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBillDetails() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Bill ID</th><th>Customer ID</th><th>Unit Amount</th><th>Total Amount</th></tr>";
			String query = "select * from ebill";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				
				String billID = rs.getString("billID");
				String cusID = rs.getString("cusID");
				String unit = rs.getString("unit");
				String amount = rs.getString("amount");
				

				// Add into the html table
				output += "<tr><td>" + billID + "</td>";
				output += "<td>" + cusID + "</td>";
				output += "<td>" + unit + "</td>";
				output += "<td>" + amount + "</td>";
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Bill Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBillDetails(String billID, String cusID, String unit, String amount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE ebill SET cusID=?,unit=?,amount=?" + "WHERE billID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			
			preparedStmt.setString(1, cusID);
			preparedStmt.setString(2, unit);
			preparedStmt.setString(3, amount);
			preparedStmt.setString(4, billID);
			
			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Bill Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteBillDetails(String billID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from ebill where billID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, (billID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Bill Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
