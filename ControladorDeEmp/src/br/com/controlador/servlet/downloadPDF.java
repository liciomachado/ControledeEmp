package br.com.controlador.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.modelo.Empenho;

@WebServlet("/downloadPDF")
public class downloadPDF extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("numID"));
		EmpenhoDao dao = new EmpenhoDao();
		Empenho emp = new Empenho();
		emp = dao.buscaPorIDSemNF(id);
		
		response.setContentType("application/pdf"); // tipo do conteúdo na resposta
		response.setContentLength(emp.getEmpenhoDigitalizado().length); // opcional. ajuda na barra de progresso
		response.setHeader("Content-Disposition", "attachment; filename="+emp.getNumeroEmpenho()+".pdf");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(emp.getEmpenhoDigitalizado(), 0, emp.getEmpenhoDigitalizado().length);
		outputStream.flush();
		outputStream.close();
		
	}


}
