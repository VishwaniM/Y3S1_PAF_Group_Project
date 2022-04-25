package model;

import java.sql.*;

public class Complaint {

	// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paflab", "root", "");
				System.out.println("Successfully connected");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error connected");
			}
			return con;
		}

		public String insertComplaint(String name, String email, int pnumber, String category, String complaint, String area, String remarks) {
			String output = "";
			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}

				// create a prepared statement
				String query = " insert into complaints(`complaintID`, `complainerName` , `email`,`phoneNumber`,`complaintCategory`,`complaint`, `issueArea` ,`remarks`)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, 0);			
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, email);
				preparedStmt.setInt(4, pnumber);				
				preparedStmt.setString(5, category);
				preparedStmt.setString(6, complaint);
				preparedStmt.setString(7, area);
				preparedStmt.setString(8, remarks);

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting the complaints.";
				System.err.println(e.getMessage());
			}

			return output;
		}

		public String readComplaint() {
			String output = "";

			try {

				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Complainer Name</th>"
						+ "<th>Email</th>" 
						+ "<th>Phone Number</th>" 
						+ "<th>Complaint category</th>"
						+ "<th>Complaint</th>" 
						+ "<th>Issue Area</th>" 
						+ "<th>Remarks</th>"
						+ "<th>Update</th><th>Remove</th></tr>";

				String query = "select * from complaints";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {
					String complaintID = Integer.toString(rs.getInt("complaintID"));
					String complainerName = rs.getString("complainerName");
					String email = rs.getString("email");
					String phoneNumber = Integer.toString(rs.getInt("phoneNumber"));
					String complaintCategory = rs.getString("complaintCategory");
					String complaint = rs.getString("complaint");
					String issueArea = rs.getString("issueArea");
					String remarks = rs.getString("remarks");

					// Add into the html table
					
					output += "<td>" + complainerName + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + phoneNumber + "</td>";
					output += "<td>" + complaintCategory + "</td>";
					output += "<td>" + complaint + "</td>";
					output += "<td>" + issueArea + "</td>";
					output += "<td>" + remarks + "</td>";


					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='items.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='complaintID' type='hidden' value='" + complaintID + "'>" + "</form></td></tr>";
				}
				con.close();

				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the complaints.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		public String updateComplaint(int ID, String name, String email, int pnumber, String category, String complaint, String area, String remarks) {
		
			String output = "";
			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for updating.";
				}

				// create a prepared statement
				String query = "UPDATE complaints SET complainerName=?,email=?,phoneNumber=?,complaintCategory=?,complaint=?,issueArea=?,remarks=?" + "WHERE complaintID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
										
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, email);
				preparedStmt.setInt(3, pnumber);
				preparedStmt.setString(4, category);
				preparedStmt.setString(5, complaint);
				preparedStmt.setString(6, area);
				preparedStmt.setString(7, remarks);
				preparedStmt.setInt(8, ID);

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the complaint.";
				System.err.println(e.getMessage());
			}

			return output;
		}

		public String deleteComplaint(String complaintID) {
			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "delete from complaints where complaintID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, Integer.parseInt(complaintID));

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Deleted successfully";

			} catch (Exception e) {
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}

			return output;
		}
}

