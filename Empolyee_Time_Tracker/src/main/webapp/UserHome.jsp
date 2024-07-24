<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.model.User"%>
<%@ page import="com.model.Task"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserHome</title>
</head>
<body>
	<% if(session==null||session.getAttribute("user")==null){
	response.sendRedirect("Login.jsp");
	return;
}
	%>

	<%User user = (User) session.getAttribute("user"); %>
	<%String username = user.getName(); %>
	<h2>
		Welcome,
		<%=username %></h2>


	<button
		onclick="window.location.href='viewTask.jsp?username=<%=username %>'">View
		Task</button>
	<button
		onclick="window.location.href='addTask.jsp?username=<%=username %>'">Add
		Task</button>

</body>
</html>
