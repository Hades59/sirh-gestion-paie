<%@page import="org.springframework.context.event.ContextRefreshedEvent"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Remplacer la ligne du dessus par celle-ci pour dÃ©sativer le zoom -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> -->
<meta name="description" content="">
<meta name="author" content="">
<!-- Permet d'afficher un icÃ´ne dans la barre d'adresse -->
<!-- <link rel="shortcut icon" href="image/favicon.png"> -->
<title>Créer Bulletin</title>


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
		<a class="navbar-brand" href="<%=request.getContextPath()%>/mvc/employes/lister">Employé</a> <a
			class="navbar-brand" href="<%=request.getContextPath()%>/mvc/bulletin/lister">Bulletin</a>
	</nav>



	<div class="container">
		<a href="index.html"><i class="icon icon-arrow-left"
			aria-hidden="true" style="font-size: 100px; color: black;"></i></a>
		<div class="text-center" style="margin-top: -8%;">
			<h1>Ajouter un bulletin</h1>
		</div>
		<form:form modelAttribute="formBull" class="form-horizontal"
			role="form" name="form" action="" method="post">
			<div class="form-group">
				<label for="inputPeriode" class="col-sm-7 control-label">Période</label>
				<div class="col-sm-10">
					<form:select path="periode" class="form-control">
						<option>Veuillez choisir la période</option>
						<c:forEach items="${listPer}" var="per">
							<form:option value="${per.id}" label="${per.range}" />
						</c:forEach>
					</form:select>
				</div>
			</div>

			<div class="form-group">
				<label for="inputMatricule" class="col-sm-7 control-label">Matricule</label>
				<div class="col-sm-10">
					<form:select path="remunerationEmploye" class="form-control">
						<option>Veuillez choisir le matricule</option>
						<c:forEach items="${listRem}" var="rem">
							<form:option value="${rem.id}" label="${rem.matricule }" />
						</c:forEach>
					</form:select>
				</div>
			</div>

			<div class="form-group">
				<label for="inputPrime" class="col-sm-7 control-label">Prime Exceptionnelle</label>
				<div class="col-sm-10">
					<form:input type="text" path="primeExceptionnelle" name="prime" class="form-control" id="prime"/>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-20">
					<button type="submit" class="btn btn-info"
						style="margin-left: 54.5%;">
						<i class="icon icon-check icon-lg"></i> Ajouter	</button>
				</div>
			</div>
		</form:form>

	</div>
</body>

</html>