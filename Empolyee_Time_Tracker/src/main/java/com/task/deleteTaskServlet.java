package com.task;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.TaskDAO;

/**
 * Servlet implementation class deleteTaskServlet
 */
@WebServlet("/deleteTaskServlet")
public class deleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		TaskDAO taskDAO = new TaskDAO();
		
		try {
			taskDAO.deleteTask(id);
			response.sendRedirect("UserHome.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


