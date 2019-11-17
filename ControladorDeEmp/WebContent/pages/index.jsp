<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.EmpenhoDao" />

	<table class="table table-hover">
		<thead class="thead-light">
			<tr>
				<th scope="col">Empresa</th>
				<th scope="col">Nº NF</th>
				<th scope="col">Nº Empenho</th>
				<th scope="col">Valor</th>
				<th scope="col">Destino</th>
				<th scope="col">Visualizar Empenho</th>
				<th scope="col">Visualizar NF</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${dao.lista}">
				<tr>
					<th scope="row">J ZAGO</th>
					<td>100</td>
					<td>2019NE800213</td>
					<td>650,00</td>
					<td>ALMOX</td>
					<td><a
						href="https://drive.google.com/open?id=1FnGg-EbQi3LJ9svmpp6vlugFTosfLWI1">Visualizar</td>
					<td><a
						href="http://www.nfe.fazenda.gov.br/portal/consultaCompleta.aspx?tipoConteudo=XbSeqxE8pl8=">Visualizar
							NF</td>
				</tr>
				<tr>
					<td>${emp.idEmpenho}</td>
					<td>${emp.dataEmpenho.time}</td>
					<td>${emp.numeroEmpenho}</td>
					<td>${emp.destino}</td>
					<td>${emp.valorTotal}</td>
				</tr>
			</c:forEach>
	</table>

	<c:import url="rodape.jsp" />