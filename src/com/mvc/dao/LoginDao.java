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

import org.apache.commons.codec.binary.Base64;

import com.mvc.bean.LoginBean;
import com.mvc.util.DBConnection;
import com.mvc.util.ServletUtility;

public class LoginDao {
	public String authenticateUser(LoginBean loginBean, HttpServletRequest request) {
		String email = loginBean.getEmail(); // Assign user entered values to temporary variables.
		String password = loginBean.getPassword(); // Assign user entered values to temporary variables.

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String emailDB = "";
		String passwordDB = "";

		try {
			con = DBConnection.createConnection();  // Fetch database connection object
			statement = con.prepareStatement("select * from users where email=? and password=?");  // Statement is used to write queries.
			statement.setString(1, email);
			statement.setString(2, password);

			resultSet = statement.executeQuery(); // Fetching all the records and storing in a resultSet. 

			while (resultSet.next()) // Until next row is present otherwise it return false
			{
				emailDB = resultSet.getString("email"); // fetch the values present in database
				passwordDB = resultSet.getString("password"); 

				if (email.equals(emailDB) && password.equals(passwordDB)) { //checking the credentials
					request.setAttribute("email", resultSet.getString("email")); // Assign result set values to attribute
					request.setAttribute("fullName", resultSet.getString("fullName")); // Assign result set values to attribute
					request.setAttribute("company", resultSet.getString("company")); // Assign result set values to attribute
					request.setAttribute("phoneNo", resultSet.getString("phoneNo")); // Assign result set values to attribute
					request.setAttribute("password", resultSet.getString("password")); // Assign result set values to attribute
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
						String base64Image = new String(Base64.encodeBase64(imageBytes));
						System.out.println(base64Image);
						inputStream.close();
						outputStream.close();

						request.setAttribute("profilePic", base64Image);
					} catch (Exception e) {
						request.setAttribute("profilePic", ServletUtility.NO_IMAGE_FOUND);
					}

					return "SUCCESS"; //use successfully authenticated. 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Invalid user credentials"; // Return appropriate message in case of failure
	}

}