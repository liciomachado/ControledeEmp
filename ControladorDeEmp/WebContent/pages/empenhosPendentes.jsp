<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.EmpenhoDao" />

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
					<th scope="col">Situação</th>
					<th scope="col">Enviado para empresa dia</th>
					<th scope="col">Obs:</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="emp" items="${dao.listaEmpenhosPendentes}">
					<tr>
						<td><a
							href="https://drive.google.com/open?id=1FnGg-EbQi3LJ9svmpp6vlugFTosfLWI1"
							target="_blank">${emp.numeroEmpenho}</td>
						<td>${emp.empresa.nome}</td>
						<td>${emp.valorTotal}</td>
						<td>Pendente Entrega</td>
						<td><fmt:formatDate value="${emp.dataEmpenho.time}" /></td>
						<td><button type="button" class="btn btn-primary"
								data-toggle="modal" data-target="#ExemploModalCentralizado">
								Ver</button></td>

						<div class="modal fade" id="ExemploModalCentralizado"
							tabindex="-1" role="dialog"
							aria-labelledby="TituloModalCentralizado" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="TituloModalCentralizado">Observações</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Fechar">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										15/08/2019 - Contato com a empresa(material a caminho)<br>
										20/08/2019 - Recebimento do material(computador recebido com
										defeito)
										<form>
											<div class="form-group">
												<label for="message-text" class="col-form-label">Comentario:</label>
												<textarea class="form-control" id="message-text"></textarea>
											</div>
											<div class="text-right">
												<button type="button" class="btn btn-primary">Salvar</button>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-warning"
											data-toggle="modal" data-target="#msgAutomatica"
											data-dismiss="modal">Enviar Mensagem automática</button>
										<button type="button" class="btn btn-outline-dark">+
											Obs</button>
									</div>
								</div>
							</div>
						</div>


						<div class="modal fade" id="msgAutomatica" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Mensagem
											automática</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Fechar">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="recipient-name" class="col-form-label">Confirmar
													envio de mensagem automática para a empresa
													######################## referente ao empenho ####NE######</label>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">Cancelar</button>
										<button type="button" class="btn btn-primary">Enviar</button>
									</div>
								</div>
							</div>
						</div>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</br></br></br></br></br>
<c:import url="rodape.jsp" />