package com.mvc.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.mvc.bean.RegisterBean;
import com.mvc.util.DBConnection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class RegisterDao {
	public String registerUser(RegisterBean registerBean) {
		String fullName = registerBean.getFullName(); // Assign user entered values to temporary variables.
		String email = registerBean.getEmail(); // Assign user entered values to temporary variables.
		String phoneNo = registerBean.getPhoneNo(); // Assign user entered values to temporary variables.
		String password = registerBean.getPassword(); // Assign user entered values to temporary variables.
		String company = registerBean.getCompany(); // Assign user entered values to temporary variables.

		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = DBConnection.createConnection(); // Fetch database connection object
			String query = "insert into users(fullName,email,password,phoneNo,company) values (?,?,?,?,?)";
			preparedStatement = con.prepareStatement(query); // Statement is used to write queries.
			preparedStatement.setString(1, fullName);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, phoneNo);
			preparedStatement.setString(5, company);

			int i = preparedStatement.executeUpdate();

			if (i != 0) // Just to ensure data has been inserted into the database
				return "SUCCESS";
		} catch (MySQLIntegrityConstraintViolationException e) {
			return "DUPLICATE";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Oops.. Something went wrong there..!"; // On failure, send a message from here.
	}

	public String updateUser(String email, RegisterBean registerBean, HttpServletRequest request) {
		String fullName = registerBean.getFullName();
		String phoneNo = registerBean.getPhoneNo();
		String company = registerBean.getCompany();

		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = DBConnection.createConnection(); // Fetch database connection object
			// Insert user details into the table 'users'
			String query = "update users set fullName=?, phoneNo=?, company=? where email=?";
			// Making use of prepared statements here to insert bunch of data
			preparedStatement = con.prepareStatement(query); // Statement is used to write queries.
			preparedStatement.setString(1, fullName);
			preparedStatement.setString(2, phoneNo);
			preparedStatement.setString(3, company);
			preparedStatement.setString(4, email);

			int i = preparedStatement.executeUpdate();

			if (i != 0) {// Just to ensure data has been inserted into the database
				registerBean = getUserDetails(email);

				request.setAttribute("email", registerBean.getEmail());
				request.setAttribute("fullName", registerBean.getFullName());
				request.setAttribute("company", registerBean.getCompany());
				request.setAttribute("phoneNo", registerBean.getPhoneNo());
				request.setAttribute("password", registerBean.getPassword());
				request.setAttribute("profilePic", registerBean.getProfilePic());
				return "SUCCESS";
				
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			return "DUPLICATE";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Oops.. Something went wrong there..!"; // On failure, send a message from here.
	}

	public String updatePassword(String email, String password, HttpServletRequest request) {

		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = DBConnection.createConnection(); // Fetch database connection object
			
			String query = "update users set password=? where email=?"; // Update user password into the table 'users'
			// Making use of prepared statements here to update data
			preparedStatement = con.prepareStatement(query); // Statement is used to write queries.
			preparedStatement.setString(1, password); 
			preparedStatement.setString(2, email);

			int i = preparedStatement.executeUpdate();

			if (i != 0) { // Just to ensure data has been updated into the database

				RegisterBean registerBean = getUserDetails(email);

				request.setAttribute("email", registerBean.getEmail());
				request.setAttribute("fullName", registerBean.getFullName());
				request.setAttribute("company", registerBean.getCompany());
				request.setAttribute("phoneNo", registerBean.getPhoneNo());
				request.setAttribute("password", registerBean.getPassword());
				request.setAttribute("profilePic", registerBean.getProfilePic());

				return "SUCCESS";

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Oops.. Something went wrong there..!"; // On failure, send a message from here.

	}

	public String updateProfilePic(String email, InputStream image, HttpServletRequest request) throws IOException {

		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = DBConnection.createConnection();  // Fetch database connection object
			// Update user password into the table 'users'
			String query = "update users set profilePic=? where email=?";
			// Making use of prepared statements here to update data
			preparedStatement = con.prepareStatement(query); // Statement is used to write queries.
			preparedStatement.setBlob(1, image);
			preparedStatement.setString(2, email);

			int i = preparedStatement.executeUpdate();

			if (i != 0) { // Just to ensure data has been updated into the database
				RegisterBean registerBean = getUserDetails(email);

				request.setAttribute("email", registerBean.getEmail());
				request.setAttribute("fullName", registerBean.getFullName());
				request.setAttribute("company", registerBean.getCompany());
				request.setAttribute("phoneNo", registerBean.getPhoneNo());
				request.setAttribute("password", registerBean.getPassword());
				request.setAttribute("profilePic", registerBean.getProfilePic());

				return "SUCCESS";

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Oops.. Something went wrong there..!"; // On failure, send a message from here.

	}

	public RegisterBean getUserDetails(String email) {

		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = DBConnection.createConnection();  // Fetch database connection object
			String getUserQuery = "select * from users where email=?";
			preparedStatement = con.prepareStatement(getUserQuery); // Statement is used to write queries.
			preparedStatement.setString(1, email);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) // Until next row is present otherwise it return false
			{
				RegisterBean registrationBean = new RegisterBean();

				registrationBean.setEmail(resultSet.getString("email")); // Assign result set values to registration bean.
				registrationBean.setFullName(resultSet.getString("fullName")); // Assign result set values to registration bean.
				registrationBean.setCompany(resultSet.getString("company")); // Assign result set values to registration bean.
				registrationBean.setPhoneNo(resultSet.getString("phoneNo")); // Assign result set values to registration bean.
				registrationBean.setPassword(resultSet.getString("password")); // Assign result set values to registration bean.
				if (null != resultSet.getBlob("profilePic")) {
					try {
						//converting blob to base64 browser readable string
						Blob blob = resultSet.getBlob("profilePic");
						InputStream inputStream = blob.getBinaryStream();
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[4096];
						int bytesRead = -1;

						while ((bytesRead = inputStream.read(buffer)) != -1) {
							outputStream.write(buffer, 0, bytesRead);
						}

						byte[] imageBytes = outputStream.toByteArray();
						String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);

						inputStream.close();
						outputStream.close();
						registrationBean.setProfilePic(base64Image);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				return registrationBean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
