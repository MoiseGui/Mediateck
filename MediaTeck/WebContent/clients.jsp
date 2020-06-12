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
				</ul>
			</div>
		</div>
		<div class="main-panel">
			<!-- Navbar -->
			<nav
				class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
				<div class="container-fluid">
					<div class="navbar-wrapper">
						<a class="navbar-brand" href="javascript:;">Clients</a>
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
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="card-header card-header-primary">
									<h4 class="card-title ">Liste des clients</h4>
									<p class="display-inline card-category">Cette liste peut
										être filtrée à l'aide des outils présents ci-dessous.</p>
									<div class="pull-right">
										<a href="AddClient" class="text-white"><h4><i
											class="fa fa-plus-circle"></i> Nouveau client
										</h4></a>
									</div>
								</div>
								<div class="card-body">


									<div class="fresh-table full-color-azure">

										<table id="fresh-table" class="table">
											<%
												List<Client> clients = (List<Client>) session.getAttribute("clients");

											if (clients == null || clients.isEmpty()) {
												out.print(
												"<div class='alert alert-warning alert-dismissible fadeIn first'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Erreur: </strong>");
												out.print("Aucun client trouvé.");
												out.print("</div>");
											} else {
											%>
											<thead>
												<th data-field="id" data-sortable="true" class="text-center">Numéro</th>
												<th data-field="nom" data-sortable="true"
													class="text-center">Nom</th>
												<th data-field="prenom" data-sortable="true"
													class="text-center">Prénom</th>
												<th data-field="actions" data-formatter="operateFormatter"
													data-events="operateEvents" class="text-center">Actions</th>
											</thead>
											<tbody id="myTable">

												<%
													for (Client client : clients) {
												%>
												<tr>
													<td><%=client.getId()%></td>
													<td><%=client.getNom()%></td>
													<td><%=client.getPrenom()%></td>
													<td></td>
												</tr>
												<!-- The Modal -->
												<div class="modal" id="myModal<%=client.getId()%>">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<h4 class="modal-title">Confirmer la suppression</h4>
																<button type="button" class="close" data-dismiss="modal">&times;</button>
															</div>
															<div class="modal-body">
																Voulez vous vraiment supprimer le client
																<%=client.getPrenom()%>
																<%=client.getNom()%>
																?
															</div>
															<div class="modal-footer">
																<!-- 																<a class="text-white" -->
																<%-- 																	href="Clients?cli=<%=client.getId()%>"> --%>
																<button type="button" class="btn btn-success"
																	data-dismiss="modal"
																	onClick="javascript:location.href = 'Clients?cli=<%=client.getId()%>';">Oui</button>
																<!-- 																</a> -->
																<button type="button" class="btn btn-danger"
																	data-dismiss="modal">Annuler</button>
															</div>
														</div>
													</div>
												</div>
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

		window.operateEvents = {
		// 			'click .edit' : function(e, value, row, index) {
		// 				alert('You click edit icon, row id: ' + row['id'])
		// 				console.log(value, row, index)
		// 			},
		// 			'click .remove' : function(e, value, row, index) {
		// 				$table.bootstrapTable('remove', {
		// 					field : 'id',
		// 					values : [ row.id ]
		// 				})
		// 			}
		}

		function operateFormatter(value, row, index) {
			return [
					'<a rel="tooltip" title="Edit" class="table-action edit" href="ClientEdit?edit='
							+ row['id'] + '" title="Edit">',
					'<i class="fa fa-edit"></i>',
					'</a>',
					'<a rel="tooltip" title="Remove" class="table-action remove" href="#" title="Remove" data-toggle="modal" data-target="#myModal'+ row['id'] + '">',
					'<i class="fa fa-remove"></i>', '</a>' ].join('')
		}

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
				pageSize : 5,
				pageList : [ 3, 5, 8, 10, 15, 20 ],

				formatShowingRows : function(pageFrom, pageTo, totalRows) {
					return 'Afficher'
				},
				formatRecordsPerPage : function(pageNumber) {
					return pageNumber + ' Clients'
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
		if (request.getAttribute("error") != null && request.getAttribute("errorNo") != null) {
		Integer code = (Integer) request.getAttribute("errorNo");
		int errorCode = code.intValue();

		if (errorCode < 0) {
			System.out.print(errorCode);
	%>
	<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
	<script type="text/javascript">
		md.showNotification('top', 'right', 2,
				'Un problème est survenu lors de la supression du client.');
	</script>
	<%
		} else if (errorCode > 0) {
	%>
	<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
	<script type="text/javascript">
		// 	alert("i am here");
		md.showNotification('top', 'right', 3, 'Client supprimé avec succès.');
	</script>
	<%
		}
	}
	%>

	<%
		if (request.getAttribute("editError") != null && request.getAttribute("editNo") != null) {
		Integer code = (Integer) request.getAttribute("editNo");
		int errorCode = code.intValue();

		if (errorCode < 0) {
// 			System.out.print(errorCode);
	%>
	<!--type = ['', 'info', 'danger', 'success', 'warning', 'rose', 'primary']; -->
	<script type="text/javascript">
		md.showNotification('top', 'right', 2,
						'Un problème est survenu lors de la sauvegarde des changements sur le client.');
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