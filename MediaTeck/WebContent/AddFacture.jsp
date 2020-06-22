<%@page import="beans.Ligne_facture"%>
<%@page import="java.time.LocalDate"%>
<%@page import="utils.DateUtil"%>
<%@page import="beans.Facture"%>
<%@page import="beans.Produit"%>
<%@page import="beans.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@page import="beans.Client"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
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



<%
	Integer value = (Integer) session.getAttribute("newFac");
int i = value.intValue();

boolean newFac = false;
if (i == 1)
	newFac = true;

Facture facture = (Facture) session.getAttribute("FactureAdd");
%>



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
					<li class="nav-item active"><a class="nav-link"
						href="Dashboard"> <i class="material-icons">dashboard</i>
							<p>Accueil</p>
					</a></li>
					<li class="nav-item "><a class="nav-link" href="User"> <i
							class="material-icons">person</i>
							<p>Mon compte</p>
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
						<a class="navbar-brand" href="javascript:;">Nouvelle facture</a>
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
									<a class="dropdown-item" href="#">Mon compte</a>
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
					<form method="post" action="AddFacture" class="needs-validation"
						novalidate>
						<div class="row">

							<div class="col-md-6">
								<div class="card">
									<div class="card-header card-header-primary">
										<h4 class="card-title">Ajouter une facture</h4>
										<p class="card-category">Remplissez les champs ci-dessous</p>
									</div>
									<div class="card-body">
										<input type="hidden" name="id">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="bmd-label-floating">Date </label> <input
														type="date" name="date" class="form-control"
														value="<%if (newFac)
	out.print(DateUtil.format(LocalDate.now()));
else
	out.print(facture.getDate_fac());%>"
														required>
													<div class="valid-feedback">Correct.</div>
													<div class="invalid-feedback">Veillez choisir une
														date.</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="bmd-label-floating">Client</label> <select
														name="client" class="form-control" required>
														<option></option>
														<%
															List<Client> clients = (List<Client>) request.getAttribute("clients");

														for (Client client : clients) {
														%>
														<option value="<%=client.getId()%>"
															<%if (!newFac && facture.getClient().getId() == client.getId())
	out.print("selected");%>>
															<%=client.getPrenom()%>
															<%=client.getNom()%>
														</option>
														<%
															}
														%>
													</select>
													<div class="valid-feedback">Correct.</div>
													<div class="invalid-feedback">Veillez choisir un
														client.</div>
												</div>
											</div>
										</div>
										<input type="button" class="btn btn-warning pull-left"
											onclick="javascript:location.href='Clients'" value="Retour" />
										<div class="clearfix"></div>

									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="card">
									<div class="card-header card-header-primary">
										<h4 class="card-title">Ajouter un produit</h4>
										<p class="card-category">Veillez choisir le produit puis
											la quantité commandée.</p>
									</div>
									<div class="card-body">
										<input type="hidden" name="id">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="bmd-label-floating">Produit</label> <select
														name="produit" class="form-control" required>
														<option></option>
														<%
															List<Produit> produits = (List<Produit>) request.getAttribute("produits");

														for (Produit produit : produits) {
														%>
														<option value="<%=produit.getId()%>">
															<%=produit.getDesignation()%> :
															<%=produit.getQte_stock()%> disponibles
														</option>
														<%
															}
														%>
													</select>
													<div class="valid-feedback">Correct.</div>
													<div class="invalid-feedback">Veillez choisir un
														produit.</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="bmd-label-floating">Quantité</label> <input
														type="number" min="1" name="qte" class="form-control"
														required>
													<div class="valid-feedback">Correct.</div>
													<div class="invalid-feedback">Veillez entrer une
														quantité valide.</div>
												</div>
											</div>
										</div>

										<input type="submit" name="ajouter"
											class="btn btn-primary pull-right" value="Ajouter" />
										<div class="clearfix"></div>
									</div>
								</div>
							</div>

							<div class="col-lg-12 col-md-12">
								<div class="card">
									<div class="card-header card-header-primary">
										<h4 class="card-title">Liste des produits</h4>
									</div>
									<div class="card-body table-responsive">
										<table class="table table-hover">
											<thead>
												<th class="text-center">Produit</th>
												<th class="text-center">Prix unitaire</th>
												<th class="text-center">Quantité</th>
												<th class="text-center">Total</th>
												<th class="text-center">Actions</th>
											</thead>
											<%
												if (newFac || facture.getLigne_factures() == null || facture.getLigne_factures().isEmpty()) {
												out.print(
												"<div class='alert alert-warning alert-dismissible fadeIn first'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Info: </strong>");
												out.print("Aucun produit ajouté.");
												out.print("</div>");
											} else {
												for (Ligne_facture ligne_facture : facture.getLigne_factures()) {
											%>

											<tbody>
												<tr>
													<td class="text-center"><%=ligne_facture.getProduit().getDesignation()%></td>
													<td class="text-center"><%=ligne_facture.getProduit().getPrix()%></td>
													<td class="text-center"><%=ligne_facture.getQte_achete()%></td>
													<td class="text-center"><%=ligne_facture.getProduit().getPrix() * ligne_facture.getQte_achete()%></td>
													<td class="td-actions text-center"><a
														href="AddFacture?remove=<%=ligne_facture.getProduit().getId()%>"><button
																type="button" rel="tooltip" title="Retirer"
																class="btn btn-danger btn-link btn-sm">
																<i class="material-icons">close</i>
															</button></a></td>
												</tr>
											</tbody>
											<%
												}
											}
											%>
										</table>
					</form>
					<form method="post" action="AddFacture">
						<h3 class="pull-left">
							Total :
							<%=facture.getTotal()%>&nbsp;
						</h3>
						<input type="submit" name="enregistrer"
							class="btn btn-success pull-right"
							style="background-color: green" value="Enregistrer"
							<%if (newFac || facture.getLigne_factures() == null || facture.getLigne_factures().isEmpty())
	out.print("disabled");%> />
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
					href="https://www.moisegui.com" target="_blank">Moïse Gui</a> And <a
					href="#">Ezaghab Chaimaa</a> for a better web.
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
				'Nouvelle facture enregistrée avec succès.');
	</script>
	<%
		}
		else if(errorCode == 20001){
			%>
			<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
			<script type="text/javascript">
				// 	alert("i am here");
				md
						.showNotification(
								'top',
								'right',
								2,
								'Erreur 20001 : la quantité en stock de ce produit est insuffisante pour en acheter autant.');
			</script>
			<%
		}
		else {
	%>
	<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
	<script type="text/javascript">
		// 	alert("i am here");
		md
				.showNotification(
						'top',
						'right',
						2,
						'Erreur: un problème est survenu lors de l\'ajout de la facture. Veuillez réessayer plutard.');
	</script>
	<%
		}
	}
	%>

</body>

</html>