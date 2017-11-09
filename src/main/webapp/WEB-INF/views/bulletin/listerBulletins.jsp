<%@page import="org.springframework.context.event.ContextRefreshedEvent"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Remplacer la ligne du dessus par celle-ci pour dÃ©sativer le zoom -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> -->
<meta name="description" content="">
<meta name="author" content="">
<!-- Permet d'afficher un icÃ´ne dans la barre d'adresse -->
<!-- <link rel="shortcut icon" href="image/favicon.png"> -->
<title>Liste des bulletins</title>

<!-- css Bootstrap -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css"
	rel="stylesheet">

<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<!-- HTML5 Shim et Respond.js permet Ã  IE8 de supporter les Ã©lÃ©ments du HTML5 -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light"
		style="margin-left: 12%;">
		<a class="navbar-brand"
			href="<%=request.getContextPath()%>/mvc/employes/lister">Employé</a>
		<a class="navbar-brand"
			href="<%=request.getContextPath()%>/mvc/bulletin/lister">Bulletin</a>
	</nav>



	<div class="container">
		<!-- <a href="index.html"><i class="icon icon-arrow-left" aria-hidden="true" style="font-size:100px;color:black;"></i></a> -->
		<div class="text-center">
			<h1>Liste des Bulletin</h1>
		</div>

		<a href="<%=request.getContextPath()%>/mvc/bulletin/creer"
			class="btn btn-info" role="button"
			style="margin-bottom: 2%; margin-left: 84%;">Créer un nouveau
			bulletin</a>

		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th width="20%;">Date / Heure de création</th>
					<th>Période</th>
					<th>Matricule</th>
					<th>Salaire Brut</th>
					<th>Net Impossable</th>
					<th>Net A Payer</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${mapBull }" var="bull">
					<tr>
						<td>${bull.key.dateHeureCreation}</td>
						<td>${bull.key.periode.range }</td>
						<td>${bull.key.remunerationEmploye.matricule }</td>
						<td>${bull.value.salaireBrut }</td>
						<td>${bull.value.netImposable }</td>
						<td>${bull.value.netAPayer }</td>
						<td><a
							href="<%=request.getContextPath()%>/mvc/bulletin/visualiser/${bull.key.id}">Visualiser</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</body>

</html>