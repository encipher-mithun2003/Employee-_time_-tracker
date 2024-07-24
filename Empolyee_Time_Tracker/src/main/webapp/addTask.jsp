<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% String username = request.getParameter("username"); %>


	<form action="addTaskServlet" method="post">

		<input type="hidden" name="username" value=<%=username %>> <label
			for="role">Role:</label> <input name="role" id="role" type="text"
			required><br>
		<br> <label for="project">Project:</label> <input name="project"
			id="project" type="text" required><br>
		<br> <label for="date">Date:</label> <input name="date" id="date"
			type="date" required><br>
		<br> <label for="StartTime">Start Time:</label> <input
			type="time" name="StartTime" id="StartTime" required><br>
		<br> <label for="EndTime">End Time:</label> <input name="EndTime"
			id="EndTime" type="time" required><br>
		<br> <label for="taskCategory">Task Category:</label> <input
			name="taskCategory" id="taskCategory" type="text" required><br>
		<br> <label for="description">Description:</label> <input
			name="description" id="description" type="text" required><br>
		<br>

		<button type="submit">Submit</button>
		<br>
		<br>

	</form>

	<% String error = request.getParameter("error"); %>

	<% if(error!=null && !error.isEmpty()) { %>
	<%=error %>
	<%} %>

</body>
</html>