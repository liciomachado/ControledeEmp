<%@page import="br.com.controlador.jdbc.dao.ObservacoesEmpresaDao"%>
<%@page import="br.com.controlador.jdbc.modelo.ObservacoesEmpresa"%>
<%@page import="br.com.controlador.jdbc.dao.EmpresaDao"%>
<%@page import="br.com.controlador.jdbc.modelo.Empresa"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="br.com.controlador.jdbc.dao.EmpenhoDao"%>
<%@page import="br.com.controlador.jdbc.modelo.Empenho"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% 	int numEmpenho = Integer.parseInt(request.getParameter("idEmpresa")); 

	Empenho emp = new Empenho();
	EmpenhoDao dao = new EmpenhoDao();
	List<Empenho> empList = dao.buscaPorIDSemNFparaEmpresa(numEmpenho);
	
	Double totalEmpenhado = dao.somaEmpenhadoEmpresa(numEmpenho);
	Locale ptBr = new Locale("pt", "BR");
	String valorString = NumberFormat.getCurrencyInstance(ptBr).format(totalEmpenhado);

	int mediaTempoEntregaPorEmpresa = dao.mediaEntregaPorEmpresa(numEmpenho);
	
	Empresa empresa = new Empresa();
	EmpresaDao daoEmpresa = new EmpresaDao();
	empresa = daoEmpresa.buscaPorId(numEmpenho);
	String nomeEmpresa = empresa.getNome();
	pageContext.setAttribute("empresa", empList);
	
	ObservacoesEmpresa obs = new ObservacoesEmpresa();
	ObservacoesEmpresaDao obsDao = new ObservacoesEmpresaDao();
	List<ObservacoesEmpresa> obsList = obsDao.getListaPeloId(numEmpenho);
	pageContext.setAttribute("obs", obsList);
%>

<c:import url="cabecalho.jsp" />

<div class="container">
	<div class="row">
		<div class="display-4 col-md-12"><%=nomeEmpresa%></div>
		</br>
		
		<span class="box2"> 
			<h3>Total Empenhado: <%=valorString%> </h3>
		</span>
		<span class="box2"> 
			<h3>Tempo médio de entrega: <%=mediaTempoEntregaPorEmpresa%> Dias</h3>
		</span>
	</div>
		<fieldset class="border p-2" id="observacoes">
			<legend class="w-auto">Observações sobre a empresa </legend>
			<c:forEach var="obs" items="${obs}">
			${obs.usuario.nome} -> <fmt:formatDate value="${obs.dataObs.time}" /> - ${obs.observacao} </br>
			</c:forEach>
			<div class="form-group" id="txtObs" style="display:none;">
			<form action="../salvaObservacaoempresa" method="post">
				<input hidden type="text" value="<%=numEmpenho%>" name="pegaIdEmpresa">
				<textarea name="pegaObs" class="form-control" id="exampleFormControlTextarea1" rows="2" for="salvaOBs"></textarea>
				<input class="btn btn-primary mb-2" type="submit" id="salvaOBs" value="Salvar">
			</form>
			</div>
			<div style="text-align: right;">
			<button id="adicionaObs" type="button" class="btn btn-default" aria-label="Left Align">
				<img alt="add" width="25" height="25" src="../img/add.png">
			</button>
			</div>
		</fieldset>
		</br>
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
				<c:forEach var="emp" items="${empresa}">
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
</br></br></br></br></br>
<c:import url="rodape.jsp" />