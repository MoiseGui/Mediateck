<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<link rel="apple-touch-icon" sizes="76x76" href="images/favicon.png">
<link rel="icon" type="image/png" href="images/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />
<!--     Fonts and icons     -->
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
<link href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="assets/css/fresh-bootstrap-table.css" rel="stylesheet" />
<link href="assets/css/demo.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<!-- CSS Files -->
<link href="assets/css/material-dashboard.css?v=2.1.2" rel="stylesheet" />
<title>Admin - Mediateck</title>
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
					<li class="nav-item active  "><a class="nav-link" href="Admin">
							<i class="material-icons">dashboard</i>
							<p>Accueil</p>
					</a></li>
					<li class="nav-item "><a class="nav-link" href="Clients">
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
								<input type="text" class="form-control" id="tableSearch"
									placeholder="Chercher un client">
								<button type="submit"
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
									<p class="card-category">Vous pouvez filtrer la liste
										depuis le champs de recherche</p>
								</div>
								<div class="card-body">
									<div class="fresh-table full-color-azure">
										<table id="fresh-table" class="table">
											<thead>
												<th data-field="id">ID</th>
												<th data-field="name">Name</th>
												<th data-field="salary">Salary</th>
												<th data-field="country">Country</th>
												<th data-field="city">City</th>
												<th data-field="actions" data-formatter="operateFormatter"
													data-events="operateEvents">Actions</th>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>Dakota Rice</td>
													<td>$36,738</td>
													<td>Niger</td>
													<td>Oud-Turnhout</td>
													<td></td>
												</tr>
												<tr>
													<td>2</td>
													<td>Minerva Hooper</td>
													<td>$23,789</td>
													<td>Curaçao</td>
													<td>Sinaai-Waas</td>
													<td></td>
												</tr>
												<tr>
													<td>3</td>
													<td>Sage Rodriguez</td>
													<td>$56,142</td>
													<td>Netherlands</td>
													<td>Baileux</td>
													<td></td>
												</tr>
												<tr>
													<td>4</td>
													<td>Philip Chaney</td>
													<td>$38,735</td>
													<td>Korea, South</td>
													<td>Overland Park</td>
													<td></td>
												</tr>
												<tr>
													<td>5</td>
													<td>Doris Greene</td>
													<td>$63,542</td>
													<td>Malawi</td>
													<td>Feldkirchen in Kärnten</td>
													<td></td>
												</tr>
												<tr>
													<td>6</td>
													<td>Mason Porter</td>
													<td>$78,615</td>
													<td>Chile</td>
													<td>Gloucester</td>
													<td></td>
												</tr>
												<tr>
													<td>7</td>
													<td>Alden Chen</td>
													<td>$63,929</td>
													<td>Finland</td>
													<td>Gary</td>
													<td></td>
												</tr>
												<tr>
													<td>8</td>
													<td>Colton Hodges</td>
													<td>$93,961</td>
													<td>Nicaragua</td>
													<td>Delft</td>
													<td></td>
												</tr>
												<tr>
													<td>9</td>
													<td>Illana Nelson</td>
													<td>$56,142</td>
													<td>Heard Island</td>
													<td>Montone</td>
													<td></td>
												</tr>
												<tr>
													<td>10</td>
													<td>Nicole Lane</td>
													<td>$93,148</td>
													<td>Cayman Islands</td>
													<td>Cottbus</td>
													<td></td>
												</tr>
												<tr>
													<td>11</td>
													<td>Chaim Saunders</td>
													<td>$5,502</td>
													<td>Romania</td>
													<td>New Quay</td>
													<td></td>
												</tr>
												<tr>
													<td>12</td>
													<td>Josiah Simon</td>
													<td>$50,265</td>
													<td>Christmas Island</td>
													<td>Sint-Jans-Molenbeek</td>
													<td></td>
												</tr>
												<tr>
													<td>13</td>
													<td>Ila Poole</td>
													<td>$67,413</td>
													<td>Montenegro</td>
													<td>Pontevedra</td>
													<td></td>
												</tr>
												<tr>
													<td>14</td>
													<td>Shana Mejia</td>
													<td>$58,566</td>
													<td>Afghanistan</td>
													<td>Ballarat</td>
													<td></td>
												</tr>
												<tr>
													<td>15</td>
													<td>Lana Ryan</td>
													<td>$64,151</td>
													<td>Martinique</td>
													<td>Portobuffolè</td>
													<td></td>
												</tr>
												<tr>
													<td>16</td>
													<td>Daquan Bender</td>
													<td>$91,906</td>
													<td>Sao Tome and Principe</td>
													<td>Walhain-Saint-Paul</td>
													<td></td>
												</tr>
												<tr>
													<td>17</td>
													<td>Connor Wagner</td>
													<td>$86,537</td>
													<td>Germany</td>
													<td>Solihull</td>
													<td></td>
												</tr>
												<tr>
													<td>18</td>
													<td>Boris Horton</td>
													<td>$35,094</td>
													<td>Laos</td>
													<td>Saint-Mard</td>
													<td></td>
												</tr>
												<tr>
													<td>19</td>
													<td>Winifred Ryan</td>
													<td>$64,436</td>
													<td>Ireland</td>
													<td>Ronciglione</td>
													<td></td>
												</tr>
											</tbody>
										</table>
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
								<li><a href="#"> Mediateck </a>
								</li>
							</ul>
						</nav>
						<div class="copyright float-right">
							&copy;
							<script>
								document.write(new Date().getFullYear())
							</script>
							, made with <i class="material-icons">favorite</i> by <a
								href="https://www.moisegui.com" target="_blank">Moïse Gui</a>
							And <a href="#">Ezaghab Chaimaa</a> for a better web.
						</div>
					</div>
				</footer>
			</div>
		</div>
		<!--   Core JS Files   -->
		<script src="assets/js/core/jquery.min.js"></script>
		<script src="assets/js/core/popper.min.js"></script>
		<script src="assets/js/core/bootstrap-material-design.min.js"></script>
		<script src="assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
		<!-- Plugin for the momentJs  -->
		<script src="assets/js/plugins/moment.min.js"></script>
		<!--  Plugin for Sweet Alert -->
		<script src="assets/js/plugins/sweetalert2.js"></script>
		<!-- Forms Validations Plugin -->
		<script src="assets/js/plugins/jquery.validate.min.js"></script>
		<!-- Plugin for the Wizard, full documentation here: https://github.com/VinceG/twitter-bootstrap-wizard -->
		<script src="assets/js/plugins/jquery.bootstrap-wizard.js"></script>
		<!--	Plugin for Select, full documentation here: http://silviomoreto.github.io/bootstrap-select -->
		<script src="assets/js/plugins/bootstrap-selectpicker.js"></script>
		<!--  Plugin for the DateTimePicker, full documentation here: https://eonasdan.github.io/bootstrap-datetimepicker/ -->
		<script src="assets/js/plugins/bootstrap-datetimepicker.min.js"></script>
		<!--  DataTables.net Plugin, full documentation here: https://datatables.net/  -->
		<script src="assets/js/plugins/jquery.dataTables.min.js"></script>
		<!--	Plugin for Tags, full documentation here: https://github.com/bootstrap-tagsinput/bootstrap-tagsinputs  -->
		<script src="assets/js/plugins/bootstrap-tagsinput.js"></script>
		<!-- Plugin for Fileupload, full documentation here: http://www.jasny.net/bootstrap/javascript/#fileinput -->
		<script src="assets/js/plugins/jasny-bootstrap.min.js"></script>
		<!--  Full Calendar Plugin, full documentation here: https://github.com/fullcalendar/fullcalendar    -->
		<script src="assets/js/plugins/fullcalendar.min.js"></script>
		<!-- Vector Map plugin, full documentation here: http://jvectormap.com/documentation/ -->
		<script src="assets/js/plugins/jquery-jvectormap.js"></script>
		<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
		<script src="assets/js/plugins/nouislider.min.js"></script>
		<!-- Include a polyfill for ES6 Promises (optional) for IE11, UC Browser and Android browser support SweetAlert -->
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
		<!-- Library for adding dinamically elements -->
		<script src="assets/js/plugins/arrive.min.js"></script>
		<!-- Chartist JS -->
		<script src="assets/js/plugins/chartist.min.js"></script>
		<!--  Notifications Plugin    -->
		<script src="assets/js/plugins/bootstrap-notify.js"></script>
		<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
		<script src="assets/js/material-dashboard.js?v=2.1.2"
			type="text/javascript"></script>
		
		<!-- Javascript -->

<script src="js/bootstrap.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>

<script type="text/javascript">
  var $table = $('#fresh-table')
  var $alertBtn = $('#alertBtn')

  window.operateEvents = {
    'click .like': function (e, value, row, index) {
      alert('You click like icon, row: ' + JSON.stringify(row))
      console.log(value, row, index)
    },
    'click .edit': function (e, value, row, index) {
      alert('You click edit icon, row: ' + JSON.stringify(row))
      console.log(value, row, index)
    },
    'click .remove': function (e, value, row, index) {
      $table.bootstrapTable('remove', {
        field: 'id',
        values: [row.id]
      })
    }
  }

  function operateFormatter(value, row, index) {
    return [
      '<a rel="tooltip" title="Like" class="table-action like" href="javascript:void(0)" title="Like">',
        '<i class="fa fa-heart"></i>',
      '</a>',
      '<a rel="tooltip" title="Edit" class="table-action edit" href="javascript:void(0)" title="Edit">',
        '<i class="fa fa-edit"></i>',
      '</a>',
      '<a rel="tooltip" title="Remove" class="table-action remove" href="javascript:void(0)" title="Remove">',
        '<i class="fa fa-remove"></i>',
      '</a>'
    ].join('')
  }

  $(function () {
    $table.bootstrapTable({
      classes: 'table table-hover table-striped text-white',
      toolbar: '.toolbar',

      search: true,
      showRefresh: true,
      showToggle: true,
      showColumns: true,
      pagination: true,
      striped: true,
      sortable: true,
      pageSize: 8,
      pageList: [8, 10, 25, 50, 100],

      formatShowingRows: function (pageFrom, pageTo, totalRows) {
        return ''
      },
      formatRecordsPerPage: function (pageNumber) {
        return pageNumber + ' clients visible'
      }
    })

  })

</script>
		
		
</body>
</html>