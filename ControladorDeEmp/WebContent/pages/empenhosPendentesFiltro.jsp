<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.EmpenhoDao" />

<div class="container">
	<div class="row">
		<div class="display-4"><a href="${pageContext.request.contextPath}/pages/empenhosPendentes.jsp">Empenhos Pendentes</a></div>
		<br>
		<table class="table table-hover">
			<thead class="thead-light">
				<tr>
					<th scope="col">Nº Empenho</th>
					<th scope="col">Empresa</th>
					<th scope="col">Valor</th>
					<th scope="col">Destino</th>
					<th scope="col">Situação</th>
					<th scope="col">Enviado</th>
					<th scope="col">Obs:</th>
				</tr>
				<tr>
					<th scope="col">
					</th>
					<th scope="col"><form action="${pageContext.request.contextPath}/filtroPendentes" method="get" class="form-inline my-2 my-lg-0">
										<input class="form-control mr-sm-2" type="search" name="empresa" placeholder="pesquisa por empresa" required aria-label="Search">
										<input hidden type="text" name="filtro" value="filtroEmpresa">
										<input hidden class="form-control mr-sm-2" type="submit" placeholder="Pesquisar empenho" aria-label="Search">
									</form>
					</th>					
					<th scope="col"><form action="${pageContext.request.contextPath}/filtroPendentes" method="get" class="form-inline my-2 my-lg-0">
										<input class="form-control mr-sm-2" type="search"  name="valor" placeholder="pesquisar por valor" required aria-label="Search">
										<input hidden type="text" name="filtro" value="filtroValor">
										<input hidden class="form-control mr-sm-2" type="submit" placeholder="Pesquisar empenho" aria-label="Search">
									</form>
					</th>	
					<th scope="col"><form action="${pageContext.request.contextPath}/filtroPendentes" method="get" class="form-inline my-2 my-lg-0">
										<input class="form-control mr-sm-2" type="search" name="destino" placeholder="pesquisar por Destino" required aria-label="Search">
										<input hidden type="text" name="filtro" value="filtroDestino">
										<input hidden class="form-control mr-sm-2" type="submit" placeholder="Pesquisar empenho" aria-label="Search">
									</form>
					</th>					
					<th scope="col"></th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="emp" items="${empenhos}">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/downloadPDF?numID=${emp.idEmpenho}">${emp.numeroEmpenho}</td>
						<td>${emp.empresa.nome}</td>
						<td>${emp.valorTotal}</td>
						<td>${emp.destino}</td>
						<td>Pendente Entrega</td>
						<td><fmt:formatDate value="${emp.dataEmpenho.time}" /></td>
						<c:set var="test" value="${emp.idEmpenho}"/>
						<form action="${pageContext.request.contextPath}/pages/detalheEmpenho.jsp" method="get">
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