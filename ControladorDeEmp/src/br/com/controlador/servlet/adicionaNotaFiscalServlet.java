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
import javax.servlet.http.HttpSession;

import br.com.controlador.jdbc.dao.NotaFiscalDao;
import br.com.controlador.jdbc.dao.ObservacoesDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Empresa;
import br.com.controlador.jdbc.modelo.NotaFiscal;
import br.com.controlador.jdbc.modelo.Observacoes;
import br.com.controlador.jdbc.modelo.Usuario;

@WebServlet("/adicionaNotaFiscal")
public class adicionaNotaFiscalServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
		
		String chaveAcesso = request.getParameter("inputChaveAcesso");
		String dataString = request.getParameter("inputDataEmissao");
		String TxtObs = request.getParameter("inputObs");
		double valorNF = Double.parseDouble(request.getParameter("inputPreco"));
		int idEmpenho = Integer.parseInt(request.getParameter("inputEmp"));
		int numNota = Integer.parseInt(request.getParameter("inputNota"));
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
		//-----------------PEGANDO DATA DE EMISSAO
		Calendar dataEmissao = null;

        // fazendo a conversão da data
        try {
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
            dataEmissao = Calendar.getInstance();
            dataEmissao.setTime(date2);
        } catch (ParseException e) {
            System.out.println("Erro de conversão da data");
            return; //para a execução do método
        }
        
		Empenho emp = new Empenho();
		emp.setIdEmpenho(idEmpenho);
		
		NotaFiscal nota = new NotaFiscal();
		nota.setChaveAcesso(chaveAcesso);
		nota.setEmpenho(emp);
		nota.setValorTotal(valorNF);
		nota.setDataEmissao(dataEmissao);
		nota.setDataRecebido(data);
		nota.setNumNota(numNota);
		
		Usuario usuario = new Usuario(); 
		HttpSession s = request.getSession();
		usuario.setIdUsuario(Integer.parseInt(s.getAttribute("userId").toString()));
		nota.setUsuario(usuario);
		
		NotaFiscalDao dao = new NotaFiscalDao();
		dao.adiciona(nota);
		System.out.println(TxtObs);
		
		if(TxtObs != "") {
			Observacoes obs = new Observacoes();
			obs.setIdEmpenho(idEmpenho);
			obs.setDataObs(data);
			obs.setObservacao(TxtObs);
			obs.setIdUsuario(usuario.getIdUsuario());
			ObservacoesDao obsDao = new ObservacoesDao();
			obsDao.adiciona(obs);
		}
		response.sendRedirect("pages/index.jsp");
		//response.sendRedirect("pages/detalheEmpenho.jsp?numEmpenho="+emp.getNumeroEmpenho()+"#observacoes");
	}
}
