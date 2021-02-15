package com.mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.mvc.bean.RegisterBean;
import com.mvc.dao.RegisterDao;
import com.mvc.util.ServletUtility;

public class UpdateProfileServlet extends HttpServlet {

	public UpdateProfileServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");

		RegisterBean registerBean = new RegisterBean();
		// Using Java Beans - An easiest way to play with group of related data
		registerBean.setFullName(request.getParameter("fullName"));
		registerBean.setEmail(request.getParameter("email"));
		registerBean.setPhoneNo(request.getParameter("phoneNo"));
		registerBean.setCompany(request.getParameter("company"));
		registerBean.setPassword(request.getParameter("password"));

		RegisterDao registerDao = new RegisterDao();
		String responseStr = registerDao.updateUser(email, registerBean, request);

		if (responseStr.equals("SUCCESS")) {
		} else {
			// On failure, you can display a error message to user on user profile page
			request.setAttribute("errMessage", responseStr);

		}
		request.getRequestDispatcher("/UserProfile.jsp").forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		RegisterDao registrationDao = new RegisterDao();
		RegisterBean registerBean = registrationDao.getUserDetails(email);

		if (registerBean != null) {
			request.setAttribute("email", registerBean.getEmail());
			request.setAttribute("fullName", registerBean.getFullName());
			request.setAttribute("company", registerBean.getCompany());
			request.setAttribute("phoneNo", registerBean.getPhoneNo());
			request.setAttribute("password", registerBean.getPassword());
			ServletUtility.forward("UpdateUserProfile.jsp", request, response); // Forward request to the
																				// UserProfile.jsp
		} else {
			request.setAttribute("errMessage", "User not found");
			ServletUtility.forward("UserProfile.jsp", request, response); // Forward request to the UserProfile.jsp
		}

	}

}