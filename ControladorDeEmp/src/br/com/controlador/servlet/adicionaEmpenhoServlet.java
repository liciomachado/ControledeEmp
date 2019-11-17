package br.com.controlador.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Empresa;


@WebServlet("/adicionaEmpenho")
public class adicionaEmpenhoServlet extends HttpServlet {
    protected void service(HttpServletRequest request,
                        HttpServletResponse response)
                        throws IOException, ServletException {
    	
    	String numEmpenho = request.getParameter("numEmpenho");
    	String destino = request.getParameter("destinoEmpenho");
    	double valorTotal = Double.parseDouble(request.getParameter("valor"));
 
    	Empresa empresa = new Empresa();
    	empresa.setNome(request.getParameter("nomeEmpresa"));
    	empresa.setIdEmpresa(1);
    	
    	Empenho empenho = new Empenho();
    	empenho.setNumeroEmpenho(numEmpenho);
    	empenho.setEmpresa(empresa);
    	empenho.setDestino(destino);

    	
    	Calendar data = null;
		Date d = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(dStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        data = Calendar.getInstance();
		data.setTime(date);
		empenho.setDataEmpenho(data);
		
		empenho.setValorTotal(800);
		EmpenhoDao dao = new EmpenhoDao();
		dao.adiciona(empenho);

        // imprime o nome do contato que foi adicionado
		response.sendRedirect("pages/index.jsp");
    }
}
