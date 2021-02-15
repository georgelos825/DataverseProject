<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Profile</title>
</head>
<body>
	<center>
		<h2>Update Profile Picture</h2>
	</center>
	<div style="text-align: right">
		<a href="LogoutServlet">Logout</a>
	</div>

		<form name="form" action="ChangeProfilePicServlet" method="post"
		enctype="multipart/form-data">
		<table style="width: 50%; text-align: left; padding: 50px">
			<tr>
				<th>Profile Pic</th>
				<td>Select file to upload: <input type="file" name="uploadFile" />
					<br />
				</td>
			</tr>
			<tr>
				<!-- refer to the video to understand request.getAttribute() -->
				<td><span style="color: red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>
			</tr>
			<tr>
				<th></th>
				<td><input type="submit" value="Upload" /></td>
			</tr>

		</table>
	</form>
</body>
</html>