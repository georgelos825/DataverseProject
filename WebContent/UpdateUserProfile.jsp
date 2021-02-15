<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Profile</title>
<script>
	function validate() {
		var fullname = document.form.fullname.value;
		if (fullname == null || fullname == "") {
			alert("Full Name can't be blank");
			return false;
		}
	}
</script>
</head>
<body>
	<center>
		<h2>Update User Profile</h2>
	</center>
	<div style="text-align: right">
		<a href="LogoutServlet">Logout</a>
	</div>

	<form name="form" action="UpdateProfileServlet" method="post"
		onsubmit="return validate()">
		<table style="width: 50%; text-align: left; padding: 50px">
			<tr>
				<th>Full name</th>
				<td><input type="text" name="fullName" value="<%=request.getAttribute("fullName")%>"/></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><%=request.getAttribute("email")%></td>
			</tr>
			<tr>
				<th>Phone No</th>
				<td><input type="text" name="phoneNo"
					value="<%=request.getAttribute("phoneNo") == null ? "" : request.getAttribute("phoneNo")%>" /></td>
			</tr>
			<tr>
				<th>Company</th>
				<td><input type="text" name="company"
					value="<%=request.getAttribute("company") == null ? "" : request.getAttribute("company")%>" /></td>
			</tr>
			<tr>
			<th>Profile Pic</th>
			<td>
			
			<a href="ChangeProfilePicServlet">Update Profile Picture</a>
				<!-- <form method="post" action="ChangeProfilePicServlet"
					enctype="multipart/form-data">
					Select file to upload: <input type="file" name="uploadFile" /> <br />
					<br /> <input type="submit" value="Upload" />
				</form> -->
			</td>
		</tr>
			<tr>
				<th></th>
				<td><input type="submit" value="Update"></input></td>
			</tr>

		</table>
	</form>
</body>
</html>