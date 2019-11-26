package br.com.controlador.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/protocolaItens")
public class ProtocolaItensServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {
	
		System.out.println(request.getParameter("opcoes[0]"));
	}
}