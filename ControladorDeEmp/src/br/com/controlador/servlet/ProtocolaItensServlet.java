package br.com.controlador.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.logica.GeraPDF;


@WebServlet("/protocolaItens")
public class ProtocolaItensServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {
	
		List<Integer> lista = new ArrayList<Integer>();

		for(String name: request.getParameterMap().keySet()) {
			for(String value: request.getParameterValues(name)) {
					//System.out.println(name+ ": "+ value);
					//String[] checked = request.getParameterValues("opcoes[]");
					lista.add(Integer.parseInt(value));
			}
		}
		for (int valores : lista) {
			EmpenhoDao dao = new EmpenhoDao();
			dao.alteraStatus(valores);
			System.out.println(valores);
		}
		GeraPDF t = new GeraPDF();
		t.writeUsingIText(lista);
		
		response.sendRedirect("pages/index.jsp");
	}
}