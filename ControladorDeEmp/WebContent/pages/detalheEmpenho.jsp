<%@page import="br.com.controlador.jdbc.dao.NotaFiscalDao"%>
<%@page import="br.com.controlador.jdbc.modelo.NotaFiscal"%>
<%@page import="br.com.controlador.jdbc.dao.EmpenhoDao"%>
<%@page import="br.com.controlador.jdbc.modelo.Empenho"%>
<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<% 	String numEmpenho = request.getParameter("numEmpenho"); 

	Empenho emp = new Empenho();
	EmpenhoDao dao = new EmpenhoDao();
	emp = dao.buscaEmpenhoCompleto(numEmpenho);
	pageContext.setAttribute("empenho", emp);

	NotaFiscal nf = new NotaFiscal();
	NotaFiscalDao nfDao = new NotaFiscalDao();
	List<NotaFiscal> nfList = nfDao.getListaPorId(emp.getIdEmpenho());
	pageContext.setAttribute("nfList", nfList);

	Observacoes obs = new Observacoes();
	ObservacoesDao obsDao = new ObservacoesDao();
	List<Observacoes> obsList = obsDao.getListaPeloId(emp.getIdEmpenho());
	pageContext.setAttribute("obs", obsList);
	
	boolean testeValorEmpenho = dao.SaldoEmpenho(emp.getIdEmpenho());
%>
<jsp:useBean id="dao2" class="br.com.controlador.jdbc.dao.ObservacoesDao" />
<c:set var="dataCadastro" value="<%=emp.getDataEmpenho().getTime() %>" />
<c:set var="dataEmail" value="<%=emp.getDataEmpenho().getTime() %>" />
<% if(!nfList.isEmpty()){%>
	<c:set var="dataTransporte" value="<%=nfList.get(0).getDataEmissao().getTime()%>" />
	<c:set var="dataRecebido" value="<%=nfList.get(0).getDataRecebido().getTime() %>" />
	<%if(emp.getEtapa() == 5){ %>
	<c:set var="dataProtocolo" value="<%=nfList.get(0).getDataProtocolado().getTime() %>" />
<% }}%>
<c:import url="cabecalho.jsp" />

<div style="margin-left: 20%; margin-right: 20%;">
	<% if(emp.getEtapa() <= 3) { %>
	<div class="display-4" style="text-align: center;"><%= numEmpenho %></div>
	<div id="contentDetalheEmpenhoNaoEntregue">
		<img src="../img/document.png" width="25" height="25"
			style="float: left;" class="d-inline-block align-top" alt="">
	 	<img src="../img/envelope.png" width="30" height="30"
			style="margin-left: 20%" class="d-inline-block align-top" alt="">
		<img src="../img/shipped.png" width="30" height="30"
			style="margin-left: 20%;" class="d-inline-block align-top" alt="">
		<img src="../img/check1.png" width="30" height="27"
			style="margin-left: 20%;" class="d-inline-block align-top" alt="">
		<img src="../img/money.png" width="30" height="27"
			style="float: right;" class="d-inline-block align-top" alt="">
	</div>
	<% } %>
	<% if(emp.getEtapa() == 4) { %>
	<div class="display-4" style="text-align: center;"><%= numEmpenho %></div>
	<div id="contentDetalheEmpenhoEntregue">
		<img src="../img/document.png" width="25" height="25"
			style="float: left;" class="d-inline-block align-top" alt=""><img
			src="../img/envelope.png" width="30" height="30"
			style="margin-left: 20%" class="d-inline-block align-top" alt="">
		<img src="../img/shipped.png" width="30" height="30"
			style="margin-left: 20%;" class="d-inline-block align-top" alt="">
		<img src="../img/check1.png" width="30" height="27"
			style="margin-left: 20%;" class="d-inline-block align-top" alt="">
		<img src="../img/money.png" width="30" height="27"
			style="float: right;" class="d-inline-block align-top" alt="">
	</div>
	
	<% } %>
	<% if(emp.getEtapa() == 5 ||emp.getEtapa() == 6) { %>
	<div class="display-4" style="text-align: center;"><%= numEmpenho %></div>
	<div id="contentDetalheEmpenhoConcluido">
		<img src="../img/document.png" width="25" height="25"
			style="float: left;" class="d-inline-block align-top" alt=""> 
		<img src="../img/envelope.png" width="30" height="30"
			style="margin-left: 20%" class="d-inline-block align-top" alt="">
		<img src="../img/shipped.png" width="30" height="30"
			style="margin-left: 20%;" class="d-inline-block align-top" alt="">
		<img src="../img/check1.png" width="30" height="27"
			style="margin-left: 20%;" class="d-inline-block align-top" alt="">
		<img src="../img/money.png" width="30" height="27"
			style="float: right;" class="d-inline-block align-top" alt="">
	</div>
	<% } %>
	<div>
		<p style="float: left;margin-bottom: 0;" class="d-inline-block align-top">Cadastrado:</p>
		<p style="margin-left: 10%;margin-bottom: 0;" class="d-inline-block align-top">Enviado:</p>
		<p style="margin-left: 16%;margin-bottom: 0;" class="d-inline-block align-top">Transporte:</p>
		<p style="margin-left: 13%;margin-bottom: 0;" class="d-inline-block align-top">Recebido:</p>
		<p style="float: right;margin-bottom: 0;" class="d-inline-block align-top">Pagamento:</p>
	</div>
	<div>
		<p style="float: left;" class="d-inline-block align-top"><fmt:formatDate value="${dataCadastro}" pattern="dd/MM/yyyy"/></p>
		<p style="margin-left: 10%" class="d-inline-block align-top"><fmt:formatDate value="${dataEmail}" pattern="dd/MM/yyyy"/></p>
		<p style="margin-left: 13%" class="d-inline-block align-top"><fmt:formatDate value="${dataTransporte}" pattern="dd/MM/yyyy"/></p>
		<p style="margin-left: 13%" class="d-inline-block align-top"><fmt:formatDate value="${dataRecebido}" pattern="dd/MM/yyyy"/></p>
		<p style="float: right;" class="d-inline-block align-top"><fmt:formatDate value="${dataProtocolo}" pattern="dd/MM/yyyy"/></p>	
	</div>
	 
</div>
<div class="container">

	<%if(testeValorEmpenho == false){%>
	<div class="alert alert-danger" role="alert">
	  VALOR DO EMPENHO E NOTA FISCAL NÃO CONFEREM OU EXISTE SALDO !
	</div>
	<%}%>
	<fieldset class="border p-2" id="empenho">
		<legend class="w-auto">Empenho </legend>
		<form method="post" action="../servletEmpenho">
			<input hidden type="text" value="alteraEmpenho" name="acao">
			<div class="form-row">
			<div class="form-group col-md-12">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="" id="HabilitaEmpenho"> 
					<label class="form-check-label" for="HabilitaEmpenho"> Habilitar Edição  </label>
				</div>
			</div>
				<div class="form-group col-md-3">
						<label for="idEmpenho">Enviado por: </label> <input readonly
							type="text" class="form-control" id="idEmpenho"
							placeholder="" name="idEmpenho" required="" value="<%= emp.getUsuario().getNome()%>">
				</div>
				<div class="form-group col-md-3">
					<label for="numeroEmpenho">Numero Empenho </label> <input readonly
						type="text" class="form-control" id="numeroEmpenho"
						placeholder="xxxxNExxxxxx" name="numEmpenho" required="" value="<%= emp.getNumeroEmpenho()%>">
				</div>

				<div class="form-group col-md-3">
					<label for="valor">Valor R$</label> <input type="text" readonly
						class="form-control" id="valor" placeholder="" name="valor"
						required="" value="<%= emp.getValorTotal() %>">
				</div>
				<div class="form-group col-md-3">
					<label for="destinoEmpenho">Destino / Verba </label> <input readonly
						type="text" class="form-control" id="destinoEmpenho"
						placeholder="" name="destinoEmpenho" required="" value="<%= emp.getDestino() %>">
				</div>
				<div class="form-group col-md-6">
				<a class="btn btn btn-outline-success" href="../downloadPDF?numID=<%= emp.getIdEmpenho()%>" role="button">Baixar Empenho</a>
				</div>
				<div class="custom-file col-md-6">
					<input type="file" disabled accept="pdf/*" id="validatedCustomFile"
						name="imagem" required> <label for="validatedCustomFile"></label>
				</div>
				<input hidden type="text" value="<%= emp.getIdEmpenho() %>" name="idEmp">
				<div class="col-lg-12" style="text-align: right;">
					<button type="submit" class="btn btn-primary mb-2">Alterar Empenho</button>
				</div>			
			</div>
		</form>	
	</fieldset>
	
	<fieldset class="border p-2" id="empresa">

		<legend class="w-auto">Empresa </legend>
		<form action="../adicionaEmpresa" method="post">
		<input hidden type="text" value="alteraNoEmpenho" name="acao">
		<input hidden type="text" value="<%= emp.getEmpresa().getIdEmpresa() %>" name="idEmpresa">
		<input hidden type="text" value="<%= emp.getNumeroEmpenho() %>" name="numEmpenho">
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
	
	<fieldset class="border p-2" id="notafiscal">
		<legend class="w-auto">Nota Fiscal </legend>
		<form action="../servletNotaFiscal" method="post" >
		<div class="form-row">
		<c:forEach var="nf" items="${nfList}">
		
		<input hidden type="text" value="alteraNF" name="acao">
		<input hidden type="text" value="${nf.idNotaFiscal}" name="idNF">
		<input hidden type="text" value="<%= emp.getNumeroEmpenho() %>" name="numEmpenho">

		<div class="form-group col-md-12">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" value=""id="habilitaNF" readonly> 
				<label class="form-check-label" for="habilitaNF"> Habilitar Edição </label>
			</div>
		</div>
					<div class="form-group col-md-4">
						<label for="inputChaveAcesso"><a href="http://www.nfe.fazenda.gov.br/portal/consultaRecaptcha.aspx?tipoConteudo=XbSeqxE8pl8%3D&tipoConsulta=completa&nfe=${nf.chaveAcesso}"  target="_blank">Chave de acesso</a></label> <input type="text" readonly
							class="form-control" id="inputChaveAcesso" name="inputChaveAcesso" required value="${nf.chaveAcesso}">
					</div>
					<div class="form-group col-md-1">
						<label for="inputNota">Nº Nota</label> <input type="text" readonly
							class="form-control" id="inputNota" name="inputNota" required value="${nf.numNota }">
					</div>
					<div class="form-group col-md-2">
						<label for="inputDataEmissao">Data de Emissão:</label> <input readonly
							type="date" class="form-control" id="inputDataEmissao" name="inputDataEmissao"
							required value="<fmt:formatDate value="${nf.dataEmissao.time}" pattern="yyyy-MM-dd"/>">
					</div>
					<div class="form-group col-md-2">
						<label for="inputPreco">Valor total R$</label> <input type="text" readonly
							class="form-control " id="inputPreco" name="inputPreco" required placeholder="00.00"
							value="${nf.valorTotal}">
					</div>
					<div class="form-group col-md-2">
						<label for="inputChaveAcesso">Por:</label> <input type="text" readonly
							class="form-control" id="inputChaveAcesso" name="inputChaveAcesso" required value="${nf.usuario.nome}">
					</div>
					<div class="form-group col-md-1">
						<label for="">.</label>
						<button type="submit" class="form-control btn btn-primary">Edit</button>
					</div>
					</form>
					<form action="../servletNotaFiscal" method="post">
						<input hidden type="text" value="excluir" name="acao">
						<input hidden type="number" value="00" name="inputPreco">
						<input hidden type="number" value="00" name="inputNota">
						<input hidden type="text" value="${nf.idNotaFiscal}" name="idNF">
						<input hidden type="text" value="<%= emp.getNumeroEmpenho() %>" name="numEmpenho">
								<button type="submit" class="btn btn-danger">x</button>						
					</form>
					
					<form action="../servletNotaFiscal" method="post">
						<input hidden type="text" value="alteraStatus" name="acao">
						<input hidden type="number" value="00" name="inputPreco">
						<input hidden type="number" value="00" name="inputNota">
						<input hidden type="text" value="${nf.idNotaFiscal}" name="idNF">
						<input hidden type="text" value="<%= emp.getNumeroEmpenho() %>" name="numEmpenho">
								<button type="submit" class="btn btn-info"> Protocolar novamente</button>
					</form>
					
				</c:forEach>
				
			</div>
			
			<div class="form-group" id="txtNF" style="display:none;">
				<form action="../servletNotaFiscal" method="post">
					<input hidden type="text" value="<%= emp.getIdEmpenho() %>" name="pegaIdEmpenho">
					<input hidden type="text" value="adicionaNFDetalhes" name="acao">
					<input hidden type="text" value="<%= emp.getNumeroEmpenho() %>" name="numEmpenho">
					<div class="form-row">
					<div class="form-group col-md-5">
						<label for="inputChaveAcesso">Chave de acesso</label> <input type="text"
							class="form-control" id="inputChaveAcesso" name="inputChaveAcesso" required value="">
					</div>
					<div class="form-group col-md-2">
						<label for="inputNota">Nº Nota</label> <input type="text"
							class="form-control" id="inputNota" name="inputNota" required value="">
					</div>
					<div class="form-group col-md-3">
						<label for="inputDataEmissao">Data de Emissão:</label> <input
							type="date" class="form-control" id="inputDataEmissao" name="inputDataEmissao"
							required value="">
					</div>
					<div class="form-group col-md-2">
						<label for="dinheiro">Valor total R$</label> <input type="text"
							class="form-control " id="dinheiro" name="inputPreco" required placeholder="00.00"
							value="">
					</div>
						<input class="btn btn-primary mb-2" type="submit" id="salvaOBs" value="Salvar">
					</div>
				</form>
			</div>
			<div style="text-align: right;">
				<button id="adicionaNF" type="button" class="btn btn-default" aria-label="Left Align">
					<img alt="add" width="25" height="25" src="../img/add.png">
				</button>
			</div>
	</fieldset>
	
	<fieldset class="border p-2" id="observacoes">
		<legend class="w-auto">Observações </legend>
		<c:forEach var="obs" items="${obs}">
		${obs.usuario.nome} -> <fmt:formatDate value="${obs.dataObs.time}" /> - ${obs.observacao} </br>
		</c:forEach>
		<div class="form-group" id="txtObs" style="display:none;">
		<form action="../salvaObservacao" method="post">
			<input hidden type="text" value="<%= emp.getIdEmpenho() %>" name="pegaIdEmpenho">
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
	
</div>
</br>
<c:import url="rodape.jsp" />