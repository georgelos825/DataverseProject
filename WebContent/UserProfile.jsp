<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Profile</title>

</head>
<body>
	<center>
		<h2>User Profile</h2>
	</center>
	<div style="text-align: right">
		<a href="LogoutServlet">Logout</a>
	</div>

	Welcome
	<%=request.getAttribute("fullName")%>, you have just logged in.
	<!-- Refer to the video to understand how this works -->


	<table style="width: 50%; text-align: left; padding: 50px">
		<tr>
			<th>Full name</th>
			<td><%=request.getAttribute("fullName")%>
		</tr>
		<tr>
			<th>Email</th>

			<td><%=request.getAttribute("email")%></td>
		</tr>
		<tr>
			<th>Phone No</th>
			<td><%=request.getAttribute("phoneNo") == null ? "" : request.getAttribute("phoneNo")%></td>
		</tr>
		<tr>
			<th>Company</th>
			<td><%=request.getAttribute("company") == null ? "" : request.getAttribute("company")%></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><a href="ChangePasswordServlet"> Change Password</a></td>
		</tr>
		<tr>
			<th>Profile Pic</th>
			<td><img
				src="data:image/jpg;base64,<%=request.getAttribute("profilePic")%> "
				width="150" height="150" /></td>
		</tr>
		<tr>
			<th></th>
			<td><a href="UpdateProfileServlet">Update Profile</a></td>
		</tr>

	</table>
</body>
</html>