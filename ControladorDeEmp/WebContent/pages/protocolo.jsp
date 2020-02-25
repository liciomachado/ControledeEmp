<%@page import="br.com.controlador.jdbc.modelo.NotaFiscal"%>
<%@page import="br.com.controlador.jdbc.modelo.Observacoes"%>
<%@page import="java.util.List"%>
<%@page import="br.com.controlador.jdbc.dao.ObservacoesDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.NotaFiscalDao" />

<div class="container">
    <div class="row formulario">
        <div class="col-md-12">
            <div class = "display-4">Protocolo</div>
        </div>
    </div>   
    <div class="row cadastro">
        <div class="col-md-12">
            <form class="form-group needs-validation justify-content-center" method="post" action="../protocolaItens" novalidate>
                <table class="table table-hover">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Nº Empenho</th>
                            <th scope="col">Empresa</th>
                            <th scope="col">Valor</th>
                            <th scope="col">Nota Fiscal</th>
                            <th scope="col">Destino</th>
                            <th scope="col">Recebido:</th>
                            <th scope="col">Protocolar</th>
                        </tr>
                    </thead>
                    <tbody>
                		<c:forEach var="nota" items="${dao.notasProtocolar}">
                   			<tr>
                   			
	                            <th scope="row"><a href="../downloadPDF?numID=${nota.empenho.idEmpenho}" target="_blank">${nota.empenho.numeroEmpenho}</th>
	                            <td>${nota.empresa.nome}</td>
	                            <td>${nota.valorTotal}</td>   
	                            <td>${nota.numNota}</td>
	                            <td>${nota.empenho.destino}</td>
	                            <td><fmt:formatDate value="${nota.dataRecebido.time}" /></td>
	                            <td><input type="checkbox" name="checkId[]" value="${nota.idNotaFiscal}" checked></td>
                            </tr>
                        </c:forEach>
                        
                    </tbody>
                </table>
                <div class="text-right">
                	<button type="submit" class="btn btn-success mb-2" id="botao" class="btn" target="_blank">Gerar Protocolo</button>
                </div>
            </form>
        </div>
    </div>
</div>

<c:import url="rodape.jsp" />