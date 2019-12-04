package br.com.controlador.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.modelo.Empenho;

@WebServlet("/functionJSON")
public class functionJSON extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			EmpenhoDao dao = new EmpenhoDao();
			Empenho empenho = new Empenho();
			empenho = dao.buscaParaNF(id);
			double valor = empenho.getValorTotal();
			String empresa = empenho.getEmpresa().getNome();
			response.getWriter().println(empresa + "/" + valor);
		}catch (Exception e) {
			
		}
		
    }
}
