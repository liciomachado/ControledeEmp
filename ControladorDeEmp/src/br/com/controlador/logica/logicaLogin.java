package br.com.controlador.logica;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;

import br.com.controlador.jdbc.dao.UsuarioDao;
import br.com.controlador.jdbc.modelo.Usuario;

@WebServlet("/logicaLogin")
public class logicaLogin extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {


		Usuario user = new Usuario();
		user.setNome(request.getParameter("usuario"));
		user.setSenha(request.getParameter("senha"));

		UsuarioDao dao = new UsuarioDao();

		Usuario user2 = dao.autenticar(user);

		HttpSession sessao = request.getSession(true);
		if (user2.getIdUsuario() != 0)  {
			System.out.println("Usuario Conectado");
			sessao.setAttribute("usuario", user2.getNome());
			sessao.setAttribute("senhaIncorreta", false);

			//request.getRequestDispatcher("index.jsp").forward(request, response);
			response.sendRedirect("pages/index.jsp");

		}else {
			System.out.println("Senha incorreta");
			sessao.setAttribute("senhaIncorreta", true);
			response.sendRedirect("login.jsp");
		}
	}
}
