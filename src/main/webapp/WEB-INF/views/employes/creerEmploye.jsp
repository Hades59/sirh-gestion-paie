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
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> -->
<meta name="description" content="">
<meta name="author" content="">
<!-- Permet d'afficher un icÃ´ne dans la barre d'adresse -->
<!-- <link rel="shortcut icon" href="image/favicon.png"> -->
<title>Créer Employé</title>

<!-- css Bootstrap -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css"
	rel="stylesheet">

<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

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
		<a href="index.html"><i class="icon icon-arrow-left"
			aria-hidden="true" style="font-size: 100px; color: black;"></i></a>
		<div class="text-center" style="margin-top: -8%;">
			<h1>Ajouter un employé</h1>
		</div>
		<form:form modelAttribute="rem" class="form-horizontal" role="form"
			name="form" action="" method="post">
			<div class="form-group">
				<label for="inputMatricule" class="col-sm-7 control-label">Matricule</label>
				<div class="col-sm-10">
					<form:input path="matricule" type="text" name="matricule"
						class="form-control" id="matricule" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputEntreprise" class="col-sm-7 control-label">Entreprise</label>
				<div class="col-sm-10">
					<form:select path="entreprise" class="form-control">
						<option>Veuillez choisir l'entreprise</option>
						<form:options items="${listEntr}" itemValue="id"
							itemLabel="denomination" />
					</form:select>
				</div>
			</div>

			<div class="form-group">
				<label for="inputProfil" class="col-sm-7 control-label">Profil</label>
				<div class="col-sm-10">
					<form:select path="profilRemuneration" class="form-control">
						<option>Veuillez choisir le profil</option>
						<form:options items="${listProf}" itemValue="id" itemLabel="code" />
					</form:select>
				</div>
			</div>

			<div class="form-group">
				<label for="inputPassword1" class="col-sm-7 control-label">Grade</label>
				<div class="col-sm-10">
					<form:select path="grade" class="form-control">
						<option>Veuillez choisir le grade</option>
						<c:forEach items="${listGrad}" var="grad">
							<form:option value="${grad.id}"
								label="${grad.code } - ${grad.salaireAnnuel}/ans" />
						</c:forEach>
						<%-- 						<form:options items="${listGrad}" itemValue="id" itemLabel="code"/> --%>
					</form:select>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-20">
					<button type="submit" class="btn btn-info"
						style="margin-left: 54.5%;">
						<i class="icon icon-check icon-lg"></i> Ajouter
					</button>
				</div>
			</div>
		</form:form>

	</div>
</body>

</html>