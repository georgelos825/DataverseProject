<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<script>
	function validate() {
		var password = document.form.password.value;
		var conpassword = document.form.conpassword.value;

		if (!password
				.match(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%* #+=\(\)\^?&])[A-Za-z\d$@$!%* #+=\(\)\^?&]{8,}$/)) {
			alert("Password must contain atleast 8 characters, one lowercase or uppercase character, one special character and one number.");
			return false;
		} else if (password != conpassword) {
			alert("Confirm Password should match with the Password");
			return false;
		}
	}
</script>
</head>
<body>
	<center>
		<h2>Change password</h2>
	</center>
	<form name="form" action="ChangePasswordServlet" method="post"
		onsubmit="return validate()">
		<table align="center">
			<tr>
				<td>New Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>Confirm New Password</td>
				<td><input type="password" name="conpassword" /></td>
			</tr>
			<tr>
				<td><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Update"></input> <input
					type="reset" value="Reset"></input></td>
			</tr>
		</table>
	</form>
</body>
</html>