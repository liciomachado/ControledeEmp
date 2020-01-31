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
					<th scope="col">Enviado</th>
				</tr>
				<tr>
					<th scope="col"></th>
					<th scope="col"><form action="../filtroPendentes" method="get" class="form-inline my-2 my-lg-0">
										<input class="form-control mr-sm-2" type="search" name="empresa" placeholder="pesquisa por empresa" required aria-label="Search">
										<input hidden type="text" name="filtro" value="filtroEmpresa">
										<input hidden class="form-control mr-sm-2" type="submit" placeholder="Pesquisar empenho" aria-label="Search">
									</form>
					</th>					
					<th scope="col"><form action="../filtroPendentes" method="get" class="form-inline my-2 my-lg-0">
										<input class="form-control mr-sm-2" type="search" name="valor" placeholder="pesquisar por valor" required aria-label="Search">
										<input hidden type="text" name="filtro" value="filtroValor">
										<input hidden class="form-control mr-sm-2" type="submit" placeholder="Pesquisar empenho" aria-label="Search">
									</form>
					</th>	
					<th scope="col"><form action="../filtroPendentes" method="get" class="form-inline my-2 my-lg-0">
										<input class="form-control mr-sm-2" type="search" name="destino" placeholder="pesquisar por Destino" required aria-label="Search">
										<input hidden type="text" name="filtro" value="filtroDestino">
										<input hidden class="form-control mr-sm-2" type="submit" placeholder="Pesquisar empenho" aria-label="Search">
									</form>
					</th>					
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody  style="cursor: pointer">
				<c:forEach var="emp" items="${dao.listaEmpenhosPendentes}">
					<tr class="trRecebidos" data-url="detalheEmpenho.jsp?numEmpenho=${emp.numeroEmpenho}">
						<td><a href="../downloadPDF?numID=${emp.idEmpenho}">${emp.numeroEmpenho}</td>
						<td>${emp.empresa.nome}</td>
						<td>${emp.valorTotal}</td>
						<td>${emp.destino}</td>
						<td>Pendente Entrega</td>
						<td><fmt:formatDate value="${emp.dataEmpenho.time}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

<c:import url="rodape.jsp" />