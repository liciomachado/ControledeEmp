<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.EmpenhoDao" />

<div class="container">
	<div class="row formulario">
		<div class="col-md-12">
			<div class="display-4">Inserir NF</div>
		</div>
	</div>
	<div class="row cadastro">
		<div class="col-md-12">
			<form class="form-group needs-validation justify-content-center"
				method="post" action="../adicionaNotaFiscal" novalidate name="adicionaNF">
				<div class="form-row">
					<div class="form-group col-md-5">
						<label for="inputChaveAcesso">Chave de acesso</label> <input type="text"
							class="form-control" id="inputChaveAcesso" name="inputChaveAcesso" required minlength="44" maxlength="44" value="">
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
					<div class="form-group col-md-3">
						<label for="inputEmp">Empenho</label> 
						<select class="js-example-basic-single form-control col-md-11" id="inputEmp" name="inputEmp">
							<option value="">Selecione um Empenho</option>
							<c:forEach var="emp" items="${dao.listaEmpenhosPendentes}">
								<option value="${emp.idEmpenho}">${emp.numeroEmpenho}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group col-md-6">
						<label for="setEmpresa">Empresa</label> <input type="text"
							class="form-control " id="setEmpresa" readonly name="setEmpresa" required
							value="">
					</div>
					<div class="form-group col-md-3">
						<label for="setValor">Valor total do Empenho</label> <input
							type="text" class="form-control" id="setValor" readonly name="setValor"
							required value="R$">
					</div>
					<div class="form-group col-md-12">
						<label for="inputObs">Obs :</label>
						<textarea class="form-control" id="inputObs" rows="2"
							name="inputObs" placeholder="Descreva aqui observações relacionadas a entrega do material" value="" required></textarea>
					</div>

				</div>
				<div id="botao">
					<button type="submit" class="btn btn-success mb-2" id="botao"
						name="salvaAviso" class="btn">Salvar</button>
				</div>
			</form>
		</div>
	</div>
</div>
<c:import url="rodape.jsp" />