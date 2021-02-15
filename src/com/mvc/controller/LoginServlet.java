package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.LoginBean;
import com.mvc.dao.LoginDao;
import com.mvc.util.ServletUtility;

public class LoginServlet extends HttpServlet {

	public LoginServlet() // default constructor
	{

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Here email and password are the names in the input box in Login.jsp page. retrieving the values entered by the user and keeping in instance variables for further use.
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		LoginBean loginBean = new LoginBean(); // creating object for LoginBean class, which is a normal java class,
												// contains just setters and getters. Bean classes are efficiently used
												// in java to access user information wherever required in the
												// application.

		loginBean.setEmail(email); // setting the email and password through the loginBean object then only you
									// can get it in future.
		loginBean.setPassword(password);

		LoginDao loginDao = new LoginDao(); // creating object for LoginDao. This class contains main logic of the
											// application.

		String userValidate = loginDao.authenticateUser(loginBean, request); // Calling authenticateUser function

		if (userValidate.equals("SUCCESS")) // If function returns success string then user will be rooted to Home page
		{
			HttpSession session = request.getSession();
			session.setAttribute("email", email);

			request.setAttribute("email", email); // with setAttribute() you can define a "key" and value pair so
													// that you can get it in future using getAttribute("key")
			ServletUtility.forward("UserProfile.jsp", request, response); //Forward request to the UserProfile.jsp
		} else {
			request.setAttribute("errMessage", userValidate); // If authenticateUser() function returnsother than
																// SUCCESS string it will be sent to Login page again.
																// Here the error message returned from function has
																// been stored in a errMessage key.
			request.getRequestDispatcher("/Login.jsp").forward(request, response);// forwarding the request
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward("Login.jsp", request, response);  //Forward request to the Login.jsp
	}
}