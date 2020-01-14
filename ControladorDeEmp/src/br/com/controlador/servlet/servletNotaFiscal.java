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

import br.com.controlador.jdbc.dao.NotaFiscalDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.NotaFiscal;
import br.com.controlador.jdbc.modelo.Usuario;

@WebServlet("/servletNotaFiscal")
public class servletNotaFiscal extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		String chaveAcesso = request.getParameter("inputChaveAcesso");
		String dataString = request.getParameter("inputDataEmissao");
		double valorNF = Double.parseDouble(request.getParameter("inputPreco"));
		int numNota = Integer.parseInt(request.getParameter("inputNota"));
		String numEmpenho = request.getParameter("numEmpenho");

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
		NotaFiscal nota = new NotaFiscal();
		NotaFiscalDao dao = new NotaFiscalDao();

		switch (acao) {
		case "adicionaNFDetalhes":
			int idEmpenho = Integer.parseInt(request.getParameter("pegaIdEmpenho"));
			emp.setIdEmpenho(idEmpenho);
			
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
			
			dao.adiciona(nota);
			
			response.sendRedirect("pages/detalheEmpenho.jsp?numEmpenho="+numEmpenho+"#notafiscal");
			break;
		case "alteraNF":
			int idNF = Integer.parseInt(request.getParameter("idNF"));

			nota.setIdNotaFiscal(idNF);
			nota.setChaveAcesso(chaveAcesso);
			nota.setValorTotal(valorNF);
			nota.setDataEmissao(dataEmissao);
			nota.setNumNota(numNota);
			
			dao.alteraNosDetalhes(nota);

			response.sendRedirect("pages/detalheEmpenho.jsp?numEmpenho="+numEmpenho+"#notafiscal");
			break;
		case "excluir":
			int idNF2 = Integer.parseInt(request.getParameter("idNF"));
			nota.setIdNotaFiscal(idNF2);
			
			dao.remove(nota);
			
			response.sendRedirect("pages/detalheEmpenho.jsp?numEmpenho="+numEmpenho+"#notafiscal");
			break;
		}
	}

}
