<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:import url="cabecalho.jsp" />

<div class="container">
<div class="display-4" style="text-align: center;">Meu Menu</div>
	<div class="row">
		<a href="meusPendentes.jsp"> 
				<span class="box"> 
				<img alt="img" width="50" height="50" src="../img/index/hourglass.png">
					<p>Empenhos Pendentes</p>
				</span>
			</a> 
			<a href="meusRecebidos.jsp"> 
				<span class="box"> 
				<img alt="img" width="50" height="50" src="../img/index/success.png">
					<p>Empenhos Recebidos</p>
				</span>
			</a> 
		<a href="meuProtocolo.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/checklist.png">
				<p>Protocolar</p>
			</span>
		</a> 
		
		<a href="https://www.google.com/gmail/"  target="_blank"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/gmail2.png">
				<p>Gmail</p>
			</span>
		</a> 
		
		
		<a href="http://www.nfe.fazenda.gov.br/portal/consultaRecaptcha.aspx?tipoConsulta=completa&tipoConteudo=XbSeqxE8pl8=" target="_blank"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/nfe.png">
				<p>Site NFE</p>
			</span>
		</a> 
		<a href="configConta.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/settings.png">
				<p>Configurações da Conta</p>
			</span>
		</a> 
		

	</div>
</div>

<c:import url="rodape.jsp" />