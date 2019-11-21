<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="cabecalho.jsp" />

<jsp:useBean id="dao" class="br.com.controlador.jdbc.dao.EmpresaDao" />

<div class="container">
	<div class="row formulario">
		<div class="col-md-12">
			<div class="display-4">Cadastro/Envio de Empenhos</div>

			<div class="row cadastro">
				<div class="col-md-12">
					<form enctype="multipart/form-data"
						class="form-group needs-validation justify-content-center"
						method="post" action="../adicionaEmpenho" novalidate>
						<div class="form-row">
							<h2 class="bd-title">Informações para Inserção no Banco de
								dados</h2>
							<br>
							<div class="form-group col-md-5">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""id="inputCadastro"> 
									<label class="form-check-label" for="inputCadastro"> Empresa não cadastrada </label>
								</div>
							 <select class="js-example-basic-single form-control col-md-11" id="inputEmp" name="inputEmp">
								<option value="">Selecione a Empresa</option>
								<c:forEach var="empresa" items="${dao.lista}">
									<option value="${empresa.idEmpresa}">${empresa.nome}</option>
								</c:forEach>
							</select>
							</div>
							<div class="form-group col-md-5">
								<label for="numeroEmpenho">Numero Empenho </label> <input
									type="text" class="form-control" id="numeroEmpenho"
									placeholder="xxxxNExxxxxx" name="numEmpenho" required=""
									value="">
							</div>
							

							<div class="form-group col-md-2">
								<label for="valor">Valor R$</label> <input type="text"
									class="form-control" id="valor" placeholder="" name="valor"
									required="" value="">
							</div>
							<div class="form-group col-md-5">
								<label for="destinoEmpenho">Destino / Verba </label> <input
									type="text" class="form-control" id="destinoEmpenho"
									placeholder="" name="destinoEmpenho" required="" value="">
							</div>
							
							<button type="submit" class="btn btn-success mb-5" id="botao"
								name="">Cadastrar somente o empenho</button>
						</div>
						<div id="mostraCadastro" style="display:none;" >
							<form>
								<div class="form-row">
									<div class="form-group col-md-4">
										<label for="nomeEmpresa">Nome Empresa </label> <input
											type="text" class="form-control" id="nomeEmpresa"
											placeholder="" name="nomeEmpresa" required="" value="">
									</div>
									<div class="form-group col-md-4">
										<label for="numTelefone">Telefone </label> <input
											type="text" class="form-control" id="numTelefone"
											placeholder="" name="numTelefone" required="" value="">
									</div>
									<div class="form-group col-md-4">
										<label for="nomeEmail">Email </label> <input
											type="text" class="form-control" id="nomeEmail"
											placeholder="" name="nomeEmail" required="" value="">
									</div>
									<div class="col-lg-12" style="text-align: right;">
								      <button type="submit" class="btn btn-primary mb-2">Salvar</button>
								    </div>
								</div>
							</form>
						</div>
						<div class="form-row">
							<h2 class="bd-title">Informações para Envia a empresa</h2>

							<div class="form-group col-md-12">
								<label for="inputCodigo">Título do Email</label> <input
									type="text" class="form-control" id="inputCodigo"
									placeholder="Título" name="titulo" required="" value="">
							</div>

							<div class="form-group col-md-12">
								<label for="inputDesc">Mensagem</label>
								<textarea class="form-control" id="inputDesc" rows="4"
									name="noticia" placeholder="Mensagem a ser enviada" required></textarea>
							</div>
							<div class="custom-file col-md-12">
								<input type="file" accept="pdf/*" id="validatedCustomFile"
									name="imagem" required> <label
									for="validatedCustomFile">Selecione o empenho</label>
							</div>
						</div>

						<button type="submit" class="btn btn-success mb-2" id="botao"
							name="salvaNoticia">Enviar Email/Gravar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<c:import url="rodape.jsp" />