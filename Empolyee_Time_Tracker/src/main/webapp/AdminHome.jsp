<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.model.User"%>
<%@ page import="com.DAO.UserDAO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home</title>
</head>

<% UserDAO userDAO = new UserDAO(); %>

<% List<User> employees = userDAO.getAllEmployees();  %>

<body>

	<table border="1">

		<thead>
			<tr>
				<th>Name</th>
				<th>Role</th>
				<th>Mail</th>
				<th>Role Id</th>
			</tr>
		</thead>

		<tbody>

			<% for(User employee : employees){ %>

			<tr>
				<td><%=employee.getName() %></td>
				<td><%=employee.getRole() %></td>
				<td><%=employee.getEmail() %></td>
				<td><%=employee.getRoleId() %></td>
				<td><a href="viewEmpoyeeTasks.jsp?name=<%=employee.getName()%>">View
						Task</a></td>
			</tr>
			<% } %>
		</tbody>

	</table>

	<br>
	<br>

	<button onclick="window.location.href='addUser.jsp'">Add User</button>
	<button onclick="window.location.href='Login.jsp'">Log Out</button>

</body>
</html>