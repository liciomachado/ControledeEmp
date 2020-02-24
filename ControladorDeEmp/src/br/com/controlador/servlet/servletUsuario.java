package br.com.controlador.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;

import br.com.controlador.jdbc.dao.UsuarioDao;
import br.com.controlador.jdbc.modelo.Usuario;

@WebServlet("/servletUsuario")
public class servletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		int id = Integer.parseInt(request.getParameter("idUsuario"));
		String graduacao = request.getParameter("graduacao");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String senhaGmail = request.getParameter("senhaGmail");

		Usuario user = new Usuario();
		user.setIdUsuario(id);
		user.setGraduacao(graduacao);
		user.setNome(nome);
		SimpleHash hash = new SimpleHash("md5",senha);
		user.setSenha(hash.toHex());
		user.setEmail(email);
		user.setSenhaGmail(senhaGmail);

		UsuarioDao dao = new UsuarioDao();

		switch (acao) {
		case "alterar":
			dao.altera(user);
			response.sendRedirect("pages/index.jsp");
			HttpSession sessao = request.getSession();
			sessao.setAttribute("UsuarioCompleto", user);
			break;
		case "novo":
			dao.adiciona(user);
			response.sendRedirect("login.jsp");
			break;
		}

	}

}
