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
import br.com.controlador.jdbc.dao.EmpresaDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Empresa;

@WebServlet("/empresaJSON")
public class empresaJSON extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Empresa empresa = new Empresa();
			EmpresaDao dao = new EmpresaDao();
			empresa = dao.buscaPorId(id);
			response.getWriter().println(empresa.getEmail());
		}catch (Exception e) {
			
		}
		
    }
}
