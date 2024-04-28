<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management Application</title>
<style type="text/css">
	<%@ include file="css/list.css"%>
</style>
</head>
<body>
	<div class="container">
		<h1>Employee Management Application</h1>
		<div class="add">
			<a href="<%=request.getContextPath() %>/new">Add New Employee</a>
		</div>
		<div class="card">
			<table class="table">
				<tr id="header">
					<th>SR</th>
					<th>Name</th>
					<th>Address</th>
					<th>Gender</th>
					<th>Salary</th>
					<th>Date of Birth</th>
					<th>Action</th>
				</tr>
				<!-- <c:set var="count" value="0" scope="page"/> -->
				<c:forEach var="list" items="${list}">
					<c:set var="count" value="${list.sr}" scope="page"/>
					<tr>
						<td><c:out value="${list.sr}"/></td>
						<td><c:out value="${list.name}"/></td>
						<td><c:out value="${list.address}"/></td>
						<c:if test="${list.gender == 1}"><td>Male</td></c:if>
						<c:if test="${list.gender == 2}"><td>Female</td></c:if>
						<td><c:out value="${list.salary}"/></td>
						<td><fmt:formatDate value="${list.birthdate}" pattern="dd/MM/yyyy"/></td>
					<td><a id='delete' href="delete?employeeId=<c:out value='${list.employeeId}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="add">
			<c:if test="${count > 5}">
				<c:set var="pre" value="${count - (count%5==0? 10 : count%5 + 5)}" scope="page"/>
				<a href="list?sr=<c:out value='${pre}'/>">Previous</a>
			</c:if>
			<c:if test="${count%5 == 0 && count > 0}">
				<a href="list?sr=<c:out value='${count}'/>">Next</a>
			</c:if>
		</div>
	</div>
</body>
</html>