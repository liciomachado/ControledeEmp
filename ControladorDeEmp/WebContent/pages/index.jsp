<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:import url="cabecalho.jsp" />

<div class="container">
	<div class="row">


		<a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Cadastrar Empenho</p>
			</span>
		</a> 
		<a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Cadastrar Nota Fiscal</p>
			</span>
		</a> <a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Protocolar</p>
			</span>
		</a> <a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Empenhos Pendentes</p>
			</span>
		</a> <a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Empenhos Recebidos</p>
			</span>
		</a> <a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Gerenciar Empenhos</p>
			</span>
		</a> <a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Ver Protocolos</p>
			</span>
		</a> 
		<a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Gerenciar Empresas</p>
			</span>
		</a> 
		<a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Gerenciar Nota Fiscal</p>
			</span>
		</a> 
		<a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>GMAIL</p>
			</span>
		</a> 
		<a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Site NFE</p>
			</span>
		</a> 
		<a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Configurações do Site</p>
			</span>
		</a> 
		

	</div>
</div>

</br></br></br></br></br></br></br>

<c:import url="rodape.jsp" />