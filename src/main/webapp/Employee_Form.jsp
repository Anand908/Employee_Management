<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Form</title>
<style type="text/css">
  <%@ include file="css/form.css" %>
</style>
</head>
<body>
	<div class="container">
		<h1>Employee Management Application</h1>
		<hr>
		<div class="card">
			<div class="content">
				<h2>Register</h2>
				<form action="insert" method="post">
					<div class="inputBx">
						<input name="name" type="text" required="required" placeholder="Enter Name"/>
					</div>
					<div class="inputBx">
						<label>Gender</label>
						<div class="radiobutton">
							<input id="male" value="1" type="radio" name="gender" required="required"/>
							<label for="male">Male</label>
							<input id="female" value="2" type="radio" name="gender" required="required"/>
							<label for="female">Female</label>
						</div>				
					</div>
					<div class="inputBx">
						<input name="salary" required="required" placeholder="Enter Salary" maxlength="5" type="text" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
					</div>
					<div class="inputBx">
						<label>Date of Birth</label>
						<input name="birthdate" type="date" required="required" placeholder="Enter Your DOB"/>
					</div>
					<div class="inputBx">
						<textarea maxlength="150" name="address" required="required" placeholder="Enter Address" ></textarea>
					</div>
					<div class="inputBx">
						<input type="reset" value="Reset" />
						<input type="submit" value="Submit" />
					</div>
				</form>
			</div>	
		</div>
	</div>
</body>
</html>