<%@page import="org.apache.shiro.session.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8" />
<!--<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1">-->
<link rel="stylesheet" href="../css/bootstrap4/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap4/css/estilos.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/select2/dist/css/select2.min.css">
<title>Gerenciador de Empenhos</title>
<link rel="shortcut icon" href="../img/iconOM.png" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo01"
			aria-controls="navbarTogglerDemo01" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
			<a class="navbar-brand" href="../pages/index.jsp"> <img
				src="../img/iconOM.png" width="30" height="30"
				class="d-inline-block align-top" alt=""> Controlador de
				Empenhos
			</a>
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				

			</ul>
			<form action="detalheEmpenho.jsp" method="get" class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search" name="numEmpenho" placeholder="Pesquisar empenho" aria-label="Search">
				<input hidden class="form-control mr-sm-2" type="submit" placeholder="Pesquisar empenho" aria-label="Search">
			</form>

			<ul class="nav nav-pills">
				<li class="nav-item dropdown">
				<a
					class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
						<img src="../img/user.png" width="30" height="30"
						class="d-inline-block align-top" alt=""> <%=session.getAttribute("usuario")%>
				</a>
				
					<div class="dropdown-menu">
						<a class="dropdown-item" href="gerenciamentoSite.php">Configurações do site</a> 
						<a class="dropdown-item" href="adicionaEmpenho.jsp">Envio de Empenhos</a> 
						<a class="dropdown-item" href="adicionaNF.jsp">Recebimento de NF</a> 
						<a class="dropdown-item" href="protocolo.jsp">Envio para pagamento</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="../logicaDeslogar">Sair </a>
					</div>
				</li>
			</ul>
		</div>
	</nav>