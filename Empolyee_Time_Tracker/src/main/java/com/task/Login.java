package com.task;
import com.DAO.UserDAO;
import com.model.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String roleId = request.getParameter("roleId");
		
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		user.setRoleId(roleId);
		request.setAttribute("user",user);
		
		
		UserDAO userDAO = new UserDAO();
		
		if(roleId.equals("1")) {
			if(userDAO.validateUser(username,password,roleId)) {
				HttpSession session=request.getSession();
				session.setAttribute("user",user);
				response.sendRedirect("UserHome.jsp");
				/*
				 * RequestDispatcher dispatcher = request.getRequestDispatcher("UserHome.jsp");
				 * dispatcher.forward(request, response);
				 */
				//response.sendRedirect("UserHome.jsp");
			}
			else {
				response.sendRedirect("UserLogin.jsp?message=Check given details!");
			}
		}
		else {
			if(userDAO.validateAdmin(username,password,roleId)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHome.jsp");
				dispatcher.forward(request, response);
				//response.sendRedirect("AdminHome.jsp");
			}
			else {
				response.sendRedirect("UserLogin.jsp?message=Check given details!");
			}
		}
		
	}

}
