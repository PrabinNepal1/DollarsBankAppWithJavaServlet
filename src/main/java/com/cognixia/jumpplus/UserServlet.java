package com.cognixia.jumpplus;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jumpplus.controller.DollarsBankController;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	DollarsBankController bankCont = new DollarsBankController();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = bankCont.currentCustomer.getUserId();
		String email = bankCont.currentCustomer.getEmail();
		String balannce = String.valueOf(bankCont.currentCustomer.getAccount().getBalance());
		
		request.setAttribute("name", name);
		request.setAttribute("email", name);
		request.setAttribute("balance", name);
		RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
		rd.include(request, response);
		
	}

}
