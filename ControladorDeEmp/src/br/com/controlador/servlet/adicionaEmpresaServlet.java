package br.com.controlador.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlador.jdbc.dao.EmpresaDao;
import br.com.controlador.jdbc.modelo.Empresa;

@WebServlet("/adicionaEmpresa")
@MultipartConfig(maxFileSize = 16177215)
public class adicionaEmpresaServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		String acao = request.getParameter("acao");
		int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
		String nome = request.getParameter("nomeEmpresa");
		String tel = request.getParameter("numTelefone");
		String email = request.getParameter("nomeEmail");
		
		Empresa empresa = new Empresa();
		EmpresaDao dao = new EmpresaDao();
		switch (acao) {
		case "adiciona":
			
			empresa.setNome(nome);
			empresa.setContato(tel);
			empresa.setEmail(email);
			
			dao.adiciona(empresa);
			
			response.sendRedirect("pages/adicionaEmpenho.jsp");
			break;
		case "excluir":
			
			empresa.setIdEmpresa(idEmpresa);
			dao.remove(empresa);
			
			response.sendRedirect("pages/gerenciaEmpresas.jsp");
			break;
		case "atualiza":
			
			empresa.setIdEmpresa(idEmpresa);
			empresa.setNome(nome);
			empresa.setContato(tel);
			empresa.setEmail(email);
			
			dao.altera(empresa);
			
			response.sendRedirect("pages/gerenciaEmpresas.jsp");
			break;
		}
		
	}
}

