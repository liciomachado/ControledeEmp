package br.com.controlador.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.modelo.Empenho;

@WebServlet("/servletEmpenho")
public class servletEmpenho extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		int idEmpenho = Integer.parseInt(request.getParameter("idEmp"));
		String numEmpenho = request.getParameter("numEmpenho");
		String destino = request.getParameter("destinoEmpenho");
		Double valorTotal = Double.parseDouble(request.getParameter("valor"));
		
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
			}
	}
}
