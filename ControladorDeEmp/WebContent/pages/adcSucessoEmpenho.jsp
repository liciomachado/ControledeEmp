<%@page import="java.nio.file.Files"%>
<%@page import="java.io.OutputStream"%>
<%@page import="javax.swing.JFileChooser"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.Base64"%>
<%@page import="javax.swing.ImageIcon"%>
<%@page import="br.com.controlador.jdbc.dao.EmpenhoDao"%>
<%@page import="br.com.controlador.jdbc.modelo.Empenho"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="cabecalho.jsp" />
<br />
<br />
<br />
<br />
<div style="text-align: center;">
	<h2>Empenho inserido na base de dados com sucesso !</h2>

		<%
			System.out.println(session.getAttribute("LastResult"));
			Integer resultado = (Integer)   session.getAttribute("LastResult");
			EmpenhoDao dao = new EmpenhoDao();
			Empenho emp = dao.buscaPorIDSemNF(resultado);
			
            String encode = Base64.getEncoder().encodeToString(emp.getEmpenhoDigitalizado());
            request.setAttribute("imgBase", encode);
            
        %>

			<p><%=emp.getIdEmpenho()%></p>
			<p><%=emp.getNumeroEmpenho()%></p>
			<p><%=emp.getDestino()%></p>
			<p><%=emp.getDataEmpenho().getTime().toGMTString()%></p>
			<p><%=emp.getValorTotal()%></p>
			<img src="data:image/jpeg;base64,${imgBase}" />
			
	<br /> <br /> <a href="adicionaEmpenho.jsp"
		class="btn btn-success btn-lg" role="button" aria-disabled="true">Cadastrar
		mais Empenhos</a>
	<p>ou</p>
	<a href="index.jsp" class="btn btn-success btn-lg" role="button"
		aria-disabled="true">Pagina Inicial</a> <br /> <br /> <br /> <br />
</div>

<c:import url="rodape.jsp" />