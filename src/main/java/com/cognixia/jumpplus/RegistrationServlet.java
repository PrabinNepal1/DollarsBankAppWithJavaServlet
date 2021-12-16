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


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	DollarsBankController bankCont = new DollarsBankController();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create a Bundle of errors in the form of Map
        //Map<String, String> errors = new HashMap<String, String>();
        
		//getParameter() will read all form data as string
		
		String email =  request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		double initialDeposit = Double.parseDouble(request.getParameter("initialDeposit"));
		
		
		
		if(!bankCont.validPassword(password)){
			request.setAttribute("invalidPassword", "Invalid Password");
			RequestDispatcher err = request.getRequestDispatcher("registration.jsp");
			err.include(request, response);
		}
		
		else if(!password.equals(confirmPassword)){
			request.setAttribute("passwordMismatch", "Confirm you password. They don't match");
			RequestDispatcher err = request.getRequestDispatcher("registration.jsp");
			err.include(request, response);
		}
		else if(!bankCont.validUserId(userName)) {
			request.setAttribute("invalidUserId", "Username already exist");
			RequestDispatcher err = request.getRequestDispatcher("registration.jsp");
			err.include(request, response);
			
		}
		else {
			
			Account account = bankCont.addInitialTransaction(initialDeposit, userName);
			Customer customer = new Customer(email, userName, password, account);
			bankCont.addCustomer(customer);
			bankCont.writeToFile();
			
			for(Customer c: bankCont.customerList) {
				System.out.println(c.getUserId());
			}

	 		response.sendRedirect("login.jsp");  
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
