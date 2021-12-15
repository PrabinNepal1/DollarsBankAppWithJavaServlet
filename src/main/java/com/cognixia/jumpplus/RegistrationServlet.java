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
        Map<String, String> errors = new HashMap<String, String>();
        
		//getParameter() will read all form data as string
		
		String email =  request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		double initialDeposit = Double.parseDouble(request.getParameter("initialDeposit"));
		
		if(!bankCont.validPassword(password)){
			errors.put("invalidPassword", "Password must be 8 charachter long with at least one Uppercase, Symbol and Number");
		}
		
		if(!password.equals(confirmPassword)){
			errors.put("passwordMismatch", "Passwords do not match");
		}
		if(!bankCont.validUserId(email)) {
			errors.put("invalidUserId", "Username Already Exist");
			
		}
		if(errors.isEmpty()) {
			Account account = bankCont.addInitialTransaction(initialDeposit, userName);
			Customer customer = new Customer(email, userName, password, account);
			bankCont.addCustomer(customer);

	 		response.sendRedirect("login.jsp");  
	 		  
		}else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		}
	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
