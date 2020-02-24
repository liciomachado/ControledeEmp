package br.com.controlador.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.dao.UsuarioDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Usuario;

@WebServlet("/servletEmpenho")
public class servletEmpenho extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		int idEmpenho = Integer.parseInt(request.getParameter("idEmp"));
		String numEmpenho = request.getParameter("numEmpenho");
		String destino = request.getParameter("destinoEmpenho");
		Double valorTotal = 0.0;
		try {
			valorTotal = Double.parseDouble(request.getParameter("valor"));
		} catch (Exception e) {

		}
		Empenho emp = new Empenho();
		EmpenhoDao dao = new EmpenhoDao();
		
		switch (acao) {
			case "alteraEmpenho":
				
				emp.setIdEmpenho(idEmpenho);
				emp.setNumeroEmpenho(numEmpenho);
				emp.setDestino(destino);
				emp.setValorTotal(valorTotal);
				
				dao.altera(emp);
				response.sendRedirect("pages/detalheEmpenho.jsp?numEmpenho="+numEmpenho);
				break;
			case "excluir":
				Usuario user = new Usuario();
				HttpSession sessao = request.getSession();
				user.setNome((String)sessao.getAttribute("usuario"));
				user.setSenha(request.getParameter("senha"));
				UsuarioDao dao2 = new UsuarioDao();
				Usuario user2 = dao2.autenticar(user);
				if (user2.getIdUsuario() != 0)  {
					emp.setIdEmpenho(idEmpenho);
					dao.remove(emp);
					response.sendRedirect("pages/index.jsp");
				}else {
					PrintWriter out = response.getWriter();
					out.println("<body>");
					out.println("<p>Senha incorreta</p>");
					out.println("<a href='pages/detalheEmpenho.jsp?numEmpenho="+numEmpenho+"'>Voltar</a>");
			        out.println("</body>");
					
				}
				
				
				break;
			}
	}
}
