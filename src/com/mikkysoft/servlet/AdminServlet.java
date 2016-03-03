package com.mikkysoft.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mikkysoft.controller.AdminController;
import com.mikkysoft.controller.UserController;
import com.mikkysoft.model.AccessType;
import com.mikkysoft.model.Circle;
import com.mikkysoft.model.User;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = UserController.getLoggedUser();
		String tableName = request.getParameter("table");
		if(user.getType().equals(AccessType.ADMIN)){
			AdminController controller = new AdminController();
			if(tableName.equals("circle")){
				List<Circle> circles = controller.getAllCircles();
				request.setAttribute("circles", circles);
				dispatchRequest(request, response);
			}
			
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	private void dispatchRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/jsp/circle_admin.jsp");
		dispatcher.include(request, response);
	}

}
