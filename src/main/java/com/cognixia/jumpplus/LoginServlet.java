package com.cognixia.jumpplus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jumpplus.controller.DollarsBankController;
import com.cognixia.jumpplus.model.Account;
import com.cognixia.jumpplus.model.Customer;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	DollarsBankController bankCont = new DollarsBankController();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create a Bundle of errors in the form of Map
        //Map<String, String> errors = new HashMap<String, String>();
        
		//getParameter() will read all form data as string
		
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		if(!bankCont.checkLoginCreds(userName, password)) {
			request.setAttribute("error", "Invalid Credentials");
			RequestDispatcher err = request.getRequestDispatcher("login.jsp");
			err.include(request, response);
			
			for(Customer c: bankCont.customerList) {
			System.out.println(c.getUserId());
			}
			
		}
		else {
			request.setAttribute("user", userName);
			RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
			rd.forward(request, response);
			
			response.sendRedirect("user.jsp");
		}
	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

	

}
