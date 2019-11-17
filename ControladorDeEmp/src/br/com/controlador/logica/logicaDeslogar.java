package br.com.controlador.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet("/logicaDeslogar")
public class logicaDeslogar extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {
		
		HttpSession sessao = request.getSession(false);
		sessao.invalidate();
		System.out.println("Deslogado");
		response.sendRedirect("login.jsp");
	}
}
