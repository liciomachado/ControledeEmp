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
	
	JSONObject resp = new JSONObject();
	
	public functionJSON(int id) throws JSONException {
		EmpenhoDao dao = new EmpenhoDao();
		Empenho empenho = new Empenho();
		empenho = dao.buscaPorID(id);
		
		resp.put("empresa", empenho.getNumeroEmpenho());
		resp.put("valor", empenho.getValorTotal());
		
	}
	public JSONObject retornaCliente(int id) throws JSONException {
		EmpenhoDao dao = new EmpenhoDao();
		Empenho empenho = new Empenho();
		empenho = dao.buscaPorID(id);
		
		resp.put("empresa", empenho.getNumeroEmpenho());
		resp.put("valor", empenho.getValorTotal());
		
		return resp;
	}
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		JSONObject resp = new JSONObject();

		EmpenhoDao dao = new EmpenhoDao();
		Empenho empenho = new Empenho();
		empenho = dao.buscaPorID(id);
		
		resp.put("empresa", empenho.getNumeroEmpenho());
		resp.put("valor", empenho.getValorTotal());
		
        response.setContentType("application/json");
        response.getWriter().write(resp.toString());
    }

}
