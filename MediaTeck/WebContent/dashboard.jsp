<%@page import="beans.Facture"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="beans.ClientSAV"%>
<%@page import="java.util.List"%>
<%-- <%@page import="java.util.Date"%> --%>
<html lang="fr">

<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76" href="images/favicon.png">
<link rel="icon" type="image/png" href="images/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>SAV - Mediateck</title>
<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Style -->
<!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet" /> -->
<!-- <link href="assets/css/fresh-bootstrap-table.css" rel="stylesheet" /> -->
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.css">

<!-- Fonts and icons
  <link href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" rel="stylesheet">
  <link href="http://fonts.googleapis.com/css?family=Roboto:400,700,300" rel="stylesheet" type="text/css"> -->



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
			data-image="images/bg_img.jpg">
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
						<a class="navbar-brand" href="javascript:;">Tableau de bord.</a>
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
						<div class="col-md-12">
							<div class="card">
								<div class="card-header card-header-primary">
									<h4 class="card-title ">Liste des Factures</h4>
									<p class="display-inline card-category">Cette liste peut
										être filtrée à l'aide des outils présents ci-dessous.</p>
									<div class="pull-right">
										<a href="AddFacture" class="text-white"><h4><i
											class="fa fa-plus-circle"></i> Nouvelle facture
										</h4></a>
									</div>
								</div>
								<div class="card-body">


									<div class="fresh-table full-color-azure">

										<table id="fresh-table" class="table">
											<%
												List<Facture> factures = (List<Facture>) request.getAttribute("factures");

											if (factures == null || factures.isEmpty()) {
												out.print(
												"<div class='alert alert-warning alert-dismissible fadeIn first'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Erreur: </strong>");
												out.print("Aucune facture trouvée.");
												out.print("</div>");
											} else {
											%>
											<thead>
												<th data-field="id" data-sortable="true" class="text-center">Numéro</th>
												<th data-field="client" data-sortable="true"
													class="text-center">Client</th>
												<th data-field="date" data-sortable="true"
													class="text-center">Date</th>
												<th data-field="total" data-sortable="true"
													class="text-center">Total</th>
												<th data-field="actions" class="">Actions</th>
											</thead>
											<tbody id="myTable">

												<%
													for (Facture facture : factures) {
												%>
												<tr>
													<td><%=facture.getNum_fac()%></td>
													<td><%=facture.getClient().getPrenom() + ' ' + facture.getClient().getNom()%></td>
													<td><%=facture.getDate_fac()%></td>
													<td><%=facture.getTotal()%></td>
													<td>
													<div class="row">
														<a rel="tooltip" title="Modifier" class="table-action edit"
															href="FactureEdit?edit=<%=facture.getNum_fac()%>">
															<button type="submit" class="btn btn-white btn-round btn-just-icon">
								                                  <i class="fa fa-edit material-icons text-primary"></i>
								                                  <div class="ripple-container"></div>
								                              </button>
														</a>
								                        <form target="_blank" method="post" action="GetFacture">
								                           <input type="hidden" name="facture" value="<%=facture.getNum_fac()%>" >
								                              <button name="generer" type="submit" rel="tooltip" title="Générer la facture" class="btn btn-white btn-round btn-just-icon">
								                                  <i class="fa fa-file-pdf-o material-icons text-primary"></i>
								                                  <div class="ripple-container"></div>
								                              </button>
								                         </form>
													</div>
													
														
													</td>
												</tr>

												<%
													}
												}
												%>

											</tbody>
										</table>
									</div>
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
							document.write(new Date().getFullYear())
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
	<script
		src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>


	<script src="assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>

	<!--  Notifications Plugin    -->
	<script src="assets/js/plugins/bootstrap-notify.js"></script>

	<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
	<script src="assets/js/material-dashboard.js?v=2.1.2"
		type="text/javascript"></script>

	<script type="text/javascript">
		var $table = $('#fresh-table')

		$(function() {
			$table.bootstrapTable({
				classes : 'table table-hover',
				toolbar : '.toolbar',

				search : true,
				showRefresh : false,
				showToggle : true,
				showColumns : true,
				pagination : true,
				striped : true,
				sortable : true,
				pageSize : 4,
				pageList : [ 2, 4, 8, 10, 15, 20 ],

				formatShowingRows : function(pageFrom, pageTo, totalRows) {
					return 'Afficher'
				},
				formatRecordsPerPage : function(pageNumber) {
					return pageNumber + ' Factures'
				}
			})

		})
	</script>

	<script>
		$(document)
				.ready(
						function() {
							$("#tableSearch")
									.on(
											"keyup",
											function() {
												var value = $(this).val()
														.toLowerCase();
												$("#myTable tr")
														.filter(
																function() {
																	$(this)
																			.toggle(
																					$(
																							this)
																							.text()
																							.toLowerCase()
																							.indexOf(
																									value) > -1)
																});
											});
						});
	</script>

	<%
		if (request.getAttribute("userError") != null && request.getAttribute("userNo") != null) {
		Integer code = (Integer) request.getAttribute("userNo");
		int errorCode = code.intValue();

		if (errorCode == 1) {
			// 			System.out.print(errorCode);
	%>
	<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
	<script type="text/javascript">
		md
				.showNotification('top', 'right', 3,
						'Vos informations personnelles ont été enregistrées avec succès.');
	</script>
	<%
		} else {
	%>
	<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
	<script type="text/javascript">
		// 	alert("i am here");
		md
				.showNotification(
						'top',
						'right',
						2,
						'Erreur: un problème est survenu lors du changement de vos information de compte. Veuillez réessayer plutard.');
	</script>
	<%
		}
	}
	%>
	
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
		} else {
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
	
	<%
		if (request.getAttribute("factureError") != null && request.getAttribute("factureNo") != null) {
		Integer code = (Integer) request.getAttribute("factureNo");
		int errorCode = code.intValue();

		if (errorCode < 0) {
// 			System.out.print(errorCode);
	%>
	<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
	<script type="text/javascript">
		md.showNotification('top', 'right', 2,
						'Un problème est survenu lors de la sauvegarde des changements sur la facture.');
	</script>
	<%
		} else if (errorCode > 0) {
	%>
	<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
	<script type="text/javascript">
		// 	alert("i am here");
		md.showNotification('top', 'right', 3,
				'Modifications enregistrées avec succès.');
	</script>
	<%
		}
	}
	%>

</body>

</html>