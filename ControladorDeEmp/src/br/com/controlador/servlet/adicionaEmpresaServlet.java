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

		String nome = request.getParameter("nomeEmpresa");
		String tel = request.getParameter("numTelefone");
		String email = request.getParameter("nomeEmail");
		
		Empresa empresa = new Empresa();
		empresa.setNome(nome);
		empresa.setContato(tel);
		empresa.setEmail(email);
		
		EmpresaDao dao = new EmpresaDao();
		dao.adiciona(empresa);
		
		response.sendRedirect("pages/adicionaEmpenho.jsp");
		
	}
}

