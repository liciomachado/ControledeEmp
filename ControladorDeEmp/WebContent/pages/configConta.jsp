<%@page import="br.com.controlador.jdbc.modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%

HttpSession sessao = request.getSession();
Usuario usuario = (Usuario)sessao.getAttribute("UsuarioCompleto");

%>

<c:import url="cabecalho.jsp" />
<div class="container">
	<div class="display-4" style="text-align: center">Configurações da Conta</div>
	
	<form action="../servletUsuario" method="post">
	<input hidden type="text" value="<%=usuario.getIdUsuario() %>" name="idUsuario">
	<input hidden type="text" value="alterar" name="acao">
	<div class="form-group">
            <div class="col-md-6 offset-md-3">
			<label for="grad">Posto/Graduação</label> 
			<select class="js-example-basic-single form-control col-md-11" id="grad" name="graduacao" required>
				<option value="">Selecione...</option>
				<option value="Coronel">Coronel</option>
				<option value="Tenente Coronel">Tenente Coronel</option>
				<option value="Major">Major</option>
				<option value="Capitão">Capitão</option>
				<option value="1º Tenente">1º Tenente</option>
				<option value="2º Tenente">2º Tenente</option>
				<option value="Aspirate a Oficial">Aspirate a Oficial</option>
				<option value="Sub Tenente">Sub Tenente</option>
				<option value="1º Sargento">1º Sargento</option>
				<option value="2º Sargento">2º Sargento</option>
				<option value="3º Sargento">3º Sargento</option>
				<option value="Cabo">Cabo</option>
				<option value="Soldado">Soldado</option>
			</select>
		</div>
	</div>
	
	<div class="form-group">
            <div class="col-md-6 offset-md-3">
                <label for="nome">Nome</label> 
				<input type="text" class="form-control" id="nome" name="nome" required value="<%=usuario.getNome() %>">   
            </div>
     </div>
    
    <div class="form-group">
            <div class="col-md-6 offset-md-3">
                <label for="senha">Senha </label> 
				<input type="password" class="form-control" id="senha" name="senha" placeholder="Coloque sua senha atual ou nova" required value=""> 
            </div>
     </div>
     <div class="form-group">
            <div class="col-md-6 offset-md-3">
                <label for="email">Email/Gmail</label> 
				<input type="text" class="form-control" id="email" name="email" required value="<%=usuario.getEmail() %>"> 
            </div>
     </div>
     <div class="form-group">
            <div class="col-md-6 offset-md-3">
                <label for="senhaGmail">Senha do Gmail</label> 
				<input type="password" class="form-control" id="senhaGmail" name="senhaGmail" required value="<%=usuario.getSenhaGmail()%>"> 
				</br>
				<div id="botao" style="text-align: right;">
					<button type="submit" class="btn btn-success mb-2" id="botao"
						name="salvaAviso" class="btn">Salvar Alterações</button>
				</div>
            </div>
            
     </div>
	</form>

	
</div>
<c:import url="rodape.jsp" />