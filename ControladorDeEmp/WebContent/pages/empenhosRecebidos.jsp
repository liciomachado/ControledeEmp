<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.NotaFiscalDao" />
<div class="container">
<div class="display-4">Recebidos Recentemente</div>
<div class="table-responsive-md">
	<table id="dtBasicExample" class="table table-hover" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th class="th-sm">Nº Empenho</th>
				<th class="th-sm">Empresa</th>
				<th class="th-sm">NF</th>
				<th class="th-sm">Data Enviado</th>
				<th class="th-sm">Data Recebido</th>
				<th class="th-sm">Valor</th>
				<th class="th-sm">Destino</th>
				<th class="th-sm">Visualizar NF</th>
			</tr>
		</thead>
		<tbody style="cursor: pointer">
			<c:forEach var="nota" items="${dao.notaRecebidos}">
				<tr	onclick=location.href='detalheEmpenho.jsp?numEmpenho=${nota.empenho.numeroEmpenho}'>
					<td><a href="../downloadPDF?numID=${nota.empenho.idEmpenho}"
						target="_blank">${nota.empenho.numeroEmpenho}</td>
					<td>${nota.empresa.nome}</td>
					<td>${nota.numNota}</td>
					<td><fmt:formatDate value="${nota.dataEmissao.time}" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${nota.dataRecebido.time}" pattern="yyyy-MM-dd"/></td>
					<td>${nota.valorTotal}</td>
					<td>${nota.empenho.destino}</td>
					<td><a
						href="http://www.nfe.fazenda.gov.br/portal/consultaRecaptcha.aspx?tipoConteudo=XbSeqxE8pl8%3D&tipoConsulta=completa&nfe=${nota.chaveAcesso}"
						target="_blank">Visualizar NF</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>	
</div>
<c:import url="rodape.jsp" />