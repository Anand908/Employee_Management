<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Delete Employee</title>
<style type="text/css">
	<%@ include file = "css/delete.css"  %>
</style>
</head>
<body>
	<div class="container">
        <div class="card">
            <c:forEach var="employee" items="${employee}">
                <div class="details">
                        <div class="content">
                            <h3>Employee Id</h3>
                            <h3><c:out value="${employee.employeeId}"/></h3>
                        </div>
                        <div class="content">
                            <h3>Name</h3>
                            <h3><c:out value="${employee.name}"/></h3>
                        </div>
                        <div class="content">
                            <h3>Gender</h3>
                            <c:if test="${employee.gender == 1}"><h3>Male</h3></c:if>
                            <c:if test="${employee.gender == 2}"><h3>Female</h3></c:if>
                        </div>
                        <div class="content">
                            <h3>DOB</h3>
                            <h3><fmt:formatDate value="${employee.birthdate}" pattern="dd/MM/yyyy"/></h3>
                        </div>
                        <div class="content">
                            <h3>Address</h3>
                            <h3><c:out value="${employee.address}"/></h3>
                        </div>
                        <div class="content">
                            <h3>Salary</h3>
                            <h3><c:out value="${employee.salary}"/></h3>
                        </div>
                </div>
                <h3>Do you want to Delete this Employee</h3>
                <div class="button">
                    <a href="<%=request.getContextPath() %>/list">Cancel</a>
                    <a id='delete' href="deleteData?employeeId=<c:out value='${employee.employeeId}' />">Delete</a>
                </div>
            </c:forEach>
        </div>
	</div>       
</body>
</html>