<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.EmpenhoDao" />
<jsp:useBean id="dao2" class="br.com.controlador.jdbc.dao.ObservacoesDao" />
<div class="container">
<div class="display-4">Empenhos pendentes</div>
<br>

<div class="table-responsive-md">
	<table id="dtBasicExample" class="table table-hover"
		cellspacing="0" width="100%">
		<thead>
			<tr>
				<th class="th-sm">Nº Empenho</th>
				<th class="th-sm">Empresa</th>
				<th class="th-sm">Valor</th>
				<th class="th-sm">Destino</th>
				<th class="th-sm">Situação</th>
				<th class="th-sm">Enviado</th>
			</tr>
		</thead>
		<tbody style="cursor: pointer">
			<c:forEach var="emp" items="${dao.listaEmpenhosPendentes}">
				<tr class="trRecebidos"
					data-url="detalheEmpenho.jsp?numEmpenho=${emp.numeroEmpenho}">
					<td><a href="../downloadPDF?numID=${emp.idEmpenho}">${emp.numeroEmpenho}</td>
					<td>${emp.empresa.nome}</td>
					<td>${emp.valorTotal}</td>
					<td>${emp.destino}</td>
					<td>Pendente</td>
					<td><fmt:formatDate value="${emp.dataEmpenho.time}" /></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th class="th-sm">Nº Empenho</th>
				<th class="th-sm">Empresa</th>
				<th class="th-sm">Valor</th>
				<th class="th-sm">Destino</th>
				<th class="th-sm">Situação</th>
				<th class="th-sm">Enviado</th>
			</tr>
		</tfoot>
	</table>
</div>
</div>
<c:import url="rodape.jsp" />