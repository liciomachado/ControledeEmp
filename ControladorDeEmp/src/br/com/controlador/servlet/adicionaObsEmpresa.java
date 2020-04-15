package br.com.controlador.servlet;

import java.io.IOException;
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

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.dao.ObservacoesEmpresaDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.ObservacoesEmpresa;
import br.com.controlador.jdbc.modelo.Usuario;

@WebServlet("/salvaObservacaoempresa")
public class adicionaObsEmpresa extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
		
		int id = Integer.parseInt(request.getParameter("pegaIdEmpresa"));
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
		
		HttpSession s = request.getSession();
		Usuario user = new Usuario();
		user.setIdUsuario(Integer.parseInt(s.getAttribute("userId").toString()));
		
		ObservacoesEmpresa observacao = new ObservacoesEmpresa();
		observacao.setIdEmpresa(id);
		observacao.setObservacao(obs);
		observacao.setDataObs(data);
		observacao.setUsuario(user);
		
		ObservacoesEmpresaDao dao = new ObservacoesEmpresaDao();
		dao.adiciona(observacao);
		
		response.sendRedirect("pages/detalhesEmpresa.jsp?idEmpresa="+id);
	}
}
	