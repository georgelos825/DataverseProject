<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<script> 
var recaptchaWidget;
var onloadCallback = function() {
    recaptchaWidget = grecaptcha.render('google-recaptcha', {
      'sitekey' : '6LdStVgaAAAAAGBMyqbYgqIDFXcjEWFDd4-0AUHH'
    });
  };

function validate()
{ 
	var recaptchaErrorMessage = document.getElementById('recaptcha-error');
	 var recaptchaResponse = grecaptcha.getResponse(recaptchaWidget);
	 
	 if(recaptchaResponse == '' || recaptchaResponse == null || recaptchaResponse == undefined){
		 recaptchaErrorMessage.style.display = "block";
		 return false;
	 }else{
		 recaptchaErrorMessage.style.display = "none";
	 }
	
     var fullname = document.form.fullname.value;
     var email = document.form.email.value;
     var phoneNo = document.form.phoneNo.value; 
     var password = document.form.password.value;
     var conpassword= document.form.conpassword.value;
     if (fullname==null || fullname=="")
     { 
     alert("Full Name can't be blank"); 
     return false; 
     }
     else if (email==null || email=="")
     { 
     alert("Email can't be blank"); 
     return false; 
     }
     else if(!password.match(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%* #+=\(\)\^?&])[A-Za-z\d$@$!%* #+=\(\)\^?&]{8,}$/))
     { 
     alert("Password must contain atleast 8 characters, one lowercase or uppercase character, one special character and one number."); 
     return false; 
     } 
     else if (password!=conpassword)
     { 
     alert("Confirm Password should match with the Password"); 
     return false; 
     } 
 } 
 
 function resetCaptcha(){
	 var recaptchaErrorMessage = document.getElementById('recaptcha-error');
	 recaptchaErrorMessage.style.display = "none";
	 grecaptcha.reset(recaptchaWidget);
 }

</script> 
</head>
<body>
<center><h2>Dataverse user registration</h2></center>
    <form name="form" action="RegisterServlet" method="post" onsubmit="return validate()">
        <table align="center">
         <tr>
         <td>Full Name</td>
         <td><input type="text" name="fullname" /></td>
         </tr>
         <tr>
         <td>Email</td>
         <td><input type="text" name="email" /></td>
         </tr>
         <tr>
         <td>Phone No</td>
         <td><input type="text" name="phoneNo" /></td>
         </tr>
         <tr>
         <td>Company</td>
         <td><input type="text" name="company" /></td>
         </tr>
         <tr>
         <td>Password</td>
         <td><input type="password" name="password" /></td>
         </tr>
         <tr>
         <td>Confirm Password</td>
         <td><input type="password" name="conpassword" /></td>
         </tr>
         <tr>
         <td><%=(request.getAttribute("errMessage") == null) ? ""
         : request.getAttribute("errMessage")%></td>
         </tr>
         <tr>
         <td></td>
         <td>
         	<div id="recaptcha-error" style="display:none;color:red">Please verify that you are not a robot.</div>
         	<div id="google-recaptcha"></div>
         </td>
<!--          data-sitekey="6LdStVgaAAAAAGBMyqbYgqIDFXcjEWFDd4-0AUHH" -->
         </tr>
         <tr>
         <td></td>
         <td><input type="submit" value="Register"></input><input
         type="reset" value="Reset" onclick="resetCaptcha();"></input>
         <a href="LoginServlet">Login</a></td>
         </tr>
        </table>
    </form>
    
    <!-- <script src="https://www.google.com/recaptcha/api.js" async defer></script> -->
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
    async defer>
</script>
</body>
</html>
