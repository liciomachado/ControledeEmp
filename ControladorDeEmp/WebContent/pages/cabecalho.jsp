<%@page import="br.com.controlador.jdbc.modelo.Usuario"%>
<%@page import="org.apache.shiro.session.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%

HttpSession sessao = request.getSession();

//Usuario usuario = (Usuario)sessao.getAttribute("UsuarioCompleto");

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8" />
<!--<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1">-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap4/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap4/css/estilos.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/select2/dist/css/select2.min.css">
<title>Gerenciador de Empenhos</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/iconOM.png" />
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
			<a class="navbar-brand" href="${pageContext.request.contextPath}/pages/index.jsp"> <img
				src="${pageContext.request.contextPath}/img/iconOM.png" width="30" height="30"
				class="d-inline-block align-top" alt=""> Controlador de
				Empenhos
			</a>
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active">
			        <a class="nav-link" href="${pageContext.request.contextPath}/pages/meusEmpenhos.jsp">Meu Menu <span class="sr-only">(current)</span></a>
		      </li>

			</ul>
			<jsp:useBean id="dao56" class="br.com.controlador.jdbc.dao.EmpenhoDao" />
			
			<form action="${pageContext.request.contextPath}/pages/detalheEmpenho.jsp" method="get" class="form-inline my-2 my-lg-0">
				<%--<input class="form-control mr-sm-2" type="search" name="numEmpenho" id="numEmpenho" placeholder="Pesquisar empenho" aria-label="Search"> --%>
				<select class="js-example-basic-single form-control col-md-12" onchange="this.form.submit()" id="inputEmp2" name="numEmpenho">
							<option value="">Pesquisar Empenho</option>
							<c:forEach var="emp" items="${dao56.listaNumerosDeEmpenho}">
								<option value="${emp.numeroEmpenho}">${emp.numeroEmpenho}</option>
							</c:forEach>
				</select>
				<input hidden class="form-control mr-sm-2" type="submit" placeholder="Pesquisar empenho" aria-label="Search">
			</form>

			<ul class="nav nav-pills">
				<li class="nav-item dropdown">
				<a
					class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
						<img src="${pageContext.request.contextPath}/img/user.png" width="30" height="30"
						class="d-inline-block align-top" alt=""> <%=sessao.getAttribute("usuario_grad")+" "+sessao.getAttribute("usuario")%>
				</a>
				
					<div class="dropdown-menu">
						<a class="dropdown-item" href="gerenciamentoSite.php">Sobre</a> 
						<a class="dropdown-item" href="adicionaEmpenho.jsp">Envio de Empenhos</a> 
						<a class="dropdown-item" href="adicionaNF.jsp">Recebimento de NF</a> 
						<a class="dropdown-item" href="protocolo.jsp">Envio para pagamento</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/logicaDeslogar">Sair </a>
					</div>
				</li>
			</ul>
		</div>
	</nav>