<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.EmpresaDao" />

<div class="container">
	<div class="row formulario">
			<div class="display-4">Cadastro/Envio de Empenhos</div>

			<div class="cadastro">
				<div id="mostraCadastro" style="display:none;" >
							<form action="../adicionaEmpresa" method="post">
								<div class="form-row" style="text-transform: uppercase;">
									<div class="form-group col-md-4">
										<label for="nomeEmpresa">Nome Empresa </label> <input
											type="text" class="form-control inputEmpresa" id="nomeEmpresa"
											placeholder="" name="nomeEmpresa" required="" value="">
									</div>
									<div class="form-group col-md-4">
										<label for="numTelefone">Telefone </label> <input
											type="text" class="form-control" id="numTelefone" pattern="(^[\d-\)\(]+$)"
											placeholder="" name="numTelefone" required="" value="">
									</div>
									<div class="form-group col-md-4">
										<label for="nomeEmail">Email </label> <input
											type="text" class="form-control" id="nomeEmail"
											placeholder="" name="nomeEmail" required="" value="">
									</div>
									<input hidden type="text" value="1" name="idEmpresa">
									<input hidden type="text" value="adiciona" name="acao">
									<div class="col-lg-12" style="text-align: right;">
								      <button type="submit" class="btn btn-primary mb-2">Salvar</button>
								    </div>
								</div>
							</form>
						</div>
					<form enctype="multipart/form-data"
						class="form-group needs-validation justify-content-center"
						method="post" action="../adicionaEmpenho" novalidate>
						
						<div class="form-row">
							<div class="form-group col-md-12">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""id="inputCadastro"> 
									<label class="form-check-label" for="inputCadastro"> Empresa não cadastrada </label>
								</div>
							</div>
							
							<div class="form-group col-md-3">
								<label for="inputEmpenho">Empenho</label> 
								<select class="js-example-basic-single form-control col-md-11" id="inputEmpenho" name="inputEmpenho">
									<option value="">Selecione a Empresa</option>
									<c:forEach var="empresa" items="${dao.lista}">
										<option value="${empresa.idEmpresa}">${empresa.nome}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-md-3">
								<label for="numeroEmpenho">Numero Empenho </label> <input
									type="text" class="form-control" id="numeroEmpenho"
									placeholder="xxxxNExxxxxx" name="numEmpenho" required="true"
									value="">
							</div>

							<div class="form-group col-md-3">
								<label for="dinheiro">Valor R$</label> <input type="text"
									class="form-control" id="dinheiro" placeholder="" name="valor"
									required="" value="">
							</div>
							<div class="form-group col-md-3">
								<label for="destinoEmpenho">Destino / Verba </label> <input
									type="text" class="form-control" id="destinoEmpenho"
									placeholder="" name="destinoEmpenho" required="" value="">
							</div>
							<div class="form-group col-md-3">
								 <input type="text" class="form-control" id="emailEmpresa"
									 name="emailEmpresa">
							</div>
							<div class="custom-file col-md-6">
								<input type="file" accept="pdf/*" id="validatedCustomFile"
									name="imagem" required> <label
									for="validatedCustomFile">Selecione o empenho</label>
							</div>
							<div class="custom-file col-md-3" style="text-align: right;">
							<button type="submit" class="btn btn-success mb-5" id="botao"
								name="">Cadastrar somente o empenho</button>
							</div>
						</div>
						
						<div class="form-row">
							
							<div class="form-group col-md-12">
								<label for="mensagemAdicional">Mensagem Complementar</label>
								<textarea class="form-control" id="mensagemAdicional" rows="4"
									name="mensagem" placeholder="Mensagem Complementar referente ao empenho" required></textarea>
							</div>
							
						</div>
						<div style="text-align: right;">
							<button type="submit" class="btn btn-success mb-2" id="botao"
							name="salvaNoticia" formaction="../enviaEmail">Enviar Email/Gravar</button>
						</div>
					</form>
				
			</div>
		
	</div>
</div>

<c:import url="rodape.jsp" />