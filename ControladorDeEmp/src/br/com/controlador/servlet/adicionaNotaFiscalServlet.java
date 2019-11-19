package br.com.controlador.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlador.jdbc.dao.NotaFiscalDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.NotaFiscal;

@WebServlet("/adicionaNotaFiscal")
public class adicionaNotaFiscalServlet {
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException, ParseException {
		
		String chaveAcesso = request.getParameter("inputChaveAcesso");
		String dataString = request.getParameter("inputDataEmissao");
		double valorNF = Double.parseDouble(request.getParameter("inputPreco"));
		int idEmpenho = Integer.parseInt(request.getParameter("inputEmp"));
		System.out.println(request.getParameter("inputEmp"));
		Calendar dataEmissao = null;

        // fazendo a conversão da data
        try {
            Date date =new SimpleDateFormat("yyyy/MM/dd").parse(dataString);
            dataEmissao = Calendar.getInstance();
            dataEmissao.setTime(date);
        } catch (ParseException e) {
            System.out.println("Erro de conversão da data");
            return; //para a execução do método
        }
        
        Calendar data = null;
		Date d = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		Date date = new SimpleDateFormat("yyyy/MM/dd").parse(dStr);
        data = Calendar.getInstance();
		data.setTime(date);
		
		Empenho emp = new Empenho();
		emp.setIdEmpenho(idEmpenho);
		
		NotaFiscal nota = new NotaFiscal();
		nota.setChaveAcesso(chaveAcesso);
		nota.setEmpenho(emp);
		nota.setValorTotal(valorNF);
		nota.setDataEmissao(dataEmissao);
		nota.setDataRecebido(data);
		
		NotaFiscalDao dao = new NotaFiscalDao();
		
		System.out.println(nota.toString());
		dao.adiciona(nota);
		
		response.sendRedirect("pages/index.jsp");
	}
}
