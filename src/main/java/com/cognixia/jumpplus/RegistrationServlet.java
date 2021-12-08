package com.cognixia.jumpplus;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jumpplus.controller.DollarsBankController;

public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//getParameter() will read all form data as string
		
		String email =  request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		double balance = Double.parseDouble(request.getParameter("initialDeposit"));
		
		if(DollarsBankController.validUserId(email)) {
			request.setAttribute("error", "Username Already Exist");
			request.getRequestDispatcher("/registration.html").forward(request, response);
		}
	
	}
}
