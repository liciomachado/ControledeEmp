package br.com.controlador.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlador.jdbc.dao.NotaFiscalDao;
import br.com.controlador.jdbc.dao.ObservacoesDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Empresa;
import br.com.controlador.jdbc.modelo.NotaFiscal;
import br.com.controlador.jdbc.modelo.Observacoes;

@WebServlet("/salvaObservacao")
public class adicionaObservacaoServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
		
		int id = Integer.parseInt(request.getParameter("pegaIdEmpenho"));
		String obs = request.getParameter("pegaObs");
		
		
		//-----------------PEGANDO DATA DE AGORA
				Date d = new Date();
				System.out.println(d);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					d = df.parse(d.toString());
				} catch (ParseException e1) {
					System.out.println("Erro na conversao de data");
				}
				Calendar data = Calendar.getInstance();
				data.setTime(d); 
		
		Observacoes observacao = new Observacoes();
		observacao.setIdEmpenho(id);
		observacao.setObservacao(obs);
		observacao.setDataObs(data);
		
		ObservacoesDao dao = new ObservacoesDao();
		dao.adiciona(observacao);
		
		response.sendRedirect("pages/adcSucessoEmpenho.jsp");
		
		
	}
}
	