<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dollars Bank App</title>
	
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	  rel="stylesheet" 
	  integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
	  crossorigin="anonymous">

</head>
<body>
<header class="bg-dark text-white pt-1">
	 <div class="container">
	 <div class="row">
	 	<div class="col-md-5">
	 		<p class="m-0">Welcome to Dollars Bank!</p>
	 	</div>
	 	<div class="col-md-5 text-center">
	 		<p class="m-0"><i class="bi bi-envelope"></i> dollarsbank@global.com</p>
	 	</div>
	 	
	 	<div class="col text-center">
	 		<p><i class="bi bi-telephone"></i>1 202 555 0191</p>
	 	</div>
	 </div>
	 	
    	
  	</div>
</header>
	<section class="vh-100" style="background-color: #eee;">
	  <div class="container">
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-lg-12 col-xl-11">
	        <div class="card text-black mt-5" style="border-radius: 25px;">
	          <div class="card-body p-md-5">
	            <div class="row justify-content-center">
	              <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
	
	                <p class="text-center h1 fw-bold mb-2 mx-1 mx-md-4 mt-4">Sign up</p>
					
	                <form class="mx-1 mx-md-2" method="POST" action="./RegistrationServlet">
	             
	                  <div class="d-flex flex-row align-items-center mb-2">
	                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
	                    <div class="form-outline flex-fill mb-0">
	                      <label class="form-label" for="form3Example1c">User Name</label>
	 
	                      <input type="text" id="form3Example1c" name="userName" class="form-control" />
	                      <span class="text-danger"><%= request.getAttribute("invalidUserId") %></span>
	                      
	                    </div>
	                  </div>
	
	                  <div class="d-flex flex-row align-items-center mb-2">
	                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
	                    <div class="form-outline flex-fill mb-0">
	                    	 <label class="form-label" for="form3Example3c">Your Email</label>
	                      <input type="email" id="form3Example3c" name="email" class="form-control" />
	                     
	                    </div>
	                  </div>
	
	                  <div class="d-flex flex-row align-items-center mb-2">
	                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
	                    <div class="form-outline flex-fill mb-0">
	                    	 <label class="form-label" for="form3Example4c">Password</label>
	                    	 <span class="text-danger"><%=  request.getAttribute("invalidPassword") %>
							</span>
	                      <input type="password" id="form3Example4c" name="password" class="form-control" />
	                     
	                    </div>
	                  </div>
	
	                  <div class="d-flex flex-row align-items-center mb-2">
	                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
	                    <div class="form-outline flex-fill mb-0">
	                    	 <label class="form-label" for="form3Example4cd">Repeat your password</label>
	                    	 
	                    	 <span class="text-danger" name="errorInvalidPassword"><%=  request.getAttribute("passwordMismatch") %></span>
	                      <input type="password" id="form3Example4cd" name="confirmPassword" class="form-control" />
	                     
	                    </div>
	                  </div>
	                  
	                  <div class="d-flex flex-row align-items-center mb-2">
	                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
	                    <div class="form-outline flex-fill mb-0">
	                    	<label class="form-label" for="form3Example4cde">Initial Deposit</label>
	                      <input type="number" min=1 id="form3Example4cde" name="initialDeposit" class="form-control" required />
	                      
	                    </div>
	                  </div>
					
	                  <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-2">
	                    <button type="submit" class="btn btn-primary btn-lg">Register</button>
	                  </div>
	
	                </form>
	
	              </div>
	             
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
</section>
<footer style="width:100%;"class="bg-dark text-center text-white py-2 ">
  <div class="container">
    <p class="m-0">Copyright &copy; Dollars Bank 2021</p>
  </div>
 </footer>




<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>

</body>

</html>