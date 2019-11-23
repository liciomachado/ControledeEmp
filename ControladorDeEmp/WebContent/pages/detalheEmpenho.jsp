<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="cabecalho.jsp" />

<div style="margin-left: 20%; margin-right: 20%;">
		<div class="display-4" style="text-align: center;">2019NE800505</div>
<div id="contentDetalheEmpenhoNaoEntregue">
	<img src="../img/document.png" width="25" height="25" style="float: left;" class="d-inline-block align-top" alt="">
	<img src="../img/envelope.png" width="30" height="30" style="margin-left:20%" class="d-inline-block align-top" alt="">
	<img src="../img/shipped.png" width="30" height="30" style="margin-left: 20%;" class="d-inline-block align-top" alt="">
	<img src="../img/check1.png" width="30" height="27" style="margin-left: 20%;" class="d-inline-block align-top" alt="">
	<img src="../img/money.png" width="30" height="27" style="float: right;" class="d-inline-block align-top" alt="">
</div>

</div>
</br></br></br></br></br></br>
<c:import url="rodape.jsp" />