package br.com.controlador.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import br.com.controlador.jdbc.modelo.Usuario;

@WebServlet("/logicaDeslogar")
public class logicaDeslogar extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {
		
		HttpSession sessao = request.getSession();
		Usuario user = (Usuario)sessao.getAttribute("UsuarioCompleto");
		sessao = request.getSession(false);
		sessao.invalidate();
		System.out.println(user.getNome() + " Deslogou-se");
		response.sendRedirect("login.jsp");
	}
}
