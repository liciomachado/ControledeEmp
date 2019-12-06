<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:import url="cabecalho.jsp" />

<div class="container">
	<div class="row">


		<a href="adicionaEmpenho.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/document.png">
				<p>Cadastrar Empenho</p>
			</span>
		</a> 
		<a href="adicionaNF.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/nfeNew.png">
				<p>Cadastrar Nota Fiscal</p>
			</span>
		</a> 
		<a href="protocolo.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/checklist.png">
				<p>Protocolar</p>
			</span>
		</a> 
		<a href="empenhosPendentes.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/hourglass.png">
				<p>Empenhos Pendentes</p>
			</span>
		</a> 
		<a href="empenhosRecebidos.jsp"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/success.png">
				<p>Empenhos Recebidos</p>
			</span>
		</a> 
		<a href="https://www.google.com/gmail/"  target="_blank"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/gmail2.png">
				<p>Gmail</p>
			</span>
		</a> 
		<a href="#"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/file.png">
				<p>Gerenciar Empenhos</p>
			</span>
		</a> 
		<a href="#"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/gerency.png">
				<p>Gerenciar Nota Fiscal</p>
			</span>
		</a> 
		<a href="#"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/checklist2.png">
				<p>Ver Protocolos</p>
			</span>
		</a> 
		<a href="#"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/enterprise.png">
				<p>Gerenciar Empresas</p>
			</span>
		</a> 
		<a href="http://www.nfe.fazenda.gov.br/portal/consultaRecaptcha.aspx?tipoConsulta=completa&tipoConteudo=XbSeqxE8pl8=" target="_blank"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/nfe.png">
				<p>Site NFE</p>
			</span>
		</a> 
		<a href="#"> 
			<span class="box"> 
			<img alt="img" width="50" height="50" src="../img/index/settings.png">
				<p>Configurações do Site</p>
			</span>
		</a> 
		

	</div>
</div>

</br></br></br></br></br></br></br>

<c:import url="rodape.jsp" />