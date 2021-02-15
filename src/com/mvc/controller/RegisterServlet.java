package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.RegisterBean;
import com.mvc.dao.RegisterDao;
import com.mvc.util.ServletUtility;

public class RegisterServlet extends HttpServlet {

	public RegisterServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Copying all the input parameters in to local variables
		String fullName = request.getParameter("fullname"); 
		String email = request.getParameter("email");
		String phoneNo = request.getParameter("phoneNo");
		String company = request.getParameter("company");
		String password = request.getParameter("password");

		RegisterBean registerBean = new RegisterBean();
		// Using Java Beans - An easiest way to play with group of related data
		registerBean.setFullName(fullName);
		registerBean.setEmail(email);
		registerBean.setPhoneNo(phoneNo);
		registerBean.setCompany(company);
		registerBean.setPassword(password);

		RegisterDao registerDao = new RegisterDao();

		// The core Logic of the Registration application is present here. We are going
		// to insert user data in to the database.
		String userRegistered = registerDao.registerUser(registerBean);

		if (userRegistered.equals("SUCCESS")) // On success, you can display a message to user on Home page
		{
			request.setAttribute("email", registerBean.getEmail());
			request.setAttribute("fullName", registerBean.getFullName());
			request.setAttribute("company", registerBean.getCompany());
			request.setAttribute("phoneNo", registerBean.getPhoneNo());
			request.setAttribute("password", registerBean.getPassword());
			request.setAttribute("profilePic", registerBean.getProfilePic());
			HttpSession session = request.getSession();
			session.setAttribute("email", registerBean.getEmail());
			request.getRequestDispatcher("/UserProfile.jsp").forward(request, response);
		} else if (userRegistered.equals("DUPLICATE")) {
			request.setAttribute("errMessage", "Email already exist.");
			request.getRequestDispatcher("/Register.jsp").forward(request, response);
		} else // On Failure, display a meaningful message to the User.
		{
			request.setAttribute("errMessage", userRegistered);
			request.getRequestDispatcher("/Register.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward("Register.jsp", request, response); //Forward request to the Register.jsp
	}
}