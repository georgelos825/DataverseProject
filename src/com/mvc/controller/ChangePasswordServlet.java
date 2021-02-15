package com.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.dao.RegisterDao;
import com.mvc.util.ServletUtility;

public class ChangePasswordServlet extends HttpServlet {

	public ChangePasswordServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String email = (String) session.getAttribute("email");  // Copying email input parameter in to local variable
		String password = request.getParameter("password"); // Copying password input parameter in to local variable

		RegisterDao registerDao = new RegisterDao();

		String responseStr = registerDao.updatePassword(email, password, request);

		if (!responseStr.equals("SUCCESS")) // On failure, you can display a error message to user on user profile page
		{
			request.setAttribute("errMessage", responseStr);
			request.getRequestDispatcher("/UserProfile.jsp").forward(request, response); //Forward request to the UserProfile.jsp
		}

		request.getRequestDispatcher("/UserProfile.jsp").forward(request, response); //Forward request to the UserProfile.jsp
	} 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setAttribute("email", session.getAttribute("email"));
		ServletUtility.forward("ChangePassword.jsp", request, response); //Forward request to the ChangePassword.jsp
	}
}