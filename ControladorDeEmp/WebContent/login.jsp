<%@page import="javax.swing.text.StyledEditorKit.BoldAction"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% if(session.getAttribute("senhaIncorreta") == null){
	session.setAttribute("senhaIncorreta", false); 
	}
	boolean incorreto = Boolean.valueOf(session.getAttribute("senhaIncorreta").toString());%>

<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=Comfortaa"
	rel="stylesheet">
<link href="css/styleLogin.css" rel="stylesheet">
<link rel="shortcut icon" href="img/iconOM.png" />
</head>
<body>
	<div class="login">
		<img src="img/user.png" class="usuario" width="100" height="100"
			alt="">
		<h1>Login</h1>
		<form method="POST" action="logicaLogin">

			<p>Usuario</p>
			<input type="text" name="usuario"
				placeholder="Insira seu nome de usuario">

			<p>Senha</p>
			<input type="password" name="senha" placeholder="Insira sua senha">
			
			<% if(incorreto == true){%>
				<input name="erroSenha" class="senhaincorreta" value="LOGIN E/OU SENHA INCORRETOS">
			<%} %>

			<input type="submit" name="logaUsuario" value="Entrar">
			<a href="cadastro.jsp" style="color: white; text-align: center;">Cadastre-se agora</a>
		</form>
	</div>
</body>
</html>
