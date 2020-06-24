<%@page import="beans.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@page import="beans.Client"%>
<%@page import="java.util.List"%>
<html lang="fr">

<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76" href="images/favicon.png">
<link rel="icon" type="image/png" href="images/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Clients - Mediateck</title>
<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!--     Fonts and icons     -->
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<!-- CSS Files -->
<link href="assets/css/material-dashboard.css?v=2.1.2" rel="stylesheet" />

</head>

<body class="">
	<div class="wrapper ">
		<div class="sidebar" data-color="purple" data-background-color="white"
			data-image="../assets/img/sidebar-1.jpg">
			<div class="logo">
				<a href="#" class="simple-text logo-normal"> <img class="w-75"
					src="images/logo.png" alt="Mediateck">
				</a>
			</div>
			<div class="sidebar-wrapper">
				<ul class="nav">
					<li class="nav-item"><a class="nav-link" href="Admin"> <i
							class="material-icons">dashboard</i>
							<p>Accueil</p>
					</a></li>
					<li class="nav-item active"><a class="nav-link" href="Clients">
							<i class="material-icons">contacts</i>
							<p>Clients</p>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="Produits">
							<i class="material-icons">table_chart</i>
							<p>Produits</p>
					</a></li>
					<li class="nav-item "><a class="nav-link" href="Commandes">
							<i class="material-icons">content_paste</i>
							<p>Commandes</p>
					</a></li>
					<li class="nav-item "><a class="nav-link" href="Users"> <i
							class="material-icons">people</i>
							<p>Utilisateurs</p>
					</a></li>
					<li class="nav-item "><a class="nav-link" href="User"> <i
							class="material-icons">person</i>
							<p>Mon compte</p>
					</a></li>
					<li class="nav-item "><a class="nav-link" href="Logout"> <i
							class="material-icons">login</i>
							<p>Quitter</p>
					</a></li>
				</ul>
			</div>
		</div>
		<div class="main-panel">
			<!-- Navbar -->
			<nav
				class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
				<div class="container-fluid">
					<div class="navbar-wrapper">
						<a class="navbar-brand" href="javascript:;">Nouveau client</a>
					</div>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						aria-controls="navigation-index" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="sr-only">Toggle navigation</span> <span
							class="navbar-toggler-icon icon-bar"></span> <span
							class="navbar-toggler-icon icon-bar"></span> <span
							class="navbar-toggler-icon icon-bar"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-end">
						<form class="navbar-form">
							<div class="input-group no-border">
								<input type="text" value="" class="form-control"
									placeholder="Search..." id="tableSearch">
								<button type="button"
									class="btn btn-white btn-round btn-just-icon">
									<i class="material-icons">search</i>
									<div class="ripple-container"></div>
								</button>
							</div>
						</form>
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link" href="javascript:;">
									<i class="material-icons">dashboard</i>
									<p class="d-lg-none d-md-block">Stats</p>
							</a></li>
							<li class="nav-item dropdown"><a class="nav-link" href="#"
								id="navbarDropdownMenuLink" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <i
									class="material-icons">notifications</i> <span
									class="notification">5</span>
									<p class="d-lg-none d-md-block">Notifications</p>
							</a>
								<div class="dropdown-menu dropdown-menu-right"
									aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="#">Mike John responded to
										your email</a> <a class="dropdown-item" href="#">You have 5
										new tasks</a> <a class="dropdown-item" href="#">You're now
										friend with Andrew</a> <a class="dropdown-item" href="#">Another
										Notification</a> <a class="dropdown-item" href="#">Another One</a>
								</div></li>
							<li class="nav-item dropdown"><a class="nav-link"
								href="User" id="navbarDropdownProfile" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <i
									class="material-icons">person</i>
									<p class="d-lg-none d-md-block">Mon compte</p>
							</a>
								<div class="dropdown-menu dropdown-menu-right"
									aria-labelledby="navbarDropdownProfile">
									<a class="dropdown-item" href="User">Mon compte</a>
									<!--                   <a class="dropdown-item" href="#">Settings</a> -->
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="Logout">Quitter</a>
								</div></li>
						</ul>
					</div>
				</div>
			</nav>
			<!-- End Navbar -->
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						
						<div class="col-md-8">
							<div class="card">
								<div class="card-header card-header-primary">
									<h4 class="card-title">Ajouter un client</h4>
									<p class="card-category">Mettez les informations du nouveau client
										ci-dessous</p>
								</div>
								<div class="card-body">
									<form method="post" action="AddClient"
										class="needs-validation" novalidate>
									<input type="hidden" name="id">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="bmd-label-floating">Nom</label> <input
														type="text" name="nom"
														class="form-control" required>
													<div class="valid-feedback">Correct.</div>
													<div class="invalid-feedback">Veillez saisir un nom.</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="bmd-label-floating">Prénom</label> <input
														type="text" name="prenom"
														class="form-control" required>
													<div class="valid-feedback">Correct.</div>
													<div class="invalid-feedback">Veillez saisir un
														prénom.</div>
												</div>
											</div>
										</div>
										<input type="button"
											class="btn btn-warning pull-left" onclick="javascript:location.href='Clients'" value="Retour" />
										<input type="submit" name="ajouter"
											class="btn btn-primary pull-right" value="Ajouter" />
										<div class="clearfix"></div>
									</form>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<footer class="footer">
				<div class="container-fluid">
					<nav class="float-left">
						<ul>
							<li><a href="#"> Mediateck </a></li>

						</ul>
					</nav>
					<div class="copyright float-right">
						&copy;
						<script>
							document
									.write(new Date().getFullYear())
						</script>
						, made with <i class="material-icons">favorite</i> by <a
							href="https://www.moisegui.com" target="_blank">Moïse Gui</a> And
						<a href="#">Ezaghab Chaimaa</a> for a better web.
					</div>
				</div>
			</footer>
		</div>
	</div>


	<!-- Javascript -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<!--  Notifications Plugin    -->
	<script src="assets/js/plugins/bootstrap-notify.js"></script>

	<script src="assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>


	<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
	<script src="assets/js/material-dashboard.js?v=2.1.2"
		type="text/javascript"></script>

	<script>
		// Disable form submissions if there are invalid fields
		(
				function() {
					'use strict';
					window.addEventListener('load', function() {
						// Get the forms we want to add validation styles to
						var forms = document
								.getElementsByClassName('needs-validation');
						// Loop over them and prevent submission
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
										if (form.checkValidity() === false) {
											event.preventDefault();
											event.stopPropagation();
										}
										form.classList.add('was-validated');
									}, false);
								});
					}, false);
				})();
	</script>
	

	<%
		if (request.getAttribute("NewError") != null && request.getAttribute("NewNo") != null) {
		Integer code = (Integer) request.getAttribute("NewNo");
		int errorCode = code.intValue();

		if (errorCode == 1) {
// 			System.out.print(errorCode);
	%>
	<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
	<script type="text/javascript">
		md.showNotification('top', 'right', 3,
						'Nouveau client enregistré avec succès.');
	</script>
	<%
		} else {
	%>
	<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
	<script type="text/javascript">
		// 	alert("i am here");
		md.showNotification('top', 'right', 2,
				'Erreur: un problème est survenu lors de l\'ajout du nouveau client. Veuillez réessayer plutard.');
	</script>
	<%
		}
	}
	%>

</body>

</html>