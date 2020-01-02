<%@page import="br.com.controlador.jdbc.dao.EmpresaDao"%>
<%@page import="br.com.controlador.jdbc.modelo.Empresa"%>
<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	Empresa empresa = new Empresa();
	empresa.setIdEmpresa(0);
	empresa.setNome("");
	empresa.setEmail("");
	empresa.setContato("");
	EmpresaDao daoEmp = new EmpresaDao();
	
	if(request.getParameter("idEmpresa") != null){
		int id = Integer.parseInt(request.getParameter("idEmpresa"));
		empresa = daoEmp.buscaPorId(id);
		//pageContext.setAttribute("empresa", empresa);	
	}

%>

<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.EmpresaDao" />

<div class="container">
	<div class="row formulario">
		<div class="display-4">Gerenciamento de empresas</div>
		<br>
			<form action="../adicionaEmpresa" method="post">
			<input hidden type="text" value="<%= empresa.getIdEmpresa()%>" name="idEmpresa">
				<div class="form-row" style="text-transform: uppercase;">
					<div class="form-group col-md-4">
						<label for="nomeEmpresa">Nome Empresa </label> <input
							type="text" class="form-control" id="nomeEmpresa"
							placeholder="" name="nomeEmpresa" required="" value="<%= empresa.getNome()%>">
					</div>
					<div class="form-group col-md-4">
						<label for="numTelefone">Telefone </label> <input
							type="text" class="form-control" id="numTelefone" pattern="(^[\d-\)\(]+$)"
							placeholder="" name="numTelefone" value="<%= empresa.getContato() %>">
					</div>
					<div class="form-group col-md-4">
						<label for="nomeEmail">Email </label> <input
							type="text" class="form-control" id="nomeEmail"
							placeholder="" name="nomeEmail" required="" value="<%= empresa.getEmail() %>">
					</div>
					
					<div class="col-lg-12" style="text-align: right;">
					<% if(empresa.getIdEmpresa() != 0) { %>
						<input hidden type="text" value="atualiza" name="acao">
						<button type="submit" class="btn btn-primary mb-2">Atualizar</button>
					<% }else { %>
						<input hidden type="text" value="adiciona" name="acao">
						<button type="submit" class="btn btn-primary mb-2">Salvar</button>
					<% } %>
				    </div>
				</div>
			</form>
			
		<table class="table table-hover">
			<thead class="thead-light">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Nome</th>
					<th scope="col">Contato</th>
					<th scope="col">Email</th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody style="cursor: pointer">
				<c:forEach var="emp" items="${dao.lista}">
					<tr class="trEmpresas" data-url="detalhesEmpresa.jsp?idEmpresa=${emp.idEmpresa}">
						<td>${emp.idEmpresa}</td>
						<td>${emp.nome}</td>
						<td>${emp.contato}</td>
						<td>${emp.email}</td>
						<form action="gerenciaEmpresas.jsp" method="post">
						<input hidden type="text" value="${emp.idEmpresa}" name="idEmpresa">
						<input hidden type="text" value="excluir" name="acao">
						<td><button type="submit" class="btn btn-outline-info"
							data-toggle="modal" data-target="#ExemploModalCentralizado">
							Editar</button></td>
						<td><button formaction="../adicionaEmpresa" type="submit" class="btn btn-danger"
						data-toggle="modal" data-target="#ExemploModalCentralizado">
						X</button></td></form>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</br></br></br></br></br>

<c:import url="rodape.jsp" />