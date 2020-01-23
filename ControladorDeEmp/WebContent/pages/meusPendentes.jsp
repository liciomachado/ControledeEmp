<%@page import="br.com.controlador.jdbc.dao.EmpenhoDao"%>
<%@page import="br.com.controlador.jdbc.modelo.Empenho"%>
<%@page import="br.com.controlador.jdbc.modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.NotaFiscalDao"%>
<%@page import="br.com.controlador.jdbc.modelo.NotaFiscal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	Empenho emp = new Empenho();
	EmpenhoDao empDao = new EmpenhoDao();
	HttpSession sessao = request.getSession();
	Usuario usuario = (Usuario)sessao.getAttribute("UsuarioCompleto");
	List<Empenho> nfList = empDao.getListaMeusEmpenhosPendentes(usuario.getIdUsuario());
	pageContext.setAttribute("empsList", nfList);
%>
<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.EmpenhoDao" />
<div class="display-4">Meus Pendentes</div>
<table class="table table-hover">
	<thead class="thead-light">
		<tr>
			<th scope="col">Nº Empenho</th>
			<th scope="col">Empresa</th>
			<th scope="col">Valor</th>
			<th scope="col">Destino</th>
			<th scope="col">Situação</th>
			<th scope="col">Enviado dia</th>
		</tr>
	</thead>
	<tbody style="cursor: pointer">
		<c:forEach var="emp" items="${empsList}">
			<tr class="trRecebidos" data-url="detalheEmpenho.jsp?numEmpenho=${emp.numeroEmpenho}">
				<td><a href="../downloadPDF?numID=${emp.idEmpenho}">${emp.numeroEmpenho}</td>
				<td>${emp.empresa.nome}</td>
				<td>${emp.valorTotal}</td>
				<td>${emp.destino}</td>
				<td>Pendente Entrega</td>
				<td><fmt:formatDate value="${emp.dataEmpenho.time}" /></td>
				
		</c:forEach>
</table>
</br></br></br></br>
<c:import url="rodape.jsp" />