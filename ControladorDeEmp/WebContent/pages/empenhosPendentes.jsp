<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.EmpenhoDao" />
<jsp:useBean id="dao2" class="br.com.controlador.jdbc.dao.ObservacoesDao" />


<div class="container">
	<div class="row">
		<div class="display-4">Empenhos pendentes</div>
		<br>
		<table class="table table-hover">
			<thead class="thead-light">
				<tr>
					<th scope="col">Nº Empenho</th>
					<th scope="col">Empresa</th>
					<th scope="col">Valor</th>
					<th scope="col">Destino</th>
					<th scope="col">Situação</th>
					<th scope="col">Enviado dia</th>
					<th scope="col">Obs:</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="emp" items="${dao.listaEmpenhosPendentes}">
					<tr>
						<td><a
							href="../downloadPDF?numID=${emp.idEmpenho}">${emp.numeroEmpenho}</td>
						<td>${emp.empresa.nome}</td>
						<td>${emp.valorTotal}</td>
						<td>${emp.destino}</td>
						<td>Pendente Entrega</td>
						<td><fmt:formatDate value="${emp.dataEmpenho.time}" /></td>
						<c:set var="test" value="${emp.idEmpenho}"/>
						<form action="detalheEmpenho.jsp" method="get">
						<input hidden type="text" value="${emp.numeroEmpenho}" name="numEmpenho">
						<td><button type="submit" class="btn btn-primary"
								data-toggle="modal" data-target="#ExemploModalCentralizado">
								Ver</button></td></a></form> 
					
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</br></br></br></br></br>
<c:import url="rodape.jsp" />