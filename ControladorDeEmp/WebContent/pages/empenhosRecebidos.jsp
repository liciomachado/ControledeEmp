<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.NotaFiscalDao" />
<div class="display-4">Recebidos Recentemente</div>
<table class="table table-hover">
	<thead class="thead-light">
		<tr>
			<th scope="col">Nº Empenho</th>
			<th scope="col">Empresa</th>
			<th scope="col">NF</th>
			<th scope="col">Data Enviado</th>
			<th scope="col">Data Recebido</th>
			<th scope="col">Valor</th>
			<th scope="col">Destino</th>
			<th scope="col">Visualizar NF</th>
		</tr>
		<tr>
			<th scope="col"></th>	
			<th scope="col"><form action="${pageContext.request.contextPath}/filtroRecebidos" method="get" class="form-inline my-2 my-lg-0">
										<input class="form-control mr-sm-2" type="search" name="empresa" placeholder="pesquisa por empresa" required aria-label="Search">
										<input hidden type="text" name="filtro" value="filtroEmpresa">
										<input hidden class="form-control mr-sm-2" type="submit" placeholder="Pesquisar empenho" aria-label="Search">
									</form>
					</th>	
			<th scope="col"></th>	
			<th scope="col"></th>
			<th scope="col"></th>
			<th scope="col"></th>
			<th scope="col"><form action="${pageContext.request.contextPath}/filtroRecebidos" method="get" class="form-inline my-2 my-lg-0">
										<input class="form-control mr-sm-2" type="search" name="destino" placeholder="pesquisa por destino" required aria-label="Search">
										<input hidden type="text" name="filtro" value="filtroDestino">
										<input hidden class="form-control mr-sm-2" type="submit" placeholder="Pesquisar empenho" aria-label="Search">
									</form>
					</th>	
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody style="cursor: pointer">
		<c:forEach var="nota" items="${dao.notaRecebidos}">
			<tr class="trRecebidos" data-url="detalheEmpenho.jsp?numEmpenho=${nota.empenho.numeroEmpenho}">
				<td><a href="../downloadPDF?numID=${nota.empenho.idEmpenho}" target="_blank">${nota.empenho.numeroEmpenho}</td>
				<td>${nota.empresa.nome}</td>
				<td>${nota.numNota}</td>
				<td><fmt:formatDate value="${nota.dataEmissao.time}" /></td>	
				<td><fmt:formatDate value="${nota.dataRecebido.time}" /></td>	
				<td>${nota.valorTotal}</td>
				<td>${nota.empenho.destino}</td>
				<td><a href="http://www.nfe.fazenda.gov.br/portal/consultaRecaptcha.aspx?tipoConteudo=XbSeqxE8pl8%3D&tipoConsulta=completa&nfe=${nota.chaveAcesso}"  target="_blank">Visualizar NF</td>
			</tr>
		</c:forEach>
</table>
<c:import url="rodape.jsp" />