package com.task;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.UserDAO;
import com.model.User;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String EmployeeName = request.getParameter("username");
		String Role = request.getParameter("role");
		String Mail = request.getParameter("mail");
		String Password = request.getParameter("password");
		String RoleId = "1";
		
		User user = new User();
		UserDAO userDAO = new UserDAO();
		
		user.setName(EmployeeName);
		user.setRole(Role);
		user.setEmail(Mail);
		user.setPassword(Password);
		user.setRoleId(RoleId);
		
		try {
			userDAO.addUser(user);
			response.sendRedirect("addUser.jsp?message=Employee Registered Successfully!");
		}
		catch(SQLException e) {
			e.printStackTrace();
			response.sendRedirect("addUser.jsp?message=?Something gone wrong!");
		}
		
	}

}
