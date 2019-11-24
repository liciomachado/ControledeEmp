<%@page import="br.com.controlador.jdbc.dao.EmpenhoDao"%>
<%@page import="br.com.controlador.jdbc.modelo.Empenho"%>
<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<% 	String numEmpenho = request.getParameter("numEmpenho"); 

	Empenho emp = new Empenho();
	EmpenhoDao dao = new EmpenhoDao();
	emp = dao.buscaEmpenhoCompleto(numEmpenho);
	pageContext.setAttribute("empenho", emp);

	Observacoes obs = new Observacoes();
	ObservacoesDao obsDao = new ObservacoesDao();
	List<Observacoes> obsList = obsDao.getListaPeloId(emp.getIdEmpenho());
	pageContext.setAttribute("obs", obsList);
%>
<jsp:useBean id="dao2" class="br.com.controlador.jdbc.dao.ObservacoesDao" />

<c:import url="cabecalho.jsp" />

<div style="margin-left: 20%; margin-right: 20%;">
	<div class="display-4" style="text-align: center;"><%= numEmpenho %></div>
	<div id="contentDetalheEmpenhoNaoEntregue">
		<img src="../img/document.png" width="25" height="25"
			style="float: left;" class="d-inline-block align-top" alt=""> <img
			src="../img/envelope.png" width="30" height="30"
			style="margin-left: 20%" class="d-inline-block align-top" alt="">
		<img src="../img/shipped.png" width="30" height="30"
			style="margin-left: 20%;" class="d-inline-block align-top" alt="">
		<img src="../img/check1.png" width="30" height="27"
			style="margin-left: 20%;" class="d-inline-block align-top" alt="">
		<img src="../img/money.png" width="30" height="27"
			style="float: right;" class="d-inline-block align-top" alt="">
	</div>
</div>
<div class="container">
	<fieldset class="border p-2">
		<legend class="w-auto">Empenho </legend>
		<form enctype="multipart/form-data"
			class="form-group needs-validation justify-content-center"
			method="post" action="../adicionaEmpenho" novalidate>
			<div class="form-row">
			<div class="form-group col-md-12">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="" id="HabilitaEmpenho"> 
						<label class="form-check-label" for="inputCadastro"> Habilitar Edição  </label>
					</div>
				</div>
				<div class="form-group col-md-3">
						<label for="idEmpenho">Identificador </label> <input readonly
							type="text" class="form-control" id="idEmpenho"
							placeholder="" name="idEmpenho" required="" value="<%= emp.getIdEmpenho() %>">
				</div>
				<div class="form-group col-md-3">
					<label for="numeroEmpenho">Numero Empenho </label> <input readonly
						type="text" class="form-control" id="numeroEmpenho"
						placeholder="xxxxNExxxxxx" name="numEmpenho" required="" value="<%= emp.getNumeroEmpenho()%>">
				</div>

				<div class="form-group col-md-3">
					<label for="valor">Valor R$</label> <input type="number" readonly
						class="form-control" id="valor" placeholder="" name="valor"
						required="" value="<%= emp.getValorTotal() %>">
				</div>
				<div class="form-group col-md-3">
					<label for="destinoEmpenho">Destino / Verba </label> <input readonly
						type="text" class="form-control" id="destinoEmpenho"
						placeholder="" name="destinoEmpenho" required="" value="<%= emp.getDestino() %>">
				</div>
				<div class="custom-file col-md-12">
					<input type="file" readonly accept="pdf/*" id="validatedCustomFile"
						name="imagem" required> <label for="validatedCustomFile">Selecione
						o empenho</label>
				</div>
				<div class="col-lg-12" style="text-align: right;">
					<button type="submit" class="btn btn-primary mb-2">Alterar Empenho</button>
				</div>			
			</div>
		</form>	
	</fieldset>
	<fieldset class="border p-2">

		<legend class="w-auto">Empresa </legend>
		<form action="../adicionaEmpresa" method="post">
			<div class="form-row">
			<div class="form-group col-md-12">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""id="habilitaEmpresa"> 
									<label class="form-check-label" for="habilitaEmpresa"> Habilitar Edição  </label>
								</div>
							</div>
				<div class="form-group col-md-4">
					<label for="nomeEmpresa">Nome Empresa </label> <input type="text" readonly
						class="form-control" id="nomeEmpresa" placeholder=""
						name="nomeEmpresa" required="" value="<%= emp.getEmpresa().getNome() %>">
				</div>
				<div class="form-group col-md-4">
					<label for="numTelefone">Telefone </label> <input type="text" readonly
						class="form-control" id="numTelefone" placeholder=""
						name="numTelefone" required="" value="<%= emp.getEmpresa().getContato() %>">
				</div>
				<div class="form-group col-md-4">
					<label for="nomeEmail">Email </label> <input type="text" readonly
						class="form-control" id="nomeEmail" placeholder=""
						name="nomeEmail" required="" value="<%= emp.getEmpresa().getEmail() %>">
				</div>

				<div class="col-lg-12" style="text-align: right;">
					<button type="submit" class="btn btn-primary mb-2">Alterar Empresa</button>
				</div>
			</div>
		</form>
	</fieldset>
	<fieldset class="border p-2">
		<legend class="w-auto">Nota Fiscal </legend>
		<div class="form-row">
		<div class="form-group col-md-12">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""id="habilitaNF" readonly> 
									<label class="form-check-label" for="habilitaNF"> Habilitar Edição </label>
								</div>
							</div>
					<div class="form-group col-md-5">
						<label for="inputChaveAcesso">Chave de acesso</label> <input type="text" readonly
							class="form-control" id="inputChaveAcesso" name="inputChaveAcesso" required value="<%= emp.getNotaFiscal().getChaveAcesso() %>">
					</div>
					<div class="form-group col-md-2">
						<label for="inputNota">Nº Nota</label> <input type="text" readonly
							class="form-control" id="inputNota" name="inputNota" required value="<%= emp.getNotaFiscal().getNumNota() %>">
					</div>
					<div class="form-group col-md-3">
						<label for="inputDataEmissao">Data de Emissão:</label> <input readonly
							type="date" class="form-control" id="inputDataEmissao" name="inputDataEmissao"
							required value="<fmt:formatDate value="${emp.notaFiscal.dataEmissao.time}" pattern="dd-MM-yyyy"/>">
					</div>
					<div class="form-group col-md-2">
						<label for="inputPreco">Valor total R$</label> <input type="number" readonly
							class="form-control " id="inputPreco" name="inputPreco" required placeholder="00.00"
							value="<%= emp.getNotaFiscal().getValorTotal() %>">
					</div>
					<div class="col-lg-12" style="text-align: right;">
					<button type="submit" class="btn btn-primary mb-2">Alterar Nota Fiscal</button>
				</div>
			</div>
		
	</fieldset>
	
	<fieldset class="border p-2">
		<legend class="w-auto">Observações </legend>
		<c:forEach var="obs" items="${obs}">
		<p><fmt:formatDate value="${obs.dataObs.time}" /> - ${obs.observacao}</p>
		</c:forEach>
	</fieldset>
	
</div>
</br>
<c:import url="rodape.jsp" />