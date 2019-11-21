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

@WebServlet("/functionJSON2")
public class functionJSON2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		//int id = Integer.parseInt(request.getParameter("inputEmp"));
		int id = 33;
		if(id != 0) {
			response.setContentType("text/html");
			try {
				response.getWriter().write(retornaCliente(id));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
	
	public String retornaCliente(int id) throws JSONException {
		StringBuffer returnData = null;
		
		EmpenhoDao dao = new EmpenhoDao();
		Empenho empenho = new Empenho();
		empenho = dao.buscaPorID(id);
		
		returnData = new StringBuffer("{\"topic\":{");
		returnData.append("\"empresa\": \""+empenho.getNumeroEmpenho()+"\",");
		returnData.append("\"valor\": \""+empenho.getValorTotal()+"\"");
		returnData.append("}}");
		return returnData.toString();
	}
	

}
