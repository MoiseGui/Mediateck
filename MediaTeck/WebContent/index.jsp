<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<link href="css/style.css" rel="stylesheet" id="bootstrap-css">
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<link rel="title icon" href="images/favicon.png">
    <title>Mediatech</title>
</head>
<body>
    
	<div class="wrapper fadeInDown">
	  <div id="formContent">
	    <!-- Tabs Titles -->
	
	    <!-- Icon -->
	    <div class="fadeIn first m-5">
	      <img src="images/logo.png" id="icon" alt="User Icon" />
	    </div>
	    
	     <div id="formHeader">
	      <%
			Object error = request.getAttribute("error");
			
			if (error != null) {
				String message = (String) request.getAttribute("errorMessage");
				out.print("<div class='alert alert-danger alert-dismissible fadeIn first'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Erreur: </strong>");
				out.print(message);
				out.print("</div>");
			}
			%>
	    </div>
	
	    <!-- Login Form -->
	    <form method="post" action="login">
	      <input type="text" id="login" class="fadeIn second" name="login" required placeholder="Nom d'utilisateur">
	      <input type="password" id="password" class="fadeIn third" name="pass" required placeholder="Mot de passe">
	      <input type="submit" class="fadeIn fourth" value="Log In">
	    </form>
		
	    <!-- Remind Passowrd -->
	    <div id="formFooter">
	      <a class="underlineHover" href="#">Mot de passe oublié ?</a>
	    </div>
	
	  </div>
	</div>
</body>
</html>


