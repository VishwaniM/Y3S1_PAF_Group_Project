package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class user {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/userm?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUserDetails(String userID, String uName, String email, String pnumber) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user(`userID`,`uName`,`email`,`pnumber`)"
					+ " values (?, ?, ?, ? )";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, userID);
			preparedStmt.setString(2, uName);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, pnumber);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted User successfully";
		} catch (Exception e) {
			output = "Error while inserting the Power Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUserDetails() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User ID</th><th>User Name</th><th>Email</th><th>Phone Number</th></tr>";
			String query = "select * from user";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				
				String userID = rs.getString("userID");
				String uName = rs.getString("uName");
				String email = rs.getString("email");
				String pnumber = rs.getString("pnumber");
				

				// Add into the html table
				output += "<tr><td>" + userID + "</td>";
				output += "<td>" + uName + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + pnumber + "</td>";
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the User Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUserDetails(String userID, String uName, String email, String pnumber) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE user SET uName=?,email=?,pnumber=?" + "WHERE userID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			
			preparedStmt.setString(1, uName);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, pnumber);
			preparedStmt.setString(4, userID);
			
			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated User Details successfully";
		} catch (Exception e) {
			output = "Error while updating the User Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteUserDetails(String userID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from user where userID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, (userID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted User details successfully";
		} catch (Exception e) {
			output = "Error while deleting the User Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}

